package com.finhub.framework.swift.servlet;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.util.ClassUtils;
import com.finhub.framework.logback.util.LogUtils;
import com.finhub.framework.swift.bean.FileUpload;
import com.finhub.framework.swift.property.SwiftProperties;
import com.finhub.framework.swift.utils.ObjUtils;
import com.finhub.framework.swift.utils.ValidateUtils;
import com.finhub.framework.web.property.FilterProperties;
import com.finhub.framework.web.safe.HttpServletRequestSafeWrapper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.validation.DataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.Part;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_CAMEL_REQUEST_ID;

/**
 * swift项目迁移springboot项目，由于两个框架所用架构不同。
 * 如在swift项目改为标准的springboot项目
 */
@Slf4j
public class HttpRequest extends HttpServletRequestSafeWrapper {

    private final static String FACED_REQUEST_PROPERTY = "request";

    private final static String TOMCAT_REQUEST_POST_DATA_PROPERTY = "postData";

    private final HttpServletRequest request;

    private final String requestId;

    private final String body;

    private byte[] cachedBytes;

    private Map<String, String> parameterMap = Maps.newLinkedHashMap();

    private Map<String, Cookie> cookieMap = Maps.newLinkedHashMap();

    private Map<String, FileUpload> fileMap = Maps.newLinkedHashMap();

    private Map<String, String> headerMap = new CaseInsensitiveMap<>();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public HttpRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        super(request);
        super.setCharacterEncoding(CharsetUtils.DEFAULT_ENCODE);

        this.request = request;
        this.requestId = this.getReqId();
        this.initParameterCashed();
        //初始化，消息头参数、cookies、attribution缓存
        this.initCookiesCashed();
        this.initHeaderCashed();
        //初始化缓存content-type=application/json body流信息，防止读取之后丢失
        this.initBufferCashed();
        //设置json字符串
        this.body = this.setBody();
        //初始化上传文件缓存
        this.initUpFileCashed();
    }

    /**
     * 初始化请求缓存参数
     */
    private void initParameterCashed() {

        Enumeration<String> parameterNames = super.getParameterNames();
        if (parameterNames == null) {
            return;
        }
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if (StringUtils.isBlank(key)) {
                continue;
            }
            parameterMap.put(key, checkedDecodeText(super.getParameter(key)));
        }
    }

    /**
     * 获取http请求线程日志追踪标示
     *
     * @return
     */
    private String getReqId() {
        String requestId = super.getHeader(MDC_KEY_CAMEL_REQUEST_ID);

        if (isBlank(requestId)) {
            requestId = super.getHeader(MDC_KEY_CAMEL_REQUEST_ID);
        }

        return LogUtils.fillRequestId(requestId);
    }

    /**
     * 初始化请求cookies缓存
     */
    private void initCookiesCashed() {

        Cookie[] cookies = super.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
    }

    /**
     * 初始化请求header缓存
     */
    private void initHeaderCashed() {
        Enumeration<String> headerNames = super.getHeaderNames();
        if (ObjectUtil.isNotNull(headerNames)) {
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                headerMap.put(key, super.getHeader(key));
            }
        }
    }

    /**
     * 缓存请求json body流，兼容controller层的@requestBody接参数
     */
    private void initBufferCashed() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            IOUtils.copy(super.getInputStream(), outputStream);
        } catch (IOException e) {
            log.info("initBufferCashed error");
        }
        this.cachedBytes = outputStream.toByteArray();
    }

    /**
     * 设置请求body json串，兼容swift方式
     *
     * @return
     */
    /**
     * 设置请求body json串，兼容swift方式
     *
     * @return
     */
    private String setBody() {
        //form表单提交json特殊处理
        if (StrUtil.containsIgnoreCase(super.getContentType(), MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            return getFormJsonBody();
        }

        try {
            return StreamUtils.copyToString(getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("load request body error : ", e);
        }

        return null;
    }

    /**
     * application/x-www-form-urlencoded 提交json body特殊逻辑
     *
     * @return
     */
    private String getFormJsonBody() {
        //获取request门面
        RequestFacade requestFacade = getRequestFacade(request);

        //获取request门面的tomcat-> request属性
        Request connectorRequest = ClassUtils.getPropertyValue(requestFacade, FACED_REQUEST_PROPERTY, Request.class);

        //获取tomcat->request中的postData属性作为jsonBody
        byte[] postData =
            ClassUtils.getPropertyValue(connectorRequest, TOMCAT_REQUEST_POST_DATA_PROPERTY, byte[].class);
        if (postData == null || postData.length == 0) {
            return null;
        }
        int contentLength = request.getContentLength();
        if (contentLength == -1) {
            this.cachedBytes = postData;
        } else {
            this.cachedBytes = Arrays.copyOf(postData, request.getContentLength());
        }

        if (cachedBytes.length == 0) {
            return null;
        }
        String body = null;
        try {
            body = new String(cachedBytes, CharsetUtils.DEFAULT_ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return body;
    }

    /**
     * 检查是否要对文本内容进行解码
     *
     * @param text 待处理文本
     * @return
     */
    private String checkedDecodeText(String text) {
        if (Func.isBlank(text)) {
            return text;
        }

        if (SwiftProperties.me().isNotDecodeParameter()) {
            return text;
        }

        try {
            return URLDecoder.decode(text, Charset.forName(request.getCharacterEncoding()), false);
        } catch (Exception e) {
            return text;
        }
    }

    /**
     * 初始化上传文件缓存
     */
    private void initUpFileCashed() {

        if (HttpMethod.GET.name().equalsIgnoreCase(super.getMethod())) {
            return;
        }
        String contentType = this.getContentType();
        if (isBlank(contentType)) {
            return;
        }

        if (StrUtil.containsIgnoreCase(contentType, MediaType.MULTIPART_FORM_DATA_VALUE)
            || StrUtil.containsIgnoreCase(contentType, MediaType.APPLICATION_OCTET_STREAM_VALUE)
            || StrUtil.containsIgnoreCase(contentType, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        ) {
            try {
                Collection<Part> parts = super.getParts();
                if (CollectionUtil.isEmpty(parts)) {
                    return;
                }

                for (Part part : parts) {
                    if (isBlank(part.getContentType())
                        || isBlank(part.getSubmittedFileName())) {
                        continue;
                    }
                    this.fileMap.put(part.getName(), new FileUpload(part));
                }
            } catch (IOException | ServletException e) {
                //吃掉form表单没有上传文件异常。正常接收参数。
            }
        }

    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    /**
     * 销毁对象状态
     */
    public void destroy() {
        if (log.isDebugEnabled()) {
            log.info("destroy httpRequest instance");
        }
        this.cachedBytes = null;
        this.cookieMap = null;
        this.parameterMap = null;
        this.fileMap = null;
        this.headerMap = null;
        this.cachedBytes = null;
    }

    public Map<String, String> getParameterMapForSwift() {
        return this.parameterMap;
    }

    public String getBody() {
        return this.body;
    }

    @Override
    protected boolean isFilterXss() {
        return FilterProperties.me().isSwiftXssEnabled();
    }

    @Override
    protected boolean isFilterSql() {
        return FilterProperties.me().isSwiftSqlEnabled();
    }

    public String getUri() {
        String queryString = request.getQueryString();
        return Func.isBlank(queryString) ?
            getRequestURI() : Func.builder(getRequestURI(), StrConstants.S_QUESTION, queryString).toString();
    }

    public String getRequestId() {
        return requestId;
    }

    public Map<String, Cookie> getCookieMap() {
        return cookieMap;
    }

    public String getCookie(String key) {
        Cookie cookie = cookieMap.get(key);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public String getParameter(String key, String defaultValue) {
        String value = getParameter(key);
        if (isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    public Integer getIntParameter(String key) {
        return ObjUtils.toInteger(getParameter(key));
    }

    public Integer getIntParameter(String key, Integer defaultValue) {
        return ObjUtils.toInteger(getParameter(key), defaultValue);
    }

    public Long getLongParameter(String key) {
        return ObjUtils.toLong(getParameter(key));
    }

    public Long getLongParameter(String key, Long defaultValue) {
        return ObjUtils.toLong(getParameter(key), defaultValue);
    }

    public BigDecimal getBigDecimalParameter(String key) {

        return ObjUtils.toBigDecimal(getParameter(key));
    }

    public BigDecimal getBigDecimalParameter(String key, BigDecimal defaultValue) {
        return ObjUtils.toBigDecimal(getParameter(key), defaultValue);
    }

    public Boolean getBooleanParameter(String key) {

        return ObjUtils.toBoolean(getParameter(key));
    }

    public Boolean getBooleanParameter(String key, Boolean defaultValue) {
        return ObjUtils.toBoolean(getParameter(key), defaultValue);
    }


    public FileUpload getFile(String key) {
        return this.fileMap.get(key);
    }

    public InputStream getFileInputStream(String key) throws IOException {
        return this.getFile(key).getInputStream();
    }

    public InputStream getFileInputStream(MultipartFile fileUpload) throws IOException {
        if (fileUpload == null) {
            return null;
        }
        return fileUpload.getInputStream();
    }

    public String getPathValue(String key) {
        Map<String, String> uriVariables =
            (Map<String, String>) super.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (uriVariables == null) {
            return null;
        }
        return uriVariables.get(key);
    }

    public Integer getIntPathValue(String key) {
        return ObjUtils.toInteger(getPathValue(key));
    }

    public Long getLongPathValue(String key) {
        return ObjUtils.toLong(getPathValue(key));
    }

    public Map<String, FileUpload> getFileMap() {
        return this.fileMap;
    }

    public <T> T getBodyObject(Class<T> clazz) {
        String body = getBody();
        if (StrUtil.isEmpty(body)) {
            return null;
        }
        T obj = null;
        try {
            obj = JSON.parseObject(body, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return ValidateUtils.validate(obj);
    }

    public <T> List<T> getBodyArray(Class<T> clazz) {
        String body = getBody();
        if (StrUtil.isEmpty(body)) {
            return null;
        }
        try {
            return JSON.parseArray(body, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public Map<String, Object> getBodyMap() {
        return getBodyObject(HashMap.class);
    }


    @Override
    public String getQueryString() {
        return checkedDecodeText(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        return checkedDecodeText(super.getParameter(name));
    }

    @Override
    public ServletInputStream getInputStream() {
        return new MultiplexServletInputStream(this.cachedBytes);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public <T> T bindObj(T obj) {
        DataBinder dataBinder = new DataBinder(obj);
        dataBinder.registerCustomEditor(Date.class,
            new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        dataBinder.bind(new MutablePropertyValues(parameterMap));
        return ValidateUtils.validate(obj);
    }

    @Override
    public String getHeader(String name) {
        return headerMap.get(name.toLowerCase());
    }

    public void setHeader(String name, String value) {
        headerMap.put(name.toLowerCase(), value);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        Set<String> set = new HashSet<>();
        set.add(headerMap.get(name.toLowerCase()));
        return Collections.enumeration(set);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(headerMap.keySet());
    }


    private RequestFacade getRequestFacade(HttpServletRequest request) {
        if (request instanceof RequestFacade) {
            return (RequestFacade) request;
        } else if (request instanceof HttpServletRequestWrapper) {
            HttpServletRequestWrapper wrapper = (HttpServletRequestWrapper) request;
            HttpServletRequest wrappedRequest = (HttpServletRequest) wrapper.getRequest();
            return getRequestFacade(wrappedRequest);
        } else {
            throw new IllegalArgumentException("Cannot convert [" + request.getClass() +
                "] to org.apache.catalina.connector.RequestFacade");
        }
    }

    private static class MultiplexServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

        public MultiplexServletInputStream(byte[] bytes) {
            this.inputStream = new ByteArrayInputStream(bytes);
        }

        /**
         * 复用标示，tomcat读过一次后会将标示返回为true，后续在读取流会根据该标示拒绝访问流读取。
         * 重写方法，将读取流该为复用流
         *
         * @return
         */
        @Override
        public boolean isFinished() {
            return false;
        }

        /**
         * 是否可以进行读取标示，当调用证明此流已经缓存完毕，可以进行读取
         *
         * @return
         */
        @Override
        public boolean isReady() {
            return true;
        }

        /**
         * 设置监听器，底层tomcat通过该监听器判断流是否可用，重写后，创建对象即可读取数据。
         *
         * @param readListener
         */
        @Override
        public void setReadListener(ReadListener readListener) {

        }

        /**
         * 读取流
         *
         * @return
         * @throws IOException
         */
        @Override
        public int read() throws IOException {
            return inputStream.read();
        }
    }

}

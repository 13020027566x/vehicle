package com.finhub.framework.web.safe;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.web.property.FilterProperties;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import static com.finhub.framework.core.Func.isNotEmpty;
import static com.finhub.framework.core.safe.WafUtils.STANDARD_HTTP_HEADERS;
import static com.finhub.framework.core.safe.WafUtils.filterText;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_APP_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_DEPT_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_IP_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_VEHICLE_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_REQUEST_ID_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_TOKEN_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_TRACE_ID_KEY;
import static com.finhub.framework.web.constant.WebConstants.SESSION_IN_COOKIE;
import static com.finhub.framework.web.constant.WebConstants.SESSION_IN_HEADER;

/**
 * 安全的 HttpServlet (过滤 SQL 和 XSS)
 *
 * @author Mickey
 * @version 1.0
 * @since 15-8-4 下午4:45
 */
@Slf4j
public class HttpServletRequestSafeWrapper extends HttpServletRequestWrapper {

    private static final Set<String> IGNORE_HTTP_HEADERS =
        Sets.newHashSet("web-type", "x-tingyun", "sid", "access_token",
            HEADER_TOKEN_KEY.toLowerCase(), HEADER_FROM_IP_KEY.toLowerCase(), HEADER_FROM_APP_KEY.toLowerCase(),
            HEADER_FROM_DEPT_KEY.toLowerCase(), HEADER_TRACE_ID_KEY.toLowerCase(), HEADER_REQUEST_ID_KEY.toLowerCase(),
            HEADER_FROM_VEHICLE_KEY.toLowerCase());

    private static final Set<String> IGNORE_HTTP_PARAMETERS = Sets.newHashSet("access_token", "sign", "data", "xml");

    private final HttpServletRequest orgRequest;

    private final byte[] cachedBytes;

    public HttpServletRequestSafeWrapper(final HttpServletRequest request) {
        super(request);
        this.orgRequest = request;
        this.cachedBytes = cachedByteArray();
    }

    @Override
    public Cookie[] getCookies() {
        String sessionId = getHeader(SESSION_IN_HEADER);
        if (Func.isEmpty(sessionId)) {
            return super.getCookies();
        }

        Cookie[] cookies = super.getCookies();
        int cookieLength = 0;
        if (cookies != null) {
            cookieLength = cookies.length;
        }

        Cookie[] newCookies = new Cookie[cookieLength + 1];
        for (int i = 0; i < cookieLength; i++) {
            Cookie cookie = cookies[i];
            if (SESSION_IN_COOKIE.equals(cookie.getName())) {
                cookie.setValue(sessionId);
                return cookies;
            }
            newCookies[i] = cookie;
        }

        newCookies[cookieLength] = new Cookie(SESSION_IN_COOKIE, sessionId);
        return newCookies;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Set<String> parameterNameSafeList = Sets.newHashSet();
        Enumeration<String> parameterNames = super.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameterName = isIgnoreParameter(parameterName) ? parameterName : filterTextWithCustom(parameterName,
                isFilterXss(), isFilterSql());
            parameterNameSafeList.add(parameterName);
        }
        return Collections.enumeration(parameterNameSafeList);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterSafeMap = Maps.newHashMap();
        Set<String> parameterNameSet = super.getParameterMap().keySet();
        for (String key : parameterNameSet) {
            parameterSafeMap.put(key, getParameterValues(key));
        }
        return parameterSafeMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        if (isIgnoreParameter(name)) {
            return super.getParameterValues(name);
        }
        String[] values = super.getParameterValues(filterTextWithCustom(name, isFilterXss(), isFilterSql()));
        if (Func.isEmpty(values)) {
            return null;
        }
        int count = values.length;
        String[] safeValues = new String[count];
        for (int i = 0; i < count; i++) {
            safeValues[i] = filterTextWithCustom(values[i], isFilterXss(), isFilterSql());
        }
        return safeValues;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss & sql过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(final String name) {
        if (isIgnoreParameter(name)) {
            return super.getParameter(name);
        }
        String value = super.getParameter(filterTextWithCustom(name, isFilterXss(), isFilterSql()));

        return isNotEmpty(value) ? filterTextWithCustom(value, isFilterXss(), isFilterSql()) : null;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss & sql过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(final String name) {
        if (isIgnoreHeader(name)) {
            return super.getHeader(name);
        }
        String value = super.getHeader(filterTextWithCustom(name, isFilterXss(), isFilterSql()));

        return isNotEmpty(value) ? filterTextWithCustom(value, isFilterXss(), isFilterSql()) : null;
    }

    @Override
    public Enumeration<String> getHeaders(final String name) {
        if (isIgnoreHeader(name)) {
            return super.getHeaders(name);
        }

        Set<String> headerValSafeList = Sets.newHashSet();
        Enumeration<String> headerVals = super.getHeaders(name);
        while (headerVals.hasMoreElements()) {
            headerValSafeList.add(
                filterTextWithCustom(String.valueOf(headerVals.nextElement()), isFilterXss(), isFilterSql()));
        }
        return Collections.enumeration(headerValSafeList);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> headerNamesSafeList = Sets.newHashSet();
        Enumeration<String> headerNames = super.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headerName = isIgnoreHeader(headerName) ?
                headerName :
                filterTextWithCustom(headerName, isFilterXss(), isFilterSql());
            headerNamesSafeList.add(headerName);
        }
        return Collections.enumeration(headerNamesSafeList);
    }

    private String filterTextWithCustom(String str, final boolean isFilterXss, final boolean isFilterSql) {
        List<FilterProperties.FilterRuler> filterRulers = FilterProperties.me().getFilterRulers();

        if (Func.isNotEmpty(filterRulers)) {
            for (FilterProperties.FilterRuler filterRuler : filterRulers) {
                str = str.replaceAll(filterRuler.getRegex(), filterRuler.getReplacement());
            }
        } else {
            str = filterText(str, isFilterXss, isFilterSql);
        }

        return str;
    }

    @Override
    public ServletInputStream getInputStream() {
        if (Func.isEmpty(this.cachedBytes)) {
            try {
                return super.getInputStream();
            } catch (IOException e) {
                log.warn("get super input stream error : ", e);
                return new MultiplexServletInputStream(new byte[0]);
            }
        }
        return new MultiplexServletInputStream(this.cachedBytes);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * xss是否跳过特定的header参数
     *
     * @param name
     * @return
     */
    private boolean isIgnoreHeader(final String name) {
        boolean customIgnore = false;
        Set<String> ignoreHeaders = FilterProperties.me().getIgnoreHeaders();
        if (isNotEmpty(ignoreHeaders)) {
            customIgnore = ignoreHeaders.contains(name);
        }
        return customIgnore || STANDARD_HTTP_HEADERS.contains(name.toLowerCase()) || IGNORE_HTTP_HEADERS.contains(
            name.toLowerCase());
    }

    /**
     * xss是否跳过特定的请求parameter参数
     *
     * @param name
     * @return
     */
    private boolean isIgnoreParameter(final String name) {
        boolean customIgnore = false;
        Set<String> ignoreParameters = FilterProperties.me().getIgnoreParameters();
        if (isNotEmpty(ignoreParameters)) {
            customIgnore = ignoreParameters.contains(name);
        }
        return customIgnore || IGNORE_HTTP_PARAMETERS.contains(name.toLowerCase());
    }


    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(final HttpServletRequest req) {
        if (req instanceof HttpServletRequestSafeWrapper) {
            return ((HttpServletRequestSafeWrapper) req).getOrgRequest();
        }
        return req;
    }


    public byte[] getCachedBytes() {
        return cachedBytes;
    }

    public String getBody() {
        if (this.cachedBytes == null) {
            return StrConstants.S_EMPTY;
        }

        try {
            return new String(this.cachedBytes, orgRequest.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            log.warn("get post body error ", e);
        }

        return StrConstants.S_EMPTY;
    }

    /**
     * 缓存请求 application/json body 流，兼容 Controller 层的 @requestBody 接参数
     */
    private byte[] cachedByteArray() {
        if (!StrUtil.containsIgnoreCase(super.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            return null;
        }

        // application/json 提交 json 缓存流特殊处理
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            IOUtils.copy(super.getInputStream(), outputStream);
        } catch (IOException e) {
            log.warn(String.format("copy %s inputStream error", super.getRequest().getClass()), e);
        }

        return outputStream.toByteArray();
    }

    protected boolean isFilterXss() {
        if (FilterProperties.me().isXssEnabled()) {
            String requestUri = getRequestURI();
            Set<String> xssIncludeUrls = FilterProperties.me().getXssIncludeUrls();
            Set<String> xssExcludeUrls = FilterProperties.me().getXssExcludeUrls();

            if (isNotEmpty(xssExcludeUrls) && xssExcludeUrls.contains(requestUri)) {
                return false;
            }

            if (isNotEmpty(xssIncludeUrls) && !xssIncludeUrls.contains(requestUri)) {
                return false;
            }
        }

        return FilterProperties.me().isXssEnabled();
    }

    protected boolean isFilterSql() {
        if (FilterProperties.me().isSqlEnabled()) {
            String requestUri = getRequestURI();
            Set<String> sqlIncludeUrls = FilterProperties.me().getSqlIncludeUrls();
            Set<String> sqlExcludeUrls = FilterProperties.me().getSqlExcludeUrls();

            if (isNotEmpty(sqlExcludeUrls) && sqlExcludeUrls.contains(requestUri)) {
                return false;
            }

            if (isNotEmpty(sqlIncludeUrls) && !sqlIncludeUrls.contains(requestUri)) {
                return false;
            }
        }

        return FilterProperties.me().isSqlEnabled();
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
         * @throws java.io.IOException
         */
        @Override
        public int read() throws IOException {
            return inputStream.read();
        }
    }
}

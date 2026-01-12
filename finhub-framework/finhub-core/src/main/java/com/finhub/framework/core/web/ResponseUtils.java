package com.finhub.framework.core.web;

import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.json.JsonUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.constant.enums.UtilResponseEnum;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class ResponseOutputUtils.
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@UtilityClass
public class ResponseUtils {

    // -- header 常量定义 --//
    /**
     * The Constant HEADER_ENCODING.
     */
    private static final String HEADER_ENCODING = "encoding";

    /**
     * The Constant HEADER_NOCACHE.
     */
    private static final String HEADER_NOCACHE = "no-cache";

    /**
     * The Constant DEFAULT_ENCODING.
     */
    public static final String DEFAULT_ENCODING = CharsetUtils.DEFAULT_ENCODE;

    /**
     * The Constant DEFAULT_NOCACHE.
     */
    private static final boolean DEFAULT_NOCACHE = true;

    // -- 绕过jsp/freemaker直接输出文本的函数 --//

    /**
     * 直接输出内容的简便函数.
     * <p/>
     * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
     * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
     * "no-cache:false");
     *
     * @param contentType the content type
     * @param content     the content
     * @param response    the response
     * @param headers     可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
     */
    public static void render(final String contentType, final String content, final HttpServletResponse response,
        final String... headers) {
        HttpServletResponse servletResponse = initResponseHeader(contentType, response, headers);
        PrintWriter writer = null;
        try {
            writer = servletResponse.getWriter();
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            UtilResponseEnum.COMMON_ERROR.newException(e.getMessage(), e);
        } finally {
            IoUtil.close(writer);
        }
    }

    /**
     * 直接输出文本.
     *
     * @param response the response
     * @param text     the text
     * @param headers  the headers
     */
    public static void renderText(final HttpServletResponse response, final String text, final String... headers) {
        render(ServletUtils.TEXT_TYPE, text, response, headers);
    }

    /**
     * 直接输出HTML.
     *
     * @param response the response
     * @param html     the html
     * @param headers  the headers
     */
    public static void renderHtml(final HttpServletResponse response, final String html, final String... headers) {
        render(ServletUtils.HTML_TYPE, html, response, headers);
    }

    /**
     * 直接输出XML.
     *
     * @param response the response
     * @param xml      the xml
     * @param headers  the headers
     */
    public static void renderXml(final HttpServletResponse response, final String xml, final String... headers) {
        render(ServletUtils.XML_TYPE, xml, response, headers);
    }

    /**
     * 直接输出JSON.
     *
     * @param response   the response
     * @param jsonString json字符串.
     * @param headers    the headers
     */
    public static void renderJson(final HttpServletResponse response, final String jsonString,
        final String... headers) {
        render(ServletUtils.JSON_TYPE, jsonString, response, headers);
    }

    /**
     * 直接输出JSON,使用Jackson转换Java对象.
     *
     * @param response the response
     * @param data     可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
     * @param headers  the headers
     */
    public static void renderJson(final HttpServletResponse response, final Object data, final String... headers) {
        render(ServletUtils.JSON_TYPE, JsonUtils.toJson(data), response, headers);
    }

    /**
     * 直接输出支持跨域Mashup的JSONP.
     *
     * @param response     the response
     * @param callbackName callback函数名.
     * @param object       Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
     * @param headers      the headers
     */
    public static void renderJsonp(final HttpServletResponse response, final String callbackName, final Object object,
        final String... headers) {
        String jsonString = null;
        try {
            jsonString = JsonUtils.toJson(object);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

        // 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
        // 如callback197("{html:'Hello World!!!'}");
        render(ServletUtils.JS_TYPE, result, response, headers);
    }

    /**
     * 直接输出文件下载
     *
     * @param bytes
     * @param fileName
     */
    public static void renderFile(final HttpServletResponse response, final byte[] bytes, final String fileName) {
        HttpServletResponse servletResponse = initResponseHeader(ServletUtils.STREAM_TYPE, response,
            "Content-Disposition:inline;filename=" + new String(fileName.getBytes(
                CharsetUtils.DEFAULT_CHARSET), CharsetUtils.DEFAULT_CHARSET),
            "Content-Length:" + String.valueOf(bytes.length));
        responseOutput(servletResponse, bytes);
    }

    /**
     * 直接输出文件下载
     *
     * @param bytes
     * @param fileName
     */
    public static void renderExcelFile(final HttpServletResponse response, final byte[] bytes, final String fileName) {
        response.setHeader("Content-Disposition",
            "attachment; filename=" + new String(fileName.getBytes(CharsetUtils.DEFAULT_CHARSET),
                CharsetUtils.DEFAULT_CHARSET));
        response.setContentType(ServletUtils.EXCEL_TYPE + ";charset=utf-8");
        ServletUtils.setNoCacheHeader(response);
        responseOutput(response, bytes);
    }

    private static void responseOutput(final HttpServletResponse response, final byte[] bytes) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            response.flushBuffer();
        } catch (Exception e) {
            UtilResponseEnum.COMMON_ERROR.newException(e);
        } finally {
            IoUtil.close(out);
        }
    }

    /**
     * 分析并设置contentType与headers.
     *
     * @param contentType the content type
     * @param response    the response
     * @param headers     the headers
     * @return the http servlet response
     */
    private static HttpServletResponse initResponseHeader(final String contentType, final HttpServletResponse response,
        final String... headers) {
        // 分析 headers 参数
        String encoding = DEFAULT_ENCODING;
        boolean noCache = DEFAULT_NOCACHE;
        for (String header : headers) {
            List<String> headerSubs = StrUtil.split(header, StrConstants.S_COLON);
            String headerName = headerSubs.get(0);
            String headerValue = headerSubs.get(1);

            if (StrUtil.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
                encoding = headerValue;
            } else if (StrUtil.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
                noCache = Boolean.parseBoolean(headerValue);
            } else {
                throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
            }
        }

        // 设置headers参数
        String fullContentType = contentType + ";charset=" + encoding;
        response.setContentType(fullContentType);
        if (noCache) {
            ServletUtils.setNoCacheHeader(response);
        }

        return response;
    }


    public static Map<String, Object> getResponseHeaderMap(final HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        Map<String, Object> result = new HashMap<>();
        if (headerNames == null) {
            return result;
        }

        for (String key : headerNames) {
            result.put(key, response.getHeader(key));
        }
        return result;
    }

}

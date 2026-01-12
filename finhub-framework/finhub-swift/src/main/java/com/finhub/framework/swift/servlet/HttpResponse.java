package com.finhub.framework.swift.servlet;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.web.RequestUtils;

import static com.finhub.framework.web.constant.WebConstants.RESULT_KEY;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

/**
 * @Author shuangfei.chen
 * @Description HttpResponse
 * @Date 2021/8/12 10:36
 **/
@Slf4j
public class HttpResponse extends HttpServletResponseWrapper {

    private final HttpRequest request;

    private final String requestId;

    private String result;

    private ByteArrayOutputStream outputStream;


    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response      The response to be wrapped
     * @param request       The request to be wrapped
     */
    public HttpResponse(HttpServletResponse response, HttpRequest request) {
        super(response);
        super.setContentType(MediaType.APPLICATION_JSON_VALUE);
        super.setCharacterEncoding(CharsetUtils.UTF_8);
        this.request = request;
        this.requestId = request.getRequestId();
    }

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response      The response to be wrapped
     * @param requestId     The request id
     *
     * @deprecated          废弃构造方法，兼容历史版本。
     */
    @Deprecated
    public HttpResponse(HttpServletResponse response, String requestId) {
        super(response);
        super.setContentType(MediaType.APPLICATION_JSON_VALUE);
        super.setCharacterEncoding(CharsetUtils.UTF_8);
        this.request = null;
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        this.setResultIntoRequestAttribute();
    }

    private void setResultIntoRequestAttribute() {

        if (Func.isNotNull(request)) {
            request.setAttribute(RESULT_KEY, this.result);
            return;
        }

        try {
            HttpServletRequest servletRequest = RequestUtils.getRequest();
            servletRequest.setAttribute(RESULT_KEY, this.result);
        } catch (Exception e) {
            log.warn("set controller result attribute error : ", e);
        }

    }

    public Map<String, String> getHeaderMap() {
        Collection<String> headerNames = getHeaderNames();
        Map<String, String> headerMap = new HashMap<>(headerNames.size());
        for (String headerName : headerNames) {
            headerMap.put(headerName, getHeader(headerName));
        }
        return headerMap;
    }

    public void setResponseContentTypePlain() {
        setContentType(MediaType.TEXT_PLAIN_VALUE);
        setCharacterEncoding(CharsetUtils.UTF_8);

    }

    public void setResponseContentTypeJson() {
        setContentType(MediaType.APPLICATION_JSON_VALUE);
        setCharacterEncoding(CharsetUtils.UTF_8);
    }

    public void setResponseContentTypeHtml() {
        setContentType(MediaType.TEXT_HTML_VALUE);
        setCharacterEncoding(CharsetUtils.UTF_8);
    }

    public ByteArrayOutputStream getByteOutputStream() {
        return outputStream;
    }


    public void outputStream(ByteArrayOutputStream outputStream, String fileName) throws IOException {
        this.outputStream = outputStream;
        setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        setCharacterEncoding(CharsetUtils.UTF_8);
        setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
    }

    public void destroy() {
        IOUtils.closeQuietly(outputStream);
    }
}

package com.finhub.framework.swift.filter;

import com.finhub.framework.swift.servlet.HttpRequest;
import com.finhub.framework.swift.servlet.HttpResponse;
import com.finhub.framework.web.vo.BaseResult;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author shuangfei.chen
 */
@Slf4j
public class HttpSwiftWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpRequest httpRequest = new HttpRequest((HttpServletRequest) request);
        HttpResponse httpResponse = new HttpResponse((HttpServletResponse) response, httpRequest);
        chain.doFilter(httpRequest, httpResponse);
        if (CharSequenceUtil.containsIgnoreCase(httpResponse.getContentType(), MediaType.APPLICATION_OCTET_STREAM_VALUE)) {
            if (httpResponse.getByteOutputStream() != null) {
                try {
                    httpResponse.getOutputStream().write(httpResponse.getByteOutputStream().toByteArray());
                } catch (IllegalStateException e) {
                    log.error("HTTP response writer content-type[application/octet-stream] result fail : ", e);
                }
            }
        } else {
            if (httpResponse.getResult() != null) {
                try {
                    JSONObject jsonObject = JSONUtil.parseObj(httpResponse.getResult());
                    jsonObject.set("requestId", BaseResult.getRequestId4Response());
                    jsonObject.set("traceId", BaseResult.getTraceId4Response());
                    httpResponse.getWriter().write(JSONUtil.toJsonStr(jsonObject));
                } catch (IllegalStateException e) {
                    log.error("HTTP response writer result fail : ", e);
                }
            }
        }
    }
}

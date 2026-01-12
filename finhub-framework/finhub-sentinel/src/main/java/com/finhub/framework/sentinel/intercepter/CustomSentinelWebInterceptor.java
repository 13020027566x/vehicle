package com.finhub.framework.sentinel.intercepter;

import com.finhub.framework.sentinel.constants.SentinelConstants;
import com.finhub.framework.sentinel.property.SentinelProperties;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Sentinel 自定义拦截器
 *
 * @author zhenxing_liang
 */
public class CustomSentinelWebInterceptor extends SentinelWebInterceptor {

    public CustomSentinelWebInterceptor(SentinelWebMvcConfig sentinelWebMvcConfig) {
        super(sentinelWebMvcConfig);
    }

    protected String getResourceName(HttpServletRequest request) {
        // 自己实现资源名称生成
        String path = request.getRequestURI();
        String method = request.getMethod();
        String originalResourceName = (method != null ? method + ":" : "") + path;

        return SentinelProperties.me().getProfile() +
            SentinelConstants.RESOURCE_NAME_CONCAT_CHAR +
            originalResourceName;
    }
}

package com.finhub.framework.web.interceptor;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.finhub.framework.logback.util.LogCommonUtils.CONTROLLER_LOG_MODULE;

/**
 * Controller层日志拦截器
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Data
@Slf4j
public class ControllerCostLogInterceptor extends AbstractResponseLogInterceptor {

    @Override
    protected boolean isThreadLocalEnable() {
        return true;
    }

    @Override
    public boolean preHandleProcess(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler) {
        return true;
    }

    @Override
    protected String getLogAspectModule() {
        return CONTROLLER_LOG_MODULE;
    }
}

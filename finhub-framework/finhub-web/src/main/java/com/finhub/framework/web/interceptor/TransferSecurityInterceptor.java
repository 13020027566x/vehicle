package com.finhub.framework.web.interceptor;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.annotation.Desensitizes;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.security.TransportSecurity;
import com.finhub.framework.core.thread.ThreadLocalUtils;
import com.finhub.framework.core.web.RequestUtils;
import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.core.web.dto.Device;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;
import com.finhub.framework.web.context.UserContextHolder;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 安全验证拦截器
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Data
@Slf4j
public class TransferSecurityInterceptor implements HandlerInterceptor {

    private static final Set<String> ALLOW_REFERER_DOMAINS = Sets.newHashSet("http://127.0.0.1", "http://localhost");
    private boolean checkReferer = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkRefererAllow(request);

        // 请求解密
        TransportSecurity.request(request);
        transferRequest(request, response);

        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
        }

        if (method != null) {
            Desensitizes desensitizes = method.getMethodAnnotation(Desensitizes.class);
            if (desensitizes != null) {
                ThreadLocalUtils.putDesensitizes(desensitizes);
            }
        }

        return true;
    }

    private void checkRefererAllow(HttpServletRequest request) {
        boolean isNotAllow = true;
        String referer = RequestUtils.getRefererUrl(request);
        if (checkReferer) {
            for (String allowDomain : ALLOW_REFERER_DOMAINS) {
                if (!Func.isEmpty(referer) && referer.trim().startsWith(allowDomain)) {
                    isNotAllow = false;
                    break;
                }
            }
        } else {
            isNotAllow = false;
        }

        if (!Func.isEmpty(referer) && isNotAllow) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.UNAUTHORIZED, "http referer is not allow");
        }
    }

    private void transferRequest(HttpServletRequest request, HttpServletResponse response) {
        // Header头部取device信息
        Device device = new Device();
        device.setVersion(request.getHeader("version"));
        device.setDevice(request.getHeader("device"));
        device.setPlatform(request.getHeader("platform"));
        device.setDeviceModel(request.getHeader("deviceModel"));
        device.setOsVersion(request.getHeader("osVersion"));
        device.setUserAgent(request.getHeader("User-Agent"));
        device.setReferer(request.getHeader("Referer"));

        request.setAttribute("device", device);
        ThreadLocalUtils.putDevice(device);

        CurrentUser user = null;
        try {
            if (Func.isNotNull(UserContextHolder.me())) {
                user = UserContextHolder.me().getCurrentUser(request, response);
            }
        } catch (Exception e) {
            log.error("UserContextHolder getCurrentUser fail.", e);
            throw e;
        } finally {
            if (user != null) {
                ThreadLocalUtils.putCurrentUser(user);
            }
        }
    }
}

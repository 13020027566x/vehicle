package com.finhub.framework.web.csrf;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Csrf拦截器，用来生成或去除CsrfToken
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
        throws Exception {
        // 非控制器请求直接跳出
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        CsrfToken csrfToken = handlerMethod.getMethodAnnotation(CsrfToken.class);
        // 判断是否含有@CsrfToken注解
        if (null == csrfToken) {
            return true;
        }

        // create、remove同时为true时异常
        if (csrfToken.create() && csrfToken.remove()) {
            log.error("CsrfToken attr create and remove can Not at the same time to true!");
            renderError(request, response, "CsrfToken attr create and remove can Not at the same time to true!");
        }

        // 创建
        CsrfTokenRepository csrfTokenRepository = SpringUtil.getBean(CsrfTokenRepository.class);
        if (csrfToken.create()) {
            CsrfTokenBean token = csrfTokenRepository.generateToken(request);
            csrfTokenRepository.saveToken(token, request, response);
            request.setAttribute(token.getParameterName(), token);
            return true;
        }
        // 校验，并且清除
        CsrfTokenBean tokenBean = csrfTokenRepository.loadToken(request);
        if (tokenBean == null) {
            renderError(request, response, "CsrfToken is null!");
        }

        // String actualToken = request.getHeader(tokenBean.getHeaderName());
        // if (actualToken == null) {
        //      actualToken = request.getParameter(tokenBean.getParameterName());
        // }
        //  if (!tokenBean.getToken().equals(actualToken)) {
        //      return renderError(request, response, "CsrfToken not eq!");
        //  }

        // CsrfToken不从request header和parameter中取, 校对放到缓存中取
        CsrfTokenBean cacheTokenBean = csrfTokenRepository.loadTokenFromCache(tokenBean);
        if (Func.isEmpty(cacheTokenBean)) {
            renderError(request, response, "CsrfToken is null!");
        }

        if (!tokenBean.getToken().equals(cacheTokenBean.getToken())) {
            renderError(request, response, "CsrfToken not eq!");
        }

        return tokenBean.getToken().equals(cacheTokenBean.getToken());
    }

    private void renderError(final HttpServletRequest request, final HttpServletResponse response,
        final String message) throws IOException {
        // 非ajax CsrfToken校验异常，先清理token
        SpringUtil.getBean(CsrfTokenRepository.class).saveToken(null, request, response);
        MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, message);
    }

    /**
     * 用于清理@CsrfToken保证只能请求成功一次
     */
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
        final ModelAndView modelAndView) throws Exception {
        // 非控制器请求直接跳出
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        CsrfToken csrfToken = handlerMethod.getMethodAnnotation(CsrfToken.class);
        if (csrfToken == null || !csrfToken.remove()) {
            return;
        }
        SpringUtil.getBean(CsrfTokenRepository.class).saveToken(null, request, response);
    }
}

package com.finhub.framework.i18n.interceptor;

import com.finhub.framework.i18n.annotation.I18n;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.finhub.framework.i18n.constant.I18nConstants.I18N_ATTRIBUTE;

/**
 * 拦截 I18n 注解，根据注解的 value 值，设置 Request i18n_attribute
 * 以获取国际化文件文档信息
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午3:58
 */
public class MessageResourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) {
        // 在跳转到该方法先清除 request 中的国际化信息
        req.removeAttribute(I18N_ATTRIBUTE);

        // 在方法中设置 i18n 路径
        if (null != req.getAttribute(I18N_ATTRIBUTE)) {
            return true;
        }

        //判断是否HandlerMethod
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
        }
        if (null == method) {
            return true;
        }

        String i18nAnnotationValue = parseI18nAnnotationValue(req, method);
        if (StrUtil.isNotBlank(i18nAnnotationValue)) {
            req.setAttribute(I18N_ATTRIBUTE, parseI18nAnnotationValue(req, method));
        }

        return true;
    }

    private String parseI18nAnnotationValue(HttpServletRequest request, HandlerMethod method) {
        // 在 method 注解了 i18n
        I18n i18nMethod = method.getMethodAnnotation(I18n.class);
        if (null != i18nMethod) {
            return i18nMethod.value();
        }

        // 在 Controller 上注解了 i18n
        I18n i18nController = method.getBeanType().getAnnotation(I18n.class);
        if (null != i18nController) {
            return i18nController.value();
        }

        return null;
    }
}

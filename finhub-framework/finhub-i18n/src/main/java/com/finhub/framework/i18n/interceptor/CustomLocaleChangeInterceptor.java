package com.finhub.framework.i18n.interceptor;

import com.finhub.framework.i18n.constant.LocaleTypeEnum;
import com.finhub.framework.i18n.property.I18nProperties;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;


import static cn.hutool.core.text.CharSequenceUtil.isBlank;

/**
 * <pre>
 * 本地化信息拦截器，用于加载本地化信息
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:51
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class CustomLocaleChangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String locale = resolveLocale(request);
        Locale localeInstance;

        if (StrUtil.isNotBlank(locale)) {
            localeInstance = this.parseLocaleValue(locale);
        } else {
            localeInstance = this.getLocale(request);
        }

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
        }

        try {
            localeResolver.setLocale(request, response, localeInstance);
        } catch (IllegalArgumentException ex) {
            log.debug("Ignoring invalid locale value [" + localeInstance.getDisplayName() + "]: " + ex.getMessage());
        }

        return true;
    }

    @Nullable
    protected Locale parseLocaleValue(String locale) {
        return LocaleTypeEnum.getValue(locale);
    }

    @Nullable
    protected String resolveLocale(HttpServletRequest request) {
        String locale = request.getParameter(I18nProperties.me().getInterceptorName());

        if (isBlank(locale)) {
            locale = request.getHeader(I18nProperties.me().getInterceptorName());
        }

        return locale;
    }

    /**
     * 解析 Locale
     *
     * @return 返回生效的 Locale
     */
    private Locale getLocale(HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);

        if (ObjectUtil.isNotEmpty(locale)) {
            return locale;
        }

        return LocaleContextHolder.getLocale();
    }
}

package com.finhub.framework.i18n.resolver;

import cn.hutool.extra.spring.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;


/**
 * {@link org.springframework.web.servlet.LocaleContextResolver} 的实现
 * 使用 HTTP 请求中包含的请求头 “Accept-Language” 指定的主语言环境，即由客户端浏览器发送的语言环境，由客户端主动选择).
 * <p>
 * 有别与 AcceptHeaderLocaleResolver 支持 setLocale
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午5:09
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class CustomHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    public static CustomHeaderLocaleResolver me() {
        try {
            return SpringUtil.getBean(CustomHeaderLocaleResolver.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new CustomHeaderLocaleResolver();
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {
        locale = locale == null ? resolveLocale(request) : locale;
        setLocaleContext(super.getDefaultLocale(), locale);
    }

    public void setLocaleContext(Locale defaultLocal, Locale locale) {
        LocaleContextHolder.setDefaultLocale(defaultLocal);
        LocaleContextHolder.setLocaleContext(new SimpleLocaleContext(locale), true);
    }
}

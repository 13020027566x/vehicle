package com.finhub.framework.web.captcha;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.captcha.CaptchaUtils;
import com.finhub.framework.core.web.CookieUtils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import static com.finhub.framework.web.constant.WebConstants.DEFAULT_CACHE_NAME;
import static com.finhub.framework.web.constant.WebConstants.DEFAULT_COOKIE_NAME;
import static com.finhub.framework.web.constant.WebConstants.DEFAULT_MAX_AGE;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 验证码缓存
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
@Data
@Slf4j
public class CaptchaCacheManager implements InitializingBean {

    public static CaptchaCacheManager me() {
        return SpringUtil.getBean(CaptchaCacheManager.class);
    }

    private CacheManager cacheManager;

    private String cacheName;

    private String cookieName;

    private Cache captchaCache;

    public CaptchaCacheManager() {
        this.cacheName = DEFAULT_CACHE_NAME;
        this.cookieName = DEFAULT_COOKIE_NAME;
    }

    public CaptchaCacheManager(CacheManager cacheManager) {
        this();
        this.cacheManager = cacheManager;
    }

    /**
     * 生成验证码
     */
    public void generate(HttpServletRequest request, HttpServletResponse response) {
        // 先检查cookie的uuid是否存在
        String cookieValue = CookieUtils.getCookie(cookieName, request);
        boolean hasCookie = true;
        if (StrUtil.isBlank(cookieValue)) {
            hasCookie = false;
            cookieValue = IdUtil.fastUUID();
        }
        String captchaCode = CaptchaUtils.generateCode().toUpperCase();
        // 不存在cookie时设置cookie
        if (!hasCookie) {
            CookieUtils.setCookie(response, cookieName, cookieValue, DEFAULT_MAX_AGE);
        }
        // 生成验证码
        CaptchaUtils.generate(response, captchaCode);
        captchaCache.put(cookieValue, captchaCode);
    }

    /**
     * 仅能验证一次，验证后立即删除
     *
     * @param request          HttpServletRequest
     * @param response         HttpServletResponse
     * @param userInputCaptcha 用户输入的验证码
     * @return 验证通过返回 true, 否则返回 false
     */
    public boolean validate(HttpServletRequest request, HttpServletResponse response, String userInputCaptcha) {
        String cookieValue = CookieUtils.getCookie(cookieName, request);
        if (StrUtil.isBlank(cookieValue)) {
            return false;
        }
        Object captchaCode = captchaCache.get(cookieValue).get();
        if (Func.isEmpty(captchaCode)) {
            return false;
        }

        // 转成大写重要
        userInputCaptcha = userInputCaptcha.toUpperCase();
        boolean result = userInputCaptcha.equals(captchaCode);
        if (result) {
            captchaCache.evictIfPresent(cookieValue);
            CookieUtils.removeCookie(cookieName, response);
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cacheManager, "cacheManager must not be null!");
        Assert.hasText(cacheName, "cacheName must not be empty!");
        Assert.hasText(cookieName, "cookieName must not be empty!");
        this.captchaCache = cacheManager.getCache(cacheName);
    }
}

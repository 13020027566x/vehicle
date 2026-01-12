package com.finhub.framework.shiro.configuration;

import com.finhub.framework.shiro.authc.RetryLimitCredentialsMatcher;
import com.finhub.framework.shiro.authz.PasswordHash;
import com.finhub.framework.shiro.cache.ShiroCacheManager;
import com.finhub.framework.shiro.filter.KickoutSessionControlFilter;
import com.finhub.framework.shiro.filter.ShiroAjaxSessionFilter;

import com.google.common.collect.Maps;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.AbstractSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.EnumSet;
import java.util.Map;
import java.util.Base64;

import static com.finhub.framework.web.constant.WebConstants.ANON;
import static com.finhub.framework.web.constant.WebConstants.EXCLUDE_PATH_LIST;
import static com.finhub.framework.web.constant.WebConstants.INCLUDE_PATH_LIST;
import static com.finhub.framework.web.constant.WebConstants.SESSION_IN_COOKIE;
import static com.finhub.framework.web.constant.WebConstants.USER_KICKOUT;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.shiro.enabled", matchIfMissing = true)
public class ShiroAutoloadConfiguration {

    /**
     * shiroCacheManager
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public ShiroCacheManager shiroCacheManager(CacheManager cacheManager) {
        ShiroCacheManager shiroCacheManager = new ShiroCacheManager();
        shiroCacheManager.setCacheManager(cacheManager);
        return shiroCacheManager;
    }

    /**
     * ShiroFilter 权限控制
     *
     * @return
     */
    @Bean("filterShiroFilterRegistrationBean")
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();

        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);

        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));

        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("staticSecurityManagerEnabled", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        rememberMeCookie.setMaxAge(7 * 24 * 60 * 60);

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie);
        cookieRememberMeManager.setCipherKey(Base64.getDecoder().decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroCacheManager shiroCacheManager, AuthorizingRealm shiroDbRealm,
                                                     DefaultWebSessionManager defaultWebSessionManager, CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroDbRealm);
        defaultWebSecurityManager.setCacheManager(shiroCacheManager);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        return defaultWebSecurityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultWebSecurityManager, KickoutSessionControlFilter kickoutSessionControlFilter) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        filterFactoryBean.setLoginUrl("/#/login");
        filterFactoryBean.setSuccessUrl("/#/home");
        filterFactoryBean.setUnauthorizedUrl("/unauth");

        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("user", new ShiroAjaxSessionFilter());
        filters.put("kickout", kickoutSessionControlFilter);
        filterFactoryBean.setFilters(filters);
        return filterFactoryBean;
    }

    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(ShiroCacheManager shiroCacheManager,
                                                                   DefaultWebSessionManager defaultWebSessionManager) {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(shiroCacheManager);
        kickoutSessionControlFilter.setKickoutCacheName("shiro-kickout-session");

        kickoutSessionControlFilter.setSessionManager(defaultWebSessionManager);
        kickoutSessionControlFilter.setKickoutAttribute("kickout");

        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/#/login?kickout=1");
        return kickoutSessionControlFilter;
    }

    private ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition filterChainDefinition = new DefaultShiroFilterChainDefinition();

        for (String excludePath : EXCLUDE_PATH_LIST) {
            filterChainDefinition.addPathDefinition(excludePath, ANON);
        }

        for (String includePath : INCLUDE_PATH_LIST) {
            filterChainDefinition.addPathDefinition(includePath, USER_KICKOUT);
        }

        return filterChainDefinition;
    }

    @Bean
    public EnterpriseCacheSessionDAO sessionDAO(ShiroCacheManager shiroCacheManager) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setCacheManager(shiroCacheManager);
        sessionDAO.setActiveSessionsCacheName("activeSessionCache");
        return sessionDAO;
    }

    @Bean(destroyMethod = "destroy")
    public DefaultWebSessionManager sessionManager(EnterpriseCacheSessionDAO enterpriseCacheSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setGlobalSessionTimeout(AbstractSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT);
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setDeleteInvalidSessions(false);

        SimpleCookie sessionIdCookie = new SimpleCookie(SESSION_IN_COOKIE);
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);

        sessionManager.setSessionDAO(enterpriseCacheSessionDAO);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public RetryLimitCredentialsMatcher credentialsMatcher(ShiroCacheManager shiroCacheManager) {
        RetryLimitCredentialsMatcher retryLimitCredentialsMatcher =
            new RetryLimitCredentialsMatcher(shiroCacheManager);
        retryLimitCredentialsMatcher.setRetryLimitCount(5);
        retryLimitCredentialsMatcher.setRetryLimitCacheName("halfHour");

        retryLimitCredentialsMatcher.setPasswordHash(PasswordHash.me());
        return retryLimitCredentialsMatcher;
    }

    /**
     * 保证实现了 Shiro 内部 lifecycle 函数的 Bean 执行
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP 式方法级权限检查
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator shiroAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 在方法中 注入 securityManager,进行代理控制
     *
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager);
        return methodInvokingFactoryBean;
    }

    /**
     * 启用 shiro 控制器授权注解拦截方式
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

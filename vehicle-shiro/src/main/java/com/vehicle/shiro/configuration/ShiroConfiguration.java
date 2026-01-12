package com.vehicle.shiro.configuration;

import com.finhub.framework.shiro.authc.RetryLimitCredentialsMatcher;
import com.finhub.framework.shiro.cache.ShiroCacheManager;

import com.vehicle.shiro.realm.ShiroDbRealm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.vehicle.shiro"})
public class ShiroConfiguration {

    /**
     * 項目自定义的Realm
     *
     * @param shiroCacheManager
     * @param retryLimitCredentialsMatcher
     * @return
     */
    @Bean
    public ShiroDbRealm shiroDbRealm(ShiroCacheManager shiroCacheManager, RetryLimitCredentialsMatcher retryLimitCredentialsMatcher) {
        ShiroDbRealm shiroDbRealm = new ShiroDbRealm(shiroCacheManager, retryLimitCredentialsMatcher);
        shiroDbRealm.setAuthorizationCachingEnabled(true);
        shiroDbRealm.setAuthorizationCacheName("authorizationCache");

        shiroDbRealm.setAuthenticationCachingEnabled(true);
        shiroDbRealm.setAuthenticationCacheName("authenticationCache");

        shiroDbRealm.setKickoutCacheName("shiro-kickout-session");

        return shiroDbRealm;
    }
}

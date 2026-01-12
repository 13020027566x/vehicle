package com.finhub.framework.cache.configuration;

import com.finhub.framework.cache.manager.GlobalCacheManager;
import com.finhub.framework.core.cache.StringKeyGenerator;
import com.finhub.framework.core.cache.guava.GuavaCache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "cache.manager", havingValue = "guava")
public class GuavaCacheManagerConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new StringKeyGenerator();
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        GlobalCacheManager globalCacheManager = new GlobalCacheManager();
        globalCacheManager.setTransactionAware(true);

        Set<GuavaCache> caches = new HashSet<>();
        caches.add(new GuavaCache("default", 3600));
        caches.add(new GuavaCache("hour", 10800));
        caches.add(new GuavaCache("day", 86400));
        caches.add(new GuavaCache("week", 432000));
        globalCacheManager.setCaches(caches);
        return globalCacheManager;
    }
}

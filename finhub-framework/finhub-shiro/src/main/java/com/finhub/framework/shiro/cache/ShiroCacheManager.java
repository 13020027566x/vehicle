package com.finhub.framework.shiro.cache;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.lang.util.Destroyable;

/**
 * 使用 spring-cache 作为 shiro 缓存
 * 缓存管理器
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
@Data
@Slf4j
public class ShiroCacheManager implements CacheManager, Destroyable {

    public static ShiroCacheManager me() {
        return SpringUtil.getBean(ShiroCacheManager.class);
    }

    private org.springframework.cache.CacheManager cacheManager;

    public org.springframework.cache.CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (log.isTraceEnabled()) {
            log.trace("Acquiring ShiroSpringCache instance named [" + name + "]");
        }
        org.springframework.cache.Cache cache = cacheManager.getCache(name);
        return new ShiroCache<>(cache);
    }

    @Override
    public void destroy() throws Exception {
        cacheManager = null;
    }
}

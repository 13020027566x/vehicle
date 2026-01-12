package com.finhub.framework.cache.config;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Map;

/**
 * Cache 配置（主要为程序执行行为的配置，或者Nacos较为复杂的配置）
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class CacheConfig {

    public static CacheConfig me() {
        try {
            return SpringUtil.getBean(CacheConfig.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new CacheConfig();
        }
    }

    /**
     * ehcache默认文件位置
     */
    private String ehcacheConfigPath = "classpath:ehcache.xml";

    /**
     * Sets the value or (hash key (or field)) serializer to be used by this template.
     */
    private boolean useJacksonSerializer = false;

    /**
     * Sets the value serializer to be used by this template.
     */
    private RedisSerializer valueSerializer;

    /**
     * Sets the hash key (or field) serializer to be used by this template.
     */
    private RedisSerializer hashValueSerializer;

    /**
     * redis CacheManager customer cacheName&configuration mapping map
     */
    private Map<String, RedisCacheConfiguration> redisCacheConfigurationMap;


}

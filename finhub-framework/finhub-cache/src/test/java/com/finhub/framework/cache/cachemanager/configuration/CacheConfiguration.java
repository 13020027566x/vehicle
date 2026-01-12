package com.finhub.framework.cache.cachemanager.configuration;

import com.finhub.framework.cache.config.CacheConfig;
import com.finhub.framework.cache.config.CacheNameConfig;
import com.finhub.framework.core.Func;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
@Configuration
@ComponentScan("com.finhub.framework.cache")
public class CacheConfiguration {

    @Bean
    public CacheConfig cacheConfig() {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setHashValueSerializer(new StringRedisSerializer());
        cacheConfig.setValueSerializer(new StringRedisSerializer());
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = Maps.newHashMap();
        redisCacheConfigurationMap.put("123123", createRedisCacheConfiguration(30L).prefixCacheNameWith("prefix::::"));
        redisCacheConfigurationMap.put("3333", createRedisCacheConfiguration1(60L));
        final RedisCacheConfiguration redisCacheConfiguration = createRedisCacheConfiguration(60L);
        cacheConfig.setRedisCacheConfigurationMap(redisCacheConfigurationMap);

        return cacheConfig;
    }


    /**
     * create customize redis cache configuration
     *
     * @param seconds       expire time & seconds
     * @return              RedisCacheConfiguration
     */
    private RedisCacheConfiguration createRedisCacheConfiguration(long seconds) {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds)).serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).disableCachingNullValues();

    }

    private RedisCacheConfiguration createRedisCacheConfiguration1(long seconds) {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds)).serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())).serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer())).disableCachingNullValues();

    }

}

package com.finhub.framework.cache.configuration;

import com.finhub.framework.cache.config.CacheConfig;
import com.finhub.framework.cache.config.CacheNameConfig;
import com.finhub.framework.cache.contants.DefaultRedisCacheNameEnum;
import com.finhub.framework.cache.property.CacheProperties;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.cache.StringKeyGenerator;
import com.finhub.framework.core.number.NumberConstants;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "cache.manager", havingValue = "redis", matchIfMissing = true)
public class RedisCacheManagerConfiguration extends CachingConfigurerSupport {

    final CacheProperties cacheProperties;

    public RedisCacheManagerConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new StringKeyGenerator();
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory, CacheConfig cacheConfig) {

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        this.putDefaultCacheConfiguration(cacheConfigurations, cacheConfig);

        this.putCustomizeCacheConfiguration(cacheConfigurations, cacheConfig);

        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(
            lettuceConnectionFactory)).transactionAware().withInitialCacheConfigurations(cacheConfigurations).build();
    }

    /**
     * 添加cacheManger默认cacheName资源分组
     *
     * @param cacheConfigurations   cacheConfiguration cacheName&expire seconds 集合
     * @param cacheConfig           get redis prefix
     */
    private void putDefaultCacheConfiguration(Map<String, RedisCacheConfiguration> cacheConfigurations,
        CacheConfig cacheConfig) {

        CacheNameConfig[] values = DefaultRedisCacheNameEnum.values();
        for (CacheNameConfig value : values) {
            if (!this.checkedRedisCacheCustomConfig(value)) {
                continue;
            }
            cacheConfigurations.put(value.getCacheName(),
                this.createRedisCacheConfiguration(cacheConfig, value.getTimeUnit().toSeconds(value.getExpire())));
        }

    }

    /**
     * 添加自定义cacheConfiguration
     *
     * @param cacheConfigurations   cacheConfiguration cacheName&expire seconds 集合
     * @param cacheConfig           get redis prefix
     */
    private void putCustomizeCacheConfiguration(Map<String, RedisCacheConfiguration> cacheConfigurations,
        CacheConfig cacheConfig) {

        if (Func.isNull(cacheConfig) || Func.isEmpty(cacheConfig.getRedisCacheConfigurationMap())) {
            return;
        }

        cacheConfigurations.putAll(cacheConfig.getRedisCacheConfigurationMap());
    }

    /**
     * 检查自定义缓存配置是否有效
     *
     * @param cacheNameConfig 配置信息
     * @return   true有效 false无效
     */
    private boolean checkedRedisCacheCustomConfig(CacheNameConfig cacheNameConfig) {

        if (Func.isNull(cacheNameConfig)) {
            return false;
        }

        if (Func.isBlank(cacheNameConfig.getCacheName())) {
            return false;
        }

        if (cacheNameConfig.getExpire() <= NumberConstants.N_ZERO) {
            return false;
        }

        if (Func.isNull(cacheNameConfig.getTimeUnit())) {
            return false;
        }

        return true;
    }

    /**
     * create customize redis cache configuration
     *
     * @param cacheConfig   redis config info
     * @param seconds       expire time & seconds
     * @return              RedisCacheConfiguration
     */
    private RedisCacheConfiguration createRedisCacheConfiguration(CacheConfig cacheConfig, long seconds) {

        if (StrUtil.isBlank(cacheProperties.getPrefix())) {
            return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds)).serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer())).serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    cacheConfig.isUseJacksonSerializer() ?
                        new GenericJackson2JsonRedisSerializer() :
                        Func.isNotNull(cacheConfig.getHashValueSerializer()) ? cacheConfig.getHashValueSerializer() :
                            new JdkSerializationRedisSerializer())).disableCachingNullValues();
        }

        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds)).prefixCacheNameWith(
            cacheProperties.getPrefix()).serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(
                new StringRedisSerializer())).serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(
                cacheConfig.isUseJacksonSerializer() ?
                    new GenericJackson2JsonRedisSerializer() :
                    Func.isNotNull(cacheConfig.getHashValueSerializer()) ? cacheConfig.getHashValueSerializer() :
                        new JdkSerializationRedisSerializer())).disableCachingNullValues();

    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError. [key='{}']", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError. [key='{}', value='{}']", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError. [key='{}']", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError. ", e);
            }
        };
    }
}

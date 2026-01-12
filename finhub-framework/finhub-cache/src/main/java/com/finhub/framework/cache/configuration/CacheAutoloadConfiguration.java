package com.finhub.framework.cache.configuration;

import com.finhub.framework.cache.property.CacheProperties;
import com.finhub.framework.cache.property.RedisProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@EnableCaching
@Configuration
@ConditionalOnProperty(name = "vehicle.cache.enabled", matchIfMissing = true)
@EnableConfigurationProperties({CacheProperties.class, RedisProperties.class})
@Import({GuavaCacheManagerConfiguration.class, RedisCacheManagerConfiguration.class, RedisTemplateConfiguration.class})
public class CacheAutoloadConfiguration {

}

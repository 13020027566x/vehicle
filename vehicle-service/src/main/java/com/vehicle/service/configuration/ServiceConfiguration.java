package com.vehicle.service.configuration;

import com.finhub.framework.cache.config.CacheConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.vehicle.service"})
public class ServiceConfiguration {

    @Bean
    CacheConfig cacheConfig() {
        return new CacheConfig();
    }

}

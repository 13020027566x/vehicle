package com.finhub.framework.sentinel.configuration;

import com.finhub.framework.sentinel.aspect.CustomSentinelResourceAspect;
import com.finhub.framework.sentinel.aspect.SentinelBeanPostProcessor;
import com.finhub.framework.sentinel.property.SentinelProperties;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sentinel启动配置类
 *
 * @author zhenxing_liang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SentinelProperties.class})
@ConditionalOnProperty(name = "vehicle.sentinel.enabled", matchIfMissing = true)
public class SentinelAutoloadConfiguration {

    @Bean
    SentinelBeanPostProcessor sentinelBeanPostProcessor() {
        return new SentinelBeanPostProcessor();
    }

    @Bean
    public SentinelResourceAspect customSentinelResourceAspect() {
        return new CustomSentinelResourceAspect();
    }
}

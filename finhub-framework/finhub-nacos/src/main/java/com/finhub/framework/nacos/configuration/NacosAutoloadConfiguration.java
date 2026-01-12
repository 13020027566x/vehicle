package com.finhub.framework.nacos.configuration;

import com.finhub.framework.nacos.listener.DynamicLoggerConfigListener;
import com.finhub.framework.nacos.property.AutoRefreshProperties;
import com.finhub.framework.nacos.property.NacosBootProperties;

import com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置优先级：JVM 参数 > 本地 Yaml > Nacos Yaml
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Slf4j
@Configuration
@Import(NacosConfigAutoConfiguration.class)
@EnableConfigurationProperties({NacosBootProperties.class})
@ConditionalOnProperty(name = "vehicle.nacos.enabled", matchIfMissing = true)
public class NacosAutoloadConfiguration {

    @Bean
    AutoRefreshProperties autoRefreshProperties() {
        return new AutoRefreshProperties();
    }

    @Bean
    DynamicLoggerConfigListener dynamicLoggerConfigListener() {
        return new DynamicLoggerConfigListener();
    }
}

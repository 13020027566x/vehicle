package com.finhub.framework.logback.configuration;

import com.finhub.framework.logback.property.LogProperties;

import cn.hutool.extra.spring.EnableSpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * Log 配置
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@EnableSpringUtil
@EnableConfigurationProperties({LogProperties.class})
@ConditionalOnProperty(name = "vehicle.logback.enabled", matchIfMissing = true)
public class LogbackAutoloadConfiguration {

}



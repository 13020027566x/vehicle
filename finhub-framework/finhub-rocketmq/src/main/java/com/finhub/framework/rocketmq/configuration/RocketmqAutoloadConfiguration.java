package com.finhub.framework.rocketmq.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * https://www.iocoder.cn/Spring-Boot/RocketMQ/?github
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.rocketmq.enabled", matchIfMissing = true)
public class RocketmqAutoloadConfiguration {
}

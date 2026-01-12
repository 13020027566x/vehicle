package com.finhub.framework.exception.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.exception.enabled", matchIfMissing = true)
public class ExceptionAutoloadConfiguration {
}

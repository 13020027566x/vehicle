package com.vehicle.provider.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableDubbo(scanBasePackages = {"com.vehicle.provider"})
@ComponentScan(basePackages = {"com.vehicle.provider"})
public class ProviderConfiguration {

}

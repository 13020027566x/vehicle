package com.vehicle.client.configuration;

import cn.hutool.extra.spring.EnableSpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableSpringUtil
@ComponentScan(basePackages = {"com.vehicle.client"})
// @EnableDubbo(scanBasePackages = {"com.vehicle.client"}) test 2.6.2
public class ClientAutoloadConfiguration {

}

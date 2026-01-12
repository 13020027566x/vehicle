package com.vehicle.web.configuration;

import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.web.context.UserContextHolder;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.vehicle.web"})
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    UserContextHolder userContextHolder() {
        return (request, response) -> (CurrentUser) SecurityUtils.getSubject().getPrincipal();
    }

}

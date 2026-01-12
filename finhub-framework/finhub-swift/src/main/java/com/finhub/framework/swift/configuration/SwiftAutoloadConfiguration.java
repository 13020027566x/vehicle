package com.finhub.framework.swift.configuration;

import com.finhub.framework.swift.filter.HttpSwiftWrapperFilter;
import com.finhub.framework.swift.property.SwiftProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuangfei.chen
 * @create 2021/8/16 15:59
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({SwiftProperties.class})
@ConditionalOnProperty(name = "vehicle.swift.enabled", matchIfMissing = true)
public class SwiftAutoloadConfiguration {

    @Bean
    public FilterRegistrationBean<HttpSwiftWrapperFilter> registerHttpSwiftWrapperFilter() {
        FilterRegistrationBean<HttpSwiftWrapperFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new HttpSwiftWrapperFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("HttpSwiftWrapperFilter");
        filterRegistrationBean.setOrder(FilterOrders.HTTP_SWIFT_WRAPPER_FILTER);
        return filterRegistrationBean;
    }

    static class FilterOrders {
        public static final int HTTP_SWIFT_WRAPPER_FILTER = Integer.MIN_VALUE;
    }
}

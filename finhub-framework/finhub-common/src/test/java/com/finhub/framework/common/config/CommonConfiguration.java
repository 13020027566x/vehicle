package com.finhub.framework.common.config;


import com.finhub.framework.nacos.property.AutoRefreshProperties;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
@Configuration
@ComponentScan({"com.finhub.framework.common", "com.finhub.framework.core"})
public class CommonConfiguration {

    @Bean
    AutoRefreshProperties autoRefreshProperties() {
        AutoRefreshProperties autoRefreshProperties = new AutoRefreshProperties();
        Map<String, String> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("env-debug-enabled", "true");
        autoRefreshProperties.setConfig(objectObjectHashMap);
        return autoRefreshProperties;
    }
}

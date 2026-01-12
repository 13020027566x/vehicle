package com.finhub.framework.web.configuration;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        // 设置文件大小限制
        factory.setMaxFileSize(DataSize.ofBytes(10485760000L)); // 10GB
        factory.setMaxRequestSize(DataSize.ofBytes(10485760000L)); // 10GB

        // 设置内存阈值
        factory.setFileSizeThreshold(DataSize.ofBytes(40960)); // 40KB

        return factory.createMultipartConfig();
    }
}

package com.finhub.framework.swagger.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger 配置
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/2 上午8:27
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    public static SwaggerProperties me() {
        try {
            return SpringUtil.getBean(SwaggerProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new SwaggerProperties();
        }
    }

    private String groupName = "finhub";

    private String basePackage = "com.vehicle";
}

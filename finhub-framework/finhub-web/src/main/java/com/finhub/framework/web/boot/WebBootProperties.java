package com.finhub.framework.web.boot;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

/**
 * Web 启动配置自检
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@Validated
@ConfigurationProperties(prefix = "spring")
public class WebBootProperties {

    public static WebBootProperties me() {
        try {
            return SpringUtil.getBean(WebBootProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new WebBootProperties();
        }
    }

    @Valid
    @NotNull(message = "spring.mvc cannot be null")
    Mvc mvc;


    @Data
    static class Mvc {
        @AssertTrue(message = "spring.mvc.throw-exception-if-no-handler-found must be true")
        Boolean throwExceptionIfNoHandlerFound;
    }


    @Valid
    @NotNull(message = "spring.web cannot be null")
    Web web;


    @Data
    static class Web {
        @Valid
        @NotNull(message = "spring.web.resources cannot be null")
        Resources resources;
    }


    @Data
    static class Resources {
        @Valid
        @AssertFalse(message = "spring.web.resources.add-mappings must be false")
        Boolean addMappings;
    }
}

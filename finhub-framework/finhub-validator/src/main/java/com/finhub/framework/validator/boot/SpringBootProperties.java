package com.finhub.framework.validator.boot;

import cn.hutool.extra.spring.SpringUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


/**
 * Spring 启动配置自检
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@Validated
@ConfigurationProperties(prefix = "spring")
public class SpringBootProperties {

    public static SpringBootProperties me() {
        try {
            return SpringUtil.getBean(SpringBootProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new SpringBootProperties();
        }
    }

    @Valid
    @NotNull(message = "spring.application cannot be null")
    private Application application;


    @Data
    static class Application {
        @NotNull(message = "spring.application.name cannot be null")
        String name;
    }


    @Valid
    @NotNull(message = "spring.profiles cannot be null")
    Profiles profiles;


    @Data
    static class Profiles {
        @NotNull(message = "spring.profiles.active cannot be null")
        String active;
    }
}

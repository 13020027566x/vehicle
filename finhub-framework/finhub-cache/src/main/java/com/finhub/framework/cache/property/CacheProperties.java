package com.finhub.framework.cache.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    public static CacheProperties me() {
        try {
            return SpringUtil.getBean(CacheProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new CacheProperties();
        }
    }

    private String prefix;

    private String manager = "redis";
}

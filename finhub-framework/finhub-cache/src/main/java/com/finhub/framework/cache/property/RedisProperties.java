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
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    public static RedisProperties me() {
        try {
            return SpringUtil.getBean(RedisProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new RedisProperties();
        }
    }

    public static final String STANDALONE = "STANDALONE";

    public static final String SENTINEL = "SENTINEL";

    public static final String CLUSTER = "CLUSTER";

    private String mode;

    private String ip;

    private Integer port;

    private String password;

    private Integer database = 0;

    private Integer timeout = 5000;

    private Integer maxIdle = 10;

    private Integer minIdle = 1;

    private Integer maxTotal = 30;

    private Integer maxWaitMillis = 5000;

    private boolean testOnBorrow = true;

    public boolean isStandaloneMode() {
       return STANDALONE.equalsIgnoreCase(this.mode);
    }

    public boolean isSentinelMode() {
        return SENTINEL.equalsIgnoreCase(this.mode);
    }

    public boolean isClusterMode() {
        return CLUSTER.equalsIgnoreCase(this.mode);
    }
}

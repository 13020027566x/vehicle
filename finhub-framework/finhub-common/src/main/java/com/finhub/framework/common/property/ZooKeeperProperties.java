package com.finhub.framework.common.property;

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
@ConfigurationProperties(prefix = "zookeeper")
public class ZooKeeperProperties {

    public static ZooKeeperProperties me() {
        try {
            return SpringUtil.getBean(ZooKeeperProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new ZooKeeperProperties();
        }
    }

    /**
     * 连接地址
     */
    private String url;

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;
}

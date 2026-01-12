package com.finhub.framework.web.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2022/1/17
 * @desc :
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.web.async")
public class AsyncProperties {

    public static AsyncProperties me() {
        try {
            return SpringUtil.getBean(AsyncProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new AsyncProperties();
        }
    }

    private String threadNamePrefix = "vehicle-http-sync-";
    /**
     * 核心线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = 20;

    /**
     * 回收时间
     */
    private long keepAliveTime = 30;

    /**
     * 回收时间类型
     */
    private TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 队列容量
     */
    private int queueCapacity = 1000;

}

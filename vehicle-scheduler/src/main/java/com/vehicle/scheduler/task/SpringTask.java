package com.vehicle.scheduler.task;

import com.finhub.framework.nacos.property.AutoRefreshProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring task 定时任务测试，适用于单系统
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@Component
public class SpringTask {

    @Scheduled(fixedRate = 5000)
    public void cronTest() {
        Object aObj = AutoRefreshProperties.me().getValue("a");
        log.info("====> auto refresh config. [a={}]", aObj.toString());

        log.info("[SpringTask]cron task execute" + System.currentTimeMillis());
    }
}

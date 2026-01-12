package com.vehicle.scheduler.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring scheduler 定时任务测试，适用于单系统
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@Component
public class SchedulerTask {

    /**
     * cron：通过表达式来配置任务执行时间
     * fixedRate：指定两次任务执行的时间间隔(毫秒)，此时间间隔指的是，前一个任务开始与下一个任务开始的间隔
     * fixedDelay：指定两次任务执行的时间间隔(毫秒)，此时间间隔指的是，前一次任务结束与下一个任务开始的间隔
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void cronTest() {
        log.info("[SchedulerTask]cron task execute" + System.currentTimeMillis());
        // ThreadPoolUtils.printAllStackTraces();
    }
}

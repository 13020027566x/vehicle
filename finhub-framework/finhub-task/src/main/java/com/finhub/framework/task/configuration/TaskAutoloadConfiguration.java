package com.finhub.framework.task.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务配置 - Spring Boot 3.x
 */
@Slf4j
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "vehicle.task.enabled", matchIfMissing = true)
public class TaskAutoloadConfiguration {

    @Bean
    public TaskScheduler vehicleTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        // 配置参数
        scheduler.setThreadNamePrefix("vehicle-scheduling-");
        scheduler.setPoolSize(10);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setRemoveOnCancelPolicy(true);

        // 错误处理
        scheduler.setErrorHandler(throwable -> {
            log.error("定时任务执行异常", throwable);
        });

        // 初始化
        scheduler.initialize();

        log.info("Vehicle定时任务调度器初始化完成");
        return scheduler;
    }
}

package com.vehicle.scheduler.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Quartz Job 定时任务测试
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@DisallowConcurrentExecution
public class DemoJob01 extends QuartzJobBean {

    private static final AtomicInteger COUNTS = new AtomicInteger();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("[QuartzJobBean.executeInternal] {} 定时第 ({}) 次执行成功", "DemoJob01", COUNTS.incrementAndGet());
    }
}

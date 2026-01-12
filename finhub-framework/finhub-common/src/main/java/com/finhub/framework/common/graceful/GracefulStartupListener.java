package com.finhub.framework.common.graceful;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
public class GracefulStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 注册逻辑 优雅上线
        log.info(">>> graceful startup, please add hook at here. <<<");
    }
}

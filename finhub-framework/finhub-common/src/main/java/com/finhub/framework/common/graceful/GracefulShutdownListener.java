package com.finhub.framework.common.graceful;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@Slf4j
public class GracefulShutdownListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        // 注销逻辑，优雅下线
        log.info(">>> graceful shutdown, please add hook at here. <<<");
    }
}

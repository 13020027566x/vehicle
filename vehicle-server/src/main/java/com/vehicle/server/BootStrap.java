package com.vehicle.server;

import com.vehicle.web.WebServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <pre>
 * 聚合启动类
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/7 上午9:43
 */
@Slf4j
public class BootStrap {
    public static void main(String[] args) {
        WebServer.start(new SpringApplicationBuilder(WebServer.class), args);
    }
}

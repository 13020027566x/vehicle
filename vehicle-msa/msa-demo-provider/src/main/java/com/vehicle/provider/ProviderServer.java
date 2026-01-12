package com.vehicle.provider;

import com.vehicle.provider.configuration.ProviderConfiguration;
import com.vehicle.scheduler.configuration.SchedulerConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Provider Server 启动类
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/7 上午9:43
 */
@SpringBootApplication
@Import({ProviderConfiguration.class, SchedulerConfiguration.class})
public class ProviderServer {

    public static void start(SpringApplicationBuilder springApplicationBuilder, String[] args) {

        // springApplicationBuilder.parent(CoreConfiguration.class, RedisConfiguration.class);

        Map<String, Object> properties = new HashMap<>(16);
        // 默认为 Web 方式启动，因为 RPC 服务默认要开 8080 端口
        springApplicationBuilder.properties(properties).run(args);
        // springApplicationBuilder.web(WebApplicationType.NONE).properties(properties).run(args);
    }
}

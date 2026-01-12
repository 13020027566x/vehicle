package com.vehicle.web;

import com.vehicle.dao.configuration.DaoConfiguration;
import com.vehicle.service.configuration.ServiceConfiguration;
import com.vehicle.shiro.configuration.ShiroConfiguration;
import com.vehicle.web.configuration.WebConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Web Server
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/7 上午9:43
 */
@SpringBootApplication
@Import({WebConfiguration.class, ShiroConfiguration.class, ServiceConfiguration.class, DaoConfiguration.class})
public class WebServer {

    public static void start(SpringApplicationBuilder springApplicationBuilder, String[] args) {

        // springApplicationBuilder.parent(CoreConfiguration.class, RedisConfiguration.class);

        Map<String, Object> properties = new HashMap<>(16);
        // properties.put(SERVER_PORT_KEY, 58080);

        springApplicationBuilder.properties(properties).run(args);
    }
}

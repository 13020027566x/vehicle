package ${conf.webPackageName};

import ${conf.daoPackageName}.configuration.DaoConfiguration;
import ${conf.servicePackageName}.configuration.ServiceConfiguration;
import ${conf.webPackageName}.configuration.WebConfiguration;
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
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@SpringBootApplication
@Import({WebConfiguration.class, ServiceConfiguration.class, DaoConfiguration.class})
public class WebServer {

    public static void start(SpringApplicationBuilder springApplicationBuilder, String[] args) {
        // springApplicationBuilder.parent(CoreConfiguration.class, RedisConfiguration.class);

        Map<String, Object> properties = new HashMap<>();
        // properties.put(SERVER_PORT_KEY, 58080);

        springApplicationBuilder.properties(properties).run(args);
    }
}

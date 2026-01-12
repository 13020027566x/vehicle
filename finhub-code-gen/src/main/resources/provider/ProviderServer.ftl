package ${conf.providerPackageName};

import ${conf.providerPackageName}.configuration.ProviderConfiguration;
import org.springframework.boot.WebApplicationType;
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
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@SpringBootApplication
@Import({ProviderConfiguration.class})
public class ProviderServer {

    public static void start(SpringApplicationBuilder springApplicationBuilder, String[] args) {

        // springApplicationBuilder.parent(CoreConfiguration.class, RedisConfiguration.class);

        Map<String, Object> properties = new HashMap<>();
        springApplicationBuilder.web(WebApplicationType.NONE).properties(properties).run(args);
    }
}

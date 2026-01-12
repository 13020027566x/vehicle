package ${conf.serverPackageName};

import ${conf.webPackageName}.WebServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <pre>
 * 聚合启动类
 * </pre>
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
public class BootStrap {
    public static void main(String[] args) {
        WebServer.start(new SpringApplicationBuilder(WebServer.class), args);
    }
}

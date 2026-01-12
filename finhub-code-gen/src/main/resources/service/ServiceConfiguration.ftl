package ${conf.servicePackageName}.configuration;

import com.finhub.framework.cache.config.CacheConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"${conf.servicePackageName}"})
public class ServiceConfiguration {

    @Bean
    CacheConfig cacheConfig() {
        return new CacheConfig();
    }
}

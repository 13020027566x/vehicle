package ${conf.providerPackageName}.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ProviderConfiguration
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Configuration
@EnableDubbo(scanBasePackages = {"${conf.providerPackageName}"})
@ComponentScan(basePackages = {"${conf.providerPackageName}"})
public class ProviderConfiguration {

}

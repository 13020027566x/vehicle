package ${conf.servicePackageName}.configuration;

import cn.hutool.extra.spring.EnableSpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableSpringUtil
@ComponentScan(basePackages = {"${conf.servicePackageName}.*.converter", "${conf.servicePackageName}.*.domain"})
public class ServiceTestConfiguration {

}

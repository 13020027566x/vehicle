package ${conf.rpcClientPackageName}.configuration;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Rpc Client 自动配置
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Configuration
@EnableSpringUtil
@ComponentScan(basePackages = {"${conf.rpcClientPackageName}"})
public class ClientAutoloadConfiguration {

}

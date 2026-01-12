package com.finhub.framework.nacos.configuration;

import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.boot.nacos.config.autoconfigure.NacosConfigEnvironmentProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

import org.springframework.core.env.ConfigurableEnvironment;
/**
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/07 15:28
 */
public class NacosEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    /**
     * nacos default environment source config
     */
    private static final String DEFAULT_NACOS_ENV_CONFIG = "default_nacos_env_config";

    /**
     * processor execute order, before execute for {@link NacosConfigEnvironmentProcessor}
     */
    private static final int DEFAULT_ORDER = Ordered.LOWEST_PRECEDENCE - 6;

    /**
     * Spring active profiles property name
     */
    private static final String ACTIVE_PROFILES_PROPERTY = "spring.profiles.active";  // 添加这行

    /**
     * namespace config for nacos
     */
    private static final String NACOS_NAMESPACE_PROPERTY = "nacos.config.namespace";

    /**
     * server address hump config for nacos
     */
    private static final String NACOS_ADDRESS_PROPERTY = "nacos.config.serverAddr";

    /**
     * server address delimiter config for nacos
     */
    private static final String NACOS_ADDRESS_LINE_PROPERTY = "nacos.config.server-addr";

    /**
     * default server address config for nacs
     */
    private static final String DEFAULT_NACOS_ADDRESS = "mse-f92e27f2-nacos-ans.mse.aliyuncs.com:8848";

    /**
     * default startup environment for spring
     */
    private static final String DEFAULT_ACTIVE_PROFILES_PROPERTY = "tx-dev";

    /**
     * 注释 Demo & 执行时机
     * @param environment
     * @param application
     */
    @Override
    @SuppressWarnings({"java:S106"})
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        Properties envProperties = new Properties();

        //if active-profiles property is null,set default value
        String activeProfiles = environment.getProperty(ACTIVE_PROFILES_PROPERTY);
        if (CharSequenceUtil.isBlank(activeProfiles)) {
            System.out.println(
                "[FINHUB-NACOS WARN] the server has no startup environment configured. [spring.profiles.active] property use default value : " + DEFAULT_ACTIVE_PROFILES_PROPERTY);
            activeProfiles = DEFAULT_ACTIVE_PROFILES_PROPERTY;
            envProperties.setProperty(ACTIVE_PROFILES_PROPERTY, DEFAULT_ACTIVE_PROFILES_PROPERTY);
        }

        //if nacos-namespace property is null,set default value
        if (!environment.containsProperty(NACOS_NAMESPACE_PROPERTY)) {
            System.out.println(
                "[FINHUB-NACOS WARN] the server has no nacos-namespace environment configured. [nacos.config.namespace] property use active profile value : " + activeProfiles);
            envProperties.setProperty(NACOS_NAMESPACE_PROPERTY, activeProfiles);
        }

        //if nacos-server-address property is null,set default value
        if (!environment.containsProperty(NACOS_ADDRESS_PROPERTY) && !environment.containsProperty(
            NACOS_ADDRESS_LINE_PROPERTY)) {
            System.out.println(
                "[FINHUB-NACOS WARN] the server has no nacos-server-addr environment configured. [nacos.config.server-addr] property use default value : " + DEFAULT_NACOS_ADDRESS);
            envProperties.setProperty(NACOS_ADDRESS_PROPERTY, DEFAULT_NACOS_ADDRESS);
        }

        if (envProperties.isEmpty()) {
            return;
        }

        environment.getPropertySources().addLast(
            new PropertiesPropertySource(DEFAULT_NACOS_ENV_CONFIG, envProperties));

    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}

package com.finhub.framework.safety.environment;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/07 15:28
 */
public class SafetyEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    /**
     * safety yaml path
     */
    private static final String SAFETY_YAML_PATH = "classpath:safety.yaml";

    /**
     * default environment source config
     */
    private static final String DEFAULT_SAFETY_ENV_CONFIG = "default_safety_env_config";

    /**
     * processor execute order
     */
    private static final int DEFAULT_ORDER = Ordered.LOWEST_PRECEDENCE - 3;

    /**
     * 注释 Demo & 执行时机cos
     * @param environment
     * @param application
     */
    @Override
    @SuppressWarnings({"java:S106"})
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        environment.getPropertySources().addLast(new PropertiesPropertySource(DEFAULT_SAFETY_ENV_CONFIG, getProperties()));
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    private static Properties getProperties() {
        YamlPropertiesFactoryBean propertiesFactoryBean = new YamlPropertiesFactoryBean();
        try {
            propertiesFactoryBean.setResources(
                new PathMatchingResourcePatternResolver().getResources(SAFETY_YAML_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFactoryBean.getObject();
    }
}

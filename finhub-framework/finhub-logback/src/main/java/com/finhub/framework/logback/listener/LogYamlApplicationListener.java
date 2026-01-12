package com.finhub.framework.logback.listener;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 日志配置默认加载类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class LogYamlApplicationListener implements GenericApplicationListener {

    private static final String REMOTE_LOGBACK_PATH = "classpath:logback.yaml";

    private static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 19;

    private static final Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
        ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class,
        ApplicationFailedEvent.class};

    private static final Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        }
    }


    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties properties = getProperties(REMOTE_LOGBACK_PATH);
        if (properties == null) {
            return;
        }
        environment.getPropertySources().addLast(new PropertiesPropertySource("logback", properties));
    }

    private static Properties getProperties(String path) {

        YamlPropertiesFactoryBean propertiesFactoryBean = new YamlPropertiesFactoryBean();
        try {
            propertiesFactoryBean.setResources(
                new PathMatchingResourcePatternResolver().getResources(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFactoryBean.getObject();
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

}

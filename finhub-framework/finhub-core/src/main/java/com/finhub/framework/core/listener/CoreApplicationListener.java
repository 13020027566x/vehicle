package com.finhub.framework.core.listener;

import cn.hutool.core.util.BooleanUtil;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import lombok.extern.slf4j.Slf4j;
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
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.util.Properties;

import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;
import static com.finhub.framework.core.str.StrConstants.S_AT;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_SKYWALKING_SERVICE_NAME_PROPERTY;

/**
 * 公共配置默认加载类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class CoreApplicationListener implements GenericApplicationListener {

    private static final Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
        ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class,
        ApplicationFailedEvent.class};

    private static final Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};

    /**
     * listener process order
     */
    private static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 11;

    /**
     * core yaml路径
     */
    private static final String CORE_YAML_PATH = "classpath:core.yaml";

    /**
     * environment property alias name for core.yaml
     */
    private static final String CORE_PROPERTY_SOURCE_NAME = "core";

    /**
     * environment property alias name for skywalking
     */
    private static final String SKYWALKING_PROPERTY_SOURCE_NAME = "skywalking";

    /**
     * finhub-agent 开关
     */
    private static final String SPRING_FINHUB_AGENT_ENABLED_PROPERTY = "vehicle.finhub-agent.enabled";

    /**
     * finhub-agent Path
     */
    private static final String SPRING_FINHUB_AGENT_PATH_PROPERTY = "vehicle.finhub-agent.path";

    /**
     * xxl.job.executor.logpath
     */
    private static final String SPRING_XXL_JOB_EXECUTOR_LOGPATH_PROPERTY = "xxl.job.executor.logpath";

    /**
     * xxl-job
     */
    private static final String XXL_JOB_PROPERTY_SOURCE_NAME = "xxl-job";

    /**
     * finhub agent jar default path
     */
    private static final String DEFAULT_FINHUB_AGENT_PATH = "/opt/agent/lib/finhub-agent.jar";

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
        if (event instanceof ApplicationStartingEvent) {
            onApplicationStartingEvent((ApplicationStartingEvent) event);
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        } else if (event instanceof ApplicationPreparedEvent) {
            onApplicationPreparedEvent((ApplicationPreparedEvent) event);
        } else if (event instanceof ContextClosedEvent
            && ((ContextClosedEvent) event).getApplicationContext().getParent() == null) {
            onContextClosedEvent((ContextClosedEvent) event);
        } else if (event instanceof ApplicationFailedEvent) {
            onApplicationFailedEvent((ApplicationFailedEvent) event);
        }
    }

    private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        // 提前初始化 Charset 避免 lookup2 偶发性死锁问题
        Charset.availableCharsets();
    }

    /**
     * init local core yaml properties for spring environment
     *
     * @param event event
     */
    @SuppressWarnings({"java:S106"})
    private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();

        Properties coreProperties = getProperties();
        if (coreProperties == null || coreProperties.isEmpty()) {
            return;
        }
        environment.getPropertySources().addLast(
            new PropertiesPropertySource(CORE_PROPERTY_SOURCE_NAME, coreProperties));

        // 用 skywalking.agent.service_name 覆盖掉 spring.application.name 值
        String skywalkingAgentServiceName = environment.getProperty(SPRING_SKYWALKING_SERVICE_NAME_PROPERTY);
        if (isNotBlank(skywalkingAgentServiceName)) {
            Properties springApplicationNameProperties = new Properties();
            springApplicationNameProperties.setProperty(SPRING_APPLICATION_NAME_PROPERTY, skywalkingAgentServiceName);

            environment.getPropertySources().addFirst(new PropertiesPropertySource(SKYWALKING_PROPERTY_SOURCE_NAME, springApplicationNameProperties));
        }

        // 覆盖掉 Nacos 配置 xxl.job.executor.logpath
        String userHome = System.getProperty("user.home");
        Properties xxlJobProperties = new Properties();
        xxlJobProperties.setProperty(SPRING_XXL_JOB_EXECUTOR_LOGPATH_PROPERTY, userHome + "/logs/xxl-job");

        environment.getPropertySources().addFirst(new PropertiesPropertySource(XXL_JOB_PROPERTY_SOURCE_NAME, xxlJobProperties));
    }

    /**
     * loading local core yaml properties for spring environment
     *
     * @return custom properties
     */
    private static Properties getProperties() {
        YamlPropertiesFactoryBean propertiesFactoryBean = new YamlPropertiesFactoryBean();
        try {
            propertiesFactoryBean.setResources(
                new PathMatchingResourcePatternResolver().getResources(CORE_YAML_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFactoryBean.getObject();
    }

    private void onApplicationPreparedEvent(ApplicationPreparedEvent event) {
        boolean isFinhubAgentEnabled = true;

        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        String springFinhubAgentEnabled = environment.getProperty(SPRING_FINHUB_AGENT_ENABLED_PROPERTY);
        if (isNotBlank(springFinhubAgentEnabled)) {
            isFinhubAgentEnabled = BooleanUtil.toBoolean(springFinhubAgentEnabled);
        }

        if (isFinhubAgentEnabled) {
            attachFinhubAgent(environment);
        }
    }

    private void onContextClosedEvent(ContextClosedEvent event) {
    }

    private void onApplicationFailedEvent(ApplicationFailedEvent event) {
    }

    private void attachFinhubAgent(ConfigurableEnvironment environment) {
        String finhubAgentPath = DEFAULT_FINHUB_AGENT_PATH;

        String springFinhubAgentPath = environment.getProperty(SPRING_FINHUB_AGENT_PATH_PROPERTY);
        if (isNotBlank(springFinhubAgentPath)) {
            finhubAgentPath = springFinhubAgentPath;
        }

        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split(S_AT)[0];
        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(finhubAgentPath);
            vm.detach();
            log.info("Load attach finhub agent success. pid={}", pid);
        } catch (IOException | AgentLoadException | AttachNotSupportedException | AgentInitializationException e) {
            log.warn("Load attach fihub agent fail. pid={}", pid, e);
        }
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}

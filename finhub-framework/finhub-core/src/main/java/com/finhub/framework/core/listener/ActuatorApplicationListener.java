package com.finhub.framework.core.listener;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.actuator.constants.ActuatorConfigMappingEnum;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author : liuwei
 * @date : 2021/12/21
 * @desc : 探活工具环境变量监听器，默认关闭所有中间件
 */
@Slf4j
public class ActuatorApplicationListener implements ApplicationListener<ApplicationEvent>, Ordered {

    private static final String ACTUATOR_PROPERTY_SOURCE_NAME = "actuator";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (!(event instanceof ApplicationEnvironmentPreparedEvent)) {
            return;
        }
        this.prepareActuatorEnvironment(((ApplicationEnvironmentPreparedEvent) event).getEnvironment());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 准备探活检测各类开关配置，默认关闭所有中间件探活
     *
     * @param environment   当前运行环境
     */
    private void prepareActuatorEnvironment(final ConfigurableEnvironment environment) {
        if (Func.isNull(environment)) {
            return;
        }
        ActuatorConfigMappingEnum[] configMappingEnums = ActuatorConfigMappingEnum.values();
        if (ArrayUtil.isEmpty(configMappingEnums)) {
            return;
        }
        Properties properties = new Properties();
        for (ActuatorConfigMappingEnum configMappingEnum : configMappingEnums) {
            if (Func.isBlank(configMappingEnum.getMetaName())
                || environment.containsProperty(configMappingEnum.getMetaName())) {
                // user has bean config key property, continue
                continue;
            }
            // user has not config key property, check health key default false
            properties.setProperty(configMappingEnum.getMetaName(), String.valueOf(configMappingEnum.isEnabled()));
        }

        if (Func.isEmpty(properties)) {
            return;
        }

        environment.getPropertySources().addLast(
            new PropertiesPropertySource(ACTUATOR_PROPERTY_SOURCE_NAME, properties));
    }

}

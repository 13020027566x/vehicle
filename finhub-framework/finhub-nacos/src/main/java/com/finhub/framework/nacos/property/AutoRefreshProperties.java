package com.finhub.framework.nacos.property;


import cn.hutool.core.util.BooleanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 自动刷新通用配置类
 * refresh:
 *     config:
 *         a: false => getValue("a")
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@NacosConfigurationProperties(prefix = "refresh", dataId = "${nacos.config.refresh-data-id}", groupId = "${nacos.config.group}", type = ConfigType.YAML, autoRefreshed = true)
public class AutoRefreshProperties {
    // 添加 Logger
    private static final Logger log = LoggerFactory.getLogger(AutoRefreshProperties.class);

    public static AutoRefreshProperties me() {
        try {
            return SpringUtil.getBean(AutoRefreshProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new AutoRefreshProperties();
        }
    }

    private static final String PROD_DEBUG_ENABLED = "prod-debug-enabled";

    private static final String OPEN_ASPECT_ENABLED = "open-aspect-enabled";

    private static final String SENTINEL_OBSERVER_LOG_KEY = "sentinel-observer-log-enable";

    private Map<String, String> config;

    public String getValue(String key) {
        return getValue(key, null);
    }

    public String getValue(String key, String defaultValue) {
        if (config == null) {
            return defaultValue;
        }
        return config.getOrDefault(key, defaultValue);
    }

    /**
     * 判断当前环境是否为 debug 状态
     *
     * @return
     */
    public boolean isEnvDebug() {
        return BooleanUtil.toBoolean(getValue(PROD_DEBUG_ENABLED));
    }

    /**
     * 是否开启切面日志
     *
     * @return
     */
    public boolean isOpenAspectLog() {
        return BooleanUtil.toBoolean(getValue(OPEN_ASPECT_ENABLED, Boolean.TRUE.toString()));
    }

    /**
     * 是否开启sentinel observer日志
     *
     * @return
     */
    public boolean isOpenSentinelObserverLog() {
        return BooleanUtil.toBoolean(getValue(SENTINEL_OBSERVER_LOG_KEY));
    }
}

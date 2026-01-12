package com.finhub.framework.nacos.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.util.parse.DefaultYamlConfigParse;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * 动态日志配置监听器
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
public class DynamicLoggerConfigListener {

    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logger.";

    private static final String DEFAULT_LEVEL = "info";

    private static final String S_COMMA = ",";

    private static final String S_MIDDLELINE = "-";

    private static final String[] ALLOW_LEVELS = {"OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE", "ALL"};

    private static final Map<String, Appender> CACHED_APPENDERS = Maps.newHashMap();

    @NacosConfigListener(dataId = "${nacos.config.data-id}", groupId = "${nacos.config.group}", type = ConfigType.YAML, timeout = 5000)
    public void onChange(String newLog) throws Exception {
        // <X> 使用 DefaultYamlConfigParse 工具类，解析配置
        Map<String, Object> properties = new DefaultYamlConfigParse().parse(newLog);
        // <Y> 遍历配置集的每个配置项，判断是否是 logging.level 配置项
        for (Object t : properties.keySet()) {
            String key = String.valueOf(t);
            // 如果是 logging.level 配置项，则设置其对应的日志级别
            if (key.startsWith(LOGGER_TAG)) {
                // 获得日志级别
                String [] configs = String.valueOf(properties.getOrDefault(key, DEFAULT_LEVEL)).split(S_COMMA);
                String level = configs[0].trim();
                String appenderName = configs.length == 1 ? StrUtil.EMPTY : configs[1].trim();

                // 设置日志级别到 LoggingSystem 中
                setLogger(key.replace(LOGGER_TAG, StrUtil.EMPTY), level, appenderName);
            }
        }
    }

    /**
     * 设置指定包日志级别
     *
     * @param packageName 包名
     * @return String 日志级别信息
     */
    private Boolean setLogger(String packageName, String level, String appenderName) {
        boolean isAllowed = isAllowed(level);
        if (isAllowed) {
            setLevel(packageName, level, appenderName);
        }
        return isAllowed;
    }

    /**
     * 设置制定包的日志级别
     *
     * @param packageName 包名
     * @param level       日志等级
     */
    private void setLevel(String packageName, String level, String appenderName) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger(packageName);
        logger.setLevel(Level.toLevel(level));
        logger.setAdditive(false);

        if (appenderName.startsWith(S_MIDDLELINE)) {
            String appenderKey = appenderName.replace(S_MIDDLELINE, StrUtil.EMPTY);

            Appender appender = logger.getAppender(appenderKey);

            if (appender != null) {
                CACHED_APPENDERS.put(appenderKey, appender);
                logger.detachAppender(appender);
            }
        } else {
            Appender appender = CACHED_APPENDERS.get(appenderName);

            if (appender == null) {
                appender = getAppender(appenderName);
            }

            if (appender != null && !logger.isAttached(appender)) {
                logger.addAppender(appender);
            }
        }

        log.debug("set logger success. [packageName={}, level={}, appender={}]", packageName, level, appenderName);
    }

    /**
     * 从 Spring 容器中获取 Appender
     * @param appenderName
     * @return
     */
    private Appender getAppender(String appenderName) {
        try {
            return SpringUtil.getBean(appenderName, Appender.class);
        } catch (Exception e) {
            log.warn("Appender get bean fail, return null. [appenderName={}]", appenderName, e);
            return null;
        }
    }

    /**
     * 判断是否是合法的日志级别
     *
     * @param level 日志等级
     * @return boolean
     */
    private boolean isAllowed(String level) {
        return Arrays.asList(ALLOW_LEVELS).contains(level.toUpperCase());
    }
}

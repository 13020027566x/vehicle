package com.finhub.framework.core.environment;

import com.finhub.framework.logback.util.EnvUtils;
import com.finhub.framework.nacos.property.AutoRefreshProperties;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.S_NULL;

/**
 * 环境配置
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/02 15:21
 */
@Data
@Slf4j
public class EnvConfig {

    public static EnvConfig me() {
        try {
            return SpringUtil.getBean(EnvConfig.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new EnvConfig();
        }
    }

    public static Environment environment() {
        try {
            return SpringUtil.getBean(Environment.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return null;
        }
    }

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active:tx-dev}")
    private String profile;

    public String getVehicleVersion() {
        return environment() == null? S_NULL : environment().getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
    }

    /**
     * 判断是否为生产环境
     *
     * @return
     */
    public boolean isProd() {
        return EnvUtils.isProd(profile);
    }

    /**
     * 判断是否为生产环境
     *
     * @return
     */
    public boolean isNotProd() {
        return EnvUtils.isNotProd(profile);
    }

    /**
     * 判断是否为 UAT
     *
     * @return
     */
    public boolean isUat() {
        return EnvUtils.isUat(profile);
    }

    /**
     * 判断是否为 UAT
     *
     * @return
     */
    public boolean isNotUat() {
        return EnvUtils.isNotUat(profile);
    }

    /**
     * 判断是否为 DEV
     *
     * @return
     */
    public boolean isDev() {
        return EnvUtils.isDev(profile);
    }

    /**
     * 判断是否为 DEV
     *
     * @return
     */
    public boolean isNotDev() {
        return EnvUtils.isNotDev(profile);
    }

    /**
     * 判断是否为 FAT
     *
     * @return
     */
    public boolean isFat() {
        return EnvUtils.isFat(profile);
    }

    /**
     * 判断是否为 FAT
     *
     * @return
     */
    public boolean isNotFat() {
        return EnvUtils.isNotFat(profile);
    }

    /**
     * 判断当前环境是否为 debug 状态
     *
     * @return
     */
    public boolean isDebug() {
        return AutoRefreshProperties.me().isEnvDebug();
    }

    public boolean isNotDebug() {
        return !isDebug();
    }

    public boolean isOpenAspectLog() {
        return AutoRefreshProperties.me().isOpenAspectLog();
    }

    public boolean isNotOpenAspectLog() {
        return !isOpenAspectLog();
    }
}

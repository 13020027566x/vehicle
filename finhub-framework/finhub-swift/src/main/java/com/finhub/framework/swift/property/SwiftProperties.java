package com.finhub.framework.swift.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swift 配置
 * @author : liuwei
 * @date : 2021/12/8
 * @desc :
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.swift")
public class SwiftProperties {

    public static SwiftProperties me() {
        try {
            return SpringUtil.getBean(SwiftProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new SwiftProperties();
        }
    }

    private boolean decodeParameter;

    public boolean isNotDecodeParameter() {
        return !decodeParameter;
    }

}

package com.finhub.framework.core.json;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JSON 配置类
 *
 * @author Mickey
 * @version 1.0
 * @since 2022/09/06 21:34
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.json")
public class JsonProperties {

    public static JsonProperties me() {
        try {
            return SpringUtil.getBean(JsonProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new JsonProperties();
        }
    }

    /**
     * 设置序列化忽略类型
     * 通过该方法对 mapper 对象进行设置，所有序列化的对象都将按改规则进行系列化
     * Include.Include.ALWAYS
     * Include.NON_DEFAULT 属性为默认值不序列化
     * Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的 json 是没有这个字段的。这样对移动端会更省流量
     * Include.NON_NULL 属性为NULL 不序列化，默认
     */
    private String serializationInclusion = "NON_NULL";

    /**
     * 解决前端在长度大于 17 位时会出现精度丢失的问题，默认为 false
     */
    private boolean front17Compatible = false;

    public JsonInclude.Include getJsonInclude() {
        if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.NON_NULL.name())) {
            return JsonInclude.Include.NON_NULL;
        } else if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.NON_ABSENT.name())) {
            return JsonInclude.Include.NON_ABSENT;
        } else if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.NON_EMPTY.name())) {
            return JsonInclude.Include.NON_EMPTY;
        } else if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.NON_DEFAULT.name())) {
            return JsonInclude.Include.NON_DEFAULT;
        } else if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.CUSTOM.name())) {
            return JsonInclude.Include.CUSTOM;
        } else if (serializationInclusion.equalsIgnoreCase(JsonInclude.Include.USE_DEFAULTS.name())) {
            return JsonInclude.Include.USE_DEFAULTS;
        } else {
            return JsonInclude.Include.ALWAYS;
        }
    }
}

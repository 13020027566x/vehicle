package com.finhub.framework.core.json;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES;
import static com.fasterxml.jackson.core.json.JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.finhub.framework.core.date.DateConstants.NORM_DATETIME_FORMAT;

/**
 * JSON 工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2022/09/06 21:34
 */
@Data
@Slf4j
public class JsonUtils {

    private ObjectMapper defaultMapper;

    private ObjectMapper supportNullValueMapper;

    private ObjectMapper snakeMapper;

    private ObjectMapper snakeSupportNullValueMapper;

    public static JsonUtils me() {
        try {
            return SpringUtil.getBean(JsonUtils.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);

            JsonProperties jsonProperties = JsonProperties.me();
            return getInstance(jsonProperties);
        }
    }

    /**
     * 创建 JsonUtils 实例
     *
     * @param jsonProperties
     * @return
     */
    public static JsonUtils getInstance(JsonProperties jsonProperties) {
        JsonUtils jsonUtils = new JsonUtils();
        jsonUtils.setDefaultMapper(setObjectMapperConfigure(null, null, jsonProperties));
        jsonUtils.setSupportNullValueMapper(
            setObjectMapperConfigure(null, JsonInclude.Include.ALWAYS, jsonProperties));

        ObjectMapper snakeMapper = setObjectMapperConfigure(null, null, jsonProperties);
        snakeMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jsonUtils.setSnakeMapper(snakeMapper);

        ObjectMapper snakeSupportNullValueMapper =
            setObjectMapperConfigure(null, JsonInclude.Include.ALWAYS, jsonProperties);
        snakeSupportNullValueMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        jsonUtils.setSnakeSupportNullValueMapper(snakeSupportNullValueMapper);
        return jsonUtils;
    }

    /**
     * set objectMapper common properties
     *
     * @param objectMapper init objectMapper
     * @param jsonProperties   JsonConfig instance
     */
    public static ObjectMapper setObjectMapperConfigure(ObjectMapper objectMapper, JsonInclude.Include include,
        JsonProperties jsonProperties) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        // 设置序列化忽略项
        objectMapper.setSerializationInclusion(include == null ? jsonProperties.getJsonInclude() : include);
        // 在序列化时，空的POJO类不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 在序列化时，当出现 Java 类中未知的属性时不报错，而是忽略此 JSON 字段
        objectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);

        // 在反序列化时，设置为false，表示： 在遇到未知属性的时候不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 在反序列化时，遇到类名错误或者map中id找不到时是否报异常。默认为true
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        // 在反序列化时，遇到无法解析的对象id不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        // 在反序列化时，默认情况下启用功能，以便在子类型属性丢失时抛出异常，关闭
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY, false);

        // 允许出现特殊字符和转义符
        objectMapper.configure(ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),
            ALLOW_UNESCAPED_CONTROL_CHARS.enabledByDefault());
        // 允许出现单引号
        objectMapper.configure(ALLOW_SINGLE_QUOTES, true);
        // 关闭默认日期将转换为Timestamp
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);

        // 设置时区
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.setDateFormat(NORM_DATETIME_FORMAT);

        if (jsonProperties.isFront17Compatible()) {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            objectMapper.registerModule(simpleModule);
        }

        return objectMapper;
    }

    /**
     * object convert json string
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return toJson(obj, false);
    }

    /**
     * object convert snake json string
     * Naming convention used in languages like C, where words are in lower-case
     *
     * @param obj
     * @return
     */
    public static String toJsonSnake(Object obj) {
        return toJson(obj, false, true);
    }

    /**
     * object convert json string
     *
     * @param obj
     * @param supportNullValue true : output json support null value; false : output json ignore null value
     * @return
     */
    public static String toJson(Object obj, boolean supportNullValue) {
        return toJson(obj, supportNullValue, false);
    }

    /**
     * object convert json string
     *
     * @param obj
     * @param supportNullValue true : output json support null value; false : output json ignore null value
     * @param snake            true : Naming convention used in languages like C, where words are in lower-case
     * @return
     */
    public static String toJson(Object obj, boolean supportNullValue, boolean snake) {
        if (obj == null) {
            return null;
        }
        try {
            if (snake) {
                if (supportNullValue) {
                    return me().getSnakeSupportNullValueMapper().writeValueAsString(obj);
                }
                return me().getSnakeMapper().writeValueAsString(obj);
            }

            if (supportNullValue) {
                return me().getSupportNullValueMapper().writeValueAsString(obj);
            }
            return me().getDefaultMapper().writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("[writeValueAsString]：" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * json string parse to Object
     *
     * @param json  json string
     * @param clazz target Object
     * @param <T>   target Object type
     * @return
     */
    public static <T> T toObj(String json, Class<T> clazz) {
        return toObj(json, clazz, false);
    }

    /**
     * snake json string parse to Object
     *
     * @param json  json string
     * @param clazz target Object
     * @param <T>   target Object type
     * @return
     */
    public static <T> T toObjSnake(String json, Class<T> clazz) {
        return toObj(json, clazz, true);
    }

    /**
     * json string parse to Object
     *
     * @param json  json string
     * @param clazz target Object
     * @param snake Naming convention used in languages like C, where words are in lower-case
     * @param <T>   target Object type
     * @return
     */
    public static <T> T toObj(String json, Class<T> clazz, boolean snake) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            if (snake) {
                return me().getSnakeMapper().readValue(json, clazz);
            }
            return me().getDefaultMapper().readValue(json, clazz);
        } catch (IOException e) {
            log.warn("[readValue]：" + e.getMessage(), e);
        }
        return null;

    }

    /**
     * json string parse to object by reference type
     *
     * @param json    json string
     * @param typeRef reference type
     * @param <T>     target
     * @return
     */
    public static <T> T toObj(String json, TypeReference<T> typeRef) {
        return toObj(json, typeRef, false);
    }

    /**
     * json string parse to object by reference type
     *
     * @param json    json string
     * @param typeRef reference type
     * @param <T>
     * @return
     */
    public static <T> T toObjSnake(String json, TypeReference<T> typeRef) {
        return toObj(json, typeRef, true);
    }

    /**
     * json string parse to object by reference type
     *
     * @param json    json string
     * @param typeRef reference type
     * @param snake   Naming convention used in languages like C, where words are in lower-case
     * @param <T>     target
     * @return
     */
    public static <T> T toObj(String json, TypeReference<T> typeRef, boolean snake) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            if (snake) {
                return me().getSnakeMapper().readValue(json, typeRef);
            }
            return me().getDefaultMapper().readValue(json, typeRef);
        } catch (IOException e) {
            log.warn("[readValue]：" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param json
     * @param collectionClass
     * @param elementClass
     * @param <T>
     * @return
     */
    public static <T> T toObj(String json, Class<? extends Collection> collectionClass, Class<?> elementClass) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType =
                me().getDefaultMapper().getTypeFactory().constructCollectionType(collectionClass, elementClass);
            return me().getDefaultMapper().readValue(json, javaType);
        } catch (IOException e) {
            log.warn("[toObj]" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param json
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @param <T>
     * @return
     */
    public static <T> T toObj(String json, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            JavaType javaType =
                me().getDefaultMapper().getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
            return me().getDefaultMapper().readValue(json, javaType);
        } catch (IOException e) {
            log.warn("[toObj]" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * format json
     *
     * @param json target json string
     * @return
     */
    public static String formatJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            Object obj = me().getDefaultMapper().readValue(json, Object.class);
            return me().getDefaultMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("[formatJson]：" + e.getMessage(), e);
        }
        return json;
    }
}

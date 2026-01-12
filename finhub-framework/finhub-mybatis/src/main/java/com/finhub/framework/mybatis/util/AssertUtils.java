package com.finhub.framework.mybatis.util;

import com.finhub.framework.core.environment.EnvConfig;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * 断言类（扩展）
 *
 * @author Mickey
 * @since 2023-07-11
 */
@Slf4j
public final class AssertUtils {


    /**
     * 根据当前环境，判断抛出异常阻断执行 还是打印 Error 日志告警
     *
     * @param message
     * @param params
     */
    public static void throwMybatisPlusException(String message, Object... params) {
        if (EnvConfig.me().isProd()) {
            log.error("{}. [profile={}, execute=success]", message, EnvConfig.me().getProfile());
        } else {
            throw ExceptionUtils.mpe(message, params);
        }
    }

    /**
     * 断言这个 boolean 为 true
     * <p>为 false 则抛出异常</p>
     *
     * @param expression boolean 值
     * @param message    消息
     */
    public static void isTrue(boolean expression, String message, Object... params) {
        if (!expression) {
            throwMybatisPlusException(message, params);
        }
    }

    /**
     * 断言这个 boolean 为 false
     * <p>为 true 则抛出异常</p>
     *
     * @param expression boolean 值
     * @param message    消息
     */
    public static void isFalse(boolean expression, String message, Object... params) {
        isTrue(!expression, message, params);
    }

    /**
     * 断言这个 object 为 null
     * <p>不为 null 则抛异常</p>
     *
     * @param object  对象
     * @param message 消息
     */
    public static void isNull(Object object, String message, Object... params) {
        isTrue(object == null, message, params);
    }

    /**
     * 断言这个 object 不为 null
     * <p>为 null 则抛异常</p>
     *
     * @param object  对象
     * @param message 消息
     */
    public static void notNull(Object object, String message, Object... params) {
        isTrue(object != null, message, params);
    }

    /**
     * 断言这个 value 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param value   字符串
     * @param message 消息
     */
    public static void notEmpty(String value, String message, Object... params) {
        isTrue(StringUtils.isNotBlank(value), message, params);
    }

    /**
     * 断言这个 collection 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param collection 集合
     * @param message    消息
     */
    public static void notEmpty(Collection<?> collection, String message, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(collection), message, params);
    }

    /**
     * 断言这个 map 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param map     集合
     * @param message 消息
     */
    public static void notEmpty(Map<?, ?> map, String message, Object... params) {
        isTrue(CollectionUtils.isNotEmpty(map), message, params);
    }

    /**
     * 断言这个 数组 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param array   数组
     * @param message 消息
     */
    public static void notEmpty(Object[] array, String message, Object... params) {
        isTrue(ArrayUtils.isNotEmpty(array), message, params);
    }
}

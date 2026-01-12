package com.finhub.framework.logback;

import com.finhub.framework.logback.property.LogProperties;
import com.finhub.framework.logback.util.LogCommonUtils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;

import java.util.Set;

/**
 * @author : liuwei
 * @date : 2022/2/21
 * @desc :  定制化字段绑定日志工具
 */
@Slf4j
@UtilityClass
public class CustomLogger {

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key   阿里云日志字段列名称
     * @param value  对应列名称的value值
     * @param msg   日志内容
     */
    public static void warn(String key, String value, String msg) {
        warn(key, value, msg, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key       阿里云日志字段列名称
     * @param value      对应列名称的value值
     * @param msg       日志模版
     * @param params    参数拼接对象集合
     */
    public static void warn(String key, String value, String msg, Object... params) {
        warn(new LogCommonUtils.LogKeyValue(key, value), msg, null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key           阿里云日志字段列名称
     * @param value          对应列名称的value值
     * @param msg           日志模版
     * @param throwable     执行异常
     */
    public static void warn(String key, String value, String msg, Throwable throwable) {
        warn(new LogCommonUtils.LogKeyValue(key, value), msg, throwable);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValue   阿里云日志字段列 key-value键值对实体
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void warn(LogCommonUtils.LogKeyValue logKeyValue, String msg, Throwable throwable, Object... params) {
        warn(Sets.newHashSet(logKeyValue), msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param params        参数拼接对象集合
     */
    public static void warn(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Object... params) {
        warn(logKeyValues, msg ,null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     */
    public static void warn(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable) {
        warn(logKeyValues, msg, throwable, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void warn(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable, Object... params) {
        execute(LogLevel.WARN, logKeyValues, msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key   阿里云日志字段列名称
     * @param value  对应列名称的value值
     * @param msg   日志内容
     */
    public static void info(String key, String value, String msg) {
        info(key, value, msg, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key       阿里云日志字段列名称
     * @param value      对应列名称的value值
     * @param msg       日志模版
     * @param params    参数拼接对象集合
     */
    public static void info(String key, String value, String msg, Object... params) {
        info(new LogCommonUtils.LogKeyValue(key, value), msg, null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key           阿里云日志字段列名称
     * @param value          对应列名称的value值
     * @param msg           日志模版
     * @param throwable     执行异常
     */
    public static void info(String key, String value, String msg, Throwable throwable) {
        info(new LogCommonUtils.LogKeyValue(key, value), msg, throwable);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValue   阿里云日志字段列 key-value键值对实体
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void info(LogCommonUtils.LogKeyValue logKeyValue, String msg, Throwable throwable, Object... params) {
        info(Sets.newHashSet(logKeyValue), msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param params        参数拼接对象集合
     */
    public static void info(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Object... params) {
        info(logKeyValues, msg ,null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     */
    public static void info(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable) {
        info(logKeyValues, msg, throwable, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void info(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable, Object... params) {
        execute(LogLevel.INFO, logKeyValues, msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key   阿里云日志字段列名称
     * @param value  对应列名称的value值
     * @param msg   日志内容
     */
    public static void debug(String key, String value, String msg) {
        debug(key, value, msg, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key       阿里云日志字段列名称
     * @param value      对应列名称的value值
     * @param msg       日志模版
     * @param params    参数拼接对象集合
     */
    public static void debug(String key, String value, String msg, Object... params) {
        debug(new LogCommonUtils.LogKeyValue(key, value), msg, null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key           阿里云日志字段列名称
     * @param value          对应列名称的value值
     * @param msg           日志模版
     * @param throwable     执行异常
     */
    public static void debug(String key, String value, String msg, Throwable throwable) {
        debug(new LogCommonUtils.LogKeyValue(key, value), msg, throwable);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValue   阿里云日志字段列 key-value键值对实体
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void debug(LogCommonUtils.LogKeyValue logKeyValue, String msg, Throwable throwable, Object... params) {
        debug(Sets.newHashSet(logKeyValue), msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param params        参数拼接对象集合
     */
    public static void debug(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Object... params) {
        debug(logKeyValues, msg ,null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     */
    public static void debug(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable) {
        debug(logKeyValues, msg, throwable, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void debug(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable, Object... params) {
        execute(LogLevel.DEBUG, logKeyValues, msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key   阿里云日志字段列名称
     * @param value  对应列名称的value值
     * @param msg   日志内容
     */
    public static void error(String key, String value, String msg) {
        error(key, value, msg, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key       阿里云日志字段列名称
     * @param value      对应列名称的value值
     * @param msg       日志模版
     * @param params    参数拼接对象集合
     */
    public static void error(String key, String value, String msg, Object... params) {
        error(new LogCommonUtils.LogKeyValue(key, value), msg, null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param key           阿里云日志字段列名称
     * @param value          对应列名称的value值
     * @param msg           日志模版
     * @param throwable     执行异常
     */
    public static void error(String key, String value, String msg, Throwable throwable) {
        error(new LogCommonUtils.LogKeyValue(key, value), msg, throwable);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValue   阿里云日志字段列 key-value键值对实体
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void error(LogCommonUtils.LogKeyValue logKeyValue, String msg, Throwable throwable, Object... params) {
        error(Sets.newHashSet(logKeyValue), msg, throwable, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param params        参数拼接对象集合
     */
    public static void error(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Object... params) {
        error(logKeyValues, msg ,null, params);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     */
    public static void error(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable) {
        error(logKeyValues, msg, throwable, (Object[]) null);
    }

    /**
     * 输出日志&将key定制化字段绑定到该阿里云日志中，显示为单独一列字段。
     *
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体集合
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    public static void error(Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable, Object... params) {
        execute(LogLevel.ERROR, logKeyValues, msg, throwable, params);
    }

    /**
     * 执行日志输出逻辑
     *
     * @param logLevel      日志级别
     * @param logKeyValues   阿里云日志字段列 key-value键值对实体
     * @param msg           日志内容
     * @param throwable     执行异常
     * @param params        参数拼接对象集合
     */
    private static void execute(LogLevel logLevel, Set<LogCommonUtils.LogKeyValue> logKeyValues, String msg, Throwable throwable,
        Object... params) {

        putCustomLogInThreadLocal(logKeyValues);

        doPrintLog(logLevel, msg, throwable, params);
    }

    /**
     * 将定制化列 key-value键值对放入ThreadLocal当前线程缓存里面
     *
     * @param logKeyValues
     */
    private static void putCustomLogInThreadLocal(Set<LogCommonUtils.LogKeyValue> logKeyValues) {
        if (CollUtil.isEmpty(logKeyValues) || !LogProperties.me().isRemote()) {
            return;
        }

        LogMdcHolder.putOnceLog(logKeyValues);
    }

    /**
     * 打印日志
     *
     * @param logLevel  日志级别
     * @param msg       日志内容
     * @param throwable 执行异常
     * @param params    参数拼接对象集合
     */
    private static void doPrintLog(LogLevel logLevel, String msg, Throwable throwable, Object[] params) {

        switch (logLevel) {
            case WARN:
                doWarn(msg, throwable, params);
                break;
            case DEBUG:
                doDebug(msg, throwable, params);
                break;
            case ERROR:
                doError(msg, throwable, params);
                break;
            default:
                doInfo(msg, throwable, params);
                break;
        }

    }

    /**
     * 执行warn日志
     *
     * @param msg       日志内容or模版
     * @param throwable 执行异常
     * @param params    参数拼接对象集合
     */
    private static void doWarn(String msg, Throwable throwable, Object... params) {
        if (throwable == null) {
            log.warn(msg, params);
            return;
        }

        log.warn(msg, throwable);
    }

    /**
     * 执行info日志
     *
     * @param msg       日志内容or模版
     * @param throwable 执行异常
     * @param params    参数拼接对象集合
     */
    private static void doInfo(String msg, Throwable throwable, Object... params) {
        if (throwable == null) {
            log.info(msg, params);
            return;
        }

        log.info(msg, throwable);
    }

    /**
     * 执行debug日志
     *
     * @param msg       日志内容or模版
     * @param throwable 执行异常
     * @param params    参数拼接对象集合
     */
    private static void doDebug(String msg, Throwable throwable, Object... params) {
        if (throwable == null) {
            log.debug(msg, params);
            return;
        }

        log.debug(msg, throwable);
    }

    /**
     * 执行error日志
     *
     * @param msg       日志内容or模版
     * @param throwable 执行异常
     * @param params    参数拼接对象集合
     */
    private static void doError(String msg, Throwable throwable, Object... params) {
        if (throwable == null) {
            log.error(msg, params);
            return;
        }

        log.error(msg, throwable);
    }

}

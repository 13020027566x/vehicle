package com.finhub.framework.logback;

import com.finhub.framework.logback.util.LogCommonUtils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import org.springframework.core.NamedThreadLocal;

import java.util.Set;

/**
 * @author : liuwei
 * @date : 2022/2/18
 * @desc :
 */
@UtilityClass
public class LogMdcHolder {

    private static final String PERMANENT_NAME = "permanent-log";

    private static final String ONCE_NAME = "once-log";

    private static final String TEMP_NAME = "temp-log";

    private static final String BEGIN_TIME = "begin-time";

    /**
     * 永久性定制化字段，表示不会主动清除，直到请求结束，才清除
     */
    private static final NamedThreadLocal<Set<LogCommonUtils.LogKeyValue>> PERMANENT_MDC_HOLDER
        = new NamedThreadLocal<>(PERMANENT_NAME);

    /**
     * 一次性定制化字段，表示输出一次日志后便会清除。
     */
    private static final NamedThreadLocal<Set<LogCommonUtils.LogKeyValue>> ONCE_MDC_HOLDER
        = new NamedThreadLocal<>(ONCE_NAME);

    private static final NamedThreadLocal<Set<LogCommonUtils.LogKeyValue>> TEMP_MDC_HOLDER
        = new NamedThreadLocal<>(TEMP_NAME);

    private static final NamedThreadLocal<Long> BEGIN_TIME_HOLDER = new NamedThreadLocal<>(BEGIN_TIME);

    //------------------------------------------全链路 所有日志带有特殊的定制化字段--------------------------------------

    /**
     * 用于存在永久性定制化日志字段，永久性（指的是第一设置后，后面的日志都会带有该定制话的字段，不会主动清除，知道请求结束才会清除本地缓存）
     *
     * @param logKeyValues
     */
    public static void putPermanentLog(final Set<LogCommonUtils.LogKeyValue> logKeyValues) {
        PERMANENT_MDC_HOLDER.set(logKeyValues);
    }

    /**
     * 追加永久性定制化日志字段
     *
     * @param logKeyValue
     */
    public static void addPermanentLog(final LogCommonUtils.LogKeyValue logKeyValue) {
        if (logKeyValue == null) {
            return;
        }
        Set<LogCommonUtils.LogKeyValue> logKeyValues = getPermanentLog();
        if (CollUtil.isEmpty(logKeyValues)) {
            putPermanentLog(Sets.newHashSet(logKeyValue));
            return;
        }

        if (logKeyValues.contains(logKeyValue)) {
            logKeyValues.remove(logKeyValue);
        }
        logKeyValues.add(logKeyValue);
    }

    /**
     * 获取的永久性定制化日志字段
     */
    public static Set<LogCommonUtils.LogKeyValue> getPermanentLog() {
        return PERMANENT_MDC_HOLDER.get();
    }

    /**
     * 删除永久性定制化日志字段
     */
    public static void removePermanentLog() {
        PERMANENT_MDC_HOLDER.remove();
    }

    //------------------------------------------ 一次性日志带有特殊的定制化字段--------------------------------------

    /**
     * 存储一次性定制话MDC字段
     *
     * @param logKeyValues
     */
    public static void putOnceLog(final Set<LogCommonUtils.LogKeyValue> logKeyValues) {
        ONCE_MDC_HOLDER.set(logKeyValues);
    }

    /**
     * 获取一次性定制话MDC字段
     */
    public static Set<LogCommonUtils.LogKeyValue> getOnceLog() {
        return ONCE_MDC_HOLDER.get();
    }

    /**
     * 删除一次性定制话MDC字段
     */
    public static void removeOnceLog() {
        ONCE_MDC_HOLDER.remove();
    }

    /**
     * 追加一次性定制化日志字段
     *
     * @param logKeyValue
     */
    public static void addOnceLog(final LogCommonUtils.LogKeyValue logKeyValue) {
        if (logKeyValue == null) {
            return;
        }
        Set<LogCommonUtils.LogKeyValue> logKeyValues = getOnceLog();
        if (CollUtil.isEmpty(logKeyValues)) {
            putOnceLog(Sets.newHashSet(logKeyValue));
            return;
        }

        if (logKeyValues.contains(logKeyValue)) {
            logKeyValues.remove(logKeyValue);
        }
        logKeyValues.add(logKeyValue);
    }

    /**
     * 存储临时定制话MDC字段
     *
     * @param logKeyValues
     */
    public static void putTempLog(final Set<LogCommonUtils.LogKeyValue> logKeyValues) {
        TEMP_MDC_HOLDER.set(logKeyValues);
    }

    /**
     * 获取临时定制话MDC字段
     */
    public static Set<LogCommonUtils.LogKeyValue> getTempLog() {
        return TEMP_MDC_HOLDER.get();
    }

    /**
     * 删除临时定制话MDC字段
     */
    public static void removeTempLog() {
        TEMP_MDC_HOLDER.remove();
    }

    /**
     * 追加临时定制化日志字段
     *
     * @param logKeyValue
     */
    public static void addTempLog(final LogCommonUtils.LogKeyValue logKeyValue) {
        if (logKeyValue == null) {
            return;
        }
        Set<LogCommonUtils.LogKeyValue> logKeyValues = getTempLog();
        if (CollUtil.isEmpty(logKeyValues)) {
            putTempLog(Sets.newHashSet(logKeyValue));
            return;
        }

        if (logKeyValues.contains(logKeyValue)) {
            logKeyValues.remove(logKeyValue);
        }
        logKeyValues.add(logKeyValue);
    }

    /**
     * 存储起始时间戳
     *
     * @param beginTime
     */
    public static void putBeginTime(final Long beginTime) {
        BEGIN_TIME_HOLDER.set(beginTime);
    }

    /**
     * 获取起始时间戳
     */
    public static Long getBeginTime() {
        return BEGIN_TIME_HOLDER.get();
    }

    /**
     * 删除起始时间戳
     */
    public static void removeBeginTime() {
        BEGIN_TIME_HOLDER.remove();
    }

    /**
     * 清除所有持有mdc-holder线程信息
     */
    public static void clear() {
        removeBeginTime();
        removeTempLog();
        removeOnceLog();
        removePermanentLog();
    }
}

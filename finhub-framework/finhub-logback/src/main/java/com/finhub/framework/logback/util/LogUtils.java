package com.finhub.framework.logback.util;

import com.finhub.framework.logback.constant.LogConstants;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;

import java.util.Map;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_CAMEL_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.RANDOM_REQUEST_ID_LENGTH;

/**
 * LogUtils
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@UtilityClass
public class LogUtils {

    public static String getContextTraceId(boolean isPutMdc) {
        String traceId = TraceContext.traceId();

        if (isPutMdc) {
            putMdcTraceId(traceId);
        }

        return traceId;
    }

    public static String getMdcTraceId() {
        return MDC.get(MDC_KEY_TRACE_ID);
    }

    public static String getTraceId() {
        return getContextTraceId(false);
    }

    public static void putMdcTraceId(String traceId) {
        MDC.put(MDC_KEY_TRACE_ID, traceId);
    }

    public static String fillTraceId(String traceId) {
        if (isBlank(traceId)) {
            // 如果MDC中已经有traceId，则直接返回
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            if (MapUtil.isNotEmpty(copyOfContextMap) && copyOfContextMap.containsKey(MDC_KEY_TRACE_ID)) {
                return copyOfContextMap.get(MDC_KEY_TRACE_ID);
            }
            // 否则，生成一个新的traceId，更新到MDC中
            return getContextTraceId(true);
        }
        // 为了保持链路一致性，直接将traceId放入MDC中
        putMdcTraceId(traceId);
        return traceId;
    }

    public static String getNewRequestId(boolean isPutMdc) {
        String requestId = generateRequestId();

        if (isPutMdc) {
            putMdcRequestId(requestId);
        }

        return requestId;
    }

    public static String getMdcRequestId() {
        return MDC.get(MDC_KEY_REQUEST_ID);
    }

    public static String getRequestId() {
        String mdcRequestId = getMdcRequestId();

        if (isBlank(mdcRequestId)) {
            return getNewRequestId(false);
        }

        return mdcRequestId;
    }

    public static void putMdcRequestId(String requestId) {
        MDC.put(MDC_KEY_REQUEST_ID, requestId);
        MDC.put(MDC_KEY_CAMEL_REQUEST_ID, requestId);
    }

    public static String fillRequestId(String requestId) {
        if (isBlank(requestId)) {
            // 如果MDC中已经有requestId，则直接返回
            Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
            if (MapUtil.isNotEmpty(copyOfContextMap) && copyOfContextMap.containsKey(MDC_KEY_REQUEST_ID) && copyOfContextMap.containsKey(MDC_KEY_CAMEL_REQUEST_ID)) {
                return copyOfContextMap.get(MDC_KEY_REQUEST_ID);
            }
            // 否则，生成一个新的requestId，更新到MDC中
            return getNewRequestId(true);
        }
        // 为了保持链路一致性，直接将requestId放入MDC中
        putMdcRequestId(requestId);
        return requestId;
    }

    /**
     * 从MDC中获取当前基地车版本
     *
     * @return
     */
    public static String getVehicleVersion() {
        return MDC.get(LogConstants.MDC_KEY_TO_VEHICLE);
    }

    public static String generateRequestId() {
        return RandomUtil.randomString(RANDOM_REQUEST_ID_LENGTH);
    }

    public static void clear() {
        MDC.clear();
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void debug(String message, Throwable t) {
        log.debug(message, t);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String message, Throwable t) {
        log.info(message, t);
    }

    public static void warn(String message) {
        log.warn(message);
    }

    public static void warn(String message, Throwable t) {
        log.warn(message, t);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable t) {
        log.error(message, t);
    }

    public static boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public static boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

}

package com.finhub.framework.logback.util;

import com.finhub.framework.logback.entity.LogCommonProperty;
import com.finhub.framework.logback.property.LogProperties;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.opentracing.ActiveSpan;
import io.opentracing.Tracer;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.slf4j.MDC;
import org.springframework.boot.logging.LogLevel;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.C_BRACKET_END;
import static com.finhub.framework.logback.constant.LogConstants.C_BRACKET_START;
import static com.finhub.framework.logback.constant.LogConstants.C_COMMA;
import static com.finhub.framework.logback.constant.LogConstants.C_SPACE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_COST;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_METHOD;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_PARAMETER;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_RESULT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_SUCCESS;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_THROWABLE;
import static com.finhub.framework.logback.constant.LogConstants.S_COLON;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY_ARRAY;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY_JSON;
import static com.finhub.framework.logback.constant.LogConstants.S_ENTER;
import static com.finhub.framework.logback.constant.LogConstants.S_LAMBDA_TYPE;
import static com.finhub.framework.logback.constant.LogConstants.S_PRINT_FBT_PACKAGE_PREFIX;
import static com.finhub.framework.logback.constant.LogConstants.S_PRINT_FINHUB_PACKAGE_PREFIX;
import static com.finhub.framework.logback.constant.LogConstants.S_SERVLET_REQUEST_PARAMETER;
import static com.finhub.framework.logback.constant.LogConstants.S_SERVLET_RESPONSE_PARAMETER;
import static com.finhub.framework.logback.constant.LogConstants.S_SPACE;
import static org.springframework.boot.logging.LogLevel.INFO;

/**
 * @author : liuwei
 * @date : 2021/11/8
 * @desc :
 */
@Slf4j
@UtilityClass
public final class LogCommonUtils {

    private static final String LOG_MARK_BEGIN = "###BEGIN###";

    private static final String LOG_MARK_END = "###END###";

    private static final String LOG_MARK_ERROR = "###ERROR###";

    private static final Set<String> JSON_PACKAGE_PREFIXES_CACHE = new ConcurrentHashSet<>(2048);

    public static final String CONTROLLER_LOG_MODULE = "Controller";

    public static final String DUBBO_PROVIDER_LOG_MODULE = "dubbo provider";

    public static final String DUBBO_CONSUMER_LOG_MODULE = "dubbo consumer";

    public static final String DATASOURCE_ROUTING_ASPECT_LOG_MODULE = "DATASOURCE ROUTING REPORT";

    private static boolean checkPrintLogEnable(final String module) {
        if (isBlank(module)) {
            return false;
        }

        if (CONTROLLER_LOG_MODULE.equals(module)) {
            return LogProperties.me().isControllerLogEnable();
        }

        if (DUBBO_PROVIDER_LOG_MODULE.equals(module)) {
            return LogProperties.me().isDubboProviderLogEnable();
        }

        if (DUBBO_CONSUMER_LOG_MODULE.equals(module)) {
            return LogProperties.me().isDubboConsumerLogEnable();
        }

        if (DATASOURCE_ROUTING_ASPECT_LOG_MODULE.equals(module)) {
            return LogProperties.me().isDatasourceRoutingAspectLogEnable();
        }

        return true;
    }

    /**
     * 处理入参日志
     *
     * @param module       模式
     * @param logLevel     日志级别
     * @param logKeyValues 日志内容
     */
    public static void processInputServerLog(String module, LogLevel logLevel, LogKeyValue... logKeyValues) {
        if (checkPrintLogEnable(module)) {
            doProcessServerLog(module, LOG_MARK_BEGIN, logLevel, logKeyValues);
        }
    }

    /**
     * 处理 Error 日志
     *
     * @param module       模式
     * @param logLevel     日志级别
     * @param logKeyValues 日志内容
     */
    public static void processErrorServerLog(String module, LogLevel logLevel, LogKeyValue... logKeyValues) {
        doProcessServerLog(module, LOG_MARK_ERROR, logLevel, logKeyValues);
    }

    /**
     * 处理出参日志
     *
     * @param module       模式
     * @param logLevel     日志级别
     * @param logKeyValues 日志内容
     */
    public static void processOutputServerLog(String module, LogLevel logLevel, LogKeyValue... logKeyValues) {
        if (checkPrintLogEnable(module)) {
            doProcessServerLog(module, LOG_MARK_END, logLevel, logKeyValues);
        }
    }

    /**
     * 处理日志
     *
     * @param module       模式
     * @param logLevel     日志级别
     * @param logKeyValues 日志内容
     */
    private static void doProcessServerLog(String module, String logMark, LogLevel logLevel,
        LogKeyValue... logKeyValues) {
        if (ArrayUtil.isEmpty(logKeyValues)) {
            return;
        }

        logLevel = logLevel == null ? INFO : logLevel;
        module = isBlank(module) ? S_EMPTY : module;

        try {
            String appendStr;

            if (LogProperties.me().isRemote()) {
                putMdcLogger(logKeyValues);
                appendStr = appendString(module, S_SPACE, logMark);
            } else {
                appendStr = appendString(module, S_SPACE, logMark, printLocalLogKeyValuesText(logKeyValues));
            }

            doPrintLog(appendStr, logLevel);
        } catch (Exception e) {
            log.warn("handle common log print fail : ", e);
        } finally {
            if (LogProperties.me().isRemote()) {
                removeMdcLogger(logKeyValues);
            }
        }
    }

    /**
     * 拼接本地文件日志内容
     *
     * @param logKeyValues 日志内容
     * @return 日志内容
     */
    private static String printLocalLogKeyValuesText(LogKeyValue... logKeyValues) {
        if (ArrayUtil.isEmpty(logKeyValues)) {
            return S_EMPTY;
        }

        StringBuilder builder = new StringBuilder(S_ENTER);
        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            builder.append(S_SPACE).append(S_SPACE).append(S_SPACE).append(S_SPACE);

            builder.append(LogUtils.getTraceId())
                .append(S_SPACE)
                .append(C_BRACKET_START).append(LogUtils.getRequestId()).append(C_BRACKET_END);

            builder.append(logKeyValue.getKey())
                .append(S_SPACE)
                .append(S_COLON)
                .append(S_SPACE)
                .append(logKeyValue.getMsg())
                .append(S_ENTER);
        }
        return builder.toString();
    }

    /**
     * 构建默认的入参内容
     *
     * @param logCommonProperty 日志参数
     * @param logKeyValues      扩展MDC内容
     * @return 最终输出日志内容
     */
    @SuppressWarnings({"java:S1168"})
    public static LogKeyValue[] defaultInputLogKeyValues(final LogCommonProperty logCommonProperty,
        LogKeyValue... logKeyValues) {

        if (logCommonProperty == null) {
            return null;
        }

        LogKeyValue method = new LogKeyValue(MDC_KEY_METHOD, logCommonProperty.getFullClassMethod());
        LogKeyValue parameter = new LogKeyValue(MDC_KEY_PARAMETER, logCommonProperty.getArgsJson());

        boolean customLogEmpty = ArrayUtil.isEmpty(logKeyValues);
        LogKeyValue[] result = new LogKeyValue[customLogEmpty ? 2 : 2 + logKeyValues.length];

        int index = 0;
        result[index++] = method;
        result[index++] = parameter;

        if (customLogEmpty) {
            return result;
        }

        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            result[index++] = logKeyValue;
        }

        return result;
    }

    /**
     * 构建默认的 Error 内容
     *
     * @param logCommonProperty 日志参数
     * @param ex                异常内容
     * @param logKeyValues      扩展MDC内容
     * @return 最终输出日志内容
     */
    @SuppressWarnings({"java:S1168"})
    public static LogKeyValue[] defaultErrorLogKeyValues(final LogCommonProperty logCommonProperty, Throwable ex,
        LogKeyValue... logKeyValues) {

        if (logCommonProperty == null) {
            return null;
        }

        LogKeyValue method = new LogKeyValue(MDC_KEY_METHOD, logCommonProperty.getFullClassMethod());
        LogKeyValue parameter = new LogKeyValue(MDC_KEY_PARAMETER, logCommonProperty.getArgsJson());
        LogKeyValue throwable = new LogKeyValue(MDC_LOG_THROWABLE, ExceptionUtil.stacktraceToString(ex));

        boolean customLogEmpty = ArrayUtil.isEmpty(logKeyValues);
        LogKeyValue[] logFileMsgKeyValues = new LogKeyValue[customLogEmpty ? 3 : 3 + logKeyValues.length];

        int index = 0;
        logFileMsgKeyValues[index++] = method;
        logFileMsgKeyValues[index++] = parameter;
        logFileMsgKeyValues[index++] = throwable;

        if (customLogEmpty) {
            return logFileMsgKeyValues;
        }

        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            logFileMsgKeyValues[index++] = logKeyValue;
        }

        return logFileMsgKeyValues;
    }

    /**
     * 构建默认的出参内容
     *
     * @param logCommonProperty 日志参数
     * @param logKeyValues      扩展MDC内容
     * @return 最终输出日志内容
     */
    @SuppressWarnings({"java:S1168"})
    public static LogKeyValue[] defaultOutputLogKeyValues(final LogCommonProperty logCommonProperty,
        LogKeyValue... logKeyValues) {

        if (logCommonProperty == null) {
            return null;
        }

        LogKeyValue method = new LogKeyValue(MDC_KEY_METHOD, logCommonProperty.getFullClassMethod());
        LogKeyValue success = new LogKeyValue(MDC_KEY_SUCCESS, String.valueOf(logCommonProperty.isSuccess()));
        LogKeyValue cost = new LogKeyValue(MDC_KEY_COST, String.valueOf(logCommonProperty.getCostTime()));
        LogKeyValue result = new LogKeyValue(MDC_KEY_RESULT, arrayToString(logCommonProperty.getResult()));

        boolean customLogEmpty = ArrayUtil.isEmpty(logKeyValues);

        LogKeyValue[] logFileMsgKeyValues = new LogKeyValue[customLogEmpty ? 4 : 4 + logKeyValues.length];

        int index = 0;
        logFileMsgKeyValues[index++] = method;
        logFileMsgKeyValues[index++] = success;
        logFileMsgKeyValues[index++] = cost;
        logFileMsgKeyValues[index++] = result;

        if (customLogEmpty) {
            return logFileMsgKeyValues;
        }

        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            logFileMsgKeyValues[index++] = logKeyValue;
        }

        return logFileMsgKeyValues;
    }

    /**
     * 处理sky-walking日志
     * <p>
     *
     * @param logCommonProperty 日志输出属性
     * @param e                 程序异常Exception
     */
    public static void processSkyWalkingLog(final LogCommonProperty logCommonProperty, Throwable e) {
        processSkyWalkingLog(new SkywalkingTracer(), logCommonProperty, e);
    }

    /**
     * 处理sky-walking日志
     * <p>
     *
     * @param logCommonProperty 日志输出属性
     * @param e                 程序异常Exception
     */
    public static void processSkyWalkingLog(final Tracer tracer, final LogCommonProperty logCommonProperty,
        Throwable e) {

        if (logCommonProperty == null || tracer == null) {
            return;
        }

        Map<String, String> logMap = Maps.newHashMap();
        ActiveSpan span = tracer.buildSpan(logCommonProperty.getFullClassMethod()).startActive();

        try {
            String fullClassMethod = logCommonProperty.getFullClassMethod();
            String argsJson = logCommonProperty.getArgsJson();

            // 入参
            int skyWalkingLogMaxLength = LogProperties.me().getSkyWalkingMaxLength();
            logMap.put("event", fullClassMethod);
            logMap.put("args", getPrintLogByLength(argsJson, skyWalkingLogMaxLength));
            if (CharSequenceUtil.isNotBlank(logCommonProperty.getBody())) {
                logMap.put("body", getPrintLogByLength(logCommonProperty.getBody(), skyWalkingLogMaxLength));
            }

            // 异常处理
            boolean success = true;
            if (e != null) {
                success = false;
                String errorName = e.getClass().getName();
                String message = ExceptionUtil.getMessage(e);
                String stacktrace = ExceptionUtil.stacktraceToString(e);
                String causeMessage = ExceptionUtil.getRootCauseMessage(e);
                logMap.put("error", errorName);
                logMap.put("message", message);
                logMap.put("stack", stacktrace);
                logMap.put("root cause", causeMessage);
            }

            // 出参
            logMap.put("success", String.valueOf(success));
            logMap.put("result",
                getPrintLogByLength(arrayToString(logCommonProperty.getResult()), skyWalkingLogMaxLength));
            logMap.put("cost.millis", String.valueOf(logCommonProperty.getCostTime()));
        } catch (Exception exception) {
            log.warn("write sky-walking log error : ", exception);
        } finally {
            if (span != null) {
                span.log(logMap);
                span.close();
            }
        }
    }

    /**
     * 向MDC添加key-value健值对
     *
     * @param logKeyValues DMC内容
     */
    private static void putMdcLogger(LogKeyValue... logKeyValues) {
        if (ArrayUtil.isEmpty(logKeyValues)) {
            return;
        }

        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            MDC.put(logKeyValue.getKey(), logKeyValue.getMsg());
        }
    }

    /**
     * 删除MDC key-value健值对
     *
     * @param logKeyValues DMC内容
     */
    private static void removeMdcLogger(LogKeyValue... logKeyValues) {
        if (ArrayUtil.isEmpty(logKeyValues)) {
            return;
        }

        for (LogKeyValue logKeyValue : logKeyValues) {
            if (logKeyValue == null) {
                continue;
            }

            MDC.remove(logKeyValue.getKey());
        }
    }


    /**
     * 执行输出日志内容
     *
     * @param logText  日志内容
     * @param logLevel 日志级别
     */
    private static void doPrintLog(String logText, LogLevel logLevel) {

        if (logLevel == null) {
            log.info(logText);
            return;
        }

        switch (logLevel) {
            case TRACE:
                log.trace(logText);
                break;
            case DEBUG:
                log.debug(logText);
                break;
            case WARN:
                log.warn(logText);
                break;
            case ERROR:
                log.error(logText);
                break;
            default:
                log.info(logText);
                break;
        }
    }

    /**
     * 追加字符串
     *
     * @param appendLine 追加字符串
     */
    public static String appendString(String... appendLine) {
        if (ArrayUtil.isEmpty(appendLine)) {
            return S_EMPTY;
        }

        StringBuilder builder = new StringBuilder();
        for (String line : appendLine) {
            builder.append(line);
        }

        return builder.toString();
    }

    /**
     * 获取Sky-walking日志，限制500，超过不输出
     *
     * @param log 日志内容
     * @return 最终字符串
     */
    @SuppressWarnings({"java:S3398"})
    private static String getServerRealPrintLog(String log) {
        return getPrintLogByLength(log, LogProperties.me().getServerMaxLength());
    }

    /**
     * 获取 Sky-walking 日志，限制 500，超过不输出
     *
     * @param log       日志内容
     * @param maxLength 最大长度
     * @return 最终日志内容
     */
    private static String getPrintLogByLength(String log, int maxLength) {
        if (isBlank(log)) {
            return S_EMPTY;
        }

        if (log.length() > maxLength) {
            if (LogProperties.me().isTruncatedEnable()) {
                return CharSequenceUtil.sub(log, 0, maxLength);
            } else {
                return String.format("this log is too long to display，more than %s", maxLength);
            }
        }
        return log;
    }

    /**
     * 打印请求参数数组： 拼接成一个字符串
     *
     * @param args 参数集合
     * @param <T>  泛型
     * @return 参数转字符串
     */
    @SafeVarargs
    public static <T> String arrayToString(T... args) {
        if (ArrayUtil.isEmpty(args)) {
            return S_EMPTY_JSON;
        }
        String log;
        try {
            log = doArrayToString(args);
        } catch (Exception e) {
            log = String.format("execute printJoinArgs(args)java.lang.String fail cause:[%s]", e.getMessage());
        }
        return log;
    }

    /**
     * 执行打印请求参数数组： 拼接成一个字符串
     *
     * @param a 数组参数
     * @return 参数转字符串
     */
    @SuppressWarnings({"java:S3776"})
    public static String doArrayToString(Object[] a) {
        if (a == null) {
            return S_EMPTY;
        }

        int iMax = a.length - 1;
        if (iMax == -1) {
            return S_EMPTY_ARRAY;
        }

        StringBuilder b = new StringBuilder();
        b.append(C_BRACKET_START);
        for (int i = 0; ; i++) {
            if (a[i] == null) {
                b.append(S_EMPTY);
            } else if (a[i] instanceof ServletRequest) {
                b.append(S_SERVLET_REQUEST_PARAMETER);
            } else if (a[i] instanceof ServletResponse) {
                b.append(S_SERVLET_RESPONSE_PARAMETER);
            } else if (ClassUtil.isBasicType(a[i].getClass())
                || ClassUtil.isSimpleValueType(a[i].getClass())
                || ClassUtil.isEnum(a[i].getClass())) {
                b.append(a[i]);
            } else if (a[i] instanceof Collection
                || a[i] instanceof Map) {
                b.append(JSON.toJSONString(a[i]));
            } else if (CharSequenceUtil.containsIgnoreCase(a[i].getClass().getTypeName(), S_LAMBDA_TYPE)) {
                b.append(S_EMPTY_JSON);
            } else if (specialPackagePrefix(a[i].getClass().getName())) {
                b.append(JSON.toJSONString(a[i]));
            } else {
                b.append(a[i]);

            }
            if (i == iMax) {
                return b.append(C_BRACKET_END).toString();
            }
            b.append(C_COMMA).append(C_SPACE);
        }
    }

    /**
     * 特殊包路径，标注请求参数用于json转换
     *
     * @param clazzFullName class全名称
     * @return 是否json打印
     */
    private static boolean specialPackagePrefix(String clazzFullName) {

        if (isBlank(clazzFullName)) {
            return false;
        }

        if (JSON_PACKAGE_PREFIXES_CACHE.contains(clazzFullName)) {
            return true;
        }

        if (CharSequenceUtil.startWith(clazzFullName, S_PRINT_FBT_PACKAGE_PREFIX)) {
            JSON_PACKAGE_PREFIXES_CACHE.add(clazzFullName);
            return true;
        }

        if (CharSequenceUtil.startWith(clazzFullName, S_PRINT_FINHUB_PACKAGE_PREFIX)) {
            JSON_PACKAGE_PREFIXES_CACHE.add(clazzFullName);
            return true;
        }

        List<String> prefixes = LogProperties.me().getPackagePrefixes();
        if (CollUtil.isEmpty(prefixes)) {
            return false;
        }

        for (String prefix : prefixes) {
            if (CharSequenceUtil.startWith(clazzFullName, prefix)) {
                JSON_PACKAGE_PREFIXES_CACHE.add(clazzFullName);
                return true;
            }
        }
        return false;
    }

    @Data
    @AllArgsConstructor
    public static class LogKeyValue {

        private String key;

        private String msg;

        @Override
        public String toString() {
            return appendString("{", "\"", key, "\"", ":", "\"", msg, "\"", "}");
        }

        public String getMsg() {
            return getServerRealPrintLog(msg);
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }

            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }

            LogKeyValue that = (LogKeyValue) o;

            return key.equals(that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

}




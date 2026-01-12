package com.finhub.framework.logback.remote;

import com.finhub.framework.logback.LogMdcHolder;
import com.finhub.framework.logback.constant.LogConstants;
import com.finhub.framework.logback.constant.LoghubConfigEnum;
import com.finhub.framework.logback.util.LogCommonUtils;
import com.finhub.framework.logback.util.LogUtils;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.openservices.aliyun.log.producer.LogProducer;
import com.aliyun.openservices.aliyun.log.producer.Producer;
import com.aliyun.openservices.aliyun.log.producer.ProducerConfig;
import com.aliyun.openservices.aliyun.log.producer.ProjectConfig;
import com.aliyun.openservices.aliyun.log.producer.errors.ProducerException;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.Index;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.LogStore;
import com.aliyun.openservices.log.exception.LogException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static cn.hutool.core.text.CharSequenceUtil.equalsIgnoreCase;
import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_PORT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_LEVEL;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_MESSAGE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_TARGET_CLASS;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_THREAD;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_THROWABLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_TIME;
import static com.finhub.framework.logback.constant.LogConstants.RPC_SERVER_PORT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_DEPT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.S_COMMA;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY;
import static com.finhub.framework.logback.constant.LogConstants.TIME_FORMAT;
import static com.finhub.framework.logback.constant.LogConstants.TIME_ZONE;
import static com.finhub.framework.logback.constant.LogConstants.WEB_SERVER_PORT_PROPERTY;

/**
 * LoghubAppender
 *
 * @author liuwei
 * @version 1.0
 * @since 2021/11/30 21:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"java:S106", "java:S112"})
public class LoghubAppender<E> extends UnsynchronizedAppenderBase<E> {

    private static final String SLS_LOG_PROJECT_NOT_EXIST_ERROR_CODE = "ProjectNotExist";

    private static final String SLS_LOG_PROJECT_QUOTA_EXCEED_ERROR_CODE = "ProjectQuotaExceed";

    private static final String SLS_LOG_STORE_NOT_EXIST_ERROR_CODE = "LogstoreNotExist";

    private static final String SLS_LOG_STORE_NOT_EXIST_ERROR_CODE_1 = "LogStoreNotExist";

    private static final String SLS_LOG_STORE_ALREADY_EXIST_ERROR_CODE = "LogstoreAlreadyExist";

    private static final String SLS_LOG_INTERNAL_SERVER_ERROR_CODE = "InternalServerError";

    private static final String SLS_LOG_STORE_INFO_INVALID_ERROR_CODE = "LogStoreInfoInvalid";

    private static final String SLS_LOG_INDEX_INFO_INVALID_ERROR_CODE = "IndexInfoInvalid";

    private static final String ERROR_CODE_KEY = ", errorCode : ";

    private static final String ERROR_STACK_KEY = ", stack : ";

    private boolean test;

    private boolean loggerClassLine;

    private String profile;

    private String project;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String userAgent = "logback";

    protected Encoder<E> encoder;

    protected ProducerConfig producerConfig = new ProducerConfig();

    protected ProjectConfig projectConfig;

    protected Producer producer;

    protected Client client;

    protected String logStore;

    protected int logStoreTtl = 5;

    protected int logStoreShareCount = 1;

    private int logStoreMaxSplitShard = 16;

    protected String topic = "";

    protected String source = "";

    protected String timeZone = TIME_ZONE;

    protected String timeFormat = TIME_FORMAT;

    protected DateTimeFormatter formatter;

    protected java.time.format.DateTimeFormatter formatter1;

    private String mdcFields;

    private String customMdcFields;

    private static Set<String> mdcFieldSet;

    @Override
    public void start() {
        try {
            doStart();
        } catch (Exception e) {
            addError("Failed to start LoghubAppender.", e);
        }
    }

    @Override
    public void append(E eventObject) {
        try {
            appendEvent(eventObject);
        } catch (Exception e) {
            addError("Failed to append event.", e);
        }
    }

    @Override
    @SuppressWarnings({"java:S2142"})
    public void stop() {
        try {
            doStop();
        } catch (Exception e) {
            addError("Failed to stop LoghubAppender.", e);
        }
    }

    /**
     * 初始化远程参数，project、accessKeyId, accessKeySecret;
     */
    private void initRemoteProperties() {
        LoghubConfigEnum loghubConfigEnum = LoghubConfigEnum.getRemoteConfigEnum(profile, test);
        if (loghubConfigEnum == null) {
            throw new IllegalArgumentException(
                String.format("log remote SLS system exception, because get log remote config fail."));
        }
        this.project = StrUtil.blankToDefault(project, loghubConfigEnum.getProject());
        this.accessKeyId = StrUtil.blankToDefault(accessKeyId, loghubConfigEnum.getAccessKeyId());
        this.accessKeySecret = StrUtil.blankToDefault(accessKeySecret, loghubConfigEnum.getAccessKeySecret());
        this.endpoint = StrUtil.blankToDefault(endpoint, loghubConfigEnum.getEndpoint());
    }

    /**
     * 创建/运行阿里云客户端
     */
    private void doStart() {
        initRemoteProperties();
        try {
            formatter = DateTimeFormat.forPattern(timeFormat).withZone(DateTimeZone.forID(timeZone));
        } catch (Exception e) {
            formatter1 = java.time.format.DateTimeFormatter.ofPattern(timeFormat).withZone(ZoneId.of(timeZone));
        }
        client = createClient();
        producer = createProducer();

        try {
            client.GetLogStore(project, logStore);
        } catch (LogException e) {
            if (equalsIgnoreCase(SLS_LOG_PROJECT_NOT_EXIST_ERROR_CODE, e.GetErrorCode())) {
                throw new RuntimeException(e.GetErrorMessage());
            } else if (equalsIgnoreCase(SLS_LOG_STORE_NOT_EXIST_ERROR_CODE, e.GetErrorCode())) {
                this.createLogStore();
            } else {
                System.out.println(
                    LogCommonUtils.appendString("ERROR query log store fail, errorMessage : ", e.GetErrorMessage(),
                        ERROR_CODE_KEY, e.GetErrorCode(),
                        ERROR_STACK_KEY, ExceptionUtil.stacktraceToString(e)));
            }
        }

        super.start();
        getContext().register(this);
        getContext().putObject(this.getClass().getName(), this);

        addInfo("LoghubAppender is started.");
    }

    /**
     * 创建client客户端
     *
     * @return client客户端
     */
    private Client createClient() {
        return new Client(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 创建日志发送方
     *
     * @return 日志发送方
     */
    private Producer createProducer() {
        projectConfig = buildProjectConfig();
        Producer logProducer = new LogProducer(producerConfig);
        logProducer.putProjectConfig(projectConfig);
        return logProducer;
    }

    /**
     * 服务启动检查是否存在logStore，不存在则创建logStore
     */
    private void createLogStore() {
        System.out.println("[Finhub Logback INFO] create log store start");
        LogStore store = new LogStore(logStore, logStoreTtl, logStoreShareCount);
        store.setmMaxSplitShard(logStoreMaxSplitShard);
        store.setmAutoSplit(true);
        try {
            client.CreateLogStore(project, store);
            System.out.println("[Finhub Logback INFO] create log store end");
            this.createLogStoreIndex();
        } catch (LogException e) {
            Set<String> stopCodeSet =
                Sets.newHashSet(SLS_LOG_PROJECT_NOT_EXIST_ERROR_CODE, SLS_LOG_INTERNAL_SERVER_ERROR_CODE,
                    SLS_LOG_STORE_INFO_INVALID_ERROR_CODE, SLS_LOG_PROJECT_QUOTA_EXCEED_ERROR_CODE);
            if (stopCodeSet.contains(e.GetErrorCode())) {
                throw new RuntimeException(e.GetErrorMessage());
            } else if (equalsIgnoreCase(SLS_LOG_STORE_ALREADY_EXIST_ERROR_CODE, e.GetErrorCode())) {
                System.out.println("[Finhub Logback WARN] current server log store already exist");
            } else {
                System.out.println(
                    LogCommonUtils.appendString("create log store fail , errorMessage : ", e.GetErrorMessage(),
                        ERROR_CODE_KEY, e.GetErrorCode(),
                        ERROR_STACK_KEY, ExceptionUtil.stacktraceToString(e)));
            }
        }
    }

    /**
     * 创建logStore后续创建logStore索引
     */
    private void createLogStoreIndex() {
        System.out.println("[Finhub Logback INFO] create log store index start");
        String logStoreIndex =
            "{\"line\":{\"token\":[\",\",\" \",\"'\",\"\\\\\\\"\",\";\",\"=\",\"(\",\")\",\"[\",\"]\",\"{\",\"}\",\"?\",\"@\",\"&\",\"<\",\">\",\"/\",\":\",\"\\\\n\",\"\\\\t\",\"\\\\r\"],\"caseSensitive\":false,\"chn\":true},\"keys\":{"
                + "\"trace_id\":{\"type\":\"text\",\"alias\":\"\"},"
                + "\"lower_trace_id\":{\"type\":\"text\",\"alias\":\"\"},"
                + "\"_cost_\":{\"type\":\"long\",\"alias\":\"\"},"
                + "\"_time_\":{\"type\":\"text\",\"alias\":\"\"},"
                + "\"_level_\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"remote_method\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"remote_success\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_ip\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_ip\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_app\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_app\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_dept\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_dept\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_port\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_port\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_lang\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_lang\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"from_vehicle\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"to_vehicle\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"request_ip\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"request_uri\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"request_method\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"response_status\":{\"type\":\"long\",\"alias\":\"\",\"doc_value\":true},"
                + "\"response_success\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"handle_method\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true},"
                + "\"handle_success\":{\"type\":\"text\",\"alias\":\"\",\"doc_value\":true}"
                + "},\"log_reduce\":false,\"max_text_len\":2048}";

        Index index = new Index();
        try {
            index.FromJsonString(logStoreIndex);
        } catch (LogException e) {
            System.out.println(
                LogCommonUtils.appendString("[Finhub Logback ERROR] create log store index fail, errorMessage : ",
                    e.GetErrorMessage(),
                    ERROR_CODE_KEY, e.GetErrorCode(),
                    ERROR_STACK_KEY, ExceptionUtil.stacktraceToString(e)));
        }
        try {
            client.CreateIndex(project, logStore, index);
            System.out.println("[Finhub Logback INFO] create log store index end");
        } catch (LogException e) {
            Set<String> stopCodeSet =
                Sets.newHashSet(SLS_LOG_PROJECT_NOT_EXIST_ERROR_CODE, SLS_LOG_STORE_NOT_EXIST_ERROR_CODE_1,
                    SLS_LOG_INDEX_INFO_INVALID_ERROR_CODE);
            if (stopCodeSet.contains(e.GetErrorCode())) {
                throw new RuntimeException(e.GetErrorMessage());
            } else {
                System.out.println(
                    LogCommonUtils.appendString("[Finhub Logback ERROR] create log store index fail, errorMessage : ",
                        e.GetErrorMessage(),
                        ERROR_CODE_KEY, e.GetErrorCode(),
                        ERROR_STACK_KEY, ExceptionUtil.stacktraceToString(e)));
            }
        }

    }

    /**
     * 构建projectConfig配置
     *
     * @return projectConfig配置
     */
    private ProjectConfig buildProjectConfig() {
        return new ProjectConfig(project, endpoint, accessKeyId, accessKeySecret, null, userAgent);
    }


    /**
     * 关闭阿里云客户端服务
     *
     * @throws InterruptedException 终端异常
     * @throws ProducerException    发送异常
     */
    private void doStop() throws InterruptedException, ProducerException {
        if (!isStarted()) {
            return;
        }

        super.stop();
        producer.close();

        addInfo("LoghubAppender is stopped.");
    }


    /**
     * 发送日志
     *
     * @param eventObject 日志事件
     */
    @SuppressWarnings({"java:S2142"})
    private void appendEvent(E eventObject) {
        //init Event Object
        if (!(eventObject instanceof LoggingEvent)) {
            return;
        }
        LoggingEvent event = (LoggingEvent) eventObject;

        List<LogItem> logItems = Lists.newArrayList();
        LogItem item = new LogItem();
        logItems.add(item);
        item.SetTime((int) (event.getTimeStamp() / 1000));

        if (formatter != null) {
            DateTime dateTime = new DateTime(event.getTimeStamp());
            item.PushBack(MDC_LOG_TIME, dateTime.toString(formatter));
        } else {
            Instant instant = Instant.ofEpochMilli(event.getTimeStamp());
            item.PushBack(MDC_LOG_TIME, formatter1.format(instant));
        }

        item.PushBack(MDC_LOG_LEVEL, event.getLevel().toString());
        item.PushBack(MDC_LOG_THREAD, event.getThreadName());

        if (loggerClassLine) {
            StackTraceElement[] caller = event.getCallerData();
            if (caller != null && caller.length > 0) {
                item.PushBack(MDC_LOG_TARGET_CLASS, caller[0].toString());
            }
        } else {
            item.PushBack(MDC_LOG_TARGET_CLASS, event.getLoggerName());
        }

        String message = event.getFormattedMessage();
        item.PushBack(MDC_LOG_MESSAGE, message);

        IThrowableProxy iThrowableProxy = event.getThrowableProxy();
        if (iThrowableProxy != null) {
            String throwable = getExceptionInfo(iThrowableProxy);
            throwable += fullDump(event.getThrowableProxy().getStackTraceElementProxyArray());
            item.PushBack(MDC_LOG_THROWABLE, throwable);
        }

        pushMdcPropertiesToLogItem(item, event);
        pushThreadLocalMdcToLogItem(item, LogMdcHolder.getPermanentLog(), false);
        pushThreadLocalMdcToLogItem(item, LogMdcHolder.getOnceLog(), true);

        try {
            producer.send(projectConfig.getProject(), logStore, topic, source, logItems,
                new LoghubSenderCallback<>(this,
                    projectConfig.getProject(), logStore, topic, source, logItems));
        } catch (Exception e) {
            this.addError(
                LogCommonUtils.appendString("Failed to send log, project=", project, ", logStore=", logStore,
                    ", topic=", topic, ", source=", source, ", logItem=", logItems.toString()), e);
        }
    }

    /**
     * 输出定制化MDC，采用当前线程的存储的MDC-key集合。
     *
     * @param item
     * @param logKeyValues
     * @param once
     */
    private void pushThreadLocalMdcToLogItem(LogItem item, Set<LogCommonUtils.LogKeyValue> logKeyValues, boolean once) {
        if (once) {
            LogMdcHolder.removeOnceLog();
        }

        if (CollUtil.isEmpty(logKeyValues)) {
            return;
        }

        for (LogCommonUtils.LogKeyValue logKeyValue : logKeyValues) {
            if (isNotAvailLogKeyValue(logKeyValue)) {
                continue;
            }
            String val = logKeyValue.getMsg();
            item.PushBack(logKeyValue.getKey(), isBlank(val) ? S_EMPTY : val);
        }
    }

    /**
     * 定制化日志实体是否合法
     *
     * @param logKeyValue
     * @return
     */
    private boolean isNotAvailLogKeyValue(LogCommonUtils.LogKeyValue logKeyValue) {
        return logKeyValue == null
            || isBlank(logKeyValue.getKey())
            || isBlank(logKeyValue.getMsg())
            ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 向日志中放入MDC属性值
     *
     * @param item  日志容器
     * @param event 日志事件
     */
    private void pushMdcPropertiesToLogItem(LogItem item, LoggingEvent event) {
        Set<String> mdcFieldSets = getMdcFieldSet();
        if (CollUtil.isEmpty(mdcFieldSets)) {
            String traceId = LogUtils.getTraceId();
            item.PushBack(MDC_KEY_TRACE_ID, isBlank(traceId) ? S_EMPTY : traceId);
            return;
        }

        Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
        if (!mdcPropertyMap.containsKey(MDC_KEY_TRACE_ID)) {
            String traceId = LogUtils.getTraceId();
            item.PushBack(MDC_KEY_TRACE_ID, isBlank(traceId) ? S_EMPTY : traceId);
        }

        for (String key : mdcFieldSets) {
            if (mdcPropertyMap.containsKey(key)) {
                String val = mdcPropertyMap.get(key);
                item.PushBack(key, isBlank(val) ? S_EMPTY : val);
            }
        }

        try {
            // 处理设置默认 TO 信息（当前应用五要素信息）
            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_IP)) {
                String toIp = NetUtil.getLocalhostStr();
                item.PushBack(MDC_KEY_TO_IP, isBlank(toIp) ? S_EMPTY : toIp);
            }

            Environment environment = SpringUtil.getBean(Environment.class);
            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_PORT)) {
                String toPort = environment.getProperty(WEB_SERVER_PORT_PROPERTY);
                if (isBlank(toPort)) {
                    toPort = environment.getProperty(RPC_SERVER_PORT_PROPERTY);
                }
                item.PushBack(MDC_KEY_TO_PORT, isBlank(toPort) ? S_EMPTY : toPort);
            }

            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_APP)) {
                String toApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);
                item.PushBack(MDC_KEY_TO_APP, isBlank(toApp) ? S_EMPTY : toApp);
            }

            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_DEPT)) {
                String toDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);
                item.PushBack(MDC_KEY_TO_DEPT, isBlank(toDept) ? S_EMPTY : toDept);
            }

            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_VEHICLE)) {
                String toVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
                item.PushBack(MDC_KEY_TO_VEHICLE, isBlank(toVehicle) ? S_EMPTY : toVehicle);
            }

            if (!mdcPropertyMap.containsKey(MDC_KEY_TO_LANG)) {
                String toLang = LocaleContextHolder.getLocale().toString();
                item.PushBack(MDC_KEY_TO_LANG, isBlank(toLang) ? S_EMPTY : toLang);
            }
        } catch (Exception e) {
            this.addWarn("Failed to add [TO Info].", e);
        }
    }


    /**
     * 获取MDC字段输出集合
     *
     * @return MDC集合
     */
    @SuppressWarnings({"java:S1168", "java:S2168"})
    public Set<String> getMdcFieldSet() {
        if (isBlank(mdcFields)) {
            return null;
        }
        if (mdcFieldSet == null) {
            synchronized (LoghubAppender.class) {
                if (mdcFieldSet == null) {
                    Set<String> mdcSet = Sets.newHashSet();
                    String[] arr = mdcFields.split(S_COMMA);
                    for (String str : arr) {
                        mdcSet.add(str.trim());
                    }
                    if (CharSequenceUtil.isNotBlank(customMdcFields)) {
                        String[] customMdcFieldsArray = customMdcFields.split(S_COMMA);
                        for (String str : customMdcFieldsArray) {
                            mdcSet.add(str.trim());
                        }
                    }
                    mdcFieldSet = mdcSet;
                }
            }
        }
        return mdcFieldSet;
    }

    /**
     * 异常内容
     *
     * @param iThrowableProxy 异常代理
     * @return 异常信息
     */
    private String getExceptionInfo(IThrowableProxy iThrowableProxy) {
        String s = iThrowableProxy.getClassName();
        String message = iThrowableProxy.getMessage();
        return (message != null) ?
            LogCommonUtils.appendString(s, LogConstants.S_COLON, LogConstants.S_SPACE, message) : s;
    }

    /**
     * 栈数组
     *
     * @param stackTraceElementProxyArray 栈数组
     * @return 当前输出class位置
     */
    private String fullDump(StackTraceElementProxy[] stackTraceElementProxyArray) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElementProxy step : stackTraceElementProxyArray) {
            builder.append(CoreConstants.LINE_SEPARATOR);
            String string = step.toString();
            builder.append(CoreConstants.TAB).append(string);
            ThrowableProxyUtil.subjoinPackagingData(builder, step);
        }
        return builder.toString();
    }

    // 下面这些方法不能删除，启动会反射进行调用 -------------------------------

    public int getTotalSizeInBytes() {
        return producerConfig.getTotalSizeInBytes();
    }

    public void setTotalSizeInBytes(int totalSizeInBytes) {
        producerConfig.setTotalSizeInBytes(totalSizeInBytes);
    }

    public long getMaxBlockMs() {
        return producerConfig.getMaxBlockMs();
    }

    public void setMaxBlockMs(long maxBlockMs) {
        producerConfig.setMaxBlockMs(maxBlockMs);
    }

    public int getIoThreadCount() {
        return producerConfig.getIoThreadCount();
    }

    public void setIoThreadCount(int ioThreadCount) {
        producerConfig.setIoThreadCount(ioThreadCount);
    }

    public int getBatchSizeThresholdInBytes() {
        return producerConfig.getBatchSizeThresholdInBytes();
    }

    public void setBatchSizeThresholdInBytes(int batchSizeThresholdInBytes) {
        producerConfig.setBatchSizeThresholdInBytes(batchSizeThresholdInBytes);
    }

    public int getBatchCountThreshold() {
        return producerConfig.getBatchCountThreshold();
    }

    public void setBatchCountThreshold(int batchCountThreshold) {
        producerConfig.setBatchCountThreshold(batchCountThreshold);
    }

    public int getLingerMs() {
        return producerConfig.getLingerMs();
    }

    public void setLingerMs(int lingerMs) {
        producerConfig.setLingerMs(lingerMs);
    }

    public int getRetries() {
        return producerConfig.getRetries();
    }

    public void setRetries(int retries) {
        producerConfig.setRetries(retries);
    }

    public int getMaxReservedAttempts() {
        return producerConfig.getMaxReservedAttempts();
    }

    public void setMaxReservedAttempts(int maxReservedAttempts) {
        producerConfig.setMaxReservedAttempts(maxReservedAttempts);
    }

    public long getBaseRetryBackoffMs() {
        return producerConfig.getBaseRetryBackoffMs();
    }

    public void setBaseRetryBackoffMs(long baseRetryBackoffMs) {
        producerConfig.setBaseRetryBackoffMs(baseRetryBackoffMs);
    }

    public long getMaxRetryBackoffMs() {
        return producerConfig.getMaxRetryBackoffMs();
    }

    public void setMaxRetryBackoffMs(long maxRetryBackoffMs) {
        producerConfig.setMaxRetryBackoffMs(maxRetryBackoffMs);
    }
    // ---------------------------------------------------------------------------------------------
}

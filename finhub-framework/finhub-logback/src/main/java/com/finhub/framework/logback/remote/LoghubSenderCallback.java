package com.finhub.framework.logback.remote;

import com.finhub.framework.logback.util.LogCommonUtils;

import com.aliyun.openservices.aliyun.log.producer.Callback;
import com.aliyun.openservices.aliyun.log.producer.Result;
import com.aliyun.openservices.log.common.LogItem;

import java.util.List;

/**
 * Loghub Sender Callback
 *
 * @author liuwei
 * @version 1.0
 * @since 2021/11/30 21:34
 */
public class LoghubSenderCallback<E> implements Callback {

    protected LoghubAppender<E> loghubAppender;

    protected String project;

    protected String logstore;

    protected String topic;

    protected String source;

    protected List<LogItem> logItems;

    public LoghubSenderCallback(LoghubAppender<E> loghubAppender, String project, String logstore, String topic,
        String source, List<LogItem> logItems) {
        super();
        this.loghubAppender = loghubAppender;
        this.project = project;
        this.logstore = logstore;
        this.topic = topic;
        this.source = source;
        this.logItems = logItems;
    }

    @Override
    public void onCompletion(Result result) {
        if (!result.isSuccessful()) {
            loghubAppender.addError(
                LogCommonUtils.appendString("Failed to send log, project=", project, ", logStore=", logstore,
                    ", topic=", topic, ", source=", source, ", logItem=", logItems.toString(), ", errorCode=",
                    result.getErrorCode(), ", errorMessage=", result.getErrorMessage()));
        }
    }
}

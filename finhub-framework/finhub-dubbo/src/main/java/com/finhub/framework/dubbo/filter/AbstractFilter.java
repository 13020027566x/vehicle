package com.finhub.framework.dubbo.filter;

import com.finhub.framework.exception.util.ExceptionUtils;
import com.finhub.framework.logback.LogMdcHolder;
import com.finhub.framework.logback.entity.LogCommonProperty;
import com.finhub.framework.logback.util.LogCommonUtils;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;
import org.springframework.boot.logging.LogLevel;
import org.springframework.util.StopWatch;

import static com.finhub.framework.logback.constant.LogConstants.LOG_CLASS_METHOD_TEMPLATE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_COST;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REMOTE_ATTACHMENT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REMOTE_METHOD;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REMOTE_PARAMETER;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REMOTE_RESULT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REMOTE_SUCCESS;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_THROWABLE;
import static com.finhub.framework.logback.util.LogCommonUtils.DUBBO_CONSUMER_LOG_MODULE;
import static com.finhub.framework.logback.util.LogCommonUtils.DUBBO_PROVIDER_LOG_MODULE;

/**
 * @author : liuwei
 * @date : 2021/11/19
 * @desc :
 */
@Slf4j
public abstract class AbstractFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        RpcContext context = RpcContext.getContext();
        boolean providerSide = context.isProviderSide();

        StringBuilder module = new StringBuilder(providerSide ? DUBBO_PROVIDER_LOG_MODULE : DUBBO_CONSUMER_LOG_MODULE);
        LogCommonProperty logProperty = this.buildLogCommonProperty(invocation, module.toString());

        LogCommonUtils.processInputServerLog(module.toString(), LogLevel.INFO,
            this.buildInputLogKeyValues(logProperty, context));


        Result result = null;
        Throwable throwable = null;
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            result = this.doInvoke(invoker, invocation);
            if (result.hasException()) {
                throwable = result.getException();
                LogCommonUtils.processErrorServerLog(module.toString(), ExceptionUtils.getErrorLogLevel(throwable),
                    this.buildErrorLogKeyValues(logProperty, throwable));
            }
            return result;
        } catch (RpcException e) {
            LogCommonUtils.processErrorServerLog(module.toString(), ExceptionUtils.getErrorLogLevel(throwable),
                this.buildErrorLogKeyValues(logProperty, throwable));

            throwable = e;
            throw e;
        } finally {
            stopWatch.stop();

            logProperty.paddingProcessResult(result == null ? null : result.getValue(), stopWatch.getTotalTimeMillis(),
                throwable == null);

            LogCommonUtils.processOutputServerLog(module.toString(), LogLevel.INFO,
                this.buildOutputLogKeyValues(logProperty));

            LogCommonUtils.processSkyWalkingLog(logProperty, throwable);

            if (providerSide) {
                MDC.clear();
                LogMdcHolder.clear();
            }
        }
    }

    private LogCommonUtils.LogKeyValue[] buildErrorLogKeyValues(LogCommonProperty logProperty, Throwable throwable) {
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[2];
        try {
            int index = 0;
            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_METHOD, logProperty.getFullClassMethod());

            logKeyValues[index] =
                new LogCommonUtils.LogKeyValue(MDC_LOG_THROWABLE, ExceptionUtil.stacktraceToString(throwable));
        } catch (Exception e) {
            log.warn("remote filter build error log fail : ", e);
        }
        return logKeyValues;
    }

    private LogCommonUtils.LogKeyValue[] buildOutputLogKeyValues(LogCommonProperty logProperty) {
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[4];
        try {
            int index = 0;
            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_METHOD, logProperty.getFullClassMethod());

            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_SUCCESS, String.valueOf(logProperty.isSuccess()));

            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_RESULT,
                    logProperty.getResult() == null ? null : JSON.toJSONString(logProperty.getResult()));

            logKeyValues[index] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_COST, String.valueOf(logProperty.getCostTime()));
        } catch (Exception e) {
            log.warn("remote filter build output log fail : ", e);
        }
        return logKeyValues;
    }

    private LogCommonUtils.LogKeyValue[] buildInputLogKeyValues(LogCommonProperty logProperty, RpcContext context) {
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[3];
        try {
            int index = 0;
            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_METHOD, logProperty.getFullClassMethod());

            logKeyValues[index++] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_PARAMETER, logProperty.getArgsJson());

            logKeyValues[index] =
                new LogCommonUtils.LogKeyValue(MDC_KEY_REMOTE_ATTACHMENT,
                    JSON.toJSONString(context.getObjectAttachments()));
        } catch (Exception e) {
            log.warn("remote filter build input log fail : ", e);
        }
        return logKeyValues;
    }

    protected LogCommonProperty buildLogCommonProperty(Invocation invocation, String mode) {
        if (invocation == null) {
            return LogCommonProperty.builder().build();
        }
        String interfaceName = invocation.getInvoker().getInterface().getName();
        String simpleName = invocation.getInvoker().getInterface().getSimpleName();
        String methodName = invocation.getMethodName();
        Object[] arguments = invocation.getArguments();

        return LogCommonProperty.builder()
            .mode(mode)
            .className(interfaceName)
            .simpleClassName(simpleName)
            .methodName(methodName)
            .args(arguments)
            .argsJson(LogCommonUtils.arrayToString(arguments))
            .fullClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, interfaceName, methodName))
            .simpleClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, simpleName, methodName))
            .build();
    }

    /**
     * 调用服务提供者方法/调用消费者方法
     *
     * @param invoker    Invoker. (API/SPI, Prototype, ThreadSafe)
     * @param invocation Invocation. (API, Prototype, NonThreadSafe)
     * @return Result
     * @throws RpcException rpcException
     */
    public abstract Result doInvoke(Invoker<?> invoker, Invocation invocation);
}

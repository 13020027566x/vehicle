package com.finhub.framework.dubbo.skywalking;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.skywalking.apm.agent.core.context.CarrierItem;
import org.apache.skywalking.apm.agent.core.context.ContextCarrier;
import org.apache.skywalking.apm.agent.core.context.ContextManager;
import org.apache.skywalking.apm.agent.core.context.tag.Tags;
import org.apache.skywalking.apm.agent.core.context.trace.AbstractSpan;
import org.apache.skywalking.apm.agent.core.context.trace.SpanLayer;
import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;

/**
 * 修复 Skywalking Dubbo Plugin 链路 Bug
 * <p>
 * 1. rpcContext.getAttachments().put() 过期不能再用 使用 rpcContext.setAttachment
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2022/09/23 17:41
 */
public class DubboPluginUtils {

    public static final String ARGUMENTS = "arguments";

    public static void beforeInvoke(final Invoker<?> invoker, final Invocation invocation) {
        final RpcContext rpcContext = RpcContext.getContext();
        final boolean isConsumer = rpcContext.isConsumerSide();
        final URL requestUrl = invoker.getUrl();
        final String host = requestUrl.getHost();
        final int port = requestUrl.getPort();
        AbstractSpan span;
        boolean needCollectArguments;
        int argumentsLengthThreshold;
        if (isConsumer) {
            final ContextCarrier contextCarrier = new ContextCarrier();
            span = ContextManager.createExitSpan(generateOperationName(requestUrl, invocation), contextCarrier,
                host + ":" + port);
            CarrierItem next = contextCarrier.items();
            while (next.hasNext()) {
                next = next.next();
                rpcContext.setAttachment(next.getHeadKey(), next.getHeadValue());
                if (invocation.getAttachments().containsKey(next.getHeadKey())) {
                    invocation.getAttachments().remove(next.getHeadKey());
                }
            }
            needCollectArguments = DubboPluginConfig.Plugin.Dubbo.COLLECT_CONSUMER_ARGUMENTS;
            argumentsLengthThreshold = DubboPluginConfig.Plugin.Dubbo.CONSUMER_ARGUMENTS_LENGTH_THRESHOLD;
        } else {
            final ContextCarrier contextCarrier = new ContextCarrier();
            CarrierItem next = contextCarrier.items();
            while (next.hasNext()) {
                next = next.next();
                next.setHeadValue(rpcContext.getAttachment(next.getHeadKey()));
            }
            span = ContextManager.createEntrySpan(generateOperationName(requestUrl, invocation), contextCarrier);
            span.setPeer(rpcContext.getRemoteAddressString());
            needCollectArguments = DubboPluginConfig.Plugin.Dubbo.COLLECT_PROVIDER_ARGUMENTS;
            argumentsLengthThreshold = DubboPluginConfig.Plugin.Dubbo.PROVIDER_ARGUMENTS_LENGTH_THRESHOLD;
        }
        Tags.URL.set(span, generateRequestURL(requestUrl, invocation));
        collectArguments(needCollectArguments, argumentsLengthThreshold, span, invocation);
        span.setComponent(ComponentsDefine.DUBBO);
        SpanLayer.asRPCFramework(span);
    }

    public static Object afterInvoke(final Object ret) {
        final Result result = (Result) ret;
        if (result != null && result.getException() != null) {
            dealException(result.getException());
        }
        ContextManager.stopSpan();
        return ret;
    }

    public static void handleMethodException(final Throwable t) {
        dealException(t);
    }

    private static void dealException(final Throwable throwable) {
        final AbstractSpan span = ContextManager.activeSpan();
        span.log(throwable);
    }

    private static String generateOperationName(final URL requestUrl, final Invocation invocation) {
        final StringBuilder operationName = new StringBuilder();
        operationName.append(requestUrl.getPath());
        operationName.append("." + invocation.getMethodName() + "(");
        for (final Class<?> classes : invocation.getParameterTypes()) {
            operationName.append(classes.getSimpleName() + ",");
        }
        if (invocation.getParameterTypes().length > 0) {
            operationName.delete(operationName.length() - 1, operationName.length());
        }
        operationName.append(")");
        return operationName.toString();
    }

    private static String generateRequestURL(final URL url, final Invocation invocation) {
        final StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(url.getProtocol() + "://");
        requestUrl.append(url.getHost());
        requestUrl.append(":" + url.getPort() + "/");
        requestUrl.append(generateOperationName(url, invocation));
        return requestUrl.toString();
    }

    private static void collectArguments(final boolean needCollectArguments, final int argumentsLengthThreshold,
        final AbstractSpan span, final Invocation invocation) {
        if (needCollectArguments && argumentsLengthThreshold > 0) {
            final Object[] parameters = invocation.getArguments();
            if (parameters != null && parameters.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder();
                boolean first = true;
                for (final Object parameter : parameters) {
                    if (!first) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(parameter);
                    if (stringBuilder.length() > argumentsLengthThreshold) {
                        stringBuilder.append("...");
                        break;
                    }
                    first = false;
                }
                span.tag("arguments", stringBuilder.toString());
            }
        }
    }
}

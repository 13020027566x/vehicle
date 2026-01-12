package com.finhub.framework.dubbo.filter;

import com.finhub.framework.dubbo.skywalking.DubboPluginUtils;
import com.finhub.framework.logback.util.LogUtils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_DEPT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY;

/**
 * 日志染色 ConsumerRpcTraceFilter
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/08 17:41
 */
@Slf4j
@Activate(group = {CommonConstants.CONSUMER})
public class ConsumerRpcTraceFilter extends AbstractFilter {

    /**
     * @param invoker
     * @param invocation
     * @return
     * @throws org.apache.dubbo.rpc.RpcException
     */
    @Override
    public Result doInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        Environment environment = SpringUtil.getBean(Environment.class);

        String fromIp = NetUtils.getIpByConfig();
        String fromApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);
        String fromDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);
        String fromVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
        String fromLang = LocaleContextHolder.getLocale().toString();

        rpcContext.setAttachment(MDC_KEY_FROM_IP, isBlank(fromIp) ? S_EMPTY : fromIp);
        rpcContext.setAttachment(MDC_KEY_FROM_APP, isBlank(fromApp) ? S_EMPTY : fromApp);
        rpcContext.setAttachment(MDC_KEY_FROM_DEPT, isBlank(fromDept) ? S_EMPTY : fromDept);
        rpcContext.setAttachment(MDC_KEY_FROM_VEHICLE, isBlank(fromVehicle) ? S_EMPTY : fromVehicle);
        rpcContext.setAttachment(MDC_KEY_FROM_LANG, isBlank(fromLang) ? S_EMPTY : fromLang);

        String requestId = LogUtils.getRequestId();
        rpcContext.setAttachment(MDC_KEY_REQUEST_ID, isBlank(requestId) ? S_EMPTY : requestId);

        String traceId = LogUtils.getTraceId();
        rpcContext.setAttachment(MDC_KEY_TRACE_ID, isBlank(traceId) ? S_EMPTY : traceId);

        Result ret;
        try {
            DubboPluginUtils.beforeInvoke(invoker, invocation);
            ret = invoker.invoke(invocation);
            DubboPluginUtils.afterInvoke(ret);
        } catch (RpcException e) {
            log.warn("ConsumerRpcTraceFilter doInvoke fail.", e);
            DubboPluginUtils.handleMethodException(e);
            throw e;
        }
        return ret;
    }

}

package com.finhub.framework.dubbo.filter;

import com.finhub.framework.dubbo.skywalking.DubboPluginUtils;
import com.finhub.framework.i18n.constant.LocaleTypeEnum;
import com.finhub.framework.i18n.property.I18nProperties;
import com.finhub.framework.i18n.resolver.CustomHeaderLocaleResolver;
import com.finhub.framework.logback.LogMdcHolder;
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
import org.slf4j.MDC;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import java.util.Locale;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_CAMEL_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_PORT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_PORT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_DEPT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY;

/**
 * 日志染色 ProviderRpcTraceFilter
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/08 17:41
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER}, order = 1)
public class ProviderRpcTraceFilter extends AbstractFilter {

    /**
     * @param invoker
     * @param invocation
     * @return
     * @throws org.apache.dubbo.rpc.RpcException
     */
    @Override
    public Result doInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result ret;
        try {
            DubboPluginUtils.beforeInvoke(invoker, invocation);
            // 设置链路追踪日志
            this.setLinkTrackerForLogger();
            ret = invoker.invoke(invocation);
            DubboPluginUtils.afterInvoke(ret);
        } catch (RpcException e) {
            log.warn("ProviderRpcTraceFilter doInvoke fail.", e);
            DubboPluginUtils.handleMethodException(e);
            throw e;
        } finally {
            // 记得 clear 相关信息，否则会导致内存溢出
            LogUtils.clear();
            LogMdcHolder.clear();
        }
        return ret;
    }

    /**
     * 设置链路追踪日志
     */
    private void setLinkTrackerForLogger() {
        RpcContext context = RpcContext.getContext();
        // 流量来源
        this.putTrafficSourceForMdc(context);
        // 追踪ID
        this.putLinkTrackerIdForMdc(context);
    }

    /**
     * 向MDC中放入流量来源信息
     *
     * @param context
     */
    private void putTrafficSourceForMdc(RpcContext context) {
        // 设置日志 from_ip & to_ip 变量
        String fromIp = context.getAttachment(MDC_KEY_FROM_IP);
        String toIp = NetUtils.getIpByConfig();

        MDC.put(MDC_KEY_FROM_IP, isBlank(fromIp) ? S_EMPTY : fromIp);
        MDC.put(MDC_KEY_TO_IP, isBlank(toIp) ? S_EMPTY : toIp);

        // 设置日志 from_port & to_port 变量
        String fromPort = String.valueOf(context.getRemotePort());
        String toPort = String.valueOf(context.getLocalPort());

        MDC.put(MDC_KEY_FROM_PORT, isBlank(fromPort) ? S_EMPTY : fromPort);
        MDC.put(MDC_KEY_TO_PORT, isBlank(toPort) ? S_EMPTY : toPort);

        // 设置日志 from_app & to_app 变量
        Environment environment = SpringUtil.getBean(Environment.class);
        String fromApp = context.getAttachment(MDC_KEY_FROM_APP);
        String toApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);

        MDC.put(MDC_KEY_FROM_APP, isBlank(fromApp) ? S_EMPTY : fromApp);
        MDC.put(MDC_KEY_TO_APP, isBlank(toApp) ? S_EMPTY : toApp);

        // 设置日志 from_dept & to_dept 变量
        String fromDept = context.getAttachment(MDC_KEY_FROM_DEPT);
        String toDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);

        MDC.put(MDC_KEY_FROM_DEPT, isBlank(fromDept) ? S_EMPTY : fromDept);
        MDC.put(MDC_KEY_TO_DEPT, isBlank(toDept) ? S_EMPTY : toDept);

        // 设置日志 from_vehicle & to_vehicle 变量
        String fromVehicle = context.getAttachment(MDC_KEY_FROM_VEHICLE);
        String toVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);

        MDC.put(MDC_KEY_FROM_VEHICLE, isBlank(fromVehicle) ? S_EMPTY : fromVehicle);
        MDC.put(MDC_KEY_TO_VEHICLE, isBlank(toVehicle) ? S_EMPTY : toVehicle);

        // 设置日志 from_lang & to_lang 变量
        Locale defaultLocal = LocaleTypeEnum.getValue(I18nProperties.me().getDefaultLocale());
        String fromLang = context.getAttachment(MDC_KEY_FROM_LANG);
        Locale fromLocale = LocaleTypeEnum.getValue(fromLang);

        CustomHeaderLocaleResolver.me().setLocaleContext(defaultLocal, fromLocale);

        String toLang = LocaleContextHolder.getLocale().toString();

        MDC.put(MDC_KEY_FROM_LANG, isBlank(fromLang) ? S_EMPTY : fromLang);
        MDC.put(MDC_KEY_TO_LANG, isBlank(toLang) ? S_EMPTY : toLang);
    }

    /**
     * 向MDC中放入流量追踪ID
     *
     * @param context
     */
    private void putLinkTrackerIdForMdc(RpcContext context) {
        String fromTraceId = context.getAttachment(MDC_KEY_TRACE_ID);
        LogUtils.fillTraceId(fromTraceId);

        String fromRequestId = context.getAttachment(MDC_KEY_REQUEST_ID);
        if (isBlank(fromRequestId)) {
            fromRequestId = context.getAttachment(MDC_KEY_CAMEL_REQUEST_ID);
        }

        LogUtils.fillRequestId(fromRequestId);
    }

}

package com.finhub.framework.web.interceptor;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.json.JsonUtils;
import com.finhub.framework.core.net.IpUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.core.thread.ThreadLocalUtils;
import com.finhub.framework.core.web.RequestUtils;
import com.finhub.framework.core.web.ResponseUtils;
import com.finhub.framework.exception.util.ExceptionUtils;
import com.finhub.framework.logback.entity.LogCommonProperty;
import com.finhub.framework.logback.property.LogProperties;
import com.finhub.framework.logback.util.LogCommonUtils;
import com.finhub.framework.logback.util.LogUtils;
import com.finhub.framework.web.safe.HttpServletRequestSafeWrapper;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.finhub.framework.logback.constant.LogConstants.LOG_CLASS_METHOD_TEMPLATE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_CAMEL_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_COST;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_LOWER_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ATTRIBUTE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_BODY;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_HEADER;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_METHOD;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_PARAMETER;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_URI;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_RESPONSE_BODY;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_RESPONSE_HEADER;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_RESPONSE_STATUS;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_RESPONSE_SUCCESS;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TRACE_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_LOG_THROWABLE;
import static com.finhub.framework.logback.constant.LogConstants.S_SPACE;
import static com.finhub.framework.web.constant.WebConstants.RESULT_KEY;

/**
 * 日志响应抽象拦截器
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Slf4j
public abstract class AbstractResponseLogInterceptor implements HandlerInterceptor {

    private static final String REQUEST_BEGIN_TIMESTAMP = "request_begin_timestamp";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        this.setRequestTrackContext(request);

        this.buildRequestContentLog(request, (HandlerMethod) handler);

        return preHandleProcess(request, response, handler);
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler, final Exception ex) {

        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        long endTime = System.currentTimeMillis();
        Object beginObj = request.getAttribute(REQUEST_BEGIN_TIMESTAMP);
        long beginTime = beginObj == null ? System.currentTimeMillis() : (long) beginObj;
        long consumeTime = endTime - beginTime;
        this.buildResponseContentLog(request, response, ex, consumeTime);
    }

    /**
     * 设置请求追踪日志上下文属性
     *
     * @param request
     */
    private void setRequestTrackContext(HttpServletRequest request) {
        if (request.getAttribute(MDC_KEY_TRACE_ID) == null) {
            request.setAttribute(MDC_KEY_REQUEST_ID, LogUtils.getRequestId());
            request.setAttribute(MDC_KEY_TRACE_ID, LogUtils.getTraceId());
            request.setAttribute(REQUEST_BEGIN_TIMESTAMP, System.currentTimeMillis());
        } else {
            MDC.put(MDC_KEY_TRACE_ID, String.valueOf(request.getAttribute(MDC_KEY_TRACE_ID)));
            MDC.put(MDC_KEY_LOWER_TRACE_ID, TraceContext.traceId());
            MDC.put(MDC_KEY_REQUEST_ID, String.valueOf(request.getAttribute(MDC_KEY_REQUEST_ID)));
            MDC.put(MDC_KEY_CAMEL_REQUEST_ID, String.valueOf(request.getAttribute(MDC_KEY_REQUEST_ID)));
        }
    }

    /**
     * 构建日志参数属性
     *
     * @param request
     * @param handlerMethod
     * @return
     */
    private LogCommonProperty buildLogCommonProperty(HttpServletRequest request, HandlerMethod handlerMethod) {

        final Class<?> aClass = handlerMethod.getBeanType();
        String className = aClass.getName();
        String simpleName = aClass.getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        String argsString = JSON.toJSONString(RequestUtils.getRequestParameterMap(request));
        String body = null;
        if (request instanceof HttpServletRequestSafeWrapper
            && Func.isNotEmpty(((HttpServletRequestSafeWrapper) request).getCachedBytes())) {
            body = ((HttpServletRequestSafeWrapper) request).getBody();
        }
        final LogCommonProperty build = LogCommonProperty.builder()
            .mode(getLogAspectModule())
            .className(className)
            .simpleClassName(simpleName)
            .methodName(methodName)
            .argsJson(argsString)
            .body(body)
            .fullClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, className, methodName))
            .simpleClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, simpleName, methodName))
            .build();
        ThreadLocalUtils.putControllerLogCommonProperty(build);
        return build;

    }

    /**
     * 输出请求入参数日志
     *
     * @param request
     * @param handler
     */
    private void buildRequestContentLog(HttpServletRequest request, HandlerMethod handler) {

        try {
            final LogCommonProperty logProperty = this.buildLogCommonProperty(request, handler);

            LogCommonUtils.processInputServerLog(getLogAspectModule(), LogLevel.INFO,
                this.buildRequestLogKeyValues(request, logProperty));

        } catch (Exception e) {
            log.warn("write request input parameter log error: ", e);
        }


    }

    /**
     * 构建请求日志输出内容
     *
     * @param request
     * @param logProperty
     * @return
     */
    private LogCommonUtils.LogKeyValue[] buildRequestLogKeyValues(HttpServletRequest request,
        LogCommonProperty logProperty) {

        int index = 0;
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[7];
        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_IP, IpUtils.getRealIP(request));

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_URI, request.getRequestURI());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_METHOD, request.getMethod() + S_SPACE + logProperty.getFullClassMethod());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_BODY, logProperty.getBody());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_PARAMETER, logProperty.getArgsJson());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_HEADER, getRequestHeaderLog(request));

        logKeyValues[index] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_ATTRIBUTE, getRequestAttributeLog(request));

        return logKeyValues;

    }

    /**
     * 输出请求输出日志
     *
     * @param request
     * @param response
     * @param ex
     * @param consumeTime
     */
    protected void buildResponseContentLog(HttpServletRequest request, HttpServletResponse response, Exception ex,
        long consumeTime) {

        try {
            final LogCommonProperty logProperty = ThreadLocalUtils.getControllerLogCommonProperty();

            logProperty.paddingProcessResult(getResponseResult(request), consumeTime);

            if (Func.isNotEmpty(ex)) {

                logProperty.setSuccess(false);

                LogCommonUtils.processErrorServerLog(getLogAspectModule(), ExceptionUtils.getErrorLogLevel(ex),
                    this.buildThrowableLogKeyValues(request, logProperty, ex));

            }

            LogCommonUtils.processOutputServerLog(getLogAspectModule(), LogLevel.INFO,
                this.buildResponseLogKeyValues(request, response, logProperty));

            // 处理链路 Span
            LogCommonUtils.processSkyWalkingLog(logProperty, ex);
        } catch (Exception e) {
            log.warn("write response output result log error: ", e);
        }
    }

    private String getResponseResult(HttpServletRequest request) {
        Object obj = request.getAttribute(RESULT_KEY);
        if (Func.isNull(obj)) {
            return StrConstants.S_NULL;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return JsonUtils.toJson(obj);
    }

    private LogCommonUtils.LogKeyValue[] buildThrowableLogKeyValues(HttpServletRequest request,
        LogCommonProperty logProperty, Exception ex) {

        int index = 0;
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[5];
        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_URI, request.getRequestURI());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_METHOD, request.getMethod() + S_SPACE + logProperty.getFullClassMethod());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_PARAMETER, logProperty.getArgsJson());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_BODY, logProperty.getBody());

        logKeyValues[index] =
            new LogCommonUtils.LogKeyValue(MDC_LOG_THROWABLE, ExceptionUtil.stacktraceToString(ex));

        return logKeyValues;

    }


    private LogCommonUtils.LogKeyValue[] buildResponseLogKeyValues(HttpServletRequest request,
        HttpServletResponse response, LogCommonProperty logProperty) {

        int index = 0;
        LogCommonUtils.LogKeyValue[] logKeyValues = new LogCommonUtils.LogKeyValue[7];

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_URI, request.getRequestURI());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_REQUEST_METHOD, request.getMethod() + S_SPACE + logProperty.getFullClassMethod());

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_RESPONSE_HEADER, this.getResponseHeaderLog(response));

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_RESPONSE_STATUS, String.valueOf(response.getStatus()));

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_RESPONSE_SUCCESS, String.valueOf(logProperty.isSuccess()));

        logKeyValues[index++] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_RESPONSE_BODY, String.valueOf(logProperty.getResult()));

        logKeyValues[index] =
            new LogCommonUtils.LogKeyValue(MDC_KEY_COST, String.valueOf(logProperty.getCostTime()));

        return logKeyValues;

    }

    /**
     * 获取请求Head头参数
     *
     * @param request
     * @return
     */
    private String getRequestHeaderLog(HttpServletRequest request) {

        try {
            LogProperties logProperties = LogProperties.me();
            List<String> headerKeys = logProperties.getRequestHeaderKeys();
            if (Func.isEmpty(headerKeys)) {
                return JSON.toJSONString(doGetPrintMap(RequestUtils.getRequestHeaderMap(request),
                    logProperties.getExcludeRequestHeaderKeys()));
            }

            Map<String, String> result = new HashMap<>(headerKeys.size());
            for (String key : headerKeys) {
                if (Func.isEmpty(key)) {
                    continue;
                }
                result.put(key, request.getHeader(key));
            }
            return JSON.toJSONString(result);
        } catch (Exception e) {
            log.warn("print request header log fail : ", e);
        }

        return StrConstants.S_EMPTY;
    }

    /**
     * 获取请求Attribute参数
     *
     * @param request
     * @return
     */
    private String getRequestAttributeLog(HttpServletRequest request) {
        try {
            List<String> attributionKeys = LogProperties.me().getRequestAttributionKeys();
            if (Func.isEmpty(attributionKeys)) {
                return StrConstants.S_EMPTY;
            }

            Map<String, String> result = new HashMap<>(attributionKeys.size());
            for (String key : attributionKeys) {
                if (Func.isEmpty(key)) {
                    continue;
                }
                result.put(key, JSON.toJSONString(request.getAttribute(key)));
            }

            return JSON.toJSONString(result);
        } catch (Exception e) {
            log.warn("print request attribute log fail : ", e);
        }

        return StrConstants.S_EMPTY;
    }

    /**
     * 获取请求Attribute参数
     *
     * @param response
     * @return
     */
    private String getResponseHeaderLog(HttpServletResponse response) {
        try {
            LogProperties logProperties = LogProperties.me();
            List<String> responseHeaderKeys = logProperties.getResponseHeaderKeys();
            if (Func.isEmpty(responseHeaderKeys)) {
                return JSON.toJSONString(doGetPrintMap(ResponseUtils.getResponseHeaderMap(response),
                    logProperties.getExcludeResponseHeaderKeys()));
            }

            Map<String, String> result = new HashMap<>(responseHeaderKeys.size());
            for (String key : responseHeaderKeys) {
                if (Func.isEmpty(key)) {
                    continue;
                }
                result.put(key, JSON.toJSONString(response.getHeader(key)));
            }
            return JSON.toJSONString(result);
        } catch (Exception e) {
            log.warn("print response header log fail : ", e);
        }

        return StrConstants.S_EMPTY;
    }

    /**
     * 获取输出map
     *
     * @param source
     * @param excludeKeys
     * @return
     */
    private Map<String, Object> doGetPrintMap(Map<String, Object> source, Set<String> excludeKeys) {
        if (Func.isEmpty(source) || Func.isEmpty(excludeKeys)) {
            return source;
        }

        for (String key : excludeKeys) {
            source.remove(key);
        }

        return source;
    }

    /**
     * 是否启用ThreadLocal类型的变量
     *
     * @return
     */
    protected abstract boolean isThreadLocalEnable();

    /**
     * 前置处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    protected abstract boolean preHandleProcess(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler);

    /**
     * 日志名称 用于区分不同模块的运行日志（Api、controller等）
     *
     * @return
     */
    protected abstract String getLogAspectModule();

}

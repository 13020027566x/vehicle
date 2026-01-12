package com.finhub.framework.web.async.task;

import com.finhub.framework.web.async.helper.WebAsyncHelper;
import com.finhub.framework.web.vo.Result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceCrossThread;
import org.slf4j.MDC;
import org.springframework.web.context.request.async.DeferredResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author : liuwei
 * @date : 2022/1/15
 * @desc :
 */
@Data
@Slf4j
@TraceCrossThread
public class AsyncReferredCallable<U> implements Callable<U> {
    /**
     * 异常结果
     */
    private static final Result<Object> ERROR_RESULT =
        (Result.instanceFail(WebAsyncHelper.DEFAULT_TIMEOUT_CODE, WebAsyncHelper.DEFAULT_TIMEOUT_MESSAGE));

    /**
     * 代理目标类
     */
    private final Object proxy;

    /**
     * 异步响应结果
     */
    private final DeferredResult<U> deferredResult;

    /**
     * 方法
     */
    private final String methodName;

    /**
     * 参数类型
     */
    private final Class<?>[] argsType;

    /**
     * 请求参数集合
     */
    private final Object[] args;

    /**
     * 线程上下文
     */
    private final Map<String, String> contextMap;

    public AsyncReferredCallable(Object proxy, DeferredResult<U> deferredResult, String methodName, Object[] args) {
        this.proxy = proxy;
        this.deferredResult = deferredResult;
        this.methodName = methodName;
        this.args = args;
        this.argsType = WebAsyncHelper.getTargetMethodParametersType(proxy.getClass().getName(), methodName);
        this.contextMap = MDC.getCopyOfContextMap();

    }


    @Override
    public U call() throws Exception {
        //放入父线程上下文
        MDC.setContextMap(this.contextMap);

        Method method = proxy.getClass().getMethod(methodName, argsType);

        try {
            Object invoke = method.invoke(proxy, args);
            U result = (U) invoke;
            this.deferredResult.setResult(result);
            return result;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof Exception) {
                this.deferredResult.setErrorResult(e.getTargetException());
            }
            this.deferredResult.setErrorResult(e);
        }

        return null;
    }

}

package com.finhub.framework.web.async.task;


import com.finhub.framework.web.async.helper.WebAsyncHelper;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceCrossThread;
import org.slf4j.MDC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author : liuwei
 * @date : 2022/1/21
 * @desc :
 */
@Slf4j
@TraceCrossThread
public class AsyncCallable<U> implements Callable<U> {


    /**
     * 代理目标类
     */
    private final Object proxy;

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

    public AsyncCallable(Object proxy, String methodName, Object[] args) {
        this.proxy = proxy;
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
            return (U) method.invoke(proxy, args);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof Exception) {
                throw (Exception) e.getTargetException();
            }
            throw e;
        }

    }

}

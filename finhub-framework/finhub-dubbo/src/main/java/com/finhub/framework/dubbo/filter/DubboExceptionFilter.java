package com.finhub.framework.dubbo.filter;

import com.finhub.framework.exception.ArgumentException;
import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.constant.enums.ArgumentResponseEnum;
import com.finhub.framework.exception.util.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.ListenableFilter;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/08 17:41
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class DubboExceptionFilter extends ListenableFilter {

    private static final String S_SEMICOLON = ";";


    public DubboExceptionFilter() {
        super.listener = new DubboExceptionListener();
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Slf4j
    static class DubboExceptionListener extends ExceptionListener {

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            // 发生异常，并且非泛化调用
            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
                Throwable exception = appResponse.getException();
                // 如果是 ServiceException 异常，直接返回
                if (exception instanceof BaseException) {
                    return;
                }

                // 如果是 FinhubException 异常，直接返回
                if (ExceptionUtils.isWarnLevelException(exception)) {
                    return;
                }

                // 如果是参数校验的 ConstraintViolationException 异常，则封装返回
                if (exception instanceof ConstraintViolationException) {
                    appResponse.setException(this.handleConstraintViolationException((ConstraintViolationException) exception));
                    return;
                }
            }
            // 其它情况，继续使用父类处理
            super.onResponse(appResponse, invoker, invocation);
        }

        private ArgumentException handleConstraintViolationException(ConstraintViolationException e) {
            // 拼接错误
            StringBuilder detailMessage = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                // 使用 ; 分隔多个错误
                if (detailMessage.length() > 0) {
                    detailMessage.append(S_SEMICOLON);
                }
                // 拼接内容到其中
                detailMessage.append(constraintViolation.getMessage());
            }
            // 返回异常
            return new ArgumentException(ArgumentResponseEnum.INVALID_ERROR, detailMessage.toString());
        }
    }

    @Slf4j
    static class ExceptionListener implements Listener {

        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
                try {
                    Throwable exception = appResponse.getException();

                    // directly throw if it's checked exception
                    if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                        return;
                    }
                    // directly throw if the exception appears in the signature
                    try {
                        Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                        Class<?>[] exceptionClasses = method.getExceptionTypes();
                        for (Class<?> exceptionClass : exceptionClasses) {
                            if (exception.getClass().equals(exceptionClass)) {
                                return;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        return;
                    }

                    // for the exception not found in method's signature, print ERROR message in server's log.
                    log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);

                    // directly throw if exception class and interface class are in the same jar file.
                    String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                    String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                    if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                        return;
                    }
                    // directly throw if it's JDK exception
                    String className = exception.getClass().getName();
                    if (className.startsWith("java.") || className.startsWith("javax.")) {
                        return;
                    }
                    // directly throw if it's dubbo exception
                    if (exception instanceof RpcException) {
                        return;
                    }

                    // otherwise, wrap with RuntimeException and throw back to the client
                    appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
                    return;
                } catch (Throwable e) {
                    log.warn("Fail to ExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
                    return;
                }
            }
        }

        @Override
        public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
            log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
        }
    }
}

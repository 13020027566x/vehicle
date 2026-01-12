package com.finhub.framework.web.async.annotation;

import com.finhub.framework.web.async.helper.WebAsyncHelper;

import org.springframework.web.context.request.async.WebAsyncTask;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2022/1/12
 * @desc : used for spring-mvc controller class or route method
 *         class: @Controller or @RequestMapping
 *         method: (@RequestMapping or @PostMapping or @GetMapping) and method return type not Void or not web-async-return-type.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AsyncRequest {

    /**
     * invoke method timeout
     *
     * @return
     */
    long timeout() default WebAsyncHelper.DEFAULT_TIMEOUT;

    /**
     * invoke method timeout unit
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * spring-mvc async return class type
     *  when returnType = java.util.concurrent.Callable ，other annotation properties no avail，callable not support timeout mode。
     *
     *  support mode： {@link com.finhub.framework.web.constant.WebAsyncModeEnum}
     *
     * @return
     */
    Class<?> returnType() default WebAsyncTask.class;

    /**
     * invoke method timeout return timeout code
     *
     * @return
     */
    int code() default WebAsyncHelper.DEFAULT_TIMEOUT_CODE;

    /**
     * invoke method timeout return timeout msg
     *
     * @return
     */
    String msg() default WebAsyncHelper.DEFAULT_TIMEOUT_MESSAGE;

}

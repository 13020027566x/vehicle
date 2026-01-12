package com.finhub.framework.web.async.entity;

import com.finhub.framework.core.Func;
import com.finhub.framework.web.async.helper.WebAsyncHelper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2022/1/25
 * @desc :
 */
@Getter
@Builder
@AllArgsConstructor
public class AsyncRequest {

    private long timeout;

    private TimeUnit timeUnit;

    private Class<?> returnType;

    private int code;

    private String msg;

    public TimeUnit getTimeUnit() {
        return timeUnit == null ? WebAsyncHelper.DEFAULT_TIME_UNIT : timeUnit;
    }

    public Class<?> getReturnType() {
        return returnType == null ? WebAsyncHelper.DEFAULT_RETURN_TYPE : returnType;
    }

    public String getMsg() {
        return Func.isBlank(msg) ? WebAsyncHelper.DEFAULT_TIMEOUT_MESSAGE : msg;
    }

}

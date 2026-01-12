package com.finhub.framework.web.constant;

import com.finhub.framework.core.Func;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * @author : liuwei
 * @date : 2022/1/22
 * @desc :
 */
public enum WebAsyncModeEnum {

    /**
     * 默认
     */
    DEFAULT(0, WebAsyncTask.class),

    /**
     * 默认异步方式，不支持超时
     */
    CALLABLE(1, Callable.class),

    /**
     * 支持超时 超时后杀掉执行线程，任务关闭，页面返回的结果为超时信息
     */
    WEB_ASYNC_TASK(2, WebAsyncTask.class),

    /**
     * 支持超时 超时后不会杀掉业务线程，业务线程会在后台处理完成，但是页面返回的结果为超时信息
     */
    DEFERRED_RESULT(3, DeferredResult.class),
    ;

    WebAsyncModeEnum(int mode, Class<?> returnClass) {
        this.mode = mode;
        this.returnClass = returnClass;
    }

    private int mode;

    private Class<?> returnClass;


    public int getMode() {
        return mode;
    }

    public Class<?> getReturnClass() {
        return returnClass;
    }

    /**
     * 根据模式串 获取返回class
     *
     * @param mode
     * @return
     */
    public static Class<?> getReturnTypeClassByMode(Integer mode) {
        if (Func.isNull(mode)) {
            return DEFAULT.getReturnClass();
        }
        WebAsyncModeEnum[] values = values();
        for (WebAsyncModeEnum webAsyncModeEnum : values) {
            if (webAsyncModeEnum.mode == mode) {
                return webAsyncModeEnum.getReturnClass();
            }
        }
        return DEFAULT.getReturnClass();
    }

    /**
     * 根据class名称获取模式枚举
     *
     * @param className
     * @return
     */
    public static WebAsyncModeEnum getModeByReturnTypeClassName(String className) {
        if (Func.isBlank(className)) {
            return DEFAULT;
        }
        for (WebAsyncModeEnum modeEnum : values()) {
            if (StrUtil.equalsIgnoreCase(className, modeEnum.getReturnClass().getName())) {
                return modeEnum;
            }
        }
        return DEFAULT;
    }
}

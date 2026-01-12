package com.finhub.framework.web.async.helper;

import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.core.str.StringUtils;
import com.finhub.framework.web.async.annotation.AsyncProxy;

import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2022/1/15
 * @desc :
 */
@UtilityClass
public final class WebAsyncHelper {

    /**
     * 默认超时时间
     */
    public static final long DEFAULT_TIMEOUT = 3600;

    /**
     * 超时时间类型
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 默认异步响应方式
     */
    public static final Class<?> DEFAULT_RETURN_TYPE = WebAsyncTask.class;

    /**
     * 超时提示信息
     */
    public static final String DEFAULT_TIMEOUT_MESSAGE = "业务超时";

    /**
     * 超时code码
     */
    public static final int DEFAULT_TIMEOUT_CODE = 500;

    /**
     * 目标方法参数类型集合缓存
     */
    private static final Map<String, Class<?>[]> TARGET_METHOD_PARAMETERS_CLASS_TYPE = new ConcurrentHashMap<>();

    /**
     * 添加AsyncProxy注解方法的参数类型缓存
     *
     * @param clazz 待解析目标class
     */
    public static void addTargetMethodParametersType(Class<?> clazz) {

        final Method[] methods = clazz.getMethods();
        if (ArrayUtil.isEmpty(methods)) {
            return;
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(AsyncProxy.class)) {
                TARGET_METHOD_PARAMETERS_CLASS_TYPE.put(
                    StringUtils.appender(StrConstants.C_COLON, clazz.getName(), method.getName()), method.getParameterTypes());
            }
        }
    }

    /**
     * 根据类&方法获取方法类型
     * @param className 类名称
     * @param method    方法名称
     * @return          缓存结果类型
     */
    public static Class<?>[] getTargetMethodParametersType(String className, String method) {
        return TARGET_METHOD_PARAMETERS_CLASS_TYPE.get(StringUtils.appender(StrConstants.C_COLON, className, method));
    }

}

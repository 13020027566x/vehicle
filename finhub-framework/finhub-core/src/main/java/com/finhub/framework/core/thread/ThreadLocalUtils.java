package com.finhub.framework.core.thread;

import com.finhub.framework.core.annotation.Desensitizes;
import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.core.web.dto.Device;
import com.finhub.framework.logback.entity.LogCommonProperty;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Method;

/**
 * ThreadLocal 工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 15-6-6 上午11:48
 */
@Slf4j
@UtilityClass
public class ThreadLocalUtils {

    private static final NamedThreadLocal<Device> DEVICE_THREAD_LOCAL = new NamedThreadLocal<>("DEVICE");

    private static final NamedThreadLocal<CurrentUser> CURRENT_USER_THREAD_LOCAL = new NamedThreadLocal<>("CURRENT_USER");

    private static final NamedThreadLocal<Method> METHOD_NAMED_THREAD_LOCAL = new NamedThreadLocal<>("METHOD_NAMED");

    private static final NamedThreadLocal<String> ID_PATTERN_THREAD_LOCAL = new NamedThreadLocal<>("ID_PATTERN");

    private static final NamedThreadLocal<String> TENANT_ID_THREAD_LOCAL = new NamedThreadLocal<>("TENANT_ID");

    private static final NamedThreadLocal<LogCommonProperty> CONTROLLER_LOG_COMMON_PROPERTY_THREAD_LOCAL =
        new NamedThreadLocal<>("CONTROLLER_LOG_COMMON_PROPERTY");

    private static final NamedThreadLocal<Object> MAPSTRUCT_CONTEXT_THREAD_LOCAL = new NamedThreadLocal<>("MAPSTRUCT_CONTEXT");

    private static final NamedThreadLocal<Desensitizes> DESENSITIZE_THREAD_LOCAL = new NamedThreadLocal<>("DESENSITIZE");

    /**
     * 存放当前方法
     *
     * @param method
     */
    public static void putCurrentMethod(Method method) {
        METHOD_NAMED_THREAD_LOCAL.set(method);
    }

    /**
     * 获取当前方法
     */
    public static Method getCurrentMethod() {
        return METHOD_NAMED_THREAD_LOCAL.get();
    }

    /**
     * 删除当前方法
     */
    public static void removeCurrentMethod() {
        METHOD_NAMED_THREAD_LOCAL.remove();
    }

    /**
     * 存放版本信息
     *
     * @param device
     */
    public static void putDevice(Device device) {
        DEVICE_THREAD_LOCAL.set(device);
    }

    /**
     * 获取版本信息
     */
    public static Device getDevice() {
        return DEVICE_THREAD_LOCAL.get();
    }

    /**
     * 删除版本信息
     */
    public static void removeDevice() {
        DEVICE_THREAD_LOCAL.remove();
    }

    /**
     * 存放USER
     *
     * @param user
     */
    public static void putCurrentUser(CurrentUser user) {
        CURRENT_USER_THREAD_LOCAL.set(user);
    }

    /**
     * 获取APP_USER
     */
    public static CurrentUser getCurrentUser() {
        return CURRENT_USER_THREAD_LOCAL.get();
    }

    /**
     * 删除APP_USER
     */
    public static void removeCurrentUser() {
        CURRENT_USER_THREAD_LOCAL.remove();
    }

    /**
     * 存放 ID_PATTERN
     *
     * @param idPattern
     */
    public static void putIdPattern(String idPattern) {
        ID_PATTERN_THREAD_LOCAL.set(idPattern);
    }

    /**
     * 获取 ID_PATTERN
     */
    public static String getIdPattern() {
        return ID_PATTERN_THREAD_LOCAL.get();
    }

    /**
     * 删除 ID_PATTERN
     */
    public static void removeIdPattern() {
        ID_PATTERN_THREAD_LOCAL.remove();
    }

    /**
     * 存放 Controller LogCommonProperty
     *
     * @param property
     */
    public static void putControllerLogCommonProperty(LogCommonProperty property) {
        CONTROLLER_LOG_COMMON_PROPERTY_THREAD_LOCAL.set(property);
    }

    /**
     * 获取 Controller LogCommonProperty
     */
    public static LogCommonProperty getControllerLogCommonProperty() {
        return CONTROLLER_LOG_COMMON_PROPERTY_THREAD_LOCAL.get();
    }

    /**
     * 删除 Controller LogCommonProperty
     */
    public static void removeControllerLogCommonProperty() {
        CONTROLLER_LOG_COMMON_PROPERTY_THREAD_LOCAL.remove();
    }

    /**
     * 存放 多租户 ID
     *
     * @param tenantId
     */
    public static void putTenantId(String tenantId) {
        TENANT_ID_THREAD_LOCAL.set(tenantId);
    }

    /**
     * 获取 多租户 ID
     */
    public static String getTenantId() {
        return TENANT_ID_THREAD_LOCAL.get();
    }

    /**
     * 删除 多租户 ID
     */
    public static void removeTenantId() {
        TENANT_ID_THREAD_LOCAL.remove();
    }

    /**
     * 存放 MapStruct Context
     *
     * @param context
     */
    public static void putMapStructContext(Object context) {
        MAPSTRUCT_CONTEXT_THREAD_LOCAL.set(context);
    }

    /**
     * 获取 MapStruct Context
     */
    public static Object getMapStructContext() {
        return MAPSTRUCT_CONTEXT_THREAD_LOCAL.get();
    }

    /**
     * 删除 MapStruct Context
     */
    public static void removeMapStructContext() {
        MAPSTRUCT_CONTEXT_THREAD_LOCAL.remove();
    }

    /**
     * 存放脱敏注解
     *
     * @param desensitize
     */
    public static void putDesensitizes(Desensitizes desensitize) {
        DESENSITIZE_THREAD_LOCAL.set(desensitize);
    }

    /**
     * 获取脱敏注解
     */
    public static Desensitizes getDesensitizes() {
        return DESENSITIZE_THREAD_LOCAL.get();
    }

    /**
     * 删除脱敏注解
     */
    public static void removeDesensitizes() {
        DESENSITIZE_THREAD_LOCAL.remove();
    }

    /**
     * 清空 ThreadLocal
     */
    public static void removeAll() {
        removeCurrentUser();
        removeCurrentMethod();
        removeDevice();
        removeIdPattern();
        removeControllerLogCommonProperty();
        removeTenantId();
        removeMapStructContext();
        removeDesensitizes();
    }
}

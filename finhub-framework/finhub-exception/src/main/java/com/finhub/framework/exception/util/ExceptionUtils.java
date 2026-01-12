package com.finhub.framework.exception.util;

import com.finhub.framework.exception.constant.MessageCodeTypeEnum;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * 异常工具类
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/08 17:41
 */
@Slf4j
@UtilityClass
public final class ExceptionUtils {

    private static final String EXCEPTION_CODE_PROPERTY = "code";

    private static final String EXCEPTION_TYPE_PROPERTY = "type";

    private static final String EXCEPTION_TITLE_PROPERTY = "title";

    private static final String EXCEPTION_DATA_PROPERTY = "data";

    /**
     * Warn Level 异常集合
     */
    private static final Set<String> WARN_LEVEL_EXCEPTION_CLASS_SET =
        Sets.newHashSet(
            "com.fenbeitong.finhub.common.exception.FinhubException",
            "com.fenbeitong.biz.airticket.exception.FbException",
            "com.fenbeitong.openapi.plugin.core.exception.OpenApiPluginException",
            "com.fenbeitong.openapi.plugin.core.exception.OpenApiBindException",
            "com.fenbeitong.openapi.plugin.core.exception.OpenApiArgumentException",
            "com.finhub.framework.exception.MessageException"
        );

    /**
     * Warn Level 异常解析缓存 key：className value:是否为 Warn Level。
     */
    private static final Map<String, Boolean> WARN_LEVEL_EXCEPTION_PARSE_CACHE = new ConcurrentHashMap<>(64);

    /**
     * 打印异常信息
     */
    public static String getMessage(Exception e) {
        String swStr = null;
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            swStr = sw.toString();
        } catch (IOException ex) {
            log.warn("get message error.", ex);
        }
        return swStr;
    }

    /**
     * 判断异常是否应该为 Warn Level
     *
     * @param e 异常
     * @return true ：Finhub异常 /false 非finhub异常
     */
    public static boolean isWarnLevelException(Throwable e) {

        if (e == null) {
            return false;
        }

        String name = e.getClass().getName();
        Boolean flag = WARN_LEVEL_EXCEPTION_PARSE_CACHE.get(name);
        if (flag != null) {
            return flag;
        }
        if (WARN_LEVEL_EXCEPTION_CLASS_SET.contains(name)) {
            WARN_LEVEL_EXCEPTION_PARSE_CACHE.put(name, true);
            return true;
        }
        Set<Class<?>> superClass = ClassUtils.getSuperClass(e.getClass());
        if (superClass.isEmpty()) {
            WARN_LEVEL_EXCEPTION_PARSE_CACHE.put(name, false);
            return false;
        }

        Set<String> superClassSet = superClass.stream().map(Class::getName).collect(Collectors.toSet());
        for (String ignoreClass : WARN_LEVEL_EXCEPTION_CLASS_SET) {
            if (superClassSet.contains(ignoreClass)) {
                WARN_LEVEL_EXCEPTION_PARSE_CACHE.put(name, true);
                return true;
            }
        }

        WARN_LEVEL_EXCEPTION_PARSE_CACHE.put(name, false);
        return false;
    }

    /**
     * 获取Finhub异常属性信息 通过反射获取
     *
     * @param e 异常
     * @return 非finhub异常返回结果null，否则返回对应实体属性
     */
    public static FinhubExceptionProperty getFinhubExceptionProperty(Exception e) {
        if (!isWarnLevelException(e)) {
            return null;
        }
        return FinhubExceptionProperty.builder()
            .data(safeReflectGetProperty(e, EXCEPTION_DATA_PROPERTY, Object.class))
            .code(safeReflectGetProperty(e, EXCEPTION_CODE_PROPERTY, Integer.class))
            .type(safeReflectGetProperty(e, EXCEPTION_TYPE_PROPERTY, Integer.class))
            .title(safeReflectGetProperty(e, EXCEPTION_TITLE_PROPERTY, String.class))
            .build();
    }

    public static LogLevel getErrorLogLevel(Throwable throwable) {
        return ExceptionUtils.isWarnLevelException(throwable) ? LogLevel.WARN : LogLevel.ERROR;
    }

    /**
     * 防止反射获取属性失败，导致程序影响上层业务
     *
     * @param e         Finhub异常
     * @param filedName 属性名称
     * @param filedType 属性class类型
     * @param <T>       属性class
     * @return 返回对应属性值
     */
    private <T> T safeReflectGetProperty(Throwable e, String filedName, Class<T> filedType) {
        try {
            return ClassUtils.getPropertyValue(e, filedName, filedType);
        } catch (Exception e1) {
            return null;
        }
    }

    /**
     * 设置异常Type属性，以及特殊type类型转换
     *
     * @param type 异常类型
     * @param code code码
     * @return 目标异常类型
     */
    public static Integer getCodeType(Integer type, Integer code) {

        if (code == null) {
            return null;
        }

        type = MessageCodeTypeEnum.getTypeByCode(code, type);

        return type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FinhubExceptionProperty {

        /**
         * code码
         */
        private Integer code;

        /**
         * 异常类型
         */
        private Integer type;

        /**
         * 提示语
         */
        private String title;

        /**
         * 异常返回内容
         */
        private Object data;

    }
}

package com.finhub.framework.core.converter;

import com.finhub.framework.core.thread.ThreadLocalUtils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Mapstruct 上下文
 * 方便在 Converter 执行时获取 Spring 上下文、应用上下文等
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023/05/30 14:15
 */
@Slf4j
@UtilityClass
public class MapstructContextHolder {

    /**
     * 设置上下文
     *
     * @param context
     */
    public static void putContext(Object context) {
        ThreadLocalUtils.putMapStructContext(context);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Object getContext() {
        return ThreadLocalUtils.getMapStructContext();
    }

    /**
     * 删除上下文
     */
    public static void removeContext() {
        ThreadLocalUtils.removeMapStructContext();
    }
}

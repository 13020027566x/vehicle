package com.finhub.framework.exception.util;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : liuwei
 * @version 1.0.0
 * @since 2021/06/30 19:07
 */
@Slf4j
@UtilityClass
public final class ClassUtils {

    public static <T, V> V getPropertyValue(T obj, String property, Class<V> vClass) {

        if (obj == null || StrUtil.isBlank(property) || vClass == null) {
            return null;
        }

        Field field = ReflectionUtils.findField(obj.getClass(), property);

        if (field == null) {
            return null;
        }

        Object o = null;
        try {
            //设置访问权限
            field.setAccessible(true);
            o = field.get(obj);
        } catch (SecurityException e) {
            log.warn("{}.{} property cannot access error", vClass, property, e);
        } catch (IllegalAccessException e) {
            log.warn("Cannot reflect {}.{} property value error", vClass, property, e);
        }
        return (V) o;
    }

    /**
     * 获取目标类的所有父类
     *
     * @param clazz
     * @return
     */
    public static Set<Class<?>> getSuperClass(Class<?> clazz) {
        Set<Class<?>> classSet = new HashSet<>();

        Class<?> superClass = clazz.getSuperclass();
        while (superClass != null) {
            classSet.add(superClass);
            superClass = superClass.getSuperclass();
        }

        return classSet;
    }
}

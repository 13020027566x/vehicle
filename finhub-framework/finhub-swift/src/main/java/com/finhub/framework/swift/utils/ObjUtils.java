package com.finhub.framework.swift.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Map;

/**
 * @Author shuangfei.chen
 * @Description ObjUtils
 * @Date 2021/8/16 16:22
 **/
@Slf4j
@UtilityClass
public class ObjUtils {

    private static final String NULL_VALUE = "NULL";
    private static final String BOOLEAN_TRUE = "TRUE";
    private static final String BOOLEAN_FALSE = "FALSE";
    private static final String BOOLEAN_T = "T";
    private static final String BOOLEAN_F = "F";
    private static final String BOOLEAN_Y = "Y";
    private static final String BOOLEAN_N = "N";
    private static final String BOOLEAN_1 = "1";
    private static final String BOOLEAN_0 = "0";

    public static <T> T ifNull(T object, T defaultValue) {
        return object == null ? defaultValue : object;
    }

    public static <T> T ifEmpty(T object, T defaultValue) {
        return isEmpty(object) ? defaultValue : object;
    }

    public static boolean isNull(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String && NULL_VALUE.equalsIgnoreCase((String) value)) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            if (StringUtils.isEmpty((String) value) || NULL_VALUE.equalsIgnoreCase((String) value)) {
                return true;
            }
        } else if (value instanceof Collection && CollectionUtil.isEmpty((Collection) value)) {
            return true;
        } else if (value instanceof Map && MapUtils.isEmpty((Map) value)) {
            return true;
        }
        return false;
    }

    public static Integer toInteger(Object obj) {
        if (isEmpty(obj)) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        // 处理字符串值
        String strValue = obj.toString().trim();
        try {
            Number number = NumberFormat.getNumberInstance().parse(strValue);
            return number.intValue();
        } catch (Exception e) {
        }
        return null;
    }

    public static Integer toInteger(Object obj, Integer defaultValue) {
        return ifNull(toInteger(obj), defaultValue);
    }

    public static Long toLong(Object obj) {
        if (isEmpty(obj)) {
            return null;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        // 处理字符串值
        String strValue = obj.toString().trim();
        try {
            Number number = NumberFormat.getNumberInstance().parse(strValue);
            return number.longValue();
        } catch (Exception e) {
        }
        return null;
    }

    public static Long toLong(Object obj, Long defaultValue) {
        return ifNull(toLong(obj), defaultValue);
    }


    public static BigDecimal toBigDecimal(Object obj) {
        if (ObjectUtil.isEmpty(obj)) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        // 处理字符串值
        String strValue = obj.toString().trim();
        try {
            Number number = NumberFormat.getNumberInstance().parse(strValue);
            return new BigDecimal(number.toString());
        } catch (Exception e) {
        }
        return null;
    }

    public static BigDecimal toBigDecimal(Object obj, BigDecimal defaultValue) {
        return ObjectUtil.defaultIfNull(toBigDecimal(obj), defaultValue);
    }

    public static Boolean toBoolean(Object obj) {
        if (ObjectUtil.isEmpty(obj)) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue() == 1;
        }
        // 处理字符串值
        String strValue = obj.toString().trim();
        if (BOOLEAN_TRUE.equalsIgnoreCase(strValue)
            || BOOLEAN_T.equalsIgnoreCase(strValue)
            || BOOLEAN_Y.equalsIgnoreCase(strValue)
            || BOOLEAN_1.equals(strValue)) {
            return Boolean.TRUE;
        }
        if (BOOLEAN_FALSE.equalsIgnoreCase(strValue)
            || BOOLEAN_F.equalsIgnoreCase(strValue)
            || BOOLEAN_N.equalsIgnoreCase(strValue)
            || BOOLEAN_0.equals(strValue)) {
            return Boolean.FALSE;
        }
        return null;
    }

    public static Boolean toBoolean(Object obj, Boolean defaultValue) {
        return ObjectUtil.defaultIfNull(toBoolean(obj), defaultValue);
    }

}

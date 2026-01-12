package com.finhub.framework.core.object;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.UtilException;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Map工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 * @deprecated 不再推荐使用 请使用 {@link cn.hutool.core.bean.BeanUtil} 等 hutool 工具类替代
 */
@Slf4j
@Deprecated
@UtilityClass
public class MapUtils {

    /**
     * Map转String
     *
     * @param map                new HashMap<>()
     * @param entryJoinSeparator "," or "&"
     * @param kvJoinSeparator    "="
     * @return
     */
    public static String toString(Map<String, Object> map, String entryJoinSeparator, String kvJoinSeparator) {
        if (map == null || map.size() <= 0) {
            return StrConstants.S_EMPTY;
        }
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            if (value == null) {
                value = StrConstants.S_EMPTY;
            }
            if (i != 0) {
                buffer.append(entryJoinSeparator);
            }
            buffer.append(key).append(kvJoinSeparator).append(value);
            i++;
        }
        return buffer.toString();
    }

    /**
     * String转Map
     *
     * @param inputStr            a=1&b=2&c=3
     * @param entrySplitSeparator "&"
     * @param kvSplitSeparator    "="
     * @return
     * @deprecated 不再建议使用，请使用 {@link cn.hutool.core.bean.BeanUtil#toBean(java.lang.Object, java.lang.Class)} 代替
     */
    @Deprecated
    public static Map<String, String> fromString(String inputStr, String entrySplitSeparator, String kvSplitSeparator) {
        Map<String, String> map = com.google.common.collect.Maps.newHashMap();
        if (inputStr != null && inputStr.length() > 0) {
            int ampersandIndex, lastAmpersandIndex = 0;
            String subStr, param, value;
            String[] paramPair;
            do {
                ampersandIndex = inputStr.indexOf(entrySplitSeparator, lastAmpersandIndex) + 1;
                if (ampersandIndex > 0) {
                    subStr = inputStr.substring(lastAmpersandIndex, ampersandIndex - 1);
                    lastAmpersandIndex = ampersandIndex;
                } else {
                    subStr = inputStr.substring(lastAmpersandIndex);
                }
                paramPair = subStr.split(kvSplitSeparator, 2);
                param = paramPair[0];
                value = paramPair.length == 1 ? StrConstants.S_EMPTY : paramPair[1];
                if (value == null) {
                    value = StrConstants.S_EMPTY;
                }
                map.put(param, value);
            } while (ampersandIndex > 0);
        }
        return map;
    }

    /**
     * Map转Bean
     *
     * @param type
     * @param map
     * @return
     * @deprecated 不再建议使用，请使用 {@link cn.hutool.core.bean.BeanUtil#toBean(java.lang.Object, java.lang.Class)} 代替
     */
    @Deprecated
    public static Object toBean(Map map, Class type, AbstractConverter... converters) {
        Map<String, AbstractConverter> converterMap = transferConverterMap(converters, false);
        Object obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                AbstractConverter converter = null;
                if (converterMap.containsKey(propertyName)) {
                    converter = converterMap.get(propertyName);
                    propertyName = converter.getFrom();
                }

                if (map.containsKey(propertyName)) {
                    String propertyTypeName = descriptor.getPropertyType().getSimpleName();

                    Object[] args = new Object[1];
                    Object value = map.get(propertyName);

                    if (converter != null) {
                        value = converter.convert(value);
                    }

                    if (StrUtil.equalsIgnoreCase("long", propertyTypeName)) {
                        args[0] = Long.valueOf(String.valueOf(value));
                    } else if (StrUtil.equalsIgnoreCase("int", propertyTypeName)) {
                        args[0] = Integer.valueOf(String.valueOf(value));
                    } else if (StrUtil.equalsIgnoreCase("double", propertyTypeName)) {
                        args[0] = Double.valueOf(String.valueOf(value));
                    } else if (StrUtil.equalsIgnoreCase("float", propertyTypeName)) {
                        args[0] = Float.valueOf(String.valueOf(value));
                    } else if (StrUtil.equalsIgnoreCase("short", propertyTypeName)) {
                        args[0] = Short.valueOf(String.valueOf(value));
                    } else if (StrUtil.equalsIgnoreCase("byte", propertyTypeName)) {
                        args[0] = Byte.valueOf(String.valueOf(value));
                    } else {
                        args[0] = value;
                    }

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        } catch (IllegalAccessException | IntrospectionException | InstantiationException | InvocationTargetException e) {
            throw new UtilException(e);
        }
    }

    public static Map<String, AbstractConverter> transferConverterMap(AbstractConverter[] converters, boolean isLeftSubject) {
        Map<String, AbstractConverter> converterMap = Maps.newHashMap();
        if (!Func.isEmpty(converters)) {
            for (AbstractConverter converter : converters) {
                converter.setIsLeftSubject(isLeftSubject);
                if (converter.getIsLeftSubject()) {
                    converterMap.put(converter.getFrom(), converter);
                } else {
                    converterMap.put(converter.getTo(), converter);
                }
            }
        }
        return converterMap;
    }

    /**
     * Bean 转 ObjMap
     * @param bean
     * @return
     * @deprecated 不再建议使用，请使用 {@link cn.hutool.core.bean.BeanUtil#beanToMap(java.lang.Object)} 代替
     */
    @Deprecated
    public static Map<String, Object> toObjMap(final Object bean, final Set<String> containKeys, final boolean isUnderCamel) {
        Map<String, Object> returnMap = Maps.newHashMap();
        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String keyName = isUnderCamel ? StrUtil.toUnderlineCase(descriptor.getName()) : descriptor.getName();
                if (!StrUtil.equals("class", keyName) && containKeys.contains(keyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object value = readMethod.invoke(bean, new Object[0]);
                    returnMap.put(keyName, value);
                }
            }
            return returnMap;
        } catch (Exception e) {
            throw new UtilException(e);
        }
    }

    /**
     * Bean 转 ObjMap
     *
     * @param bean
     * @return
     * @deprecated 不再建议使用，请使用 {@link cn.hutool.core.bean.BeanUtil#beanToMap(java.lang.Object)} 代替
     */
    @Deprecated
    public static Map<String, Object> toObjMap(Object bean, AbstractConverter... converters) {
        Map<String, AbstractConverter> converterMap = transferConverterMap(converters, true);
        Map<String, Object> returnMap = Maps.newHashMap();
        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!StrUtil.equals("class", propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);

                    if (converterMap.containsKey(propertyName)) {
                        AbstractConverter converter = converterMap.get(propertyName);
                        propertyName = converter.getTo();
                        result = converter.convert(result);
                    }
                    returnMap.put(propertyName, result);
                }
            }
            return returnMap;
        } catch (Exception e) {
            throw new UtilException(e);
        }
    }

    /**
     * Bean转StrMap
     *
     * @param bean
     * @return
     * @deprecated 不再建议使用，请使用 {@link cn.hutool.core.bean.BeanUtil#beanToMap(java.lang.Object)} 代替
     */
    @Deprecated
    public static Map<String, String> toStrMap(Object bean) {
        Map<String, String> returnMap = Maps.newHashMap();

        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!StrUtil.equals("class", propertyName)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);

                    if (result != null) {
                        returnMap.put(propertyName, result.toString());
                    } else {
                        returnMap.put(propertyName, StrConstants.S_EMPTY);
                    }
                }
            }
            return returnMap;
        } catch (Exception e) {
            throw new UtilException(e);
        }
    }
}

package com.finhub.framework.swift.property.impl;

import com.finhub.framework.swift.property.ItfConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.regex.Matcher;

public class ItfConfigImpl implements ItfConfig {

    private final Environment environment;

    public ItfConfigImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getString(String key) {
        return environment.getProperty(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    @Override
    public String[] getStringArray(String key) {
        return getStringArray(key, null);
    }

    @Override
    public String[] getStringArray(String key, String sep) {
        String value = getAndProcessValue(key);
        if (value == null) {
            return null;
        }
        if (sep == null) {
            if (value.contains(";")) {
                sep = ";";
            } else {
                sep = ",";
            }
        }
        // 将分隔取到的值trim一下
        return Arrays.stream(value.split(sep)).map(v -> StringUtils.trim(v)).toArray(String[]::new);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        String value = getAndProcessValue(key);
        if (value == null) {
            return defaultValue;
        }
        return NumberUtils.toInt(value, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        String value = getAndProcessValue(key);
        if (value == null) {
            return defaultValue;
        }
        return NumberUtils.toLong(value, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        String value = getAndProcessValue(key);
        if (value == null) {
            return defaultValue;
        }
        return NumberUtils.toFloat(value, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        String value = getAndProcessValue(key);
        if (value == null) {
            return defaultValue;
        }
        return NumberUtils.toDouble(value, defaultValue);
    }



    private String getAndProcessValue(String key) {
        String value = environment.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        Matcher matcher = ItfConfig.VALUE_RESOLVER_PATTERN.matcher(value);
        while (matcher.find()) {
            String resolveKey = StringUtils.substringBetween(matcher.group(), "${", "}");
            String resolveValue = environment.getProperty(resolveKey);
            if (StringUtils.isNotBlank(resolveValue)) {
                matcher.appendReplacement(result, resolveValue);
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }
}

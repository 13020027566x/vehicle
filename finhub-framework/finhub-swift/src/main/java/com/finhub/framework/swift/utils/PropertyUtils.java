package com.finhub.framework.swift.utils;

import java.util.Map;

public final class PropertyUtils {

    public static final String MSG_PREFIX = "msg.";

    private PropertyUtils() {
    }

    public static String getString(String key) {
        return ItfConfigUtils.getInstance().getString(key);
    }

    public static String getString(String key, String defaultVal) {
        return ItfConfigUtils.getInstance().getString(key, defaultVal);
    }

    public static String[] getStringArray(String key) {
        return ItfConfigUtils.getInstance().getStringArray(key);
    }

    public static String[] getStringArray(String key, String sep) {
        return ItfConfigUtils.getInstance().getStringArray(key, sep);
    }

    public static int getInt(String key) {
        return ItfConfigUtils.getInstance().getInt(key, 0);
    }

    public static int getInt(String key, int defaultVal) {
        return ItfConfigUtils.getInstance().getInt(key, defaultVal);
    }

    public static long getLong(String key) {
        return ItfConfigUtils.getInstance().getLong(key, -1L);
    }

    public static long getLong(String key, long defaultVal) {
        return ItfConfigUtils.getInstance().getLong(key, defaultVal);
    }

    public static double getDouble(String key) {
        return ItfConfigUtils.getInstance().getDouble(key, -1D);
    }

    public static double getDouble(String key, double defaultVal) {
        return ItfConfigUtils.getInstance().getDouble(key, defaultVal);
    }

    public static String getMessage(int code) {
        return getString(MSG_PREFIX + code, "");
    }

    public static String getMessage(int code, Map<String, Object> args) {
        return MsgStrUtil.formatString(getMessage(code), args);
    }

    public static String getMessage(String key, Map<String, Object> args) {
        return MsgStrUtil.formatString(getString(key, ""), args);
    }
}

package com.finhub.framework.i18n.constant;

import java.util.Locale;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:21
 */
public enum LocaleTypeEnum {

    ZH_CN("zh_CN", Locale.CHINA),
    ZH_TW("zh_TW", Locale.TRADITIONAL_CHINESE),
    EN_US("en_US", Locale.US),
    EN_GB("en_GB", Locale.UK),
    EN_CA("en_CA", Locale.CANADA);

    private Locale value;
    private String key;

    LocaleTypeEnum(String key, Locale value) {
        this.key = key;
        this.value = value;
    }

    public static Locale getValue(String key) {
        for (LocaleTypeEnum c : LocaleTypeEnum.values()) {
            if (c.getKey().equalsIgnoreCase(key)) {
                return c.getValue();
            }
        }
        return null;
    }

    public Locale getValue() {
        return value;
    }

    public void setValue(Locale value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

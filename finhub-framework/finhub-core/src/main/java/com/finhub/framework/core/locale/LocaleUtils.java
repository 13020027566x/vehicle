package com.finhub.framework.core.locale;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 语言工具类
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Slf4j
@UtilityClass
public class LocaleUtils {

    /**
     * 获取当前语言
     * @return
     */
    public static String getLang() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            return "zh_CN";
        }

        return locale.toString();
    }

    /**
     * 获取当前语言
     * @return
     */
    public static Locale getLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            return Locale.SIMPLIFIED_CHINESE;
        }

        return locale;
    }
}

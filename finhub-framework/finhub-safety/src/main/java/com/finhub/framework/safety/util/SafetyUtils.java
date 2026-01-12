package com.finhub.framework.safety.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 安全工具类
 * <p>
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/08/17 15:28
 */
@Slf4j
@UtilityClass
public class SafetyUtils {

    public static String getPrefix(String message) {
        return message.substring(0, message.indexOf("(") + 1);
    }
}

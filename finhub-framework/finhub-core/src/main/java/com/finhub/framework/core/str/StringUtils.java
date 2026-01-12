package com.finhub.framework.core.str;

import com.finhub.framework.core.Func;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import static com.finhub.framework.core.str.StrConstants.S_EMPTY;

/**
 * @author : liuwei
 * @date : 2022/1/19
 * @desc :
 */
@UtilityClass
public final class StringUtils extends StrUtil {

    /**
     * 将多个字符串 拼接字符串
     *
     * @param strings 字符串集合
     * @return        拼接结果字符串
     */
    public static String appender(String... strings) {
        return appender(false, null, strings);
    }

    /**
     * 将多个字符串 拼接字符串&根据 @param#separator进行分隔
     *
     * @param separator 分隔符
     * @param strings   字符串集合
     * @return          拼接结果字符串
     */
    public static String appender(char separator, String... strings) {
        return appender(true, separator, strings);
    }

    /**
     * 将多个字符串 拼接字符串&根据 @param#separator进行分隔
     *
     * @param separator 分隔符
     * @param strings   字符串集合
     * @return          拼接结果字符串
     */
    private static String appender(boolean needSeparator,Character separator, String... strings) {
        if (ArrayUtil.isEmpty(strings)) {
            return S_EMPTY;
        }

        StringBuilder builder = new StringBuilder();
        int length = strings.length;

        for (int i = 0; i < length; i++) {
            if (Func.isBlank(strings[i])) {
                builder.append(StrConstants.S_NULL);
            } else {
                builder.append(strings[i]);
            }
            if (needSeparator && i != length - 1) {
                builder.append(separator);
            }
        }

        return builder.toString();
    }

    /**
     * 调用对象的toString方法，null会返回{@code ''}
     *
     * @param obj 对象
     * @return 字符串 or {@code ''}
     */
    public static String toStringOrEmpty(Object obj) {
        return null == obj ? S_EMPTY : obj.toString();
    }
}

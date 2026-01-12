package com.finhub.framework.core.charset;


import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 字符集工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@UtilityClass
public class CharsetUtils {

    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * GBK
     */
    public static final String GBK = "GBK";

    public static final String DEFAULT_ENCODE = UTF_8;

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = Charset.forName(ISO_8859_1);
    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = Charset.forName(UTF_8);
    /**
     * GBK
     */
    public static final Charset CHARSET_GBK = Charset.forName(GBK);

    public static final Charset DEFAULT_CHARSET = CHARSET_UTF_8;

    /**
     * 转换为Charset对象
     *
     * @param charset 字符集，为空则返回默认字符集
     * @return Charset
     */
    public static Charset charset(final String charset) {
        return StrUtil.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset);
    }

    /**
     * 转换字符串的字符集编码
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(final String source, final String srcCharset, final String destCharset) {
        return convert(source, Charset.forName(srcCharset), Charset.forName(destCharset));
    }

    /**
     * 转换字符串的字符集编码
     *
     * @param source      字符串
     * @param srcCharset  源字符集，默认ISO-8859-1
     * @param destCharset 目标字符集，默认UTF-8
     * @return 转换后的字符集
     */
    public static String convert(final String source, final Charset srcCharset, final Charset destCharset) {
        Charset srcNewCharset = srcCharset;
        Charset destNewCharset = destCharset;
        if (null == srcNewCharset) {
            srcNewCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destNewCharset) {
            destNewCharset = StandardCharsets.UTF_8;
        }

        if (StrUtil.isBlank(source) || srcNewCharset.equals(destNewCharset)) {
            return source;
        }
        return new String(source.getBytes(srcNewCharset), destNewCharset);
    }

    /**
     * @return 系统字符集编码
     */
    public static String systemCharset() {
        return Charset.defaultCharset().name();
    }

}

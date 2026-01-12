package com.finhub.framework.core.cache;

import cn.hutool.core.util.CharsetUtil;
import com.finhub.framework.core.str.StrConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * 实现新的key generator
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class StringKeyGenerator implements KeyGenerator {

    /**
     * 最大字符串长度
     */
    private static final int MAX_KEY_SIZE = 200;

    @Override
    public Object generate(final Object target, final Method method, final Object... params) {
        StringBuilder sb = new StringBuilder();
        // 增加方法名作为key，防止冲突
        sb.append(method.getName());
        for (Object o : params) {
            if (o == null) {
                sb.append(StrConstants.S_NULL).append(".");
                continue;
            }
            sb.append(o.toString());
            sb.append('.');
        }
        String newKey = sb.toString().replaceAll("\\p{Cntrl}]|\\p{Space}", "_");
        try {
            byte[] newKeyBytes = newKey.getBytes(CharsetUtil.defaultCharsetName());
            if (newKeyBytes.length >= MAX_KEY_SIZE) {
                return newKey.substring(20, 60) + this.betterHashcode(newKey);
            } else {
                return newKey;
            }
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException.", e);
        }
        return null;
    }

    public String betterHashcode(final String str) {
        long h = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 257 * h + str.charAt(i);
        }
        return String.valueOf(h);
    }

}

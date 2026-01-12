package com.finhub.framework.safety.jayspt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;

/**
 * 自定义加密属性解析器（默认会采用 DefaultPropertyResolver）
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
public class CustomEncryptablePropertyResolver implements EncryptablePropertyResolver {

    private final EncryptablePropertyResolver defaultPropertyResolver;

    public CustomEncryptablePropertyResolver(EncryptablePropertyResolver defaultPropertyResolver) {
        this.defaultPropertyResolver = defaultPropertyResolver;
    }

    @Override
    public String resolvePropertyValue(String value) {
        return defaultPropertyResolver.resolvePropertyValue(value);
    }
}

package com.finhub.framework.safety.jayspt;

import com.finhub.framework.safety.property.SafetyProperties;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

/**
 * 自定义加/解密器（默认会采用 PooledPBEStringEncryptor）
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Slf4j
public class CustomStringEncryptor implements StringEncryptor {

    private final JaysptAdapter jaysptAdapter;

    public static CustomStringEncryptor me() {
        return SpringUtil.getBean(CustomStringEncryptor.class);
    }

    public CustomStringEncryptor(JaysptAdapter jaysptAdapter) {
        this.jaysptAdapter = jaysptAdapter;
    }

    @Override
    public String encrypt(String message) {
        String messageWithPrefix = StrUtil.format("{}({})", SafetyProperties.me().getEncryptMethod(), message);
        EncryptablePropertyDetector detector = jaysptAdapter.getEncryptablePropertyDetector(messageWithPrefix);
        StringEncryptor encryptor = jaysptAdapter.getStringEncryptor(messageWithPrefix);

        if (detector == null || encryptor == null) {
            return message;
        }

        String encryptMessage = encryptor.encrypt(message);
        return jaysptAdapter.getDefaultPropertyDetector().isEncrypted(messageWithPrefix) ? StrUtil.format("{}({})", SafetyProperties.me().getEncryptMethod(), encryptMessage) : encryptMessage;
    }

    @Override
    public String decrypt(String message) {
        EncryptablePropertyDetector detector = jaysptAdapter.getEncryptablePropertyDetector(message);
        StringEncryptor encryptor = jaysptAdapter.getStringEncryptor(message);

        if (detector == null || encryptor == null) {
            return message;
        }

        return encryptor.decrypt(detector.unwrapEncryptedValue(message));
    }
}

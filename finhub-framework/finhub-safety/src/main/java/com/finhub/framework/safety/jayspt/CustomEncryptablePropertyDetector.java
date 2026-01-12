package com.finhub.framework.safety.jayspt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;

/**
 * 自定义加密属性检测器（默认会采用 DefaultPropertyDetector）
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
public class CustomEncryptablePropertyDetector implements EncryptablePropertyDetector {

    private final JaysptAdapter jaysptAdapter;

    public CustomEncryptablePropertyDetector(JaysptAdapter jaysptAdapter) {
        this.jaysptAdapter = jaysptAdapter;
    }

    @Override
    public boolean isEncrypted(String message) {
        EncryptablePropertyDetector encryptablePropertyDetector = jaysptAdapter.getEncryptablePropertyDetector(message);
        return encryptablePropertyDetector != null && encryptablePropertyDetector.isEncrypted(message);
    }

    @Override
    public String unwrapEncryptedValue(String message) {
        return message;
    }
}

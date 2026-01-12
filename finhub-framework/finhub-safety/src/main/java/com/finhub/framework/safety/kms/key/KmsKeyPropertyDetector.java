package com.finhub.framework.safety.kms.key;

import com.finhub.framework.safety.jayspt.AvailableStringEncryptor;

import com.ulisesbocchio.jasyptspringboot.detector.DefaultPropertyDetector;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

/**
 * Ali KMS 密钥 加密属性检测器
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Slf4j
public class KmsKeyPropertyDetector extends DefaultPropertyDetector implements AvailableStringEncryptor {

    public static final String PREFIX = "KMS-ENC(";

    public static final String SUFFIX = ")";

    private final StringEncryptor stringEncryptor;

    public KmsKeyPropertyDetector(StringEncryptor stringEncryptor) {
        super(PREFIX, SUFFIX);
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public boolean isEncrypted(String message) {
        return super.isEncrypted(message);
    }

    @Override
    public String unwrapEncryptedValue(String message) {
        return super.unwrapEncryptedValue(message);
    }

    @Override
    public StringEncryptor availableStringEncryptor() {
        return stringEncryptor;
    }
}

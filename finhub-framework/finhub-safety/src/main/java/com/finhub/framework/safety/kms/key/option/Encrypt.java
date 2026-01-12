package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.EncryptRequest;
import com.aliyun.kms20160120.models.EncryptResponse;

/**
 * 加密
 */
public class Encrypt {

    public static EncryptResponse execute(String keyId, String plaintext) {
        EncryptRequest request = new EncryptRequest();
        request.setKeyId(keyId);
        request.setPlaintext(plaintext);

        return KmsClient.execute4v20("encrypt", request);
    }

    public static EncryptResponse execute(String plaintext) {
        return execute(SafetyProperties.me().getKmsKeyId(), plaintext);
    }
}

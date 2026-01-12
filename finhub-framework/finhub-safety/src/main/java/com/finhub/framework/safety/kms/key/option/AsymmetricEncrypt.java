package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.AsymmetricEncryptRequest;
import com.aliyun.kms20160120.models.AsymmetricEncryptResponse;

/**
 * 非对称加密
 */
public class AsymmetricEncrypt {

    public static AsymmetricEncryptResponse execute(String keyId, String keyVersionId, String plaintext, String algorithm) {
        AsymmetricEncryptRequest request = new AsymmetricEncryptRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);
        request.setPlaintext(plaintext);
        request.setAlgorithm(algorithm);

        return KmsClient.execute4v20("asymmetricEncrypt", request);
    }

    public static AsymmetricEncryptResponse execute(String keyVersionId, String plaintext, String algorithm) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId, plaintext, algorithm);
    }
}

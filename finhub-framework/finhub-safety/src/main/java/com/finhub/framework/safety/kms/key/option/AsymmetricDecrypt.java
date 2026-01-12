package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.AsymmetricDecryptRequest;
import com.aliyun.kms20160120.models.AsymmetricDecryptResponse;

/**
 * 非对称解密
 */
public class AsymmetricDecrypt {

    public static AsymmetricDecryptResponse execute(String keyId, String keyVersionId, String algorithm, String ciphertextBlob) {
        AsymmetricDecryptRequest request = new AsymmetricDecryptRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);
        request.setAlgorithm(algorithm);
        request.setCiphertextBlob(ciphertextBlob);

        return KmsClient.execute4v20("asymmetricDecrypt", request);
    }

    public static AsymmetricDecryptResponse execute(String keyVersionId, String algorithm, String ciphertextBlob) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId, algorithm, ciphertextBlob);
    }
}

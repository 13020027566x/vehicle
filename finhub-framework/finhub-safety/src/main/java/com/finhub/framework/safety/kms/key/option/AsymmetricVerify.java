package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.AsymmetricVerifyRequest;
import com.aliyun.kms20160120.models.AsymmetricVerifyResponse;

/**
 * 非对称签名验证
 */
public class AsymmetricVerify {

    public static AsymmetricVerifyResponse execute(String keyId, String keyVersionId, String algorithm, String digest, String value) {
        AsymmetricVerifyRequest request = new AsymmetricVerifyRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);
        request.setAlgorithm(algorithm);
        request.setDigest(digest);
        request.setValue(value);

        return KmsClient.execute4v20("asymmetricVerify", request);
    }

    public static AsymmetricVerifyResponse execute(String keyVersionId, String algorithm, String digest, String value) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId, algorithm, digest, value);
    }
}

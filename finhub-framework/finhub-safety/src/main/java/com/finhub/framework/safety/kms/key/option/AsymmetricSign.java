package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.AsymmetricSignRequest;
import com.aliyun.kms20160120.models.AsymmetricSignResponse;

/**
 * 非对称签名
 */
public class AsymmetricSign {

    public static AsymmetricSignResponse execute(String keyId, String keyVersionId, String algorithm, String digest) {
        AsymmetricSignRequest request = new AsymmetricSignRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);
        request.setAlgorithm(algorithm);
        request.setDigest(digest);

        return KmsClient.execute4v20("asymmetricSign", request);
    }

    public static AsymmetricSignResponse execute(String keyVersionId, String algorithm, String digest) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId, algorithm, digest);
    }
}

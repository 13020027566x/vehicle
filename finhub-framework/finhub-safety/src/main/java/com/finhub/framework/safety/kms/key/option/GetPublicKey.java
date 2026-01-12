package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.GetPublicKeyRequest;
import com.aliyun.kms20160120.models.GetPublicKeyResponse;

/**
 * 获取非对称密钥公钥
 */
public class GetPublicKey {

    public static GetPublicKeyResponse execute(String keyId, String keyVersionId) {
        GetPublicKeyRequest request = new GetPublicKeyRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);

        return KmsClient.execute4v20("getPublicKey", request);
    }

    public static GetPublicKeyResponse execute(String keyVersionId) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId);
    }
}

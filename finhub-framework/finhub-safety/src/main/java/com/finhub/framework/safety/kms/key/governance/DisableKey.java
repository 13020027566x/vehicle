package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.DisableKeyRequest;
import com.aliyun.kms20160120.models.DisableKeyResponse;

/**
 * 禁用主密钥
 */
public class DisableKey {

    public static DisableKeyResponse execute(String keyId) {
        DisableKeyRequest request = new DisableKeyRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("disableKey", request);
    }

    public static DisableKeyResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

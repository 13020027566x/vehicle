package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.EnableKeyRequest;
import com.aliyun.kms20160120.models.EnableKeyResponse;

/**
 * 启用主密钥
 */
public class EnableKey {

    public static EnableKeyResponse execute(String keyId) {
        EnableKeyRequest request = new EnableKeyRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("enableKey", request);
    }

    public static EnableKeyResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

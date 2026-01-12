package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.GenerateDataKeyRequest;
import com.aliyun.kms20160120.models.GenerateDataKeyResponse;

/**
 * 生成数据密钥
 */
public class GenerateDataKey {

    public static GenerateDataKeyResponse execute(String keyId) {
        GenerateDataKeyRequest request = new GenerateDataKeyRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("generateDataKey", request);
    }

    public static GenerateDataKeyResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

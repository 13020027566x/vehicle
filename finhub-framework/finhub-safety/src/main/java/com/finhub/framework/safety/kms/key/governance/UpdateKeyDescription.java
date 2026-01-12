package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.UpdateKeyDescriptionRequest;
import com.aliyun.kms20160120.models.UpdateKeyDescriptionResponse;

/**
 * 更新主密钥信息
 */
public class UpdateKeyDescription {

    public static UpdateKeyDescriptionResponse execute(String keyId, String description) {
        UpdateKeyDescriptionRequest request = new UpdateKeyDescriptionRequest();
        request.setKeyId(keyId);
        request.setDescription(description);

        return KmsClient.execute4v20("updateKeyDescription", request);
    }

    public static UpdateKeyDescriptionResponse execute(String description) {
        return execute(SafetyProperties.me().getKmsKeyId(), description);
    }
}

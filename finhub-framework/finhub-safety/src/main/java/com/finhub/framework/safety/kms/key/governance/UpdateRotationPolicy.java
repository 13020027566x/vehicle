package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.UpdateRotationPolicyRequest;
import com.aliyun.kms20160120.models.UpdateRotationPolicyResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 更新主密钥轮转策略
 */
@Slf4j
public class UpdateRotationPolicy {

    public static UpdateRotationPolicyResponse execute(String keyId, Boolean enableAutomaticRotation, String rotationInterval) {
        UpdateRotationPolicyRequest request = new UpdateRotationPolicyRequest();
        request.setKeyId(keyId);
        request.setEnableAutomaticRotation(enableAutomaticRotation);
        request.setRotationInterval(rotationInterval);

        return KmsClient.execute4v20("updateRotationPolicy", request);
    }

    public static UpdateRotationPolicyResponse execute(Boolean enableAutomaticRotation, String rotationInterval) {
        return execute(SafetyProperties.me().getKmsKeyId(), enableAutomaticRotation, rotationInterval);
    }
}

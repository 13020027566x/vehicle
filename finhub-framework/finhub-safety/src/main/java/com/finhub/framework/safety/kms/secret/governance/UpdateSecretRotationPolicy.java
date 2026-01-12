package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.UpdateSecretRotationPolicyRequest;
import com.aliyun.kms20160120.models.UpdateSecretRotationPolicyResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 更新动态凭据的轮转策略（目前只有托管RDS凭据），普通凭据不可使用
 */
@Slf4j
public class UpdateSecretRotationPolicy {

    public static UpdateSecretRotationPolicyResponse execute(String secretName, Boolean enableAutomaticRotation, String rotationInterval) {
        UpdateSecretRotationPolicyRequest request = new UpdateSecretRotationPolicyRequest();
        request.setSecretName(secretName);
        request.setEnableAutomaticRotation(enableAutomaticRotation);
        request.setRotationInterval(rotationInterval);

        return KmsClient.execute4v20("updateSecretRotationPolicy", request);
    }
}

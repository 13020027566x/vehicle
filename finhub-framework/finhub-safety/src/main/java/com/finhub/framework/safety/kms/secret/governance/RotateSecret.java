package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.RotateSecretRequest;
import com.aliyun.kms20160120.models.RotateSecretResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 主动轮转动态凭据（目前只有托管RDS凭据），普通凭据不可使用
 */
@Slf4j
public class RotateSecret {

    public static RotateSecretResponse execute(String secretName, String versionId) {
        RotateSecretRequest request = new RotateSecretRequest();
        request.setSecretName(secretName);
        request.setVersionId(versionId);

        return KmsClient.execute4v20("rotateSecret", request);
    }
}

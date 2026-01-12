package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.RestoreSecretRequest;
import com.aliyun.kms20160120.models.RestoreSecretResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 恢复被删除的凭据
 */
@Slf4j
public class RestoreSecret {

    public static RestoreSecretResponse execute(String secretName) {
        RestoreSecretRequest request = new RestoreSecretRequest();
        request.setSecretName(secretName);

        return KmsClient.execute4v20("restoreSecret", request);
    }
}

package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.CreateSecretRequest;
import com.aliyun.kms20160120.models.CreateSecretResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建凭据
 */
@Slf4j
public class CreateSecret {

    public static CreateSecretResponse execute(String secretName, String versionId, String secretData) {
        CreateSecretRequest request = new CreateSecretRequest();
        request.setSecretName(secretName);
        request.setVersionId(versionId);
        request.setSecretData(secretData);

        return KmsClient.execute4v20("createSecret", request);
    }
}

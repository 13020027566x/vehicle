package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.PutSecretValueRequest;
import com.aliyun.kms20160120.models.PutSecretValueResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 为凭据存入一个新版本的凭据值
 */
@Slf4j
public class PutSecretValue {

    public static PutSecretValueResponse execute(String versionId, String secretName, String secretData) {
        PutSecretValueRequest request = new PutSecretValueRequest();
        request.setVersionId(versionId);
        request.setSecretName(secretName);
        request.setSecretData(secretData);

        return KmsClient.execute4v20("putSecretValue", request);
    }
}

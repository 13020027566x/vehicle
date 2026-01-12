package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.DeleteSecretRequest;
import com.aliyun.kms20160120.models.DeleteSecretResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 删除凭据
 */
@Slf4j
public class DeleteSecret {

    public static DeleteSecretResponse execute(String secretName) {
        DeleteSecretRequest request = new DeleteSecretRequest();
        request.setSecretName(secretName);

        return KmsClient.execute4v20("deleteSecret", request);
    }
}

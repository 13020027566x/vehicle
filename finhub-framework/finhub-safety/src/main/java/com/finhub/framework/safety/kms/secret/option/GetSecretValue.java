package com.finhub.framework.safety.kms.secret.option;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.GetSecretValueRequest;
import com.aliyun.kms20160120.models.GetSecretValueResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取凭据值
 */
@Slf4j
public class GetSecretValue {

    public static GetSecretValueResponse execute(String secretName) {
        GetSecretValueRequest request = new GetSecretValueRequest();
        request.setSecretName(secretName);

        return KmsClient.execute4v20("getSecretValue", request);
    }
}

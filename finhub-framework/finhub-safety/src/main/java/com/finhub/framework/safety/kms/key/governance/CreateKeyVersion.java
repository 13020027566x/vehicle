package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.CreateKeyVersionRequest;
import com.aliyun.kms20160120.models.CreateKeyVersionResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 为主密钥创建密钥版本
 */
@Slf4j
public class CreateKeyVersion {

    public static CreateKeyVersionResponse execute(String keyId) {
        CreateKeyVersionRequest request = new CreateKeyVersionRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("createKeyVersion", request);
    }

    public static CreateKeyVersionResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

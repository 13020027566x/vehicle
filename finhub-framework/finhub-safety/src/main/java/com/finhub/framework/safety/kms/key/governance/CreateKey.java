package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.CreateKeyRequest;
import com.aliyun.kms20160120.models.CreateKeyResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建主密钥
 */
@Slf4j
public class CreateKey {

    public static CreateKeyResponse execute() {
        CreateKeyRequest request = new CreateKeyRequest();

        return KmsClient.execute4v20("createKey", request);
    }
}

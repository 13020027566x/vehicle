package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.CancelKeyDeletionRequest;
import com.aliyun.kms20160120.models.CancelKeyDeletionResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 取消主密钥删除
 */
@Slf4j
public class CancelKeyDeletion {

    public static CancelKeyDeletionResponse execute(String keyId) {
        CancelKeyDeletionRequest request = new CancelKeyDeletionRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("cancelKeyDeletion", request);
    }

    public static CancelKeyDeletionResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

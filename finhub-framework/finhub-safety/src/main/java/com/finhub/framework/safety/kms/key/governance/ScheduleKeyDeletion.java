package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.ScheduleKeyDeletionRequest;
import com.aliyun.kms20160120.models.ScheduleKeyDeletionResponse;

/**
 * 计划删除主密钥
 */
public class ScheduleKeyDeletion {

    public static ScheduleKeyDeletionResponse execute(String keyId, Integer pendingWindowInDays) {
        ScheduleKeyDeletionRequest request = new ScheduleKeyDeletionRequest();
        request.setKeyId(keyId);
        request.setPendingWindowInDays(pendingWindowInDays);

        return KmsClient.execute4v20("scheduleKeyDeletion", request);
    }

    public static ScheduleKeyDeletionResponse execute(Integer pendingWindowInDays) {
        return execute(SafetyProperties.me().getKmsKeyId(), pendingWindowInDays);
    }
}

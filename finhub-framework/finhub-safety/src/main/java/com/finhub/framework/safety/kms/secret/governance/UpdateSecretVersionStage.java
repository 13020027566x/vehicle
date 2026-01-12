package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.UpdateSecretVersionStageRequest;
import com.aliyun.kms20160120.models.UpdateSecretVersionStageResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 更新凭据版本状态
 */
@Slf4j
public class UpdateSecretVersionStage {

    public static UpdateSecretVersionStageResponse execute4RemoveFromVersion(String secretName, String versionStage, String removeFromVersion) {
        UpdateSecretVersionStageRequest request = new UpdateSecretVersionStageRequest();
        request.setSecretName(secretName);
        request.setVersionStage(versionStage);
        request.setRemoveFromVersion(removeFromVersion);

        return KmsClient.execute4v20("updateSecretVersionStage", request);
    }

    public static UpdateSecretVersionStageResponse execute4MoveToVersion(String secretName, String versionStage, String moveToVersion) {
        UpdateSecretVersionStageRequest request = new UpdateSecretVersionStageRequest();
        request.setSecretName(secretName);
        request.setVersionStage(versionStage);
        request.setMoveToVersion(moveToVersion);

        return KmsClient.execute4v20("updateSecretVersionStage", request);
    }
}

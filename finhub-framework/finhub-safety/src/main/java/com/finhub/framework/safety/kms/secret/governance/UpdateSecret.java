package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.UpdateSecretRequest;
import com.aliyun.kms20160120.models.UpdateSecretResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 更新凭据元数据
 */
@Slf4j
public class UpdateSecret {

    public static UpdateSecretResponse execute(String secretName, String description, Map<String, ?> customData) {
        UpdateSecretRequest request = new UpdateSecretRequest();
        request.setSecretName(secretName);
        request.setDescription(description);

        UpdateSecretRequest.UpdateSecretRequestExtendedConfig extendedConfig = new UpdateSecretRequest.UpdateSecretRequestExtendedConfig();
        extendedConfig.setCustomData(customData);
        request.setExtendedConfig(extendedConfig);

        return KmsClient.execute4v20("updateSecret", request);
    }
}

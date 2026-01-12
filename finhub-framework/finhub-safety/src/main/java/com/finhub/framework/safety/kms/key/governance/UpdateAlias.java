package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.UpdateAliasRequest;
import com.aliyun.kms20160120.models.UpdateAliasResponse;

/**
 * 更新主密钥别名
 */
public class UpdateAlias {

    public static UpdateAliasResponse execute(String keyId, String aliasName) {
        UpdateAliasRequest request = new UpdateAliasRequest();
        request.setKeyId(keyId);
        request.setAliasName(aliasName);

        return KmsClient.execute4v20("updateAlias", request);
    }

    public static UpdateAliasResponse execute(String aliasName) {
        return execute(SafetyProperties.me().getKmsKeyId(), aliasName);
    }
}

package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.CreateAliasRequest;
import com.aliyun.kms20160120.models.CreateAliasResponse;

/**
 * 为主密钥创建别名
 */
public class CreateAlias {

    public static CreateAliasResponse execute(String keyId, String aliasName) {
        CreateAliasRequest request = new CreateAliasRequest();
        request.setKeyId(keyId);
        request.setAliasName(aliasName);

        return KmsClient.execute4v20("createAlias", request);
    }

    public static CreateAliasResponse execute(String aliasName) {
        return execute(SafetyProperties.me().getKmsKeyId(), aliasName);
    }
}

package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.DeleteAliasRequest;
import com.aliyun.kms20160120.models.DeleteAliasResponse;

/**
 * 删除主密钥别名
 */
public class DeleteAlias {

    public static DeleteAliasResponse execute(String aliasName) {
        DeleteAliasRequest request = new DeleteAliasRequest();
        request.setAliasName(aliasName);

        return KmsClient.execute4v20("deleteAlias", request);
    }
}

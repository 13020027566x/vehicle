package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.ListSecretVersionIdsRequest;
import com.aliyun.kms20160120.models.ListSecretVersionIdsResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询凭据的所有版本信息
 */
@Slf4j
public class ListSecretVersionIds {

    public static ListSecretVersionIdsResponse execute(String secretName) {
        ListSecretVersionIdsRequest request = new ListSecretVersionIdsRequest();
        request.setSecretName(secretName);

        return KmsClient.execute4v20("listSecretVersionIds", request);
    }
}

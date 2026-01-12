package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.ListSecretsRequest;
import com.aliyun.kms20160120.models.ListSecretsResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询当前用户在当前地域创建的所有凭据
 */
@Slf4j
public class ListSecrets {

    public static ListSecretsResponse execute(Integer pageNumber, Integer pageSize) {
        ListSecretsRequest request = new ListSecretsRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        return KmsClient.execute4v20("listSecrets", request);
    }
}

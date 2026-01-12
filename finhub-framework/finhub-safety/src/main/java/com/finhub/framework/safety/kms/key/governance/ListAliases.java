package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.ListAliasesRequest;
import com.aliyun.kms20160120.models.ListAliasesResponse;

/**
 * 列出别名
 */
public class ListAliases {

    public static ListAliasesResponse execute() {
        ListAliasesRequest request = new ListAliasesRequest();

        return KmsClient.execute4v20("listAliases", request);
    }
}

package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.ListKeysRequest;
import com.aliyun.kms20160120.models.ListKeysResponse;

/**
 * 列出主密钥
 */
public class ListKeys {

    public static ListKeysResponse execute() {
        ListKeysRequest request = new ListKeysRequest();

        return KmsClient.execute4v20("listKeys", request);
    }
}

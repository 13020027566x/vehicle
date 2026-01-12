package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.ListKeyVersionsRequest;
import com.aliyun.kms20160120.models.ListKeyVersionsResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 列出主密钥的所有密钥版本
 */
@Slf4j
public class ListKeyVersions {

    public static ListKeyVersionsResponse execute(String keyId, Integer pageNumber, Integer pageSize) {
        ListKeyVersionsRequest request = new ListKeyVersionsRequest();
        request.setKeyId(keyId);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        return KmsClient.execute4v20("listKeyVersions", request);
    }

    public static ListKeyVersionsResponse execute(Integer pageNumber, Integer pageSize) {
        return execute(SafetyProperties.me().getKmsKeyId(), pageNumber, pageSize);
    }
}

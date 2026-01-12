package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.DescribeKeyRequest;
import com.aliyun.kms20160120.models.DescribeKeyResponse;

/**
 * 查询主密钥信息
 */
public class DescribeKey {

    public static DescribeKeyResponse execute(String keyId) {
        DescribeKeyRequest request = new DescribeKeyRequest();
        request.setKeyId(keyId);

        return KmsClient.execute4v20("describeKey", request);
    }

    public static DescribeKeyResponse execute() {
        return execute(SafetyProperties.me().getKmsKeyId());
    }
}

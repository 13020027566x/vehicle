package com.finhub.framework.safety.kms.key.governance;

import com.finhub.framework.safety.kms.KmsClient;
import com.finhub.framework.safety.property.SafetyProperties;

import com.aliyun.kms20160120.models.DescribeKeyVersionRequest;
import com.aliyun.kms20160120.models.DescribeKeyVersionResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询主密钥指定版本信息
 */
@Slf4j
public class DescribeKeyVersion {

    public static DescribeKeyVersionResponse execute(String keyId, String keyVersionId) {
        DescribeKeyVersionRequest request = new DescribeKeyVersionRequest();
        request.setKeyId(keyId);
        request.setKeyVersionId(keyVersionId);

        return KmsClient.execute4v20("describeKeyVersion", request);
    }

    public static DescribeKeyVersionResponse execute(String keyVersionId) {
        return execute(SafetyProperties.me().getKmsKeyId(), keyVersionId);
    }
}

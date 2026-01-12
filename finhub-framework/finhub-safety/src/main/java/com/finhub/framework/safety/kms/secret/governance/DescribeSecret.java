package com.finhub.framework.safety.kms.secret.governance;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.DescribeSecretRequest;
import com.aliyun.kms20160120.models.DescribeSecretResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询凭据的元数据信息
 */
@Slf4j
public class DescribeSecret {

    public static DescribeSecretResponse execute(String secretName) {
        DescribeSecretRequest request = new DescribeSecretRequest();
        request.setSecretName(secretName);

        return KmsClient.execute4v20("describeSecret", request);
    }
}

package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.DecryptRequest;
import com.aliyun.kms20160120.models.DecryptResponse;

/**
 * 解密
 */
public class Decrypt {

    public static DecryptResponse execute(String ciphertextBlob) {
        DecryptRequest request = new DecryptRequest();
        request.setCiphertextBlob(ciphertextBlob);

        return KmsClient.execute4v20("decrypt", request);
    }
}

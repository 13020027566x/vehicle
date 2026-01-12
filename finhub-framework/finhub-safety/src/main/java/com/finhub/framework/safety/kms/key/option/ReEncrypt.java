package com.finhub.framework.safety.kms.key.option;

import com.finhub.framework.safety.kms.KmsClient;

import com.aliyun.kms20160120.models.ReEncryptRequest;
import com.aliyun.kms20160120.models.ReEncryptResponse;

/**
 * 对密文进行转加密
 */
public class ReEncrypt {

    public static ReEncryptResponse execute(String ciphertextBlob, String destinationKeyId) {
        ReEncryptRequest request = new ReEncryptRequest();
        request.setCiphertextBlob(ciphertextBlob);
        request.setDestinationKeyId(destinationKeyId);

        return KmsClient.execute4v20("reEncrypt", request);
    }
}

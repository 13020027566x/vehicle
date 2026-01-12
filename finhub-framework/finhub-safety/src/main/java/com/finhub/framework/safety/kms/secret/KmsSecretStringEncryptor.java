package com.finhub.framework.safety.kms.secret;

import com.finhub.framework.safety.kms.secret.option.GetSecretValue;

import cn.hutool.core.util.ObjectUtil;
import com.aliyun.kms20160120.models.GetSecretValueResponse;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import static com.finhub.framework.safety.kms.secret.KmsSecretPropertyDetector.PREFIX;
import static com.finhub.framework.safety.kms.secret.KmsSecretPropertyDetector.SUFFIX;

/**
 * Ali KMS 凭据 加/解密器
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Slf4j
public class KmsSecretStringEncryptor implements StringEncryptor {

    @Override
    public String encrypt(String message) {
        throw new UnsupportedOperationException("KMS Secret 不支持加密");
    }

    @Override
    public String decrypt(String message) {
        GetSecretValueResponse response = GetSecretValue.execute(message);

        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            return response.getBody().getSecretData();
        }

        return message;
    }
}

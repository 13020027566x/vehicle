package com.finhub.framework.safety.kms.key;

import com.finhub.framework.safety.kms.key.option.Decrypt;
import com.finhub.framework.safety.kms.key.option.Encrypt;
import com.finhub.framework.safety.property.SafetyProperties;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.kms20160120.models.DecryptResponse;
import com.aliyun.kms20160120.models.EncryptResponse;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import static com.finhub.framework.safety.kms.key.KmsKeyPropertyDetector.PREFIX;
import static com.finhub.framework.safety.kms.key.KmsKeyPropertyDetector.SUFFIX;

/**
 * Ali KMS 密钥 加/解密器
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Slf4j
public class KmsKeyStringEncryptor implements StringEncryptor {

    @Override
    public String encrypt(String message) {
        message = message.trim();
        EncryptResponse response = Encrypt.execute(message);

        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            return StrUtil.format("{}{}{}", PREFIX, response.getBody().getCiphertextBlob(), SUFFIX);
        }

        return message;
    }

    @Override
    public String decrypt(String message) {
        DecryptResponse response = Decrypt.execute(message);

        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            return response.getBody().getPlaintext();
        }

        return message;
    }
}

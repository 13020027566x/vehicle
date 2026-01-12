package com.finhub.framework.safety.kms.key;

import com.finhub.framework.safety.kms.key.option.Decrypt;
import com.finhub.framework.safety.kms.key.option.GenerateDataKey;
import com.finhub.framework.safety.property.SafetyProperties;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.aliyun.kms20160120.models.DecryptResponse;
import com.aliyun.kms20160120.models.GenerateDataKeyResponse;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import static com.finhub.framework.safety.kms.key.KmsDataKeyPropertyDetector.PREFIX;
import static com.finhub.framework.safety.kms.key.KmsDataKeyPropertyDetector.SUFFIX;

/**
 * Ali KMS 数据密钥 加/解密器
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Slf4j
public class KmsDataKeyStringEncryptor implements StringEncryptor {

    @Override
    public String encrypt(String message) {
        message = message.trim();
        GenerateDataKeyResponse response = GenerateDataKey.execute();

        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            byte[] plaintextKey = Base64.decode(response.getBody().getPlaintext());
            String ciphertextBlobKey = response.getBody().getCiphertextBlob();

            String ciphertext = SecureUtil.aes(plaintextKey).encryptBase64(message);
            return StrUtil.format("{}{}@{}{}", PREFIX, ciphertext, ciphertextBlobKey, SUFFIX);
        }

        return message;
    }

    @Override
    public String decrypt(String message) {
        String[] messages = message.split("@");

        if (messages.length != 2) {
            return message;
        }

        DecryptResponse response = Decrypt.execute(messages[1]);
        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            byte[] plaintextKey = Base64.decode(response.getBody().getPlaintext());
            return SecureUtil.aes(plaintextKey).decryptStr(messages[0]);
        }

        return message;
    }
}

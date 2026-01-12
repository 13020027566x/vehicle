package com.finhub.framework.safety.kms.key;

import com.finhub.framework.safety.BaseJUnitTester;
import com.finhub.framework.safety.kms.key.option.Decrypt;
import com.finhub.framework.safety.kms.key.option.Encrypt;
import com.finhub.framework.safety.kms.key.option.GenerateDataKey;
import com.finhub.framework.safety.kms.key.option.ReEncrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.aliyun.kms20160120.models.DecryptResponse;
import com.aliyun.kms20160120.models.EncryptResponse;
import com.aliyun.kms20160120.models.GenerateDataKeyResponse;
import com.aliyun.kms20160120.models.ReEncryptResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static com.finhub.framework.safety.kms.key.KmsDataKeyPropertyDetector.PREFIX;
import static com.finhub.framework.safety.kms.key.KmsDataKeyPropertyDetector.SUFFIX;

/**
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/04 10:14
 */
@Slf4j
@EnableAutoConfiguration
public class OptionTest extends BaseJUnitTester {

    @Test
    public void testEncrypt() {
        EncryptResponse response = Encrypt.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", "Hello World!");
        log.info("Encrypt response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDecrypt() {
        DecryptResponse response = Decrypt.execute("ZmQ2MmJkZjAtZTAxZi00ZmUyLThjMDEtODgwZWQ4OWM0NDNm8BTIpKpWuhbqlQH7+FhDR/ZsmXoCAmoTjTILoqEjXAtztZZ9ym9d+w==");
        log.info("Decrypt response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testGenerateDataKey() {
        GenerateDataKeyResponse response = GenerateDataKey.execute("e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("GenerateDataKey response: {}", JSONUtil.toJsonStr(response));

        if (ObjectUtil.isNotNull(response) && response.getStatusCode() == 200) {
            byte[] plaintextKey = Base64.decode(response.getBody().getPlaintext());
            String ciphertextBlobKey = response.getBody().getCiphertextBlob();

            String ciphertext = SecureUtil.aes(plaintextKey).encryptBase64("taomingkai");
            log.info("{}{}@{}{}", PREFIX, ciphertext, ciphertextBlobKey, SUFFIX);
        }
    }

    @Test
    public void testReEncrypt() {
        ReEncryptResponse response = ReEncrypt.execute("ZmQ2MmJkZjAtZTAxZi00ZmUyLThjMDEtODgwZWQ4OWM0NDNmrFNaESFUH+VYUz+k6tEfLDEYd8gYjZZEL7KIeeU/yfLCbWds3wHdLmaf7DheSIiSw5CSZlO48dPOro3bib7nhH+4gwlSsSPE", "e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("ReEncrypt response: {}", JSONUtil.toJsonStr(response));
    }
}

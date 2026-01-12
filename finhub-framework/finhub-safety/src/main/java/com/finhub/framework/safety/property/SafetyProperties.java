package com.finhub.framework.safety.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.safety")
public class SafetyProperties {

    public static SafetyProperties me() {
        try {
            return SpringUtil.getBean(SafetyProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new SafetyProperties();
        }
    }

    /**
     * 加密方式 默认为 KMS-ENC 可选：
     * 1. KMS-ENC: KMS远程加解密（默认方式）
     * 2. KMS-DENC: KMS本地加解密（敏感数据不会上传到 Aliyun KMS Server 端）
     * 3. ENC: 使用Jasypt框架加解密（固定密钥，密钥丢失风险较大）
     */
    private String encryptMethod = "KMS-ENC";

    /**
     * 当安全方式为 kms_key 或 kms_data_key 时，需要配置以下参数
     * KMS 地域 ID
     */
    private String kmsRegionId = "cn-beijing";

    /**
     * 当安全方式为 kms_key 或 kms_data_key 时，需要配置以下参数
     * KMS 主密钥 ID
     */
    private String kmsKeyId;
}

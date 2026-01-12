package com.finhub.framework.safety.jayspt;

import cn.hutool.core.util.ObjectUtil;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import java.util.Map;

import static com.finhub.framework.safety.util.SafetyUtils.getPrefix;

/**
 * Jayspt 适配器
 *
 * 根据加密信息前后缀、及配置，选择对应的加密器和检测器
 *
 * @author Mickey
 * @version 1.0
 * @since 2023/08/23 16:07
 */
@Data
@Slf4j
public class JaysptAdapter {

    private final StringEncryptor defaultStringEncryptor;

    private final EncryptablePropertyDetector defaultPropertyDetector;

    private final Map<String, AvailableStringEncryptor> prefixAvailableStringEncryptorMap;

    public JaysptAdapter(StringEncryptor defaultStringEncryptor, EncryptablePropertyDetector defaultPropertyDetector, Map<String, AvailableStringEncryptor> prefixAvailableStringEncryptorMap) {
        this.defaultStringEncryptor = defaultStringEncryptor;
        this.defaultPropertyDetector = defaultPropertyDetector;
        this.prefixAvailableStringEncryptorMap = prefixAvailableStringEncryptorMap;
    }

    public StringEncryptor getStringEncryptor(String message) {
        String prefix = getPrefix(message);
        AvailableStringEncryptor availableStringEncryptor = prefixAvailableStringEncryptorMap.get(prefix);
        if (ObjectUtil.isNotNull(availableStringEncryptor)) {
            return availableStringEncryptor.availableStringEncryptor();
        }

        return defaultPropertyDetector.isEncrypted(message) ? defaultStringEncryptor : null;
    }

    public EncryptablePropertyDetector getEncryptablePropertyDetector(String message) {
        String prefix = getPrefix(message);
        AvailableStringEncryptor availableStringEncryptor = prefixAvailableStringEncryptorMap.get(prefix);
        if (ObjectUtil.isNotNull(availableStringEncryptor)) {
            return availableStringEncryptor;
        }

        return defaultPropertyDetector.isEncrypted(message) ? defaultPropertyDetector : null;
    }
}

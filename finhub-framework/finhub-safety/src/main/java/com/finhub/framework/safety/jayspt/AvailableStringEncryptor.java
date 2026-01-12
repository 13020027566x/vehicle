package com.finhub.framework.safety.jayspt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.jasypt.encryption.StringEncryptor;

/**
 * 可用的加/解密器接口
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/08/24 15:39
 */
public interface AvailableStringEncryptor extends EncryptablePropertyDetector {

    StringEncryptor availableStringEncryptor();
}

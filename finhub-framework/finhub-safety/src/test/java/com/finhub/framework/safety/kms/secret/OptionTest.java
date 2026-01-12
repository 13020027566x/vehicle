package com.finhub.framework.safety.kms.secret;

import com.finhub.framework.safety.BaseJUnitTester;
import com.finhub.framework.safety.kms.secret.option.GetSecretValue;

import cn.hutool.json.JSONUtil;
import com.aliyun.kms20160120.models.GetSecretValueResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/04 10:14
 */
@Slf4j
@EnableAutoConfiguration
public class OptionTest extends BaseJUnitTester {

    @Test
    public void testGetSecretValue() {
        GetSecretValueResponse response = GetSecretValue.execute("test1");
        log.info("GetSecretValue response: {}", JSONUtil.toJsonStr(response));
    }
}

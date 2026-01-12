package com.finhub.framework.safety.kms.secret;

import com.finhub.framework.safety.BaseJUnitTester;
import com.finhub.framework.safety.kms.secret.governance.CreateSecret;
import com.finhub.framework.safety.kms.secret.governance.DeleteSecret;
import com.finhub.framework.safety.kms.secret.governance.DescribeSecret;
import com.finhub.framework.safety.kms.secret.governance.ListSecretVersionIds;
import com.finhub.framework.safety.kms.secret.governance.ListSecrets;
import com.finhub.framework.safety.kms.secret.governance.PutSecretValue;
import com.finhub.framework.safety.kms.secret.governance.RestoreSecret;
import com.finhub.framework.safety.kms.secret.governance.UpdateSecret;
import com.finhub.framework.safety.kms.secret.governance.UpdateSecretRotationPolicy;
import com.finhub.framework.safety.kms.secret.governance.UpdateSecretVersionStage;

import cn.hutool.json.JSONUtil;
import com.aliyun.kms20160120.models.CreateSecretResponse;
import com.aliyun.kms20160120.models.DeleteSecretResponse;
import com.aliyun.kms20160120.models.DescribeSecretResponse;
import com.aliyun.kms20160120.models.ListSecretVersionIdsResponse;
import com.aliyun.kms20160120.models.ListSecretsResponse;
import com.aliyun.kms20160120.models.PutSecretValueResponse;
import com.aliyun.kms20160120.models.RestoreSecretResponse;
import com.aliyun.kms20160120.models.UpdateSecretResponse;
import com.aliyun.kms20160120.models.UpdateSecretRotationPolicyResponse;
import com.aliyun.kms20160120.models.UpdateSecretVersionStageResponse;
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
public class GovernanceTest extends BaseJUnitTester {

    @Test
    public void testCreateSecret() {
        CreateSecretResponse response = CreateSecret.execute("test1", "test1", "test1");
        log.info("CreateSecret response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDescribeSecret() {
        DescribeSecretResponse response = DescribeSecret.execute("test1");
        log.info("DescribeSecret response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testListSecrets() {
        ListSecretsResponse response = ListSecrets.execute(1, 10);
        log.info("ListSecrets response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testListSecretVersionIds() {
        ListSecretVersionIdsResponse response = ListSecretVersionIds.execute("test1");
        log.info("ListSecretVersionIds response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testPutSecretValue() {
        PutSecretValueResponse response = PutSecretValue.execute("test3", "test1", "test1-will-delete");
        log.info("PutSecretValue response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateSecret() {
        UpdateSecretResponse response = UpdateSecret.execute("test1", "test1", null);
        log.info("UpdateSecret response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateSecretRotationPolicy() {
        UpdateSecretRotationPolicyResponse response = UpdateSecretRotationPolicy.execute("test1", true, "7d");
        log.info("UpdateSecretRotationPolicy response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateSecretVersionStage4RemoveFromVersion() {
         UpdateSecretVersionStageResponse response = UpdateSecretVersionStage.execute4RemoveFromVersion("test1", "123", "test1");
         log.info("UpdateSecretVersionStage response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateSecretVersionStage4MoveToVersion() {
        UpdateSecretVersionStageResponse response = UpdateSecretVersionStage.execute4MoveToVersion("test1", "123456", "test1");
        log.info("UpdateSecretVersionStage response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDeleteSecret() {
        DeleteSecretResponse response = DeleteSecret.execute("test1");
        log.info("DeleteSecret response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testRestoreSecret() {
        RestoreSecretResponse response = RestoreSecret.execute("test1");
        log.info("RestoreSecret response: {}", JSONUtil.toJsonStr(response));
    }
}

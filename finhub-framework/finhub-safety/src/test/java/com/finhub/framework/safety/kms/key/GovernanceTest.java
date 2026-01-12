package com.finhub.framework.safety.kms.key;

import com.finhub.framework.safety.BaseJUnitTester;
import com.finhub.framework.safety.kms.key.governance.CancelKeyDeletion;
import com.finhub.framework.safety.kms.key.governance.CreateAlias;
import com.finhub.framework.safety.kms.key.governance.CreateKey;
import com.finhub.framework.safety.kms.key.governance.CreateKeyVersion;
import com.finhub.framework.safety.kms.key.governance.DeleteAlias;
import com.finhub.framework.safety.kms.key.governance.DescribeKey;
import com.finhub.framework.safety.kms.key.governance.DescribeKeyVersion;
import com.finhub.framework.safety.kms.key.governance.DisableKey;
import com.finhub.framework.safety.kms.key.governance.EnableKey;
import com.finhub.framework.safety.kms.key.governance.ListAliases;
import com.finhub.framework.safety.kms.key.governance.ListKeyVersions;
import com.finhub.framework.safety.kms.key.governance.ListKeys;
import com.finhub.framework.safety.kms.key.governance.ScheduleKeyDeletion;
import com.finhub.framework.safety.kms.key.governance.UpdateAlias;
import com.finhub.framework.safety.kms.key.governance.UpdateKeyDescription;
import com.finhub.framework.safety.kms.key.governance.UpdateRotationPolicy;

import cn.hutool.json.JSONUtil;
import com.aliyun.kms20160120.models.CancelKeyDeletionResponse;
import com.aliyun.kms20160120.models.CreateAliasResponse;
import com.aliyun.kms20160120.models.CreateKeyResponse;
import com.aliyun.kms20160120.models.CreateKeyVersionResponse;
import com.aliyun.kms20160120.models.DeleteAliasResponse;
import com.aliyun.kms20160120.models.DescribeKeyResponse;
import com.aliyun.kms20160120.models.DescribeKeyVersionResponse;
import com.aliyun.kms20160120.models.DisableKeyResponse;
import com.aliyun.kms20160120.models.EnableKeyResponse;
import com.aliyun.kms20160120.models.ListAliasesResponse;
import com.aliyun.kms20160120.models.ListKeyVersionsResponse;
import com.aliyun.kms20160120.models.ListKeysResponse;
import com.aliyun.kms20160120.models.ScheduleKeyDeletionResponse;
import com.aliyun.kms20160120.models.UpdateAliasResponse;
import com.aliyun.kms20160120.models.UpdateKeyDescriptionResponse;
import com.aliyun.kms20160120.models.UpdateRotationPolicyResponse;
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
    public void testCreateKey() {
        CreateKeyResponse response = CreateKey.execute();
        log.info("CreateKey response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testListKeys() {
        ListKeysResponse response = ListKeys.execute();
        log.info("ListKeys response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testCreateAlias() {
        CreateAliasResponse response = CreateAlias.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", "alias/finhub-safety-02");
        log.info("CreateAlias response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testListAliases() {
        ListAliasesResponse response = ListAliases.execute();
        log.info("ListAliases response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateAlias() {
        UpdateAliasResponse response = UpdateAlias.execute("ab4b41bf-a83f-42de-bba7-baaf00d05316", "alias/finhub-safety-01");
        log.info("UpdateAlias response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateKeyDescription() {
        UpdateKeyDescriptionResponse response = UpdateKeyDescription.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", "finhub-safety-02");
        log.info("UpdateKeyDescription response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDisableKey() {
        DisableKeyResponse response = DisableKey.execute("e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("DisableKey response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testEnableKey() {
        EnableKeyResponse response = EnableKey.execute("e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("EnableKey response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDescribeKey() {
        DescribeKeyResponse response = DescribeKey.execute("e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("DescribeKey response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDeleteAlias() {
        DeleteAliasResponse response = DeleteAlias.execute("alias/finhub-safety-01");
        log.info("DeleteAlias response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testScheduleKeyDeletion() {
         ScheduleKeyDeletionResponse response = ScheduleKeyDeletion.execute("ab4b41bf-a83f-42de-bba7-baaf00d05316", 7);
         log.info("ScheduleKeyDeletion response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testCancelKeyDeletion() {
        CancelKeyDeletionResponse response = CancelKeyDeletion.execute("ab4b41bf-a83f-42de-bba7-baaf00d05316");
        log.info("CancelKeyDeletion response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testUpdateRotationPolicy() {
        UpdateRotationPolicyResponse response = UpdateRotationPolicy.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", true, "7d");
        log.info("UpdateRotationPolicy response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testCreateKeyVersion() {
        CreateKeyVersionResponse response = CreateKeyVersion.execute("e73be547-bef4-453e-9952-9fd2c7a404e0");
        log.info("CreateKeyVersion response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testListKeyVersions() {
        ListKeyVersionsResponse response = ListKeyVersions.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", 1, 10);
        log.info("ListKeyVersions response: {}", JSONUtil.toJsonStr(response));
    }

    @Test
    public void testDescribeKeyVersion() {
        DescribeKeyVersionResponse response = DescribeKeyVersion.execute("e73be547-bef4-453e-9952-9fd2c7a404e0", "fd62bdf0-e01f-4fe2-8c01-880ed89c443f");
        log.info("DescribeKeyVersion response: {}", JSONUtil.toJsonStr(response));
    }
}

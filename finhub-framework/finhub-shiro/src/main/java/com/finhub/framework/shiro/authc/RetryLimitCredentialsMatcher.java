package com.finhub.framework.shiro.authc;

import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.shiro.authz.PasswordHash;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 输错5次密码锁定半小时，ehcache.xml配置
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {

    private static final String DEFAULT_CACHE_NAME = "retryLimitCache";

    private static final String RETRY_LIMIT_PREFIX = "LOGIN_RETRY_LIMIT";

    private String retryLimitCacheName;

    private Integer retryLimitCount;

    private Cache<String, AtomicInteger> passwordRetryCache;

    private PasswordHash passwordHash;

    private CacheManager cacheManager;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.retryLimitCacheName = DEFAULT_CACHE_NAME;
    }

    @Override
    public boolean doCredentialsMatch(final AuthenticationToken authcToken, final AuthenticationInfo info) {
        String retryLimitKey = RETRY_LIMIT_PREFIX + StrConstants.S_COLON + authcToken.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(retryLimitKey);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        if (retryCount.incrementAndGet() > retryLimitCount) {
            //if retry count > retryLimitCount throw
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "密码连续输入错误超" + retryLimitCount + "次，锁定半小时！");
        }

        boolean matches = super.doCredentialsMatch(authcToken, info);
        if (matches) {
            //clear retry data
            passwordRetryCache.remove(retryLimitKey);
        } else {
            passwordRetryCache.put(retryLimitKey, retryCount);
        }
        return matches;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(passwordHash, "you must set passwordHash!");
        super.setHashAlgorithmName(passwordHash.getAlgorithmName());
        super.setHashIterations(passwordHash.getHashIterations());
        this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
    }
}

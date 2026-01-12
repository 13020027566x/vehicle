package com.finhub.framework.cache.contants;

import com.finhub.framework.cache.config.CacheNameConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
public enum DefaultRedisCacheNameEnum implements CacheNameConfig {

    /**
     * 有效session cacheName & expire seconds
     */
    ACTIVE_SESSION_CACHE("activeSessionCache", 3600L, TimeUnit.SECONDS),

    /**
     * shrio cacheName & expire seconds
     */
    SHIRO_KICKOUT_SESSION("shiro-kickout-session", 3600L, TimeUnit.SECONDS),

    /**
     * authorizationCache cacheName & expire seconds
     */
    AUTHORIZATION_CACHE("authorizationCache", 3600L, TimeUnit.SECONDS),

    /**
     * authenticationCache cacheName & expire seconds
     */
    AUTHENTICATION_CACHE("authenticationCache", 3600L, TimeUnit.SECONDS),

    /**
     * oneMinute cacheName & expire seconds
     */
    ONE_MINUTE("oneMinute", 60L, TimeUnit.SECONDS),

    /**
     * halfMinute cacheName & expire seconds
     */
    HALF_MINUTE("halfMinute", 30L, TimeUnit.SECONDS),

    /**
     * default cacheName & expire seconds
     */
    DEFAULT("default", 3600L, TimeUnit.SECONDS),

    /**
     * halfHour cacheName & expire seconds
     */
    HALF_HOUR("halfHour", 1800L, TimeUnit.SECONDS),

    /**
     * day cacheName & expire seconds
     */
    DAY("day", 86400L, TimeUnit.SECONDS),

    /**
     * halfDay cacheName & expire seconds
     */
    HALF_DAY("halfDay", 43200L, TimeUnit.SECONDS),

    /**
     * week cacheName & expire seconds
     */
    WEEK("week", 604800L, TimeUnit.SECONDS),

    /**
     * month cacheName & expire seconds
     */
    MONTH("month", 2592000L, TimeUnit.SECONDS),
    ;

    DefaultRedisCacheNameEnum(String cacheName, long expire, TimeUnit timeUnit) {
        this.cacheName = cacheName;
        this.expire = expire;
        this.timeUnit = timeUnit;
    }

    private String cacheName;

    private long expire;

    private TimeUnit timeUnit;

    @Override
    public String getCacheName() {
        return cacheName;
    }

    @Override
    public long getExpire() {
        return expire;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}

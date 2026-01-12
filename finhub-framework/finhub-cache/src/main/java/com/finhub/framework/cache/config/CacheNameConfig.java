package com.finhub.framework.cache.config;

import com.finhub.framework.cache.contants.DefaultRedisCacheNameEnum;

import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
public interface CacheNameConfig {

    /**
     * 自定义RedisCacheConfiguration cacheName
     *
     * @return  cacheName
     */
    String getCacheName();

    /**
     * cache expire time
     *
     * @return long time
     */
    long getExpire();

    /**
     * cache expire time type
     *
     * @return TimeUnit
     */
    TimeUnit getTimeUnit();


    /**
     * 构造这模式 构造RedisCacheCustomConfig默认实现实体
     *
     * @param cacheName      cacheName  expire time default one hour -> 3600s
     * @return          RedisCacheCustomConfig默认实现实体
     */
    static CacheNameConfig builder(String cacheName) {
        DefaultRedisCacheNameEnum defaultConfig = DefaultRedisCacheNameEnum.DEFAULT;
        return builder(cacheName, defaultConfig.getExpire(), defaultConfig.getTimeUnit());
    }

    /**
     * 构造这模式 构造RedisCacheCustomConfig默认实现实体
     *
     * @param cacheName      cacheName
     * @param expire    expire time default seconds
     * @return          RedisCacheCustomConfig默认实现实体
     */
    static CacheNameConfig builder(String cacheName, long expire) {
        return builder(cacheName, expire, TimeUnit.SECONDS);
    }

    /**
     * 构造这模式 构造RedisCacheCustomConfig默认实现实体
     *
     * @param cacheName      cacheName
     * @param expire    expire time
     * @param timeUnit  time type
     * @return          RedisCacheCustomConfig默认实现实体
     */
    static CacheNameConfig builder(String cacheName, long expire, TimeUnit timeUnit) {
        return new CacheNameConfig() {
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
        };
    }

}

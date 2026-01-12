package com.finhub.framework.common.lock.service;

import com.finhub.framework.common.lock.annotation.MultiDistributeLock;
import com.finhub.framework.common.lock.annotation.inner.LockKey;
import com.finhub.framework.common.lock.annotation.DistributeLock;
import com.finhub.framework.common.lock.annotation.inner.LockTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
public class RedisServiceTestImpl implements RedisServiceTest{

    public static int i = 0;

    @Override
    @DistributeLock(key = @LockKey(prefix = "redis:dis:lock:", expression = "#var1"), time = @LockTime(waitTime = -1L))
    public String constantKeyBlockLockTest(String var1) {
        int jjjj = 1 / 0;
        i = i + 1;
//        try {
//            TimeUnit.SECONDS.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "ok";
    }

    @Override
    @DistributeLock(key = @LockKey(prefix = "redis:dis:lock:", expression = "#var_1"),mode = DistributeLock.LockMode.FAIR_LOCK)
    public String constantKeyTryLockTest(String var_1) {
        i = i + 1;
        return "ok";
    }

    @Override
    @DistributeLock(key = @LockKey(prefix = "redis:dis:lock:bean:", expression = {"#test_1.id", "#test_1.age"}), time = @LockTime(waitTime = -1L))
    public String beanKeyBlockLockTest(
        InnerRedisServiceTest test_1) {
        i = i + 1;
        return "ok";
    }

    @Override
    @DistributeLock(key = @LockKey(prefix = "redis:dis:lock:bean:", expression = {"#_test.id", "#_test.age"}))
    public String beanKeyTryLockTest(
        InnerRedisServiceTest _test) {
        i = i + 1;
        return "ok";
    }

    @Override
    @DistributeLock(key = @LockKey(prefix = "redis:dis:lock:bean:format:%s:%s", expression = {"#TEST_.id", "#TEST_.age"}, connect = LockKey.KeyConnectMode.StringFormat), time = @LockTime(waitTime = -1L))
    public String beanFormatKeyBlockLockTest(
        InnerRedisServiceTest TEST_) {
        i = i + 1;
        return "ok";
    }

    @Override
    @MultiDistributeLock(keys = {
        @LockKey(prefix = "redis:dis:lock:bean:format:{}:{}", expression = {"#TEST_1_.address",
            "#TEST_1_.id"}, connect = LockKey.KeyConnectMode.MessageFormat),
        @LockKey(prefix = "redis:dis:lock:bean:format:{}:{}", expression = {"#TEST_1_.age",
            "#TEST_1_.id"}, connect = LockKey.KeyConnectMode.MessageFormat)
        },
        time = @LockTime(waitTime = 1L))
    public String beanFormatKeyTryLockTest(
        InnerRedisServiceTest TEST_1_) {

        i = i + 1;
        return "ok";
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InnerRedisServiceTest {

        private String id;

        private int age;

        private String address;
    }

}

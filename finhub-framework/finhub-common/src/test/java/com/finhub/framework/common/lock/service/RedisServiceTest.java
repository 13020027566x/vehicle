package com.finhub.framework.common.lock.service;
;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
public interface RedisServiceTest {

    /**
     * 测试常量key阻塞锁
     * @param var1
     * @return
     */
    String constantKeyBlockLockTest(String var1);

    /**
     * 测试常量非阻塞key锁
     * @param var_1
     * @return
     */
    String constantKeyTryLockTest(String var_1);


    String beanKeyBlockLockTest(RedisServiceTestImpl.InnerRedisServiceTest test_1);

    String beanKeyTryLockTest(RedisServiceTestImpl.InnerRedisServiceTest _test);

    /**
     * 测试format - key
     * @param TEST_
     * @return
     */
    String beanFormatKeyBlockLockTest(RedisServiceTestImpl.InnerRedisServiceTest TEST_);

    String beanFormatKeyTryLockTest(RedisServiceTestImpl.InnerRedisServiceTest TEST_1_);

}

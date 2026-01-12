package com.finhub.framework.common.lock;

import com.finhub.framework.common.CommonApplicationTest;
import com.finhub.framework.common.configuration.CommonAutoloadConfiguration;
import com.finhub.framework.common.lock.service.RedisServiceTest;
import com.finhub.framework.common.lock.service.RedisServiceTestImpl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
@Slf4j
public class DistributeLockTest extends CommonApplicationTest{

    @Autowired
    private RedisServiceTest redisServiceTest;

    @Test
    public void constantKeyLockTest() {
        String aaaa = redisServiceTest.constantKeyBlockLockTest("aaaa");
        System.out.println(aaaa);
    }

    @Test
    public void constantKeyBlockLockTest() throws InterruptedException {
        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                try {
                    MDC.put("requestId", UUID.randomUUID().toString().replace("-", ""));
                    redisServiceTest.constantKeyBlockLockTest("aaaa");
                } catch (Exception e) {
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i == count);
    }


    @Test
    public void beanKeyBlockLockTest() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                try {
                    RedisServiceTestImpl.InnerRedisServiceTest test = RedisServiceTestImpl.InnerRedisServiceTest.builder()
                        .id("123")
                        .age(19)
                        .address("北京")
                        .build();
                    redisServiceTest.beanKeyBlockLockTest(test);
                } catch (Exception e) {
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i == count);
    }

    @Test
    public void beanFormatKeyBlockLockTest() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                try {
                    RedisServiceTestImpl.InnerRedisServiceTest test = RedisServiceTestImpl.InnerRedisServiceTest.builder()
                        .id("123")
                        .age(19)
                        .address("北京")
                        .build();
                    redisServiceTest.beanFormatKeyBlockLockTest(test);
                } catch (Exception e) {
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i == count);
    }


    @Test
    public void constantKeyTryLockTest() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                try {
                    redisServiceTest.constantKeyTryLockTest("aaaa");
                } catch (Exception e) {
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i <= count);
    }


    @Test
    public void beanKeyTryLockTest() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            service.execute(() -> {
                try {
                    RedisServiceTestImpl.InnerRedisServiceTest test = RedisServiceTestImpl.InnerRedisServiceTest.builder()
                        .id("123")
                        .age(19)
                        .address("北京")
                        .build();
                    redisServiceTest.beanKeyTryLockTest(test);
                } catch (Exception e) {
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i <= count);
    }

    @Test
    public void beanFormatKeyTryLockTest() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(1000);
        CountDownLatch countDownLatch = new CountDownLatch(count.get());
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count.get(); i++) {
            service.execute(() -> {
                try {
                    RedisServiceTestImpl.InnerRedisServiceTest test = RedisServiceTestImpl.InnerRedisServiceTest.builder()
                        .id("adsfasfafadsfadsfasf")
                        .age(19)
                        .address("北京")
                        .build();
                    redisServiceTest.beanFormatKeyTryLockTest(test);
                } catch (Exception e) {
                    count.getAndDecrement();
                    log.error("e: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(RedisServiceTestImpl.i);
        Assert.assertTrue(RedisServiceTestImpl.i == count.get());
    }


}

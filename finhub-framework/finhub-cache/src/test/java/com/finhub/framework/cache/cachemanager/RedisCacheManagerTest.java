package com.finhub.framework.cache.cachemanager;

import com.finhub.framework.cache.CacheApplicationTest;
import com.finhub.framework.cache.cachemanager.service.CacheServiceTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
@Slf4j
public class RedisCacheManagerTest extends CacheApplicationTest {

    @Autowired
    CacheServiceTest cacheServiceTest;


    @Test
    public void setCacheServiceTest() {
        for (int i = 0; i < 10; i++) {
            CacheServiceTest.Test byId = cacheServiceTest.getById("1");
            log.info(String.valueOf(byId));
        }

        for (int i = 0; i < 10; i++) {
            CacheServiceTest.Test byId = cacheServiceTest.putTest("2");
            log.info(String.valueOf(byId));
        }

        for (int i = 0; i < 10; i++) {
            log.info(String.valueOf(cacheServiceTest.oneMinute("3")));
        }
    }
}

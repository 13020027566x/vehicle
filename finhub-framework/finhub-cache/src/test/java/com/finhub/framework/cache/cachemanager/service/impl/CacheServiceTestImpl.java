package com.finhub.framework.cache.cachemanager.service.impl;

import com.finhub.framework.cache.cachemanager.service.CacheServiceTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
@Component
@Slf4j
public class CacheServiceTestImpl implements CacheServiceTest {
    @Override
    @Cacheable(cacheNames = "123123", key = "#id")
    public Test getById(String id) {
        log.info("执行【getById】...");
        return new Test(id, 23, "北京市");
    }

    @Override
    @Cacheable(cacheNames = "3333",key = "#id")
    public Test putTest(String id) {
        log.info("执行【putTest】...");
        return new Test(id, 23, "北京市");
    }

    @Override
    @Cacheable(cacheNames = "oneMinute",key = "#id")
    public String oneMinute(String id) {
        log.info("执行【putTest】...");
        return id;
    }
}

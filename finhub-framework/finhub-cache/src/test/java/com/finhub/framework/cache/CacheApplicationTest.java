package com.finhub.framework.cache;

import com.finhub.framework.cache.cachemanager.configuration.CacheConfiguration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheConfiguration.class)
public class CacheApplicationTest {

    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void afterClass() {
    }

}

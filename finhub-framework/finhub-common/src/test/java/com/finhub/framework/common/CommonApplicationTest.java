package com.finhub.framework.common;

import com.finhub.framework.common.config.CommonConfiguration;
import com.finhub.framework.logback.configuration.LogbackAutoloadConfiguration;
import com.finhub.framework.nacos.configuration.NacosAutoloadConfiguration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonConfiguration.class)
@EnableAspectJAutoProxy
@AutoConfigureAfter({NacosAutoloadConfiguration.class, LogbackAutoloadConfiguration.class})
public class CommonApplicationTest {

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

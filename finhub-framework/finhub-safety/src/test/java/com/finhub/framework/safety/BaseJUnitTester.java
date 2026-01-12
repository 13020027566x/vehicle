package com.finhub.framework.safety;

import com.finhub.framework.safety.configuration.SafetyAutoloadConfiguration;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = SafetyAutoloadConfiguration.class)
public class BaseJUnitTester {

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("spring.profiles.active", "tx-dev");
        System.setProperty("spring.application.dept", "PINGTAI");
        System.setProperty("skywalking.agent.service_name", "finhub-safety");
        System.setProperty("log.appender", "STDOUT");

        // one time setup method
        System.out.println("@BeforeClass - executed only one time and is first method to be executed");
    }

    @AfterClass
    public static void tearDownClass() {
        // one time tear down method
        System.out.println("@AfterClass - executed only one time and is last method to be executed");
    }

    @Before
    public void setUp() {
        System.setProperty("spring.profiles.active", "tx-dev");
        System.setProperty("spring.application.dept", "PINGTAI");
        System.setProperty("skywalking.agent.service_name", "finhub-safety");
        System.setProperty("log.appender", "STDOUT");

        // set up method executed before every test method
        System.out.println("@Before - executed before every test method");
    }

    @After
    public void tearDown() {
        // tear down method executed after every test method
        System.out.println("@After - executed after every test method");
    }
}

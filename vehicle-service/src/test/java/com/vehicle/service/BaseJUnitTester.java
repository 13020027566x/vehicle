package com.vehicle.service;

import com.vehicle.service.configuration.ServiceTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ServiceTestConfiguration.class)
public class BaseJUnitTester<D> {

    @Autowired
    protected D domain;

    @BeforeClass
    public static void setUpClass() {
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
        // set up method executed before every test method
        System.out.println("@Before - executed before every test method");
    }

    @After
    public void tearDown() {
        // tear down method executed after every test method
        System.out.println("@After - executed after every test method");
    }
}

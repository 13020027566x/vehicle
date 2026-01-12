package com.vehicle.client.demo.manager;

import com.vehicle.api.demo.dto.DemoReqDTO;
import com.vehicle.api.demo.dto.DemoResDTO;
import com.vehicle.client.configuration.ClientAutoloadConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/24 14:45
 */
@Slf4j
@EnableDubbo
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientAutoloadConfiguration.class)
public class DemoManagerTest {

    @Autowired
    DemoManager demoManager;

    @Test
    public void testGet() {
        DemoResDTO demoResDTO = demoManager.get(1);
        log.info("[run][发起一次 Dubbo RPC 请求，获得用户为(" + demoResDTO + ")");
    }

    @Test
    public void testGet2() {
        DemoResDTO demoResDTO2 = DemoManager.me().get(2);
        log.info("[run][发起一次 Dubbo RPC 请求，获得用户为(" + demoResDTO2 + ")");
    }

    @Test
    public void testGetMin() {
        DemoResDTO demoResDTO = demoManager.get(0);
        log.info("[run][发起一次 Dubbo RPC 请求，获得用户为(" + demoResDTO + ")");
    }

    @Test
    public void testAdd() throws InterruptedException {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoReqDTO.setName("123");
        demoReqDTO.setGender(1);
        demoManager.add(demoReqDTO);
        log.info("[run][发起一次 Dubbo RPC 请求，添加用户为(" + demoReqDTO + ")");
    }

    @Test
    public void testAddGarden() {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoReqDTO.setName("123");
        demoManager.add(demoReqDTO);
        log.info("[run][发起一次 Dubbo RPC 请求，添加用户为(" + demoReqDTO + ")");
    }

    @Test
    public void testAddNotNull() {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoManager.add(demoReqDTO);
        log.info("[run][发起一次 Dubbo RPC 请求，添加用户为(" + demoReqDTO + ")");
    }
}

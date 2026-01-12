package ${conf.rpcClientPackageName}.demo.manager;

import ${conf.rpcApiPackageName}.demo.dto.DemoReqDTO;
import ${conf.rpcApiPackageName}.demo.dto.DemoResDTO;
import ${conf.rpcClientPackageName}.configuration.ClientAutoloadConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Demo ManagerTest
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
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
    public void testAdd() {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoReqDTO.setName("123");
        demoReqDTO.setGender(1);
        demoManager.add(demoReqDTO);
    }

    @Test
    public void testAddGarden() {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoReqDTO.setName("123");
        demoManager.add(demoReqDTO);
    }

    @Test
    public void testAddNotNull() {
        DemoReqDTO demoReqDTO = new DemoReqDTO();
        demoManager.add(demoReqDTO);
    }
}

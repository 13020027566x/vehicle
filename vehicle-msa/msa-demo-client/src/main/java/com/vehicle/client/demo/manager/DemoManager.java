package com.vehicle.client.demo.manager;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.api.demo.DemoService;
import com.vehicle.api.demo.dto.DemoReqDTO;
import com.vehicle.api.demo.dto.DemoResDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/24 14:45
 */
@Component
@Validated
public class DemoManager implements DemoService {

    public static DemoManager me() {
        return SpringUtil.getBean(DemoManager.class);
    }

    // @Reference(version = "${dubbo.consumer.DemoService.version}") test 2.6.2
    @DubboReference(version = "${dubbo.consumer.DemoService.version}")
    private DemoService demoService;

    @Override
    public DemoResDTO get(Integer id) {
        return demoService.get(id);
    }

    @Override
    public DemoResDTO add(DemoReqDTO demoReqDTO) {
        return demoService.add(demoReqDTO);
    }
}

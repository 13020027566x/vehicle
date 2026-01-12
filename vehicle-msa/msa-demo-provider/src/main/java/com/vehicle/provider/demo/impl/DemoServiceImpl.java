package com.vehicle.provider.demo.impl;

import com.vehicle.api.demo.DemoService;
import com.vehicle.api.demo.dto.DemoReqDTO;
import com.vehicle.api.demo.dto.DemoResDTO;
import com.vehicle.provider.demo.converter.DemoConverter;
import com.vehicle.provider.demo.manager.DemoManager;
import com.vehicle.provider.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.validation.annotation.Validated;

/**
 * @author Mickey
 * @version 1.0
 * @since 2017/5/17 上午9:26
 */
@Slf4j
@Validated
//@Service(version = "${dubbo.provider.DemoService.version}") test 2.6.2
@DubboService(version = "${dubbo.provider.DemoService.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public DemoResDTO get(Integer id) {
        DemoDTO demoDTO = DemoManager.me().get(id);

        return DemoConverter.me().convertToDemoResDTO(demoDTO);
    }

    @Override
    public DemoResDTO add(DemoReqDTO demoReqDTO) {
        DemoDTO demoDTO = DemoConverter.me().convertToDemoDTO(demoReqDTO);

        demoDTO = DemoManager.me().add(demoDTO);

        return DemoConverter.me().convertToDemoResDTO(demoDTO);
    }
}

package com.vehicle.provider.demo.manager;

import com.finhub.framework.exception.BaseException;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.provider.demo.domain.DemoDO;
import com.vehicle.provider.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class DemoManager {

    public static DemoManager me() {
        return SpringUtil.getBean(DemoManager.class);
    }

    public DemoDTO get(Integer id) throws BaseException {

        return DemoDO.me().transferDemoDTO(id);
    }

    public DemoDTO add(DemoDTO demoDTO) {
        return DemoDO.me().transferDemoDTO(demoDTO);
    }
}

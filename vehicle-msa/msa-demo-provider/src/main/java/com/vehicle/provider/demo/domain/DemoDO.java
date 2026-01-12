package com.vehicle.provider.demo.domain;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.constant.enums.BusinessResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.provider.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class DemoDO {

    public static DemoDO me() {
        return SpringUtil.getBean(DemoDO.class);
    }

    public DemoDTO transferDemoDTO(Integer id) throws BaseException {
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setId(id);
        demoDTO.setName("没有昵称：" + id);
        // 1 - 男；2 - 女
        demoDTO.setGender(id % 2 + 1);

        BusinessResponseEnum.COMMON_ERROR.assertIsNull(new DemoDTO());
        return demoDTO;
    }

    public DemoDTO transferDemoDTO(DemoDTO demoDTO) {
        return demoDTO;
    }
}

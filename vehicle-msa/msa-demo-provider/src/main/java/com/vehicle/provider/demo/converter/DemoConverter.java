package com.vehicle.provider.demo.converter;

import com.finhub.framework.core.converter.BaseConverterConfig;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.api.demo.dto.DemoReqDTO;
import com.vehicle.api.demo.dto.DemoResDTO;
import com.vehicle.provider.demo.dto.DemoDTO;
import org.mapstruct.Mapper;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface DemoConverter {

    static DemoConverter me() {
        return SpringUtil.getBean(DemoConverter.class);
    }

    DemoResDTO convertToDemoResDTO(DemoDTO demoDTO);

    DemoDTO convertToDemoDTO(DemoReqDTO demoReqDTO);
}

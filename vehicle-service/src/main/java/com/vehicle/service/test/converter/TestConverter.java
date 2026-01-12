package com.vehicle.service.test.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.test.po.TestPO;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 测试 Converter
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Mapper(config = BaseConverterConfig.class)
public interface TestConverter extends BaseConverter<TestDTO, TestPO> {

    static TestConverter me() {
        return SpringUtil.getBean(TestConverter.class);
    }

    TestDTO convertToTestDTO(TestAddReqDTO testAddReqDTO);

    TestDTO convertToTestDTO(TestModifyReqDTO testModifyReqDTO);

    TestDTO convertToTestDTO(TestRemoveReqDTO testRemoveReqDTO);

    TestDTO convertToTestDTO(TestListReqDTO testListReqDTO);

    TestDTO convertToTestDTO(TestPageReqDTO testPageReqDTO);

    TestShowResDTO convertToTestShowResDTO(TestDTO testDTO);

    List<TestShowResDTO> convertToTestShowResDTOList(List<TestDTO> testDTOList);

    TestListResDTO convertToTestListResDTO(TestDTO testDTO);

    List<TestListResDTO> convertToTestListResDTOList(List<TestDTO> testDTOList);

    List<TestDTO> convertToTestDTOList(List<TestAddReqDTO> testAddReqDTOList);

    TestPageResDTO convertToTestPageResDTO(TestDTO testDTO);

    List<TestPageResDTO> convertToTestPageResDTOList(List<TestDTO> testDTOList);

    Page<TestPageResDTO> convertToTestPageResDTOPage(Page<TestDTO> testDTOPage);
}

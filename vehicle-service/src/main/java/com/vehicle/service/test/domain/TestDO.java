package com.vehicle.service.test.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

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
import com.vehicle.service.test.converter.TestConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 测试 DO
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Slf4j
@Component
public class TestDO extends BaseDO<TestDTO, TestPO, TestConverter> {

    public static TestDO me() {
        return SpringUtil.getBean(TestDO.class);
    }

    public void checkTestAddReqDTO(final TestAddReqDTO testAddReqDTO) {
        if (Func.isEmpty(testAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkTestAddReqDTOList(final List<TestAddReqDTO> testAddReqDTOList) {
        if (Func.isEmpty(testAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkTestModifyReqDTO(final TestModifyReqDTO testModifyReqDTO) {
        if (Func.isEmpty(testModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkTestRemoveReqDTO(final TestRemoveReqDTO testRemoveReqDTO) {
        if (Func.isEmpty(testRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public TestDTO buildListParamsDTO(final TestListReqDTO testListReqDTO) {
        return converter.convertToTestDTO(testListReqDTO);
    }

    public TestDTO buildPageParamsDTO(final TestPageReqDTO testPageReqDTO) {
        return converter.convertToTestDTO(testPageReqDTO);
    }

    public TestDTO buildAddTestDTO(final TestAddReqDTO testAddReqDTO) {
        return converter.convertToTestDTO(testAddReqDTO);
    }

    public List<TestDTO> buildAddBatchTestDTOList(final List<TestAddReqDTO> testAddReqDTOList) {
        return converter.convertToTestDTOList(testAddReqDTOList);
    }

    public TestDTO buildModifyTestDTO(final TestModifyReqDTO testModifyReqDTO) {
        return converter.convertToTestDTO(testModifyReqDTO);
    }

    public TestDTO buildRemoveTestDTO(final TestRemoveReqDTO testRemoveReqDTO) {
        return converter.convertToTestDTO(testRemoveReqDTO);
    }

    public List<TestListResDTO> transferTestListResDTOList(final List<TestDTO> testDTOList) {
        if (Func.isEmpty(testDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToTestListResDTOList(testDTOList);
    }

    public TestListResDTO transferTestListResDTO(final TestDTO testDTO) {
        if (Func.isEmpty(testDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToTestListResDTO(testDTO);
    }

    public Page<TestPageResDTO> transferTestPageResDTOPage(final Page<TestDTO> testDTOPage) {
        if (Func.isEmpty(testDTOPage) || Func.isEmpty(testDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToTestPageResDTOPage(testDTOPage);
    }

    public TestShowResDTO transferTestShowResDTO(final TestDTO testDTO) {
        if (Func.isEmpty(testDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToTestShowResDTO(testDTO);
    }

    public List<TestShowResDTO> transferTestShowResDTOList(final List<TestDTO> testDTOList) {
        if (Func.isEmpty(testDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToTestShowResDTOList(testDTOList);
    }
}

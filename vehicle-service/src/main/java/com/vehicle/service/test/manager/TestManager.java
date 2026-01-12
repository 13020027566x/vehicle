package com.vehicle.service.test.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.test.TestDAO;
import com.vehicle.dao.test.po.TestPO;
import com.vehicle.service.test.converter.TestConverter;
import com.vehicle.service.test.domain.TestDO;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 测试 Manager
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Slf4j
@Component
public class TestManager extends BaseManagerImpl<TestDAO, TestPO, TestDTO, TestConverter> {

    public static TestManager me() {
        return SpringUtil.getBean(TestManager.class);
    }

    public List<TestListResDTO> list(final TestListReqDTO testListReqDTO) {
        TestDTO paramsDTO = TestDO.me().buildListParamsDTO(testListReqDTO);

        List<TestDTO> testDTOList = super.findList(paramsDTO);

        return TestDO.me().transferTestListResDTOList(testDTOList);
    }

    public TestListResDTO listOne(final TestListReqDTO testListReqDTO) {
        TestDTO paramsDTO = TestDO.me().buildListParamsDTO(testListReqDTO);

        TestDTO testDTO = super.findOne(paramsDTO);

        return TestDO.me().transferTestListResDTO(testDTO);
    }

    public Page<TestPageResDTO> pagination(final TestPageReqDTO testPageReqDTO, final Integer current, final Integer size) {
        TestDTO paramsDTO = TestDO.me().buildPageParamsDTO(testPageReqDTO);

        Page<TestDTO> testDTOPage = super.findPage(paramsDTO, current, size);

        return TestDO.me().transferTestPageResDTOPage(testDTOPage);
    }

    public Boolean add(final TestAddReqDTO testAddReqDTO) {
        TestDO.me().checkTestAddReqDTO(testAddReqDTO);

        TestDTO addTestDTO = TestDO.me().buildAddTestDTO(testAddReqDTO);

        return super.saveDTO(addTestDTO);
    }

    public Boolean addAllColumn(final TestAddReqDTO testAddReqDTO) {
        TestDO.me().checkTestAddReqDTO(testAddReqDTO);

        TestDTO addTestDTO = TestDO.me().buildAddTestDTO(testAddReqDTO);

        return super.saveAllColumn(addTestDTO);
    }

    public Boolean addBatchAllColumn(final List<TestAddReqDTO> testAddReqDTOList) {
        TestDO.me().checkTestAddReqDTOList(testAddReqDTOList);

        List<TestDTO> addBatchTestDTOList = TestDO.me().buildAddBatchTestDTOList(testAddReqDTOList);

        return super.saveBatchAllColumn(addBatchTestDTOList);
    }

    public TestShowResDTO show(final String id) {
        TestDTO testDTO = super.findById(id);

        return TestDO.me().transferTestShowResDTO(testDTO);
    }

    public List<TestShowResDTO> showByIds(final List<String> ids) {
        TestDO.me().checkIds(ids);

        List<TestDTO> testDTOList = super.findBatchIds(ids);

        return TestDO.me().transferTestShowResDTOList(testDTOList);
    }

    public Boolean modify(final TestModifyReqDTO testModifyReqDTO) {
        TestDO.me().checkTestModifyReqDTO(testModifyReqDTO);

        TestDTO modifyTestDTO = TestDO.me().buildModifyTestDTO(testModifyReqDTO);

        return super.modifyById(modifyTestDTO);
    }

    public Boolean modifyAllColumn(final TestModifyReqDTO testModifyReqDTO) {
        TestDO.me().checkTestModifyReqDTO(testModifyReqDTO);

        TestDTO modifyTestDTO = TestDO.me().buildModifyTestDTO(testModifyReqDTO);

        return super.modifyAllColumnById(modifyTestDTO);
    }

    public Boolean removeByParams(final TestRemoveReqDTO testRemoveReqDTO) {
        TestDO.me().checkTestRemoveReqDTO(testRemoveReqDTO);

        TestDTO removeTestDTO = TestDO.me().buildRemoveTestDTO(testRemoveReqDTO);

        return super.remove(removeTestDTO);
    }

    @Override
    protected TestPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new TestPO();
        }

        return BeanUtil.toBean(map, TestPO.class);
    }

    @Override
    protected TestDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new TestDTO();
        }

        return BeanUtil.toBean(map, TestDTO.class);
    }
}

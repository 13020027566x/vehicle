package com.vehicle.service.test.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.test.po.TestPO;
import com.vehicle.service.test.TestService;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import com.vehicle.service.test.manager.TestManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试 ServiceImpl
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Slf4j
@Service
public class TestServiceImpl extends BaseServiceImpl<TestManager, TestPO, TestDTO> implements TestService {

    @Override
    public List<TestListResDTO> list(final TestListReqDTO testListReqDTO) {
        return manager.list(testListReqDTO);
    }

    @Override
    public TestListResDTO listOne(final TestListReqDTO testListReqDTO) {
        return manager.listOne(testListReqDTO);
    }

    @Override
    public Page<TestPageResDTO> pagination(final TestPageReqDTO testPageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(testPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final TestAddReqDTO testAddReqDTO) {
        return manager.add(testAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final TestAddReqDTO testAddReqDTO) {
        return manager.addAllColumn(testAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<TestAddReqDTO> testAddReqDTOList) {
        return manager.addBatchAllColumn(testAddReqDTOList);
    }

    @Override
    public TestShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<TestShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final TestModifyReqDTO testModifyReqDTO) {
        return manager.modify(testModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final TestModifyReqDTO testModifyReqDTO) {
        return manager.modifyAllColumn(testModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final TestRemoveReqDTO testRemoveReqDTO) {
        return manager.removeByParams(testRemoveReqDTO);
    }
}

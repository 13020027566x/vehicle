package com.vehicle.service.test;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;

import java.util.List;

/**
 * 测试 Service
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
public interface TestService extends BaseService<TestDTO> {

    static TestService me() {
        return SpringUtil.getBean(TestService.class);
    }

    /**
     * 列表
     *
     * @param testListReqDTO 入参DTO
     * @return
     */
    List<TestListResDTO> list(TestListReqDTO testListReqDTO);

    /**
     * First查询
     *
     * @param testListReqDTO 入参DTO
     * @return
     */
    TestListResDTO listOne(TestListReqDTO testListReqDTO);

    /**
     * 分页
     *
     * @param testPageReqDTO 入参DTO
     * @param current            当前页
     * @param size               每页大小
     * @return
     */
    Page<TestPageResDTO> pagination(TestPageReqDTO testPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param testAddReqDTO 入参DTO
     * @return
     */
    Boolean add(TestAddReqDTO testAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param testAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(TestAddReqDTO testAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param testAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<TestAddReqDTO> testAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    TestShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<TestShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param testModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(TestModifyReqDTO testModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param testModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(TestModifyReqDTO testModifyReqDTO);

    /**
     * 参数删除
     *
     * @param testRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(TestRemoveReqDTO testRemoveReqDTO);
}

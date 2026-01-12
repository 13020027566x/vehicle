package com.vehicle.web.test.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.test.TestService;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class TestController extends AbstractController<TestService> {

    /**
     * 测试-列表
     *
     * @param testListReqDTO 测试列表请求DTO
     * @return ItemsR<TestListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "test/list", method = {RequestMethod.GET})
    public ItemsResult<TestListResDTO> list(TestListReqDTO testListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(testListReqDTO));
    }

    /**
     * 测试-First查询
     *
     * @param testListReqDTO 测试Fist查询请求DTO
     * @return ItemR<TestListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "test/listOne", method = {RequestMethod.GET})
    public ItemResult<TestListResDTO> listOne(TestListReqDTO testListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(testListReqDTO));
    }

    /**
     * 测试-分页
     *
     * @param testPageReqDTO 测试分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageR<TestPageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "test/page", method = {RequestMethod.GET})
    public PageResult<TestPageResDTO> page(TestPageReqDTO testPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<TestPageResDTO> testPageResDTOPage = service.pagination(testPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, testPageResDTOPage.getTotal(), testPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 测试-新增
     *
     * @param testAddReqDTO 测试新增请求DTO
     * @return MessageR 新增结果
     * @undone
     */
    @RequestMapping(value = "test/add", method = {RequestMethod.POST})
    public MessageResult add(TestAddReqDTO testAddReqDTO) {
        Boolean isSuccess = service.add(testAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-新增(所有字段)
     *
     * @param testAddReqDTO 测试新增(所有字段)请求DTO
     * @return MessageR 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "test/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(TestAddReqDTO testAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(testAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-批量新增(所有字段)
     *
     * @param testAddReqDTOList 测试批量新增(所有字段)请求DTO
     * @return MessageR 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "test/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<TestAddReqDTO> testAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(testAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-详情
     *
     * @param id 主键 ID
     * @return ItemR<TestShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "test/show", method = {RequestMethod.GET})
    public ItemResult<TestShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 测试-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsR<TestShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "test/showByIds", method = {RequestMethod.POST})
    public ItemsResult<TestShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 测试-更新
     *
     * @param testModifyReqDTO 测试更新请求DTO
     * @return MessageR 更新结果
     * @undone
     */
    @RequestMapping(value = "test/modify", method = {RequestMethod.POST})
    public MessageResult modify(TestModifyReqDTO testModifyReqDTO) {
        Boolean isSuccess = service.modify(testModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-更新(所有字段)
     *
     * @param testModifyReqDTO 测试更新(所有字段)请求DTO
     * @return MessageR 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "test/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(TestModifyReqDTO testModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(testModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-删除
     *
     * @param id 主键 ID
     * @return MessageR 删除结果
     * @undone
     */
    @RequestMapping(value = "test/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageR 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "test/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 测试-删除(参数)
     *
     * @param testRemoveReqDTO 测试删除(参数)请求DTO
     * @return MessageR 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "test/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(TestRemoveReqDTO testRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(testRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

package com.vehicle.web.roleresource.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.roleresource.RoleResourceService;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色资源 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class RoleResourceController extends AbstractController<RoleResourceService> {

    /**
     * 角色资源-列表
     *
     * @param roleResourceListReqDTO 角色资源列表请求DTO
     * @return ItemsResult<RoleResourceListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "roleResource/list", method = {RequestMethod.GET})
    public ItemsResult<RoleResourceListResDTO> list(RoleResourceListReqDTO roleResourceListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(roleResourceListReqDTO));
    }

    /**
     * 角色资源-First查询
     *
     * @param roleResourceListReqDTO 角色资源Fist查询请求DTO
     * @return ItemResult<RoleResourceListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "roleResource/listOne", method = {RequestMethod.GET})
    public ItemResult<RoleResourceListResDTO> listOne(RoleResourceListReqDTO roleResourceListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(roleResourceListReqDTO));
    }

    /**
     * 角色资源-分页
     *
     * @param roleResourcePageReqDTO 角色资源分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<RoleResourcePageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "roleResource/page", method = {RequestMethod.GET})
    public PageResult<RoleResourcePageResDTO> page(RoleResourcePageReqDTO roleResourcePageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<RoleResourcePageResDTO> roleResourcePageResDTOPage = service.pagination(roleResourcePageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, roleResourcePageResDTOPage.getTotal(), roleResourcePageResDTOPage.getRecords(), size, current);
    }

    /**
     * 角色资源-新增
     *
     * @param roleResourceAddReqDTO 角色资源新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequestMapping(value = "roleResource/add", method = {RequestMethod.POST})
    public MessageResult add(RoleResourceAddReqDTO roleResourceAddReqDTO) {
        Boolean isSuccess = service.add(roleResourceAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-新增(所有字段)
     *
     * @param roleResourceAddReqDTO 角色资源新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(RoleResourceAddReqDTO roleResourceAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(roleResourceAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-批量新增(所有字段)
     *
     * @param roleResourceAddReqDTOList 角色资源批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(@RequestBody List<RoleResourceAddReqDTO> roleResourceAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(roleResourceAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-详情
     *
     * @param id 主键 ID
     * @return ItemResult<RoleResourceShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "roleResource/show", method = {RequestMethod.GET})
    public ItemResult<RoleResourceShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 角色资源-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<RoleResourceShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/showByIds", method = {RequestMethod.POST})
    public ItemsResult<RoleResourceShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 角色资源-更新
     *
     * @param roleResourceModifyReqDTO 角色资源更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequestMapping(value = "roleResource/modify", method = {RequestMethod.POST})
    public MessageResult modify(RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        Boolean isSuccess = service.modify(roleResourceModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-更新(所有字段)
     *
     * @param roleResourceModifyReqDTO 角色资源更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(roleResourceModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequestMapping(value = "roleResource/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色资源-删除(参数)
     *
     * @param roleResourceRemoveReqDTO 角色资源删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "roleResource/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(RoleResourceRemoveReqDTO roleResourceRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(roleResourceRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

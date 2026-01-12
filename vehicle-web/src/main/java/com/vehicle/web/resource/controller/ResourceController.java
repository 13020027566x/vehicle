package com.vehicle.web.resource.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.resource.ResourceService;
import com.vehicle.service.resource.dto.ResourceAddReqDTO;
import com.vehicle.service.resource.dto.ResourceListReqDTO;
import com.vehicle.service.resource.dto.ResourceListResDTO;
import com.vehicle.service.resource.dto.ResourceModifyReqDTO;
import com.vehicle.service.resource.dto.ResourcePageReqDTO;
import com.vehicle.service.resource.dto.ResourcePageResDTO;
import com.vehicle.service.resource.dto.ResourceRemoveReqDTO;
import com.vehicle.service.resource.dto.ResourceShowResDTO;
import com.vehicle.service.resource.dto.UserMenuOperationDTO;
import com.vehicle.service.resource.dto.UserMenuResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class ResourceController extends AbstractController<ResourceService> {

    /**
     * 菜单-列表
     *
     * @param resourceListReqDTO 菜单列表请求DTO
     * @return ItemsResult<ResourceListResDTO> 列表结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:CDZY:CX"})
    @RequestMapping(value = "resource/list", method = {RequestMethod.GET})
    public ItemsResult<ResourceListResDTO> list(ResourceListReqDTO resourceListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(resourceListReqDTO));
    }

    /**
     * 菜单-First查询
     *
     * @param resourceListReqDTO 菜单Fist查询请求DTO
     * @return ItemResult<ResourceListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "resource/listOne", method = {RequestMethod.GET})
    public ItemResult<ResourceListResDTO> listOne(ResourceListReqDTO resourceListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(resourceListReqDTO));
    }

    /**
     * 菜单-分页
     *
     * @param resourcePageReqDTO 菜单分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<ResourcePageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "resource/page", method = {RequestMethod.GET})
    public PageResult<ResourcePageResDTO> page(ResourcePageReqDTO resourcePageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<ResourcePageResDTO> resourcePageResDTOPage = service.pagination(resourcePageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, resourcePageResDTOPage.getTotal(), resourcePageResDTOPage.getRecords(), size, current);
    }

    /**
     * 菜单-新增
     *
     * @param resourceAddReqDTO 菜单新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:CDZY:XZ"})
    @RequestMapping(value = "resource/add", method = {RequestMethod.POST})
    public MessageResult add(ResourceAddReqDTO resourceAddReqDTO) {
        Boolean isSuccess = service.add(resourceAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-新增(所有字段)
     *
     * @param resourceAddReqDTO 菜单新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "resource/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(ResourceAddReqDTO resourceAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(resourceAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-批量新增(所有字段)
     *
     * @param resourceAddReqDTOList 菜单批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "resource/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<ResourceAddReqDTO> resourceAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(resourceAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-详情
     *
     * @param id 主键 ID
     * @return ItemResult<ResourceShowResDTO> 详情结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:CDZY:CK"})
    @RequestMapping(value = "resource/show", method = {RequestMethod.GET})
    public ItemResult<ResourceShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 菜单-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<ResourceShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "resource/showByIds", method = {RequestMethod.POST})
    public ItemsResult<ResourceShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 菜单-更新
     *
     * @param resourceModifyReqDTO 菜单更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:CDZY:BJ"})
    @RequestMapping(value = "resource/modify", method = {RequestMethod.POST})
    public MessageResult modify(ResourceModifyReqDTO resourceModifyReqDTO) {
        Boolean isSuccess = service.modify(resourceModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-更新(所有字段)
     *
     * @param resourceModifyReqDTO 菜单更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "resource/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(ResourceModifyReqDTO resourceModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(resourceModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:CDZY:SC"})
    @RequestMapping(value = "resource/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "resource/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 菜单-删除(参数)
     *
     * @param resourceRemoveReqDTO 菜单删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "resource/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(ResourceRemoveReqDTO resourceRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(resourceRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 权限资源-获取菜单资源集合
     *
     * @return ItemsResult<UserMenuResourceDTO> 菜单资源集合
     */
    @RequestMapping(value = "resource/listUserMenu", method = {RequestMethod.GET})
    public ItemsResult<UserMenuResourceDTO> listUserMenu() {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listUserMenu());
    }

    /**
     * 权限资源-根据菜单ID,获取菜单页面操作集合
     *
     * @param menuPath 菜单路径
     * @return ItemsResult<UserMenuOperationDTO> 菜单页面操作集合
     */
    @RequestMapping(value = "resource/listUserMenuOperation", method = {RequestMethod.GET})
    public ItemsResult<UserMenuOperationDTO> listUserMenuOperation(String menuPath) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listUserMenuOperation(menuPath));
    }
}

package com.vehicle.web.role.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageR;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.role.RoleService;
import com.vehicle.service.role.dto.RoleAddReqDTO;
import com.vehicle.service.role.dto.RoleListPermissionReqDTO;
import com.vehicle.service.role.dto.RoleListPermissionResDTO;
import com.vehicle.service.role.dto.RoleListReqDTO;
import com.vehicle.service.role.dto.RoleListResDTO;
import com.vehicle.service.role.dto.RoleModifyPermissionReqDTO;
import com.vehicle.service.role.dto.RoleModifyReqDTO;
import com.vehicle.service.role.dto.RolePageReqDTO;
import com.vehicle.service.role.dto.RolePageResDTO;
import com.vehicle.service.role.dto.RoleRemoveReqDTO;
import com.vehicle.service.role.dto.RoleShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class RoleController extends AbstractController<RoleService> {

    /**
     * 角色-列表
     *
     * @param roleListReqDTO 角色列表请求DTO
     * @return ItemsResult<RoleListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "role/list", method = {RequestMethod.GET})
    public ItemsResult<RoleListResDTO> list(RoleListReqDTO roleListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(roleListReqDTO));
    }

    /**
     * 角色-First查询
     *
     * @param roleListReqDTO 角色Fist查询请求DTO
     * @return ItemResult<RoleListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "role/listOne", method = {RequestMethod.GET})
    public ItemResult<RoleListResDTO> listOne(RoleListReqDTO roleListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(roleListReqDTO));
    }

    /**
     * 角色-权限列表
     *
     * @param roleListPermissionReqDTO 角色权限列表请求DTO
     * @return ItemsResult<RoleListPermissionResDTO> 权限列表结果
     */
    @RequiresPermissions(value = {"YHGL:JSGL:QXPZ"})
    @RequestMapping(value = "role/listPermission", method = {RequestMethod.GET})
    public ItemsResult<RoleListPermissionResDTO> listPermission(RoleListPermissionReqDTO roleListPermissionReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listPermission(roleListPermissionReqDTO));
    }

    /**
     * 角色-分页
     *
     * @param rolePageReqDTO 角色分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<RolePageResDTO> 分页结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:JSGL:CX"})
    @RequestMapping(value = "role/page", method = {RequestMethod.GET})
    public PageResult<RolePageResDTO> page(RolePageReqDTO rolePageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<RolePageResDTO> rolePageResDTOPage = service.pagination(rolePageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, rolePageResDTOPage.getTotal(), rolePageResDTOPage.getRecords(), size, current);
    }

    /**
     * 角色-分页R
     *
     * @param rolePageReqDTO 角色分页请求DTO
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @return PageR<RolePageResDTO> 分页结果
     */
    @RequiresPermissions(value = {"YHGL:JSGL:CX"})
    @RequestMapping(value = "role/page_r", method = {RequestMethod.GET})
    public PageR<RolePageResDTO> pageR(RolePageReqDTO rolePageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<RolePageResDTO> rolePageResDTOPage = service.pagination(rolePageReqDTO, current, size);
        return responsePageR(ResponseCodeEnum.SUCCESS, rolePageResDTOPage.getTotal(), rolePageResDTOPage.getRecords(), size, current);
    }

    /**
     * 角色-新增
     *
     * @param roleAddReqDTO 角色新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:JSGL:XZ"})
    @RequestMapping(value = "role/add", method = {RequestMethod.POST})
    public MessageResult add(RoleAddReqDTO roleAddReqDTO) {
        Boolean isSuccess = service.add(roleAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-新增(所有字段)
     *
     * @param roleAddReqDTO 角色新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "role/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(RoleAddReqDTO roleAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(roleAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-批量新增(所有字段)
     *
     * @param roleAddReqDTOList 角色批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "role/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<RoleAddReqDTO> roleAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(roleAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-详情
     *
     * @param id 主键 ID
     * @return ItemResult<RoleShowResDTO> 详情结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:JSGL:CK"})
    @RequestMapping(value = "role/show", method = {RequestMethod.GET})
    public ItemResult<RoleShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 角色-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<RoleShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "role/showByIds", method = {RequestMethod.POST})
    public ItemsResult<RoleShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 角色-更新
     *
     * @param roleModifyReqDTO 角色更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:JSGL:BJ"})
    @RequestMapping(value = "role/modify", method = {RequestMethod.POST})
    public MessageResult modify(RoleModifyReqDTO roleModifyReqDTO) {
        Boolean isSuccess = service.modify(roleModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-更新(所有字段)
     *
     * @param roleModifyReqDTO 角色更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "role/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(RoleModifyReqDTO roleModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(roleModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-权限更新
     *
     * @param roleModifyPermissionReqDTO 角色权限更新请求DTO
     * @return MessageResult 更新结果
     */
    @RequiresPermissions(value = {"YHGL:JSGL:QXPZ"})
    @RequestMapping(value = "role/modifyPermission", method = {RequestMethod.POST})
    public MessageResult modifyPermission(@RequestBody RoleModifyPermissionReqDTO roleModifyPermissionReqDTO) {
        Boolean isSuccess = service.modifyPermission(roleModifyPermissionReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:JSGL:SC"})
    @RequestMapping(value = "role/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        RoleRemoveReqDTO vo = new RoleRemoveReqDTO();
        vo.setId(id);
        return removeByParams(vo);
    }

    /**
     * 角色-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "role/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 角色-删除(参数)
     *
     * @param roleRemoveReqDTO 角色删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "role/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(RoleRemoveReqDTO roleRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(roleRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

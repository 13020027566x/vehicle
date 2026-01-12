package com.vehicle.web.userrole.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.userrole.UserRoleService;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户角色 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class UserRoleController extends AbstractController<UserRoleService> {

    /**
     * 用户角色-列表
     *
     * @param userRoleListReqDTO 用户角色列表请求DTO
     * @return ItemsResult<UserRoleListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "userRole/list", method = {RequestMethod.GET})
    public ItemsResult<UserRoleListResDTO> list(UserRoleListReqDTO userRoleListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(userRoleListReqDTO));
    }

    /**
     * 用户角色-First查询
     *
     * @param userRoleListReqDTO 用户角色Fist查询请求DTO
     * @return ItemResult<UserRoleListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "userRole/listOne", method = {RequestMethod.GET})
    public ItemResult<UserRoleListResDTO> listOne(UserRoleListReqDTO userRoleListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(userRoleListReqDTO));
    }

    /**
     * 用户角色-分页
     *
     * @param userRolePageReqDTO 用户角色分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<UserRolePageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "userRole/page", method = {RequestMethod.GET})
    public PageResult<UserRolePageResDTO> page(UserRolePageReqDTO userRolePageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<UserRolePageResDTO> userRolePageResDTOPage = service.pagination(userRolePageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, userRolePageResDTOPage.getTotal(), userRolePageResDTOPage.getRecords(), size, current);
    }

    /**
     * 用户角色-新增
     *
     * @param userRoleAddReqDTO 用户角色新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequestMapping(value = "userRole/add", method = {RequestMethod.POST})
    public MessageResult add(UserRoleAddReqDTO userRoleAddReqDTO) {
        Boolean isSuccess = service.add(userRoleAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-新增(所有字段)
     *
     * @param userRoleAddReqDTO 用户角色新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userRole/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(UserRoleAddReqDTO userRoleAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(userRoleAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-批量新增(所有字段)
     *
     * @param userRoleAddReqDTOList 用户角色批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userRole/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<UserRoleAddReqDTO> userRoleAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(userRoleAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-详情
     *
     * @param id 主键 ID
     * @return ItemResult<UserRoleShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "userRole/show", method = {RequestMethod.GET})
    public ItemResult<UserRoleShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 用户角色-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<UserRoleShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "userRole/showByIds", method = {RequestMethod.POST})
    public ItemsResult<UserRoleShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 用户角色-更新
     *
     * @param userRoleModifyReqDTO 用户角色更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequestMapping(value = "userRole/modify", method = {RequestMethod.POST})
    public MessageResult modify(UserRoleModifyReqDTO userRoleModifyReqDTO) {
        Boolean isSuccess = service.modify(userRoleModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-更新(所有字段)
     *
     * @param userRoleModifyReqDTO 用户角色更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userRole/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(UserRoleModifyReqDTO userRoleModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(userRoleModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequestMapping(value = "userRole/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "userRole/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户角色-删除(参数)
     *
     * @param userRoleRemoveReqDTO 用户角色删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "userRole/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(UserRoleRemoveReqDTO userRoleRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(userRoleRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

package com.vehicle.web.user.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.annotation.Desensitize;
import com.finhub.framework.core.annotation.Desensitizes;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import cn.hutool.core.util.DesensitizedUtil;
import com.vehicle.service.user.UserService;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserListOrganizationReqDTO;
import com.vehicle.service.user.dto.UserListOrganizationResDTO;
import com.vehicle.service.user.dto.UserListReqDTO;
import com.vehicle.service.user.dto.UserListResDTO;
import com.vehicle.service.user.dto.UserListRoleReqDTO;
import com.vehicle.service.user.dto.UserListRoleResDTO;
import com.vehicle.service.user.dto.UserModifyOrganizationReqDTO;
import com.vehicle.service.user.dto.UserModifyReqDTO;
import com.vehicle.service.user.dto.UserModifyRoleReqDTO;
import com.vehicle.service.user.dto.UserPageReqDTO;
import com.vehicle.service.user.dto.UserPageResDTO;
import com.vehicle.service.user.dto.UserPageRoleReqDTO;
import com.vehicle.service.user.dto.UserPageRoleResDTO;
import com.vehicle.service.user.dto.UserRemoveReqDTO;
import com.vehicle.service.user.dto.UserShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Validated
@RestController
public class UserController extends AbstractController<UserService> {

    /**
     * 用户-列表
     *
     * @param userListReqDTO 用户-列表 ReqDTO
     * @return ItemsResult<UserListResDTO> 列表结果
     */
    @RequestMapping(value = "user/list", method = {RequestMethod.GET})
    public ItemsResult<UserListResDTO> list(UserListReqDTO userListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(userListReqDTO));
    }

    /**
     * 用户-分页
     *
     * @param userPageReqDTO 用户-分页 ReqDTO
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @return PageResult<UserPageResDTO> 分页结果
     */
    @Desensitizes({
        @Desensitize(field = "mobile", type = DesensitizedUtil.DesensitizedType.MOBILE_PHONE)
    })
    @RequiresPermissions(value = {"YHGL:RYGL:CX"})
    @RequestMapping(value = "user/page", method = {RequestMethod.GET})
    public PageResult<UserPageResDTO> page(UserPageReqDTO userPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<UserPageResDTO> userPageResDTOPage = service.pagination(userPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, userPageResDTOPage.getTotal(), userPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 用户-角色分页
     *
     * @param userPageRoleReqDTO 用户-角色分页 ReqDTO
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @return PageResult<UserPageRoleResDTO> 分页结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:JSPZ"})
    @RequestMapping(value = "user/pageRole", method = {RequestMethod.GET})
    public PageResult<UserPageRoleResDTO> pageRole(UserPageRoleReqDTO userPageRoleReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<UserPageRoleResDTO> userPageRoleResDTOPage = service.pagination(userPageRoleReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, userPageRoleResDTOPage.getTotal(), userPageRoleResDTOPage.getRecords(), size, current);
    }

    /**
     * 用户-角色列表
     * @param userListRoleReqDTO 用户-角色列表 ReqDTO
     * @return ItemsResult<UserListRoleResDTO> 列表结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:JSPZ"})
    @RequestMapping(value = "user/listRole", method = {RequestMethod.GET})
    public ItemsResult<UserListRoleResDTO> listRole(UserListRoleReqDTO userListRoleReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listRole(userListRoleReqDTO));
    }

    /**
     * 用户-新增
     *
     * @param userAddReqDTO 用户-新增 ReqDTO
     * @return MessageResult 消息结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:XZ"})
    @RequestMapping(value = "user/add", method = {RequestMethod.POST})
    public MessageResult add(UserAddReqDTO userAddReqDTO) {
        Boolean isSuccess = service.add(userAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-详情
     *
     * @param id 主键 ID
     * @return ItemResult<UserShowResDTO> 详情结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:CK"})
    @RequestMapping(value = "user/show", method = {RequestMethod.GET})
    public ItemResult<UserShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 用户-所属机构列表
     *
     * @param userListOrganizationReqDTO 用户-所属机构列表 ReqDTO
     * @return ItemsResult<UserListOrganizationResDTO> 列表结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:JGPZ"})
    @RequestMapping(value = "user/listOrganization", method = {RequestMethod.GET})
    public ItemsResult<UserListOrganizationResDTO> listOrganization(UserListOrganizationReqDTO userListOrganizationReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listOrganization(userListOrganizationReqDTO));
    }

    /**
     * 用户-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<UserShowResDTO> 详情结果
     */
    @RequestMapping(value = "user/showByIds", method = {RequestMethod.POST})
    public ItemsResult<UserShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 用户-更新
     *
     * @param userModifyReqDTO 用户-更新 ReqDTO
     * @return MessageResult 消息结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:BJ"})
    @RequestMapping(value = "user/modify", method = {RequestMethod.POST})
    public MessageResult modify(UserModifyReqDTO userModifyReqDTO) {
        Boolean isSuccess = service.modify(userModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-更新所属角色
     *
     * @param userModifyRoleReqDTO 用户-更新所属角色 ReqDTO
     * @return MessageResult 消息结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:JSPZ"})
    @RequestMapping(value = "user/modifyRole", method = {RequestMethod.POST})
    public MessageResult modifyRole(@RequestBody UserModifyRoleReqDTO userModifyRoleReqDTO) {
        Boolean isSuccess = service.modifyRole(userModifyRoleReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-更新所属机构
     *
     * @param userModifyOrganizationReqDTO 用户-更新所属机构 ReqDTO
     * @return MessageResult 消息结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:JGPZ"})
    @RequestMapping(value = "user/modifyOrganization", method = {RequestMethod.POST})
    public MessageResult modifyOrganization(@RequestBody UserModifyOrganizationReqDTO userModifyOrganizationReqDTO) {
        Boolean isSuccess = service.modifyOrganization(userModifyOrganizationReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-删除
     *
     * @param id 主键 ID
     * @return MessageResult 消息结果
     */
    @RequiresPermissions(value = {"YHGL:RYGL:SC"})
    @RequestMapping(value = "user/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 消息结果
     */
    @RequestMapping(value = "user/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户-删除(参数)
     *
     * @param userRemoveReqDTO 用户-删除(参数) ReqDTO
     * @return MessageResult 消息结果
     */
    @RequestMapping(value = "user/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(UserRemoveReqDTO userRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(userRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

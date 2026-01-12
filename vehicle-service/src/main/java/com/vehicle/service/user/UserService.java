package com.vehicle.service.user;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserDTO;
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

import java.util.List;

/**
 * 用户 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface UserService extends BaseService<UserDTO> {

    static UserService me() {
        return SpringUtil.getBean(UserService.class);
    }

    /**
     * 列表
     *
     * @param userListReqDTO 入参DTO
     * @return
     */
    List<UserListResDTO> list(UserListReqDTO userListReqDTO);

    /**
     * First查询
     *
     * @param userListReqDTO 入参DTO
     * @return
     */
    UserListResDTO listOne(UserListReqDTO userListReqDTO);

    /**
     * 分页
     *
     * @param userPageReqDTO 入参DTO
     * @param current        当前页
     * @param size           每页大小
     * @return
     */
    Page<UserPageResDTO> pagination(UserPageReqDTO userPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param userAddReqDTO 入参DTO
     * @return
     */
    Boolean add(UserAddReqDTO userAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param userAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(UserAddReqDTO userAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param userAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<UserAddReqDTO> userAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    UserShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<UserShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param userModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(UserModifyReqDTO userModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param userModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(UserModifyReqDTO userModifyReqDTO);

    /**
     * 参数删除
     *
     * @param userRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(UserRemoveReqDTO userRemoveReqDTO);

    /**
     * 用户角色分页
     *
     * @param userPageRoleReqDTO 入参DTO
     * @param current            当前页
     * @param size               大小
     * @return
     */
    Page<UserPageRoleResDTO> pagination(UserPageRoleReqDTO userPageRoleReqDTO, Integer current, Integer size);

    /**
     * 修改用户角色
     *
     * @param userModifyRoleReqDTO
     * @return
     */
    Boolean modifyRole(UserModifyRoleReqDTO userModifyRoleReqDTO);

    /**
     * 获取用户角色列表
     *
     * @param userListRoleReqDTO
     * @return
     */
    List<UserListRoleResDTO> listRole(UserListRoleReqDTO userListRoleReqDTO);

    /**
     * 获取用户机构列表
     *
     * @param userListOrganizationReqDTO
     * @return
     */
    List<UserListOrganizationResDTO> listOrganization(UserListOrganizationReqDTO userListOrganizationReqDTO);

    /**
     * 修改用户机构
     *
     * @param userModifyOrganizationReqDTO
     * @return
     */
    Boolean modifyOrganization(UserModifyOrganizationReqDTO userModifyOrganizationReqDTO);
}

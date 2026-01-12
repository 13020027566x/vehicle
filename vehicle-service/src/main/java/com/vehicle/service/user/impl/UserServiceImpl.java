package com.vehicle.service.user.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.user.po.UserPO;
import com.vehicle.service.user.UserService;
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
import com.vehicle.service.user.manager.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserManager, UserPO, UserDTO> implements UserService {

    @Override
    public List<UserListResDTO> list(final UserListReqDTO userListReqDTO) {
        return manager.list(userListReqDTO);
    }

    @Override
    public UserListResDTO listOne(final UserListReqDTO userListReqDTO) {
        return manager.listOne(userListReqDTO);
    }

    @Override
    public Page<UserPageResDTO> pagination(final UserPageReqDTO userPageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(userPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final UserAddReqDTO userAddReqDTO) {
        return manager.add(userAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final UserAddReqDTO userAddReqDTO) {
        return manager.addAllColumn(userAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<UserAddReqDTO> userAddReqDTOList) {
        return manager.addBatchAllColumn(userAddReqDTOList);
    }

    @Override
    public UserShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<UserShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final UserModifyReqDTO userModifyReqDTO) {
        return manager.modify(userModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final UserModifyReqDTO userModifyReqDTO) {
        return manager.modifyAllColumn(userModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final UserRemoveReqDTO userRemoveReqDTO) {
        return manager.removeByParams(userRemoveReqDTO);
    }

    @Override
    public Page<UserPageRoleResDTO> pagination(final UserPageRoleReqDTO userPageRoleReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(userPageRoleReqDTO, current, size);
    }

    @Override
    public Boolean modifyRole(final UserModifyRoleReqDTO userModifyRoleReqDTO) {
        return manager.modifyRole(userModifyRoleReqDTO);
    }

    @Override
    public List<UserListRoleResDTO> listRole(final UserListRoleReqDTO userListRoleReqDTO) {
        return manager.listRole(userListRoleReqDTO);
    }

    @Override
    public List<UserListOrganizationResDTO> listOrganization(
        final UserListOrganizationReqDTO userListOrganizationReqDTO) {
        return manager.listOrganization(userListOrganizationReqDTO);
    }

    @Override
    public Boolean modifyOrganization(final UserModifyOrganizationReqDTO userModifyOrganizationReqDTO) {
        return manager.modifyOrganization(userModifyOrganizationReqDTO);
    }
}

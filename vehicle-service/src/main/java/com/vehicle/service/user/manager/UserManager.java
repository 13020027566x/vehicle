package com.vehicle.service.user.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.vehicle.dao.user.UserDAO;
import com.vehicle.dao.user.po.UserPO;
import com.vehicle.service.dictionary.DictionaryUtils;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.manager.OrganizationManager;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.role.manager.RoleManager;
import com.vehicle.service.user.converter.UserConverter;
import com.vehicle.service.user.domain.UserDO;
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
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.manager.UserOrgManager;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.manager.UserRoleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserManager extends BaseManagerImpl<UserDAO, UserPO, UserDTO, UserConverter> {

    public static UserManager me() {
        return SpringUtil.getBean(UserManager.class);
    }

    public List<UserListResDTO> list(final UserListReqDTO userListReqDTO) {
        UserDTO paramsDTO = UserDO.me().buildListParamsDTO(userListReqDTO);

        List<UserDTO> userDTOList = super.findList(paramsDTO);

        return UserDO.me().transferUserListResDTOList(userDTOList);
    }

    public UserListResDTO listOne(final UserListReqDTO userListReqDTO) {
        UserDTO paramsDTO = UserDO.me().buildListParamsDTO(userListReqDTO);

        UserDTO userDTO = super.findOne(paramsDTO);

        return UserDO.me().transferUserListResDTO(userDTO);
    }

    public Page<UserPageResDTO> pagination(final UserPageReqDTO userPageReqDTO, final Integer current, final Integer size) {
        QueryWrapper<UserPO> queryWrapper = UserDO.me().buildUserQueryWrapper(userPageReqDTO);

        Page<UserDTO> userDTOPage = super.findPage(queryWrapper, current, size);

        return UserDO.me().transferUserPageResDTOPage(userDTOPage);
    }

    public Page<UserPageRoleResDTO> pagination(final UserPageRoleReqDTO userPageRoleReqDTO, final Integer current, final Integer size) {
        UserRoleDTO userRoleParamsDTO = UserDO.me().buildUserRoleParamsDTO(userPageRoleReqDTO.getUserId());

        List<UserRoleDTO> userRoleDTOList = UserRoleManager.me().findList(userRoleParamsDTO);
        Set<String> userRoleIdSet = UserDO.me().buildUserRoleIdSet(userRoleDTOList);

        Page<RoleDTO> roleDTOPage = RoleManager.me().findPage(new RoleDTO(), current, size);

        return UserDO.me().transferUserPageRoleResDTOPage(roleDTOPage, userRoleIdSet);
    }

    public List<UserListRoleResDTO> listRole(final UserListRoleReqDTO userListRoleReqDTO) {
        UserRoleDTO userRoleParamsDTO = UserDO.me().buildUserRoleParamsDTO(userListRoleReqDTO.getUserId());

        List<UserRoleDTO> userRoleDTOList = UserRoleManager.me().findList(userRoleParamsDTO);

        return UserDO.me().transferUserListRoleResDTOList(userRoleDTOList);
    }

    public List<UserListOrganizationResDTO> listOrganization(final UserListOrganizationReqDTO userListOrganizationReqDTO) {
        UserOrgDTO userOrgParamsDTO = UserDO.me().buildUserOrgParamsDTO(userListOrganizationReqDTO.getUserId());

        List<UserOrgDTO> userOrgDTOList = UserOrgManager.me().findList(userOrgParamsDTO);
        List<OrganizationDTO> organizationDTOList = OrganizationManager.me().findList(new OrganizationDTO());

        return UserDO.me().transferUserListOrganizationResDTOList(userOrgDTOList, organizationDTOList);
    }

    public Boolean add(final UserAddReqDTO userAddReqDTO) {
        UserDO.me().checkUserAddReqDTO(userAddReqDTO);

        QueryWrapper<UserPO> queryWrapper = UserDO.me().buildUserQueryWrapper(userAddReqDTO.getLoginName());
        Integer count = UserManager.me().findCount(queryWrapper);

        UserDO.me().checkResultCount(count);

        UserDTO addUserDTO = UserDO.me().buildAddUserDTO(userAddReqDTO, DictionaryUtils.YHZT_MAP());

        return super.saveDTO(addUserDTO);
    }

    public Boolean addAllColumn(final UserAddReqDTO userAddReqDTO) {
        UserDO.me().checkUserAddReqDTO(userAddReqDTO);

        UserDTO addUserDTO = UserDO.me().buildAddUserDTO(userAddReqDTO, DictionaryUtils.YHZT_MAP());

        return super.saveAllColumn(addUserDTO);
    }

    public Boolean addBatchAllColumn(final List<UserAddReqDTO> userAddReqDTOList) {
        UserDO.me().checkUserAddReqDTOList(userAddReqDTOList);

        List<UserDTO> addBatchUserDTOList = UserDO.me().buildAddBatchUserDTOList(userAddReqDTOList);

        return super.saveBatchAllColumn(addBatchUserDTOList);
    }

    public UserShowResDTO show(final String id) {
        UserDTO userDTO = super.findById(id);

        List<RoleDTO> roleDTOList = findRoleList(id);
        List<OrganizationDTO> organizationDTOList = findOrganizationList(id);

        return UserDO.me().transferUserShowResDTO(userDTO, roleDTOList, organizationDTOList, DictionaryUtils.YHZT_MAP());
    }

    private List<OrganizationDTO> findOrganizationList(final String userId) {
        UserOrgDTO userOrgParamsDTO = UserDO.me().buildUserOrgParamsDTO(userId);

        List<UserOrgDTO> userOrgDTOList = UserOrgManager.me().findList(userOrgParamsDTO);

        List<String> orgIdList = UserDO.me().buildOrgIdList(userOrgDTOList);

        List<OrganizationDTO> organizationDTOList = Lists.newArrayList();

        if (Func.isNotEmpty(orgIdList)) {
            organizationDTOList = OrganizationManager.me().findBatchIds(orgIdList);
        }

        return organizationDTOList;
    }

    private List<RoleDTO> findRoleList(final String userId) {
        UserRoleDTO userRoleParamsDTO = UserDO.me().buildUserRoleParamsDTO(userId);

        List<UserRoleDTO> userRoleDTOList = UserRoleManager.me().findList(userRoleParamsDTO);

        List<String> roleIdList = UserDO.me().buildRoleIdList(userRoleDTOList);

        List<RoleDTO> roleDTOList = Lists.newArrayList();

        if (Func.isNotEmpty(roleIdList)) {
            roleDTOList = RoleManager.me().findBatchIds(roleIdList);
        }

        return roleDTOList;
    }

    public List<UserShowResDTO> showByIds(final List<String> ids) {
        UserDO.me().checkIds(ids);

        List<UserDTO> userDTOList = super.findBatchIds(ids);

        return UserDO.me().transferUserShowResDTOList(userDTOList);
    }

    public Boolean modify(final UserModifyReqDTO userModifyReqDTO) {
        UserDO.me().checkUserModifyReqDTO(userModifyReqDTO);

        QueryWrapper<UserPO> queryWrapper = UserDO.me().buildUserQueryWrapper(userModifyReqDTO);
        Integer count = UserManager.me().findCount(queryWrapper);

        UserDO.me().checkResultCount(count);

        UserDTO modifyUserDTO = UserDO.me().buildModifyUserDTO(userModifyReqDTO, DictionaryUtils.YHZT_MAP());

        return super.modifyById(modifyUserDTO);
    }

    public Boolean modifyAllColumn(final UserModifyReqDTO userModifyReqDTO) {
        UserDO.me().checkUserModifyReqDTO(userModifyReqDTO);

        UserDTO modifyUserDTO = UserDO.me().buildModifyUserDTO(userModifyReqDTO, DictionaryUtils.YHZT_MAP());

        return super.modifyAllColumnById(modifyUserDTO);
    }

    public Boolean removeByParams(final UserRemoveReqDTO userRemoveReqDTO) {
        UserDO.me().checkUserRemoveReqDTO(userRemoveReqDTO);

        UserDTO removeUserDTO = UserDO.me().buildRemoveUserDTO(userRemoveReqDTO);

        return super.remove(removeUserDTO);
    }

    public Boolean modifyRole(UserModifyRoleReqDTO userModifyRoleReqDTO) {
        UserDTO userDTO = UserManager.me().findById(userModifyRoleReqDTO.getUserId());

        UserDO.me().checkUserDTO(userDTO);

        UserRoleDTO userRoleParamsDTO = UserDO.me().buildUserRoleParamsDTO(userModifyRoleReqDTO.getUserId());
        UserRoleManager.me().remove(userRoleParamsDTO);

        if (Func.isNotEmpty(userModifyRoleReqDTO.getRoleIds())) {
            List<RoleDTO> roleDTOList = RoleManager.me().findBatchIds(userModifyRoleReqDTO.getRoleIds());
            List<UserRoleDTO> userRoleDTOList = UserDO.me().buildUserRoleDTOList(userDTO, roleDTOList);
            return UserRoleManager.me().saveBatchAllColumn(userRoleDTOList);
        }

        return true;
    }

    public Boolean modifyOrganization(final UserModifyOrganizationReqDTO userModifyOrganizationReqDTO) {
        UserDTO userDTO = UserManager.me().findById(userModifyOrganizationReqDTO.getUserId());

        UserDO.me().checkUserDTO(userDTO);

        UserOrgDTO userOrgParamsDTO = UserDO.me().buildUserOrgParamsDTO(userModifyOrganizationReqDTO.getUserId());
        UserOrgManager.me().remove(userOrgParamsDTO);

        if (Func.isNotEmpty(userModifyOrganizationReqDTO.getOrgIds())) {
            List<OrganizationDTO> organizationDTOList = OrganizationManager.me().findBatchIds(userModifyOrganizationReqDTO.getOrgIds());
            List<UserOrgDTO> userOrgDTOList = UserDO.me().buildUserOrgDTOList(userDTO, organizationDTOList);
            return UserOrgManager.me().saveBatchAllColumn(userOrgDTOList);
        }

        return true;
    }

    @Override
    protected UserPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserPO();
        }

        return BeanUtil.toBean(map, UserPO.class);
    }

    @Override
    protected UserDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserDTO();
        }

        return BeanUtil.toBean(map, UserDTO.class);
    }
}

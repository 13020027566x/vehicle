package com.vehicle.service.user.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vehicle.dao.user.po.UserPO;
import com.vehicle.service.dictionary.dto.DictionaryDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.user.converter.UserConverter;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserDTO;
import com.vehicle.service.user.dto.UserListOrganizationResDTO;
import com.vehicle.service.user.dto.UserListReqDTO;
import com.vehicle.service.user.dto.UserListResDTO;
import com.vehicle.service.user.dto.UserListRoleResDTO;
import com.vehicle.service.user.dto.UserModifyReqDTO;
import com.vehicle.service.user.dto.UserPageReqDTO;
import com.vehicle.service.user.dto.UserPageResDTO;
import com.vehicle.service.user.dto.UserPageRoleResDTO;
import com.vehicle.service.user.dto.UserRemoveReqDTO;
import com.vehicle.service.user.dto.UserShowResDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserDO extends BaseDO<UserDTO, UserPO, UserConverter> {

    public static UserDO me() {
        return SpringUtil.getBean(UserDO.class);
    }

    public void checkUserAddReqDTO(final UserAddReqDTO userAddReqDTO) {
        if (Func.isEmpty(userAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkResultCount(final Integer count) {
        if (!Func.isNullOrZero(count)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "登录名称已存在");
        }
    }

    public void checkUserAddReqDTOList(final List<UserAddReqDTO> userAddReqDTOList) {
        if (Func.isEmpty(userAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkUserModifyReqDTO(final UserModifyReqDTO userModifyReqDTO) {
        if (Func.isEmpty(userModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserRemoveReqDTO(final UserRemoveReqDTO userRemoveReqDTO) {
        if (Func.isEmpty(userRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserDTO(final UserDTO userDTO) {
        if (Func.isEmpty(userDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "用户ID参数错误");
        }
    }

    public QueryWrapper<UserPO> buildUserQueryWrapper(final UserPageReqDTO userPageReqDTO) {
        UserDTO userDTO = converter.convertToUserDTO(userPageReqDTO);
        UserPO userPO = converter.dtoToPO(userDTO);

        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>(userPO);
        if (Func.isNotEmpty(userPageReqDTO.getUpdateStartAt())) {
            queryWrapper.ge(UserPO.DB_COL_UPDATE_AT,
                DateUtil.millisecond(DateUtil.parseDateTime(userPageReqDTO.getUpdateStartAt())));
        }

        if (Func.isNotEmpty(userPageReqDTO.getUpdateEndAt())) {
            queryWrapper.le(UserPO.DB_COL_UPDATE_AT,
                DateUtil.millisecond(DateUtil.parseDateTime(userPageReqDTO.getUpdateEndAt())));
        }

        queryWrapper.orderByDesc(UserPO.DB_COL_ID);

        return queryWrapper;
    }

    public UserDTO buildModifyUserDTO(final UserModifyReqDTO userModifyReqDTO, Map<String, DictionaryDTO> dictionaryDTOMap) {
        UserDTO userDTO = converter.convertToUserDTO(userModifyReqDTO);

        if (Func.isNotEmpty(userDTO.getPwd())) {
            userDTO.setPwd(userDTO.getPwd());
        }

        userDTO.setStatusCode(dictionaryDTOMap.get(userModifyReqDTO.getStatusCode()).getCode());
        userDTO.setStatusVal(dictionaryDTOMap.get(userModifyReqDTO.getStatusCode()).getValue());

        return userDTO;
    }

    public UserDTO buildAddUserDTO(final UserAddReqDTO userAddReqDTO, Map<String, DictionaryDTO> dictionaryDTOMap) {
        UserDTO userDTO = converter.convertToUserDTO(userAddReqDTO);

        userDTO.setPwd(userDTO.getPwd());
        userDTO.setStatusCode(dictionaryDTOMap.get(userAddReqDTO.getStatusCode()).getCode());
        userDTO.setStatusVal(dictionaryDTOMap.get(userAddReqDTO.getStatusCode()).getValue());

        return userDTO;
    }

    public Set<String> buildUserRoleIdSet(final List<UserRoleDTO> userRoleDTOList) {
        Set<String> userRoleIdSet = Sets.newHashSet();

        if (Func.isNotEmpty(userRoleDTOList)) {
            for (UserRoleDTO userRoleDTO : userRoleDTOList) {
                userRoleIdSet.add(userRoleDTO.getRoleId());
            }
        }

        return userRoleIdSet;
    }

    public List<UserRoleDTO> buildUserRoleDTOList(final UserDTO userDTO, final List<RoleDTO> roleDTOList) {
        List<UserRoleDTO> userRoleDTOList = Lists.newArrayList();

        for (RoleDTO roleDTO : roleDTOList) {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setUserCode(userDTO.getCode());
            userRoleDTO.setUserName(userDTO.getName());
            userRoleDTO.setUserId(userDTO.getId());
            userRoleDTO.setRoleName(roleDTO.getName());
            userRoleDTO.setRoleCode(roleDTO.getCode());
            userRoleDTO.setRoleId(roleDTO.getId());
            userRoleDTOList.add(userRoleDTO);
        }

        return userRoleDTOList;
    }

    public List<UserOrgDTO> buildUserOrgDTOList(final UserDTO userDTO, final List<OrganizationDTO> organizationDTOList) {
        List<UserOrgDTO> userOrgDTOList = Lists.newArrayList();

        for (OrganizationDTO organizationDTO : organizationDTOList) {
            UserOrgDTO userOrgDTO = new UserOrgDTO();
            userOrgDTO.setUserCode(userDTO.getCode());
            userOrgDTO.setUserName(userDTO.getName());
            userOrgDTO.setUserId(userDTO.getId());
            userOrgDTO.setOrgName(organizationDTO.getName());
            userOrgDTO.setOrgCode(organizationDTO.getCode());
            userOrgDTO.setOrgId(organizationDTO.getId());
            userOrgDTOList.add(userOrgDTO);
        }

        return userOrgDTOList;
    }

    public QueryWrapper<UserPO> buildUserQueryWrapper(final String loginName) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(loginName)) {
            queryWrapper.eq(UserPO.DB_COL_LOGIN_NAME, loginName);
        }

        return queryWrapper;
    }

    public QueryWrapper<UserPO> buildUserQueryWrapper(final UserModifyReqDTO userModifyReqDTO) {
        QueryWrapper<UserPO> updateWrapper = buildUserQueryWrapper(userModifyReqDTO.getLoginName());

        updateWrapper.ne(UserPO.DB_COL_ID, userModifyReqDTO.getId());

        return updateWrapper;
    }

    public UserDTO buildListParamsDTO(final UserListReqDTO userListReqDTO) {
        return converter.convertToUserDTO(userListReqDTO);
    }

    public List<UserDTO> buildAddBatchUserDTOList(final List<UserAddReqDTO> userAddReqDTOList) {
        return converter.convertToUserDTOList(userAddReqDTOList);
    }

    public UserOrgDTO buildUserOrgParamsDTO(final String userId) {
        UserOrgDTO userOrgParamsDTO = new UserOrgDTO();

        userOrgParamsDTO.setUserId(userId);

        return userOrgParamsDTO;
    }

    public List<String> buildOrgIdList(final List<UserOrgDTO> userOrgDTOList) {
        Set<String> orgIdSet = Sets.newHashSet();

        if (Func.isNotEmpty(userOrgDTOList)) {
            for (UserOrgDTO userOrg : userOrgDTOList) {
                orgIdSet.add(userOrg.getOrgId());
            }
        }

        return Lists.newArrayList(orgIdSet);
    }

    public UserRoleDTO buildUserRoleParamsDTO(final String userId) {
        UserRoleDTO userRoleParamsDTO = new UserRoleDTO();

        userRoleParamsDTO.setUserId(userId);

        return userRoleParamsDTO;
    }

    public List<String> buildRoleIdList(final List<UserRoleDTO> userRoleDTOList) {
        Set<String> roleIdSet = Sets.newHashSet();

        if (Func.isNotEmpty(userRoleDTOList)) {
            for (UserRoleDTO userRole : userRoleDTOList) {
                roleIdSet.add(userRole.getRoleId());
            }
        }

        return Lists.newArrayList(roleIdSet);
    }

    public UserDTO buildRemoveUserDTO(final UserRemoveReqDTO userRemoveReqDTO) {
        return converter.convertToUserDTO(userRemoveReqDTO);
    }

    public UserShowResDTO transferUserShowResDTO(final UserDTO userDTO, final List<RoleDTO> roleDTOList, final List<OrganizationDTO> organizationDTOList, Map<String, DictionaryDTO> dictionaryDTOMap) {
        if (Func.isEmpty(userDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        UserShowResDTO userShowResDTO = converter.convertToUserShowResDTO(userDTO);

        if (Func.isNotEmpty(userDTO.getStatusVal()) && Func.isNotEmpty(userDTO.getStatusCode())) {
            userShowResDTO.setStatusName(dictionaryDTOMap.get(userDTO.getStatusCode()).getName());
        }

        if (Func.isNotEmpty(roleDTOList)) {
            StringBuilder roleNames = new StringBuilder();
            for (RoleDTO roleDTO : roleDTOList) {
                roleNames.append(roleDTO.getName()).append(StrConstants.S_SEMICOLON);
            }
            userShowResDTO.setRoleNames(roleNames.toString());
        }

        if (Func.isNotEmpty(organizationDTOList)) {
            StringBuilder orgNames = new StringBuilder();
            for (OrganizationDTO organizationDTO : organizationDTOList) {
                orgNames.append(organizationDTO.getName()).append(StrConstants.S_SEMICOLON);
            }
            userShowResDTO.setOrgNames(orgNames.toString());
        }

        return userShowResDTO;
    }

    public UserPageRoleResDTO transferUserPageRoleResDTO(final RoleDTO roleDTO, final Set<String> userRoleIdSet) {
        UserPageRoleResDTO userPageRoleResDTO = converter.convertToUserPageRoleResDTO(roleDTO);

        if (Func.isNotEmpty(roleDTO.getUpdateAt())) {
            userPageRoleResDTO.setUpdateAt(DateUtil.formatDateTime(DateUtil.date(roleDTO.getUpdateAt())));
        }

        if (userRoleIdSet.contains(roleDTO.getId())) {
            userPageRoleResDTO.setChecked(true);
        } else {
            userPageRoleResDTO.setChecked(false);
        }

        return userPageRoleResDTO;
    }

    public List<UserListRoleResDTO> transferUserListRoleResDTOList(final List<UserRoleDTO> userRoleDTOList) {
        List<UserListRoleResDTO> userListRoleResDTOList = Lists.newArrayList();

        if (Func.isNotEmpty(userRoleDTOList)) {
            for (UserRoleDTO userRoleDTO : userRoleDTOList) {
                UserListRoleResDTO userListRoleResDTO = new UserListRoleResDTO();
                userListRoleResDTO.setRoleId(userRoleDTO.getRoleId());
                userListRoleResDTOList.add(userListRoleResDTO);
            }
        }

        return userListRoleResDTOList;
    }

    public List<UserListOrganizationResDTO> transferUserListOrganizationResDTOList(final List<UserOrgDTO> userOrgDTOList, final List<OrganizationDTO> organizationDTOList) {
        Set<String> userOrgIdSet = Sets.newHashSet();
        if (Func.isNotEmpty(userOrgDTOList)) {
            for (UserOrgDTO userOrgDTO : userOrgDTOList) {
                userOrgIdSet.add(userOrgDTO.getOrgId());
            }
        }

        Map<String, UserListOrganizationResDTO> userListOrganizationResDTOMap = Maps.newHashMap();
        if (Func.isNotEmpty(organizationDTOList)) {
            for (OrganizationDTO organizationDTO : organizationDTOList) {
                UserListOrganizationResDTO userListOrganizationResDTO = new UserListOrganizationResDTO();
                userListOrganizationResDTO.setId(organizationDTO.getId());
                userListOrganizationResDTO.setTitle(organizationDTO.getName());
                userListOrganizationResDTO.setPid(organizationDTO.getPid());
                userListOrganizationResDTO.setChecked(userOrgIdSet.contains(organizationDTO.getId()));
                userListOrganizationResDTOMap.put(organizationDTO.getId(), userListOrganizationResDTO);
            }
        }

        List<UserListOrganizationResDTO> userListOrganizationResDTOList = Lists.newArrayList();
        for (OrganizationDTO organizationDTO : organizationDTOList) {
            UserListOrganizationResDTO userListOrganizationResDTO =
                userListOrganizationResDTOMap.get(organizationDTO.getId());
            if (StrUtil.isBlank(organizationDTO.getPid())) {
                userListOrganizationResDTOList.add(userListOrganizationResDTO);
            } else {
                UserListOrganizationResDTO parentUserListOrganizationResDTO =
                    userListOrganizationResDTOMap.get(organizationDTO.getPid());
                List<UserListOrganizationResDTO> children = parentUserListOrganizationResDTO.getChildren();
                if (children == null) {
                    children = Lists.newArrayList();
                    parentUserListOrganizationResDTO.setChildren(children);
                }
                children.add(userListOrganizationResDTO);
            }
        }

        return userListOrganizationResDTOList;
    }

    public List<UserListResDTO> transferUserListResDTOList(final List<UserDTO> userDTOList) {
        if (Func.isEmpty(userDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserListResDTOList(userDTOList);
    }

    public UserListResDTO transferUserListResDTO(final UserDTO userDTO) {
        if (Func.isEmpty(userDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserListResDTO(userDTO);
    }

    public Page<UserPageResDTO> transferUserPageResDTOPage(final Page<UserDTO> userDTOPage) {
        if (Func.isEmpty(userDTOPage) || Func.isEmpty(userDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        Page<UserPageResDTO> userPageResDTOPage = new Page<>();
        List<UserPageResDTO> userPageResDTOList = converter.convertToUserPageResDTOList(userDTOPage.getRecords());
        userPageResDTOPage.setRecords(userPageResDTOList);
        userPageResDTOPage.setTotal(userDTOPage.getTotal());

        return userPageResDTOPage;
    }

    public Page<UserPageRoleResDTO> transferUserPageRoleResDTOPage(final Page<RoleDTO> roleDTOPage, final Set<String> userRoleIdSet) {
        Page<UserPageRoleResDTO> userPageRoleResDTOPage = converter.convertToUserPageRoleResDTOPage(roleDTOPage);

        List<UserPageRoleResDTO> records = Lists.newArrayList();
        for (RoleDTO roleDTO : roleDTOPage.getRecords()) {
            records.add(UserDO.me().transferUserPageRoleResDTO(roleDTO, userRoleIdSet));
        }
        userPageRoleResDTOPage.setRecords(records);

        return userPageRoleResDTOPage;
    }

    public List<UserShowResDTO> transferUserShowResDTOList(final List<UserDTO> userDTOList) {
        if (Func.isEmpty(userDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserShowResDTOList(userDTOList);
    }
}

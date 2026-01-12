package com.vehicle.service.user.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.user.po.UserPO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserDTO;
import com.vehicle.service.user.dto.UserListReqDTO;
import com.vehicle.service.user.dto.UserListResDTO;
import com.vehicle.service.user.dto.UserModifyReqDTO;
import com.vehicle.service.user.dto.UserPageReqDTO;
import com.vehicle.service.user.dto.UserPageResDTO;
import com.vehicle.service.user.dto.UserPageRoleResDTO;
import com.vehicle.service.user.dto.UserRemoveReqDTO;
import com.vehicle.service.user.dto.UserShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface UserConverter extends BaseConverter<UserDTO, UserPO> {

    static UserConverter me() {
        return SpringUtil.getBean(UserConverter.class);
    }

    UserDTO convertToUserDTO(UserAddReqDTO userAddReqDTO);

    UserDTO convertToUserDTO(UserModifyReqDTO userModifyReqDTO);

    UserDTO convertToUserDTO(UserRemoveReqDTO userRemoveReqDTO);

    UserDTO convertToUserDTO(UserListReqDTO userListReqDTO);

    UserDTO convertToUserDTO(UserPageReqDTO userPageReqDTO);

    UserShowResDTO convertToUserShowResDTO(UserDTO userDTO);

    List<UserShowResDTO> convertToUserShowResDTOList(List<UserDTO> userDTOList);

    UserListResDTO convertToUserListResDTO(UserDTO userDTO);

    List<UserListResDTO> convertToUserListResDTOList(List<UserDTO> userDTOList);

    List<UserDTO> convertToUserDTOList(List<UserAddReqDTO> userAddReqDTOList);

    UserPageResDTO convertToUserPageResDTO(UserDTO userDTO);

    List<UserPageResDTO> convertToUserPageResDTOList(List<UserDTO> userDTOList);

    UserPageRoleResDTO convertToUserPageRoleResDTO(RoleDTO roleDTO);

    Page<UserPageRoleResDTO> convertToUserPageRoleResDTOPage(Page<RoleDTO> roleDTOPage);
}

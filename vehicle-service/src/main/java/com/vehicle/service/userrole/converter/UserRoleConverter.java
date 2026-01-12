package com.vehicle.service.userrole.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userrole.po.UserRolePO;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface UserRoleConverter extends BaseConverter<UserRoleDTO, UserRolePO> {

    static UserRoleConverter me() {
        return SpringUtil.getBean(UserRoleConverter.class);
    }

    UserRoleDTO convertToUserRoleDTO(UserRoleAddReqDTO userRoleAddReqDTO);

    UserRoleDTO convertToUserRoleDTO(UserRoleModifyReqDTO userRoleModifyReqDTO);

    UserRoleDTO convertToUserRoleDTO(UserRoleRemoveReqDTO userRoleRemoveReqDTO);

    UserRoleDTO convertToUserRoleDTO(UserRoleListReqDTO userRoleListReqDTO);

    UserRoleDTO convertToUserRoleDTO(UserRolePageReqDTO userRolePageReqDTO);

    UserRoleShowResDTO convertToUserRoleShowResDTO(UserRoleDTO userRoleDTO);

    List<UserRoleShowResDTO> convertToUserRoleShowResDTOList(List<UserRoleDTO> userRoleDTOList);

    UserRoleListResDTO convertToUserRoleListResDTO(UserRoleDTO userRoleDTO);

    List<UserRoleListResDTO> convertToUserRoleListResDTOList(List<UserRoleDTO> userRoleDTOList);

    List<UserRoleDTO> convertToUserRoleDTOList(List<UserRoleAddReqDTO> userRoleAddReqDTOList);

    UserRolePageResDTO convertToUserRolePageResDTO(UserRoleDTO userRoleDTO);

    List<UserRolePageResDTO> convertToUserRolePageResDTOList(List<UserRoleDTO> userRoleDTOList);

    Page<UserRolePageResDTO> convertToUserRolePageResDTOPage(Page<UserRoleDTO> userRoleDTOPage);
}

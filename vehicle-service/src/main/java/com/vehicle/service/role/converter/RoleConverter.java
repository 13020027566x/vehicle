package com.vehicle.service.role.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.role.po.RolePO;
import com.vehicle.service.role.dto.RoleAddReqDTO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.role.dto.RoleListPermissionReqDTO;
import com.vehicle.service.role.dto.RoleListReqDTO;
import com.vehicle.service.role.dto.RoleListResDTO;
import com.vehicle.service.role.dto.RoleModifyPermissionReqDTO;
import com.vehicle.service.role.dto.RoleModifyReqDTO;
import com.vehicle.service.role.dto.RolePageReqDTO;
import com.vehicle.service.role.dto.RolePageResDTO;
import com.vehicle.service.role.dto.RoleRemoveReqDTO;
import com.vehicle.service.role.dto.RoleShowResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface RoleConverter extends BaseConverter<RoleDTO, RolePO> {

    static RoleConverter me() {
        return SpringUtil.getBean(RoleConverter.class);
    }

    RoleDTO convertToRoleDTO(RoleAddReqDTO roleAddReqDTO);

    RoleDTO convertToRoleDTO(RoleModifyReqDTO roleModifyReqDTO);

    RoleDTO convertToRoleDTO(RoleRemoveReqDTO roleRemoveReqDTO);

    RoleDTO convertToRoleDTO(RoleListReqDTO roleListReqDTO);

    RoleDTO convertToRoleDTO(RolePageReqDTO rolePageReqDTO);

    RoleShowResDTO convertToRoleShowResDTO(RoleDTO roleDTO);

    List<RoleShowResDTO> convertToRoleShowResDTOList(List<RoleDTO> roleDTOList);

    RoleListResDTO convertToRoleListResDTO(RoleDTO roleDTO);

    List<RoleListResDTO> convertToRoleListResDTOList(List<RoleDTO> roleDTOList);

    List<RoleDTO> convertToRoleDTOList(List<RoleAddReqDTO> roleAddReqDTOList);

    RolePageResDTO convertToRolePageResDTO(RoleDTO roleDTO);

    List<RolePageResDTO> convertToRolePageResDTOList(List<RoleDTO> roleDTOList);

    RoleResourceDTO convertToRoleResourceDTO(RoleListPermissionReqDTO roleListPermissionReqDTO);

    RoleResourceDTO convertToRoleResourceDTO(RoleModifyPermissionReqDTO roleModifyPermissionReqDTO);

    Page<RolePageResDTO> convertToRolePageResDTOPage(Page<RoleDTO> roleDTOPage);
}

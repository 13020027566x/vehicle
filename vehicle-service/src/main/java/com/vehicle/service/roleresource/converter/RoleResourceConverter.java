package com.vehicle.service.roleresource.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.roleresource.po.RoleResourcePO;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface RoleResourceConverter extends BaseConverter<RoleResourceDTO, RoleResourcePO> {

    static RoleResourceConverter me() {
        return SpringUtil.getBean(RoleResourceConverter.class);
    }

    RoleResourceDTO convertToRoleResourceDTO(RoleResourceAddReqDTO roleResourceAddReqDTO);

    RoleResourceDTO convertToRoleResourceDTO(RoleResourceModifyReqDTO roleResourceModifyReqDTO);

    RoleResourceDTO convertToRoleResourceDTO(RoleResourceRemoveReqDTO roleResourceRemoveReqDTO);

    RoleResourceDTO convertToRoleResourceDTO(RoleResourceListReqDTO roleResourceListReqDTO);

    RoleResourceDTO convertToRoleResourceDTO(RoleResourcePageReqDTO roleResourcePageReqDTO);

    RoleResourceShowResDTO convertToRoleResourceShowResDTO(RoleResourceDTO roleResourceDTO);

    List<RoleResourceShowResDTO> convertToRoleResourceShowResDTOList(List<RoleResourceDTO> roleResourceDTOList);

    RoleResourceListResDTO convertToRoleResourceListResDTO(RoleResourceDTO roleResourceDTO);

    List<RoleResourceListResDTO> convertToRoleResourceListResDTOList(List<RoleResourceDTO> roleResourceDTOList);

    List<RoleResourceDTO> convertToRoleResourceDTOList(List<RoleResourceAddReqDTO> roleResourceAddReqDTOList);

    RoleResourcePageResDTO convertToRoleResourcePageResDTO(RoleResourceDTO roleResourceDTO);

    List<RoleResourcePageResDTO> convertToRoleResourcePageResDTOList(List<RoleResourceDTO> roleResourceDTOList);

    Page<RoleResourcePageResDTO> convertToRoleResourcePageResDTOPage(Page<RoleResourceDTO> roleResourceDTOPage);
}

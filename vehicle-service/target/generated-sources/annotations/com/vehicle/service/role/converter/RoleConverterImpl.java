package com.vehicle.service.role.converter;

import com.finhub.framework.core.page.Page;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTO poToDTO(RolePO po) {
        if ( po == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( po.getId() );
        roleDTO.setIsDel( po.getIsDel() );
        roleDTO.setIsTest( po.getIsTest() );
        roleDTO.setCreateAt( po.getCreateAt() );
        roleDTO.setCreateTime( po.getCreateTime() );
        roleDTO.setCreateBy( po.getCreateBy() );
        roleDTO.setCreateName( po.getCreateName() );
        roleDTO.setUpdateAt( po.getUpdateAt() );
        roleDTO.setUpdateTime( po.getUpdateTime() );
        roleDTO.setUpdateBy( po.getUpdateBy() );
        roleDTO.setUpdateName( po.getUpdateName() );
        roleDTO.setCode( po.getCode() );
        roleDTO.setName( po.getName() );
        roleDTO.setRemark( po.getRemark() );

        return roleDTO;
    }

    @Override
    public List<RoleDTO> poToDTOList(List<RolePO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( arg0.size() );
        for ( RolePO rolePO : arg0 ) {
            list.add( poToDTO( rolePO ) );
        }

        return list;
    }

    @Override
    public RolePO dtoToPO(RoleDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RolePO rolePO = new RolePO();

        rolePO.setId( arg0.getId() );
        rolePO.setIsDel( arg0.getIsDel() );
        rolePO.setIsTest( arg0.getIsTest() );
        rolePO.setCreateAt( arg0.getCreateAt() );
        rolePO.setCreateBy( arg0.getCreateBy() );
        rolePO.setCreateName( arg0.getCreateName() );
        rolePO.setUpdateAt( arg0.getUpdateAt() );
        rolePO.setUpdateBy( arg0.getUpdateBy() );
        rolePO.setUpdateName( arg0.getUpdateName() );
        rolePO.setName( arg0.getName() );
        rolePO.setCode( arg0.getCode() );
        rolePO.setRemark( arg0.getRemark() );
        rolePO.setCreateTime( arg0.getCreateTime() );
        rolePO.setUpdateTime( arg0.getUpdateTime() );

        return rolePO;
    }

    @Override
    public List<RolePO> dtoToPOList(List<RoleDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RolePO> list = new ArrayList<RolePO>( arg0.size() );
        for ( RoleDTO roleDTO : arg0 ) {
            list.add( dtoToPO( roleDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(RoleDTO arg0, RolePO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setName( arg0.getName() );
        arg1.setCode( arg0.getCode() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
    }

    @Override
    public void updateDto(RolePO arg0, RoleDTO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setCode( arg0.getCode() );
        arg1.setName( arg0.getName() );
        arg1.setRemark( arg0.getRemark() );
    }

    @Override
    public RoleDTO convertToRoleDTO(RoleAddReqDTO roleAddReqDTO) {
        if ( roleAddReqDTO == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setCode( roleAddReqDTO.getCode() );
        roleDTO.setName( roleAddReqDTO.getName() );
        roleDTO.setRemark( roleAddReqDTO.getRemark() );

        return roleDTO;
    }

    @Override
    public RoleDTO convertToRoleDTO(RoleModifyReqDTO roleModifyReqDTO) {
        if ( roleModifyReqDTO == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( roleModifyReqDTO.getId() );
        roleDTO.setCode( roleModifyReqDTO.getCode() );
        roleDTO.setName( roleModifyReqDTO.getName() );
        roleDTO.setRemark( roleModifyReqDTO.getRemark() );

        return roleDTO;
    }

    @Override
    public RoleDTO convertToRoleDTO(RoleRemoveReqDTO roleRemoveReqDTO) {
        if ( roleRemoveReqDTO == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( roleRemoveReqDTO.getId() );
        roleDTO.setIsDel( roleRemoveReqDTO.getIsDel() );
        roleDTO.setIsTest( roleRemoveReqDTO.getIsTest() );
        roleDTO.setCreateAt( roleRemoveReqDTO.getCreateAt() );
        roleDTO.setCreateBy( roleRemoveReqDTO.getCreateBy() );
        roleDTO.setCreateName( roleRemoveReqDTO.getCreateName() );
        roleDTO.setUpdateAt( roleRemoveReqDTO.getUpdateAt() );
        roleDTO.setUpdateBy( roleRemoveReqDTO.getUpdateBy() );
        roleDTO.setUpdateName( roleRemoveReqDTO.getUpdateName() );
        roleDTO.setCode( roleRemoveReqDTO.getCode() );
        roleDTO.setName( roleRemoveReqDTO.getName() );
        roleDTO.setRemark( roleRemoveReqDTO.getRemark() );

        return roleDTO;
    }

    @Override
    public RoleDTO convertToRoleDTO(RoleListReqDTO roleListReqDTO) {
        if ( roleListReqDTO == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( roleListReqDTO.getId() );
        roleDTO.setIsDel( roleListReqDTO.getIsDel() );
        roleDTO.setIsTest( roleListReqDTO.getIsTest() );
        roleDTO.setCreateAt( roleListReqDTO.getCreateAt() );
        roleDTO.setCreateBy( roleListReqDTO.getCreateBy() );
        roleDTO.setCreateName( roleListReqDTO.getCreateName() );
        roleDTO.setUpdateAt( roleListReqDTO.getUpdateAt() );
        roleDTO.setUpdateBy( roleListReqDTO.getUpdateBy() );
        roleDTO.setUpdateName( roleListReqDTO.getUpdateName() );
        roleDTO.setCode( roleListReqDTO.getCode() );
        roleDTO.setName( roleListReqDTO.getName() );
        roleDTO.setRemark( roleListReqDTO.getRemark() );

        return roleDTO;
    }

    @Override
    public RoleDTO convertToRoleDTO(RolePageReqDTO rolePageReqDTO) {
        if ( rolePageReqDTO == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setName( rolePageReqDTO.getName() );

        return roleDTO;
    }

    @Override
    public RoleShowResDTO convertToRoleShowResDTO(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleShowResDTO roleShowResDTO = new RoleShowResDTO();

        roleShowResDTO.setCode( roleDTO.getCode() );
        roleShowResDTO.setCreateAt( roleDTO.getCreateAt() );
        roleShowResDTO.setCreateBy( roleDTO.getCreateBy() );
        roleShowResDTO.setCreateName( roleDTO.getCreateName() );
        roleShowResDTO.setId( roleDTO.getId() );
        roleShowResDTO.setIsDel( roleDTO.getIsDel() );
        roleShowResDTO.setIsTest( roleDTO.getIsTest() );
        roleShowResDTO.setName( roleDTO.getName() );
        roleShowResDTO.setRemark( roleDTO.getRemark() );
        roleShowResDTO.setUpdateAt( roleDTO.getUpdateAt() );
        roleShowResDTO.setUpdateBy( roleDTO.getUpdateBy() );
        roleShowResDTO.setUpdateName( roleDTO.getUpdateName() );

        return roleShowResDTO;
    }

    @Override
    public List<RoleShowResDTO> convertToRoleShowResDTOList(List<RoleDTO> roleDTOList) {
        if ( roleDTOList == null ) {
            return null;
        }

        List<RoleShowResDTO> list = new ArrayList<RoleShowResDTO>( roleDTOList.size() );
        for ( RoleDTO roleDTO : roleDTOList ) {
            list.add( convertToRoleShowResDTO( roleDTO ) );
        }

        return list;
    }

    @Override
    public RoleListResDTO convertToRoleListResDTO(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleListResDTO roleListResDTO = new RoleListResDTO();

        roleListResDTO.setCode( roleDTO.getCode() );
        roleListResDTO.setCreateAt( roleDTO.getCreateAt() );
        roleListResDTO.setCreateBy( roleDTO.getCreateBy() );
        roleListResDTO.setCreateName( roleDTO.getCreateName() );
        roleListResDTO.setId( roleDTO.getId() );
        roleListResDTO.setIsDel( roleDTO.getIsDel() );
        roleListResDTO.setIsTest( roleDTO.getIsTest() );
        roleListResDTO.setName( roleDTO.getName() );
        roleListResDTO.setRemark( roleDTO.getRemark() );
        roleListResDTO.setUpdateAt( roleDTO.getUpdateAt() );
        roleListResDTO.setUpdateBy( roleDTO.getUpdateBy() );
        roleListResDTO.setUpdateName( roleDTO.getUpdateName() );

        return roleListResDTO;
    }

    @Override
    public List<RoleListResDTO> convertToRoleListResDTOList(List<RoleDTO> roleDTOList) {
        if ( roleDTOList == null ) {
            return null;
        }

        List<RoleListResDTO> list = new ArrayList<RoleListResDTO>( roleDTOList.size() );
        for ( RoleDTO roleDTO : roleDTOList ) {
            list.add( convertToRoleListResDTO( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> convertToRoleDTOList(List<RoleAddReqDTO> roleAddReqDTOList) {
        if ( roleAddReqDTOList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roleAddReqDTOList.size() );
        for ( RoleAddReqDTO roleAddReqDTO : roleAddReqDTOList ) {
            list.add( convertToRoleDTO( roleAddReqDTO ) );
        }

        return list;
    }

    @Override
    public RolePageResDTO convertToRolePageResDTO(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RolePageResDTO rolePageResDTO = new RolePageResDTO();

        rolePageResDTO.setCode( roleDTO.getCode() );
        rolePageResDTO.setId( roleDTO.getId() );
        rolePageResDTO.setName( roleDTO.getName() );
        rolePageResDTO.setRemark( roleDTO.getRemark() );
        if ( roleDTO.getUpdateAt() != null ) {
            rolePageResDTO.setUpdateAt( String.valueOf( roleDTO.getUpdateAt() ) );
        }
        rolePageResDTO.setUpdateName( roleDTO.getUpdateName() );

        return rolePageResDTO;
    }

    @Override
    public List<RolePageResDTO> convertToRolePageResDTOList(List<RoleDTO> roleDTOList) {
        if ( roleDTOList == null ) {
            return null;
        }

        List<RolePageResDTO> list = new ArrayList<RolePageResDTO>( roleDTOList.size() );
        for ( RoleDTO roleDTO : roleDTOList ) {
            list.add( convertToRolePageResDTO( roleDTO ) );
        }

        return list;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleListPermissionReqDTO roleListPermissionReqDTO) {
        if ( roleListPermissionReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setRoleId( roleListPermissionReqDTO.getRoleId() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleModifyPermissionReqDTO roleModifyPermissionReqDTO) {
        if ( roleModifyPermissionReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setRoleId( roleModifyPermissionReqDTO.getRoleId() );

        return roleResourceDTO;
    }

    @Override
    public Page<RolePageResDTO> convertToRolePageResDTOPage(Page<RoleDTO> roleDTOPage) {
        if ( roleDTOPage == null ) {
            return null;
        }

        Page<RolePageResDTO> page = new Page<RolePageResDTO>();

        page.setRecords( convertToRolePageResDTOList( roleDTOPage.getRecords() ) );
        page.setTotal( roleDTOPage.getTotal() );
        page.setSize( roleDTOPage.getSize() );
        page.setCurrent( roleDTOPage.getCurrent() );

        return page;
    }
}

package com.vehicle.service.roleresource.converter;

import com.finhub.framework.core.page.Page;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class RoleResourceConverterImpl implements RoleResourceConverter {

    @Override
    public RoleResourceDTO poToDTO(RoleResourcePO po) {
        if ( po == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setId( po.getId() );
        roleResourceDTO.setIsDel( po.getIsDel() );
        roleResourceDTO.setIsTest( po.getIsTest() );
        roleResourceDTO.setCreateAt( po.getCreateAt() );
        roleResourceDTO.setCreateTime( po.getCreateTime() );
        roleResourceDTO.setCreateBy( po.getCreateBy() );
        roleResourceDTO.setCreateName( po.getCreateName() );
        roleResourceDTO.setUpdateAt( po.getUpdateAt() );
        roleResourceDTO.setUpdateTime( po.getUpdateTime() );
        roleResourceDTO.setUpdateBy( po.getUpdateBy() );
        roleResourceDTO.setUpdateName( po.getUpdateName() );
        roleResourceDTO.setResourceCode( po.getResourceCode() );
        roleResourceDTO.setResourceId( po.getResourceId() );
        roleResourceDTO.setResourceName( po.getResourceName() );
        roleResourceDTO.setRoleCode( po.getRoleCode() );
        roleResourceDTO.setRoleId( po.getRoleId() );
        roleResourceDTO.setRoleName( po.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public List<RoleResourceDTO> poToDTOList(List<RoleResourcePO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleResourceDTO> list = new ArrayList<RoleResourceDTO>( arg0.size() );
        for ( RoleResourcePO roleResourcePO : arg0 ) {
            list.add( poToDTO( roleResourcePO ) );
        }

        return list;
    }

    @Override
    public RoleResourcePO dtoToPO(RoleResourceDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RoleResourcePO roleResourcePO = new RoleResourcePO();

        roleResourcePO.setId( arg0.getId() );
        roleResourcePO.setIsDel( arg0.getIsDel() );
        roleResourcePO.setIsTest( arg0.getIsTest() );
        roleResourcePO.setCreateAt( arg0.getCreateAt() );
        roleResourcePO.setCreateTime( arg0.getCreateTime() );
        roleResourcePO.setCreateBy( arg0.getCreateBy() );
        roleResourcePO.setCreateName( arg0.getCreateName() );
        roleResourcePO.setUpdateAt( arg0.getUpdateAt() );
        roleResourcePO.setUpdateTime( arg0.getUpdateTime() );
        roleResourcePO.setUpdateBy( arg0.getUpdateBy() );
        roleResourcePO.setUpdateName( arg0.getUpdateName() );
        roleResourcePO.setRoleId( arg0.getRoleId() );
        roleResourcePO.setRoleName( arg0.getRoleName() );
        roleResourcePO.setRoleCode( arg0.getRoleCode() );
        roleResourcePO.setResourceId( arg0.getResourceId() );
        roleResourcePO.setResourceName( arg0.getResourceName() );
        roleResourcePO.setResourceCode( arg0.getResourceCode() );

        return roleResourcePO;
    }

    @Override
    public List<RoleResourcePO> dtoToPOList(List<RoleResourceDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<RoleResourcePO> list = new ArrayList<RoleResourcePO>( arg0.size() );
        for ( RoleResourceDTO roleResourceDTO : arg0 ) {
            list.add( dtoToPO( roleResourceDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(RoleResourceDTO arg0, RoleResourcePO arg1) {
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
        arg1.setRoleId( arg0.getRoleId() );
        arg1.setRoleName( arg0.getRoleName() );
        arg1.setRoleCode( arg0.getRoleCode() );
        arg1.setResourceId( arg0.getResourceId() );
        arg1.setResourceName( arg0.getResourceName() );
        arg1.setResourceCode( arg0.getResourceCode() );
    }

    @Override
    public void updateDto(RoleResourcePO arg0, RoleResourceDTO arg1) {
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
        arg1.setResourceCode( arg0.getResourceCode() );
        arg1.setResourceId( arg0.getResourceId() );
        arg1.setResourceName( arg0.getResourceName() );
        arg1.setRoleCode( arg0.getRoleCode() );
        arg1.setRoleId( arg0.getRoleId() );
        arg1.setRoleName( arg0.getRoleName() );
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleResourceAddReqDTO roleResourceAddReqDTO) {
        if ( roleResourceAddReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setIsDel( roleResourceAddReqDTO.getIsDel() );
        roleResourceDTO.setIsTest( roleResourceAddReqDTO.getIsTest() );
        roleResourceDTO.setCreateAt( roleResourceAddReqDTO.getCreateAt() );
        roleResourceDTO.setCreateBy( roleResourceAddReqDTO.getCreateBy() );
        roleResourceDTO.setCreateName( roleResourceAddReqDTO.getCreateName() );
        roleResourceDTO.setUpdateAt( roleResourceAddReqDTO.getUpdateAt() );
        roleResourceDTO.setUpdateBy( roleResourceAddReqDTO.getUpdateBy() );
        roleResourceDTO.setUpdateName( roleResourceAddReqDTO.getUpdateName() );
        roleResourceDTO.setResourceCode( roleResourceAddReqDTO.getResourceCode() );
        roleResourceDTO.setResourceId( roleResourceAddReqDTO.getResourceId() );
        roleResourceDTO.setResourceName( roleResourceAddReqDTO.getResourceName() );
        roleResourceDTO.setRoleCode( roleResourceAddReqDTO.getRoleCode() );
        roleResourceDTO.setRoleId( roleResourceAddReqDTO.getRoleId() );
        roleResourceDTO.setRoleName( roleResourceAddReqDTO.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        if ( roleResourceModifyReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setId( roleResourceModifyReqDTO.getId() );
        roleResourceDTO.setIsDel( roleResourceModifyReqDTO.getIsDel() );
        roleResourceDTO.setIsTest( roleResourceModifyReqDTO.getIsTest() );
        roleResourceDTO.setCreateAt( roleResourceModifyReqDTO.getCreateAt() );
        roleResourceDTO.setCreateBy( roleResourceModifyReqDTO.getCreateBy() );
        roleResourceDTO.setCreateName( roleResourceModifyReqDTO.getCreateName() );
        roleResourceDTO.setUpdateAt( roleResourceModifyReqDTO.getUpdateAt() );
        roleResourceDTO.setUpdateBy( roleResourceModifyReqDTO.getUpdateBy() );
        roleResourceDTO.setUpdateName( roleResourceModifyReqDTO.getUpdateName() );
        roleResourceDTO.setResourceCode( roleResourceModifyReqDTO.getResourceCode() );
        roleResourceDTO.setResourceId( roleResourceModifyReqDTO.getResourceId() );
        roleResourceDTO.setResourceName( roleResourceModifyReqDTO.getResourceName() );
        roleResourceDTO.setRoleCode( roleResourceModifyReqDTO.getRoleCode() );
        roleResourceDTO.setRoleId( roleResourceModifyReqDTO.getRoleId() );
        roleResourceDTO.setRoleName( roleResourceModifyReqDTO.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleResourceRemoveReqDTO roleResourceRemoveReqDTO) {
        if ( roleResourceRemoveReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setId( roleResourceRemoveReqDTO.getId() );
        roleResourceDTO.setIsDel( roleResourceRemoveReqDTO.getIsDel() );
        roleResourceDTO.setIsTest( roleResourceRemoveReqDTO.getIsTest() );
        roleResourceDTO.setCreateAt( roleResourceRemoveReqDTO.getCreateAt() );
        roleResourceDTO.setCreateBy( roleResourceRemoveReqDTO.getCreateBy() );
        roleResourceDTO.setCreateName( roleResourceRemoveReqDTO.getCreateName() );
        roleResourceDTO.setUpdateAt( roleResourceRemoveReqDTO.getUpdateAt() );
        roleResourceDTO.setUpdateBy( roleResourceRemoveReqDTO.getUpdateBy() );
        roleResourceDTO.setUpdateName( roleResourceRemoveReqDTO.getUpdateName() );
        roleResourceDTO.setResourceCode( roleResourceRemoveReqDTO.getResourceCode() );
        roleResourceDTO.setResourceId( roleResourceRemoveReqDTO.getResourceId() );
        roleResourceDTO.setResourceName( roleResourceRemoveReqDTO.getResourceName() );
        roleResourceDTO.setRoleCode( roleResourceRemoveReqDTO.getRoleCode() );
        roleResourceDTO.setRoleId( roleResourceRemoveReqDTO.getRoleId() );
        roleResourceDTO.setRoleName( roleResourceRemoveReqDTO.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleResourceListReqDTO roleResourceListReqDTO) {
        if ( roleResourceListReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setId( roleResourceListReqDTO.getId() );
        roleResourceDTO.setIsDel( roleResourceListReqDTO.getIsDel() );
        roleResourceDTO.setIsTest( roleResourceListReqDTO.getIsTest() );
        roleResourceDTO.setCreateAt( roleResourceListReqDTO.getCreateAt() );
        roleResourceDTO.setCreateBy( roleResourceListReqDTO.getCreateBy() );
        roleResourceDTO.setCreateName( roleResourceListReqDTO.getCreateName() );
        roleResourceDTO.setUpdateAt( roleResourceListReqDTO.getUpdateAt() );
        roleResourceDTO.setUpdateBy( roleResourceListReqDTO.getUpdateBy() );
        roleResourceDTO.setUpdateName( roleResourceListReqDTO.getUpdateName() );
        roleResourceDTO.setResourceCode( roleResourceListReqDTO.getResourceCode() );
        roleResourceDTO.setResourceId( roleResourceListReqDTO.getResourceId() );
        roleResourceDTO.setResourceName( roleResourceListReqDTO.getResourceName() );
        roleResourceDTO.setRoleCode( roleResourceListReqDTO.getRoleCode() );
        roleResourceDTO.setRoleId( roleResourceListReqDTO.getRoleId() );
        roleResourceDTO.setRoleName( roleResourceListReqDTO.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceDTO convertToRoleResourceDTO(RoleResourcePageReqDTO roleResourcePageReqDTO) {
        if ( roleResourcePageReqDTO == null ) {
            return null;
        }

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();

        roleResourceDTO.setId( roleResourcePageReqDTO.getId() );
        roleResourceDTO.setIsDel( roleResourcePageReqDTO.getIsDel() );
        roleResourceDTO.setIsTest( roleResourcePageReqDTO.getIsTest() );
        roleResourceDTO.setCreateAt( roleResourcePageReqDTO.getCreateAt() );
        roleResourceDTO.setCreateBy( roleResourcePageReqDTO.getCreateBy() );
        roleResourceDTO.setCreateName( roleResourcePageReqDTO.getCreateName() );
        roleResourceDTO.setUpdateAt( roleResourcePageReqDTO.getUpdateAt() );
        roleResourceDTO.setUpdateBy( roleResourcePageReqDTO.getUpdateBy() );
        roleResourceDTO.setUpdateName( roleResourcePageReqDTO.getUpdateName() );
        roleResourceDTO.setResourceCode( roleResourcePageReqDTO.getResourceCode() );
        roleResourceDTO.setResourceId( roleResourcePageReqDTO.getResourceId() );
        roleResourceDTO.setResourceName( roleResourcePageReqDTO.getResourceName() );
        roleResourceDTO.setRoleCode( roleResourcePageReqDTO.getRoleCode() );
        roleResourceDTO.setRoleId( roleResourcePageReqDTO.getRoleId() );
        roleResourceDTO.setRoleName( roleResourcePageReqDTO.getRoleName() );

        return roleResourceDTO;
    }

    @Override
    public RoleResourceShowResDTO convertToRoleResourceShowResDTO(RoleResourceDTO roleResourceDTO) {
        if ( roleResourceDTO == null ) {
            return null;
        }

        RoleResourceShowResDTO roleResourceShowResDTO = new RoleResourceShowResDTO();

        roleResourceShowResDTO.setCreateAt( roleResourceDTO.getCreateAt() );
        roleResourceShowResDTO.setCreateBy( roleResourceDTO.getCreateBy() );
        roleResourceShowResDTO.setCreateName( roleResourceDTO.getCreateName() );
        roleResourceShowResDTO.setId( roleResourceDTO.getId() );
        roleResourceShowResDTO.setIsDel( roleResourceDTO.getIsDel() );
        roleResourceShowResDTO.setIsTest( roleResourceDTO.getIsTest() );
        roleResourceShowResDTO.setResourceCode( roleResourceDTO.getResourceCode() );
        roleResourceShowResDTO.setResourceId( roleResourceDTO.getResourceId() );
        roleResourceShowResDTO.setResourceName( roleResourceDTO.getResourceName() );
        roleResourceShowResDTO.setRoleCode( roleResourceDTO.getRoleCode() );
        roleResourceShowResDTO.setRoleId( roleResourceDTO.getRoleId() );
        roleResourceShowResDTO.setRoleName( roleResourceDTO.getRoleName() );
        roleResourceShowResDTO.setUpdateAt( roleResourceDTO.getUpdateAt() );
        roleResourceShowResDTO.setUpdateBy( roleResourceDTO.getUpdateBy() );
        roleResourceShowResDTO.setUpdateName( roleResourceDTO.getUpdateName() );

        return roleResourceShowResDTO;
    }

    @Override
    public List<RoleResourceShowResDTO> convertToRoleResourceShowResDTOList(List<RoleResourceDTO> roleResourceDTOList) {
        if ( roleResourceDTOList == null ) {
            return null;
        }

        List<RoleResourceShowResDTO> list = new ArrayList<RoleResourceShowResDTO>( roleResourceDTOList.size() );
        for ( RoleResourceDTO roleResourceDTO : roleResourceDTOList ) {
            list.add( convertToRoleResourceShowResDTO( roleResourceDTO ) );
        }

        return list;
    }

    @Override
    public RoleResourceListResDTO convertToRoleResourceListResDTO(RoleResourceDTO roleResourceDTO) {
        if ( roleResourceDTO == null ) {
            return null;
        }

        RoleResourceListResDTO roleResourceListResDTO = new RoleResourceListResDTO();

        roleResourceListResDTO.setCreateAt( roleResourceDTO.getCreateAt() );
        roleResourceListResDTO.setCreateBy( roleResourceDTO.getCreateBy() );
        roleResourceListResDTO.setCreateName( roleResourceDTO.getCreateName() );
        roleResourceListResDTO.setId( roleResourceDTO.getId() );
        roleResourceListResDTO.setIsDel( roleResourceDTO.getIsDel() );
        roleResourceListResDTO.setIsTest( roleResourceDTO.getIsTest() );
        roleResourceListResDTO.setResourceCode( roleResourceDTO.getResourceCode() );
        roleResourceListResDTO.setResourceId( roleResourceDTO.getResourceId() );
        roleResourceListResDTO.setResourceName( roleResourceDTO.getResourceName() );
        roleResourceListResDTO.setRoleCode( roleResourceDTO.getRoleCode() );
        roleResourceListResDTO.setRoleId( roleResourceDTO.getRoleId() );
        roleResourceListResDTO.setRoleName( roleResourceDTO.getRoleName() );
        roleResourceListResDTO.setUpdateAt( roleResourceDTO.getUpdateAt() );
        roleResourceListResDTO.setUpdateBy( roleResourceDTO.getUpdateBy() );
        roleResourceListResDTO.setUpdateName( roleResourceDTO.getUpdateName() );

        return roleResourceListResDTO;
    }

    @Override
    public List<RoleResourceListResDTO> convertToRoleResourceListResDTOList(List<RoleResourceDTO> roleResourceDTOList) {
        if ( roleResourceDTOList == null ) {
            return null;
        }

        List<RoleResourceListResDTO> list = new ArrayList<RoleResourceListResDTO>( roleResourceDTOList.size() );
        for ( RoleResourceDTO roleResourceDTO : roleResourceDTOList ) {
            list.add( convertToRoleResourceListResDTO( roleResourceDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleResourceDTO> convertToRoleResourceDTOList(List<RoleResourceAddReqDTO> roleResourceAddReqDTOList) {
        if ( roleResourceAddReqDTOList == null ) {
            return null;
        }

        List<RoleResourceDTO> list = new ArrayList<RoleResourceDTO>( roleResourceAddReqDTOList.size() );
        for ( RoleResourceAddReqDTO roleResourceAddReqDTO : roleResourceAddReqDTOList ) {
            list.add( convertToRoleResourceDTO( roleResourceAddReqDTO ) );
        }

        return list;
    }

    @Override
    public RoleResourcePageResDTO convertToRoleResourcePageResDTO(RoleResourceDTO roleResourceDTO) {
        if ( roleResourceDTO == null ) {
            return null;
        }

        RoleResourcePageResDTO roleResourcePageResDTO = new RoleResourcePageResDTO();

        roleResourcePageResDTO.setCreateAt( roleResourceDTO.getCreateAt() );
        roleResourcePageResDTO.setCreateBy( roleResourceDTO.getCreateBy() );
        roleResourcePageResDTO.setCreateName( roleResourceDTO.getCreateName() );
        roleResourcePageResDTO.setId( roleResourceDTO.getId() );
        roleResourcePageResDTO.setIsDel( roleResourceDTO.getIsDel() );
        roleResourcePageResDTO.setIsTest( roleResourceDTO.getIsTest() );
        roleResourcePageResDTO.setResourceCode( roleResourceDTO.getResourceCode() );
        roleResourcePageResDTO.setResourceId( roleResourceDTO.getResourceId() );
        roleResourcePageResDTO.setResourceName( roleResourceDTO.getResourceName() );
        roleResourcePageResDTO.setRoleCode( roleResourceDTO.getRoleCode() );
        roleResourcePageResDTO.setRoleId( roleResourceDTO.getRoleId() );
        roleResourcePageResDTO.setRoleName( roleResourceDTO.getRoleName() );
        roleResourcePageResDTO.setUpdateAt( roleResourceDTO.getUpdateAt() );
        roleResourcePageResDTO.setUpdateBy( roleResourceDTO.getUpdateBy() );
        roleResourcePageResDTO.setUpdateName( roleResourceDTO.getUpdateName() );

        return roleResourcePageResDTO;
    }

    @Override
    public List<RoleResourcePageResDTO> convertToRoleResourcePageResDTOList(List<RoleResourceDTO> roleResourceDTOList) {
        if ( roleResourceDTOList == null ) {
            return null;
        }

        List<RoleResourcePageResDTO> list = new ArrayList<RoleResourcePageResDTO>( roleResourceDTOList.size() );
        for ( RoleResourceDTO roleResourceDTO : roleResourceDTOList ) {
            list.add( convertToRoleResourcePageResDTO( roleResourceDTO ) );
        }

        return list;
    }

    @Override
    public Page<RoleResourcePageResDTO> convertToRoleResourcePageResDTOPage(Page<RoleResourceDTO> roleResourceDTOPage) {
        if ( roleResourceDTOPage == null ) {
            return null;
        }

        Page<RoleResourcePageResDTO> page = new Page<RoleResourcePageResDTO>();

        page.setRecords( convertToRoleResourcePageResDTOList( roleResourceDTOPage.getRecords() ) );
        page.setTotal( roleResourceDTOPage.getTotal() );
        page.setSize( roleResourceDTOPage.getSize() );
        page.setCurrent( roleResourceDTOPage.getCurrent() );

        return page;
    }
}

package com.vehicle.service.organization.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.organization.po.OrganizationPO;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class OrganizationConverterImpl implements OrganizationConverter {

    @Override
    public OrganizationDTO poToDTO(OrganizationPO po) {
        if ( po == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( po.getId() );
        organizationDTO.setIsDel( po.getIsDel() );
        organizationDTO.setIsTest( po.getIsTest() );
        organizationDTO.setCreateAt( po.getCreateAt() );
        organizationDTO.setCreateTime( po.getCreateTime() );
        organizationDTO.setCreateBy( po.getCreateBy() );
        organizationDTO.setCreateName( po.getCreateName() );
        organizationDTO.setUpdateAt( po.getUpdateAt() );
        organizationDTO.setUpdateTime( po.getUpdateTime() );
        organizationDTO.setUpdateBy( po.getUpdateBy() );
        organizationDTO.setUpdateName( po.getUpdateName() );
        organizationDTO.setAddress( po.getAddress() );
        organizationDTO.setCode( po.getCode() );
        organizationDTO.setIcon( po.getIcon() );
        organizationDTO.setIsLeaf( po.getIsLeaf() );
        organizationDTO.setName( po.getName() );
        organizationDTO.setPid( po.getPid() );
        organizationDTO.setRemark( po.getRemark() );
        organizationDTO.setSort( po.getSort() );
        organizationDTO.setTypeCode( po.getTypeCode() );
        organizationDTO.setTypeVal( po.getTypeVal() );

        return organizationDTO;
    }

    @Override
    public List<OrganizationDTO> poToDTOList(List<OrganizationPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<OrganizationDTO> list = new ArrayList<OrganizationDTO>( arg0.size() );
        for ( OrganizationPO organizationPO : arg0 ) {
            list.add( poToDTO( organizationPO ) );
        }

        return list;
    }

    @Override
    public OrganizationPO dtoToPO(OrganizationDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        OrganizationPO organizationPO = new OrganizationPO();

        organizationPO.setId( arg0.getId() );
        organizationPO.setIsDel( arg0.getIsDel() );
        organizationPO.setIsTest( arg0.getIsTest() );
        organizationPO.setCreateAt( arg0.getCreateAt() );
        organizationPO.setCreateTime( arg0.getCreateTime() );
        organizationPO.setCreateBy( arg0.getCreateBy() );
        organizationPO.setCreateName( arg0.getCreateName() );
        organizationPO.setUpdateAt( arg0.getUpdateAt() );
        organizationPO.setUpdateTime( arg0.getUpdateTime() );
        organizationPO.setUpdateBy( arg0.getUpdateBy() );
        organizationPO.setUpdateName( arg0.getUpdateName() );
        organizationPO.setName( arg0.getName() );
        organizationPO.setCode( arg0.getCode() );
        organizationPO.setAddress( arg0.getAddress() );
        organizationPO.setTypeVal( arg0.getTypeVal() );
        organizationPO.setTypeCode( arg0.getTypeCode() );
        organizationPO.setSort( arg0.getSort() );
        organizationPO.setIsLeaf( arg0.getIsLeaf() );
        organizationPO.setPid( arg0.getPid() );
        organizationPO.setIcon( arg0.getIcon() );
        organizationPO.setRemark( arg0.getRemark() );

        return organizationPO;
    }

    @Override
    public List<OrganizationPO> dtoToPOList(List<OrganizationDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<OrganizationPO> list = new ArrayList<OrganizationPO>( arg0.size() );
        for ( OrganizationDTO organizationDTO : arg0 ) {
            list.add( dtoToPO( organizationDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(OrganizationDTO arg0, OrganizationPO arg1) {
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
        arg1.setName( arg0.getName() );
        arg1.setCode( arg0.getCode() );
        arg1.setAddress( arg0.getAddress() );
        arg1.setTypeVal( arg0.getTypeVal() );
        arg1.setTypeCode( arg0.getTypeCode() );
        arg1.setSort( arg0.getSort() );
        arg1.setIsLeaf( arg0.getIsLeaf() );
        arg1.setPid( arg0.getPid() );
        arg1.setIcon( arg0.getIcon() );
        arg1.setRemark( arg0.getRemark() );
    }

    @Override
    public void updateDto(OrganizationPO arg0, OrganizationDTO arg1) {
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
        arg1.setAddress( arg0.getAddress() );
        arg1.setCode( arg0.getCode() );
        arg1.setIcon( arg0.getIcon() );
        arg1.setIsLeaf( arg0.getIsLeaf() );
        arg1.setName( arg0.getName() );
        arg1.setPid( arg0.getPid() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setSort( arg0.getSort() );
        arg1.setTypeCode( arg0.getTypeCode() );
        arg1.setTypeVal( arg0.getTypeVal() );
    }

    @Override
    public OrganizationDTO convertToOrganizationDTO(OrganizationAddReqDTO organizationAddReqDTO) {
        if ( organizationAddReqDTO == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setCode( organizationAddReqDTO.getCode() );
        organizationDTO.setIsLeaf( organizationAddReqDTO.getIsLeaf() );
        organizationDTO.setName( organizationAddReqDTO.getName() );
        organizationDTO.setPid( organizationAddReqDTO.getPid() );
        organizationDTO.setRemark( organizationAddReqDTO.getRemark() );
        organizationDTO.setSort( organizationAddReqDTO.getSort() );

        return organizationDTO;
    }

    @Override
    public OrganizationDTO convertToOrganizationDTO(OrganizationModifyReqDTO organizationModifyReqDTO) {
        if ( organizationModifyReqDTO == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( organizationModifyReqDTO.getId() );
        organizationDTO.setCode( organizationModifyReqDTO.getCode() );
        organizationDTO.setIsLeaf( organizationModifyReqDTO.getIsLeaf() );
        organizationDTO.setName( organizationModifyReqDTO.getName() );
        organizationDTO.setPid( organizationModifyReqDTO.getPid() );
        organizationDTO.setRemark( organizationModifyReqDTO.getRemark() );
        organizationDTO.setSort( organizationModifyReqDTO.getSort() );

        return organizationDTO;
    }

    @Override
    public OrganizationDTO convertToOrganizationDTO(OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        if ( organizationRemoveReqDTO == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( organizationRemoveReqDTO.getId() );
        organizationDTO.setIsDel( organizationRemoveReqDTO.getIsDel() );
        organizationDTO.setIsTest( organizationRemoveReqDTO.getIsTest() );
        organizationDTO.setCreateAt( organizationRemoveReqDTO.getCreateAt() );
        organizationDTO.setCreateBy( organizationRemoveReqDTO.getCreateBy() );
        organizationDTO.setCreateName( organizationRemoveReqDTO.getCreateName() );
        organizationDTO.setUpdateAt( organizationRemoveReqDTO.getUpdateAt() );
        organizationDTO.setUpdateBy( organizationRemoveReqDTO.getUpdateBy() );
        organizationDTO.setUpdateName( organizationRemoveReqDTO.getUpdateName() );
        organizationDTO.setAddress( organizationRemoveReqDTO.getAddress() );
        organizationDTO.setCode( organizationRemoveReqDTO.getCode() );
        organizationDTO.setIcon( organizationRemoveReqDTO.getIcon() );
        organizationDTO.setIsLeaf( organizationRemoveReqDTO.getIsLeaf() );
        organizationDTO.setName( organizationRemoveReqDTO.getName() );
        organizationDTO.setPid( organizationRemoveReqDTO.getPid() );
        organizationDTO.setRemark( organizationRemoveReqDTO.getRemark() );
        organizationDTO.setSort( organizationRemoveReqDTO.getSort() );
        organizationDTO.setTypeCode( organizationRemoveReqDTO.getTypeCode() );
        organizationDTO.setTypeVal( organizationRemoveReqDTO.getTypeVal() );

        return organizationDTO;
    }

    @Override
    public OrganizationDTO convertToOrganizationDTO(OrganizationListReqDTO organizationListReqDTO) {
        if ( organizationListReqDTO == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( organizationListReqDTO.getId() );
        organizationDTO.setIsDel( organizationListReqDTO.getIsDel() );
        organizationDTO.setIsTest( organizationListReqDTO.getIsTest() );
        organizationDTO.setCreateAt( organizationListReqDTO.getCreateAt() );
        organizationDTO.setCreateBy( organizationListReqDTO.getCreateBy() );
        organizationDTO.setCreateName( organizationListReqDTO.getCreateName() );
        organizationDTO.setUpdateAt( organizationListReqDTO.getUpdateAt() );
        organizationDTO.setUpdateBy( organizationListReqDTO.getUpdateBy() );
        organizationDTO.setUpdateName( organizationListReqDTO.getUpdateName() );
        organizationDTO.setAddress( organizationListReqDTO.getAddress() );
        organizationDTO.setCode( organizationListReqDTO.getCode() );
        organizationDTO.setIcon( organizationListReqDTO.getIcon() );
        organizationDTO.setIsLeaf( organizationListReqDTO.getIsLeaf() );
        organizationDTO.setName( organizationListReqDTO.getName() );
        organizationDTO.setPid( organizationListReqDTO.getPid() );
        organizationDTO.setRemark( organizationListReqDTO.getRemark() );
        organizationDTO.setSort( organizationListReqDTO.getSort() );
        organizationDTO.setTypeCode( organizationListReqDTO.getTypeCode() );
        organizationDTO.setTypeVal( organizationListReqDTO.getTypeVal() );

        return organizationDTO;
    }

    @Override
    public OrganizationDTO convertToOrganizationDTO(OrganizationPageReqDTO organizationPageReqDTO) {
        if ( organizationPageReqDTO == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId( organizationPageReqDTO.getId() );
        organizationDTO.setIsDel( organizationPageReqDTO.getIsDel() );
        organizationDTO.setIsTest( organizationPageReqDTO.getIsTest() );
        organizationDTO.setCreateAt( organizationPageReqDTO.getCreateAt() );
        organizationDTO.setCreateBy( organizationPageReqDTO.getCreateBy() );
        organizationDTO.setCreateName( organizationPageReqDTO.getCreateName() );
        organizationDTO.setUpdateAt( organizationPageReqDTO.getUpdateAt() );
        organizationDTO.setUpdateBy( organizationPageReqDTO.getUpdateBy() );
        organizationDTO.setUpdateName( organizationPageReqDTO.getUpdateName() );
        organizationDTO.setAddress( organizationPageReqDTO.getAddress() );
        organizationDTO.setCode( organizationPageReqDTO.getCode() );
        organizationDTO.setIcon( organizationPageReqDTO.getIcon() );
        organizationDTO.setIsLeaf( organizationPageReqDTO.getIsLeaf() );
        organizationDTO.setName( organizationPageReqDTO.getName() );
        organizationDTO.setPid( organizationPageReqDTO.getPid() );
        organizationDTO.setRemark( organizationPageReqDTO.getRemark() );
        organizationDTO.setSort( organizationPageReqDTO.getSort() );
        organizationDTO.setTypeCode( organizationPageReqDTO.getTypeCode() );
        organizationDTO.setTypeVal( organizationPageReqDTO.getTypeVal() );

        return organizationDTO;
    }

    @Override
    public OrganizationShowResDTO convertToOrganizationShowResDTO(OrganizationDTO organizationDTO) {
        if ( organizationDTO == null ) {
            return null;
        }

        OrganizationShowResDTO organizationShowResDTO = new OrganizationShowResDTO();

        organizationShowResDTO.setCode( organizationDTO.getCode() );
        organizationShowResDTO.setId( organizationDTO.getId() );
        organizationShowResDTO.setIsLeaf( organizationDTO.getIsLeaf() );
        organizationShowResDTO.setName( organizationDTO.getName() );
        organizationShowResDTO.setPid( organizationDTO.getPid() );
        organizationShowResDTO.setRemark( organizationDTO.getRemark() );
        organizationShowResDTO.setSort( organizationDTO.getSort() );

        return organizationShowResDTO;
    }

    @Override
    public List<OrganizationShowResDTO> convertToOrganizationShowResDTOList(List<OrganizationDTO> organizationDTOList) {
        if ( organizationDTOList == null ) {
            return null;
        }

        List<OrganizationShowResDTO> list = new ArrayList<OrganizationShowResDTO>( organizationDTOList.size() );
        for ( OrganizationDTO organizationDTO : organizationDTOList ) {
            list.add( convertToOrganizationShowResDTO( organizationDTO ) );
        }

        return list;
    }

    @Override
    public OrganizationListResDTO convertToOrganizationListResDTO(OrganizationDTO organizationDTO) {
        if ( organizationDTO == null ) {
            return null;
        }

        OrganizationListResDTO organizationListResDTO = new OrganizationListResDTO();

        organizationListResDTO.setId( organizationDTO.getId() );
        organizationListResDTO.setPid( organizationDTO.getPid() );

        return organizationListResDTO;
    }

    @Override
    public List<OrganizationListResDTO> convertToOrganizationListResDTOList(List<OrganizationDTO> organizationDTOList) {
        if ( organizationDTOList == null ) {
            return null;
        }

        List<OrganizationListResDTO> list = new ArrayList<OrganizationListResDTO>( organizationDTOList.size() );
        for ( OrganizationDTO organizationDTO : organizationDTOList ) {
            list.add( convertToOrganizationListResDTO( organizationDTO ) );
        }

        return list;
    }

    @Override
    public List<OrganizationDTO> convertToOrganizationDTOList(List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        if ( organizationAddReqDTOList == null ) {
            return null;
        }

        List<OrganizationDTO> list = new ArrayList<OrganizationDTO>( organizationAddReqDTOList.size() );
        for ( OrganizationAddReqDTO organizationAddReqDTO : organizationAddReqDTOList ) {
            list.add( convertToOrganizationDTO( organizationAddReqDTO ) );
        }

        return list;
    }

    @Override
    public OrganizationPageResDTO convertToOrganizationPageResDTO(OrganizationDTO organizationDTO) {
        if ( organizationDTO == null ) {
            return null;
        }

        OrganizationPageResDTO organizationPageResDTO = new OrganizationPageResDTO();

        organizationPageResDTO.setAddress( organizationDTO.getAddress() );
        organizationPageResDTO.setCode( organizationDTO.getCode() );
        organizationPageResDTO.setCreateAt( organizationDTO.getCreateAt() );
        organizationPageResDTO.setCreateBy( organizationDTO.getCreateBy() );
        organizationPageResDTO.setCreateName( organizationDTO.getCreateName() );
        organizationPageResDTO.setIcon( organizationDTO.getIcon() );
        organizationPageResDTO.setId( organizationDTO.getId() );
        organizationPageResDTO.setIsDel( organizationDTO.getIsDel() );
        organizationPageResDTO.setIsLeaf( organizationDTO.getIsLeaf() );
        organizationPageResDTO.setIsTest( organizationDTO.getIsTest() );
        organizationPageResDTO.setName( organizationDTO.getName() );
        organizationPageResDTO.setPid( organizationDTO.getPid() );
        organizationPageResDTO.setRemark( organizationDTO.getRemark() );
        organizationPageResDTO.setSort( organizationDTO.getSort() );
        organizationPageResDTO.setTypeCode( organizationDTO.getTypeCode() );
        organizationPageResDTO.setTypeVal( organizationDTO.getTypeVal() );
        organizationPageResDTO.setUpdateAt( organizationDTO.getUpdateAt() );
        organizationPageResDTO.setUpdateBy( organizationDTO.getUpdateBy() );
        organizationPageResDTO.setUpdateName( organizationDTO.getUpdateName() );

        return organizationPageResDTO;
    }

    @Override
    public List<OrganizationPageResDTO> convertToOrganizationPageResDTOList(List<OrganizationDTO> organizationDTOList) {
        if ( organizationDTOList == null ) {
            return null;
        }

        List<OrganizationPageResDTO> list = new ArrayList<OrganizationPageResDTO>( organizationDTOList.size() );
        for ( OrganizationDTO organizationDTO : organizationDTOList ) {
            list.add( convertToOrganizationPageResDTO( organizationDTO ) );
        }

        return list;
    }

    @Override
    public Page<OrganizationPageResDTO> convertToOrganizationPageResDTOPage(Page<OrganizationDTO> organizationDTOPage) {
        if ( organizationDTOPage == null ) {
            return null;
        }

        Page<OrganizationPageResDTO> page = new Page<OrganizationPageResDTO>();

        page.setRecords( convertToOrganizationPageResDTOList( organizationDTOPage.getRecords() ) );
        page.setTotal( organizationDTOPage.getTotal() );
        page.setSize( organizationDTOPage.getSize() );
        page.setCurrent( organizationDTOPage.getCurrent() );

        return page;
    }
}

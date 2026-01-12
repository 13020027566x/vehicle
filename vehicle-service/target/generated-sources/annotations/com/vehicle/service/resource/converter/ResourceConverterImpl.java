package com.vehicle.service.resource.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.resource.po.ResourcePO;
import com.vehicle.service.resource.dto.ResourceAddReqDTO;
import com.vehicle.service.resource.dto.ResourceDTO;
import com.vehicle.service.resource.dto.ResourceListReqDTO;
import com.vehicle.service.resource.dto.ResourceListResDTO;
import com.vehicle.service.resource.dto.ResourceModifyReqDTO;
import com.vehicle.service.resource.dto.ResourcePageReqDTO;
import com.vehicle.service.resource.dto.ResourcePageResDTO;
import com.vehicle.service.resource.dto.ResourceRemoveReqDTO;
import com.vehicle.service.resource.dto.ResourceShowResDTO;
import com.vehicle.service.resource.dto.UserMenuOperationDTO;
import com.vehicle.service.resource.dto.UserMenuResourceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ResourceConverterImpl implements ResourceConverter {

    @Override
    public ResourceDTO poToDTO(ResourcePO po) {
        if ( po == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( po.getId() );
        resourceDTO.setIsDel( po.getIsDel() );
        resourceDTO.setIsTest( po.getIsTest() );
        resourceDTO.setCreateAt( po.getCreateAt() );
        resourceDTO.setCreateTime( po.getCreateTime() );
        resourceDTO.setCreateBy( po.getCreateBy() );
        resourceDTO.setCreateName( po.getCreateName() );
        resourceDTO.setUpdateAt( po.getUpdateAt() );
        resourceDTO.setUpdateTime( po.getUpdateTime() );
        resourceDTO.setUpdateBy( po.getUpdateBy() );
        resourceDTO.setUpdateName( po.getUpdateName() );
        resourceDTO.setCode( po.getCode() );
        resourceDTO.setIcon( po.getIcon() );
        resourceDTO.setIsLeaf( po.getIsLeaf() );
        resourceDTO.setIsMenu( po.getIsMenu() );
        resourceDTO.setName( po.getName() );
        resourceDTO.setPid( po.getPid() );
        resourceDTO.setRemark( po.getRemark() );
        resourceDTO.setSort( po.getSort() );
        resourceDTO.setUrl( po.getUrl() );

        return resourceDTO;
    }

    @Override
    public List<ResourceDTO> poToDTOList(List<ResourcePO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ResourceDTO> list = new ArrayList<ResourceDTO>( arg0.size() );
        for ( ResourcePO resourcePO : arg0 ) {
            list.add( poToDTO( resourcePO ) );
        }

        return list;
    }

    @Override
    public ResourcePO dtoToPO(ResourceDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ResourcePO resourcePO = new ResourcePO();

        resourcePO.setId( arg0.getId() );
        resourcePO.setIsDel( arg0.getIsDel() );
        resourcePO.setIsTest( arg0.getIsTest() );
        resourcePO.setCreateAt( arg0.getCreateAt() );
        resourcePO.setCreateTime( arg0.getCreateTime() );
        resourcePO.setCreateBy( arg0.getCreateBy() );
        resourcePO.setCreateName( arg0.getCreateName() );
        resourcePO.setUpdateAt( arg0.getUpdateAt() );
        resourcePO.setUpdateTime( arg0.getUpdateTime() );
        resourcePO.setUpdateBy( arg0.getUpdateBy() );
        resourcePO.setUpdateName( arg0.getUpdateName() );
        resourcePO.setName( arg0.getName() );
        resourcePO.setCode( arg0.getCode() );
        resourcePO.setUrl( arg0.getUrl() );
        resourcePO.setIcon( arg0.getIcon() );
        resourcePO.setRemark( arg0.getRemark() );
        resourcePO.setPid( arg0.getPid() );
        resourcePO.setSort( arg0.getSort() );
        resourcePO.setIsMenu( arg0.getIsMenu() );
        resourcePO.setIsLeaf( arg0.getIsLeaf() );

        return resourcePO;
    }

    @Override
    public List<ResourcePO> dtoToPOList(List<ResourceDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ResourcePO> list = new ArrayList<ResourcePO>( arg0.size() );
        for ( ResourceDTO resourceDTO : arg0 ) {
            list.add( dtoToPO( resourceDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(ResourceDTO arg0, ResourcePO arg1) {
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
        arg1.setUrl( arg0.getUrl() );
        arg1.setIcon( arg0.getIcon() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setPid( arg0.getPid() );
        arg1.setSort( arg0.getSort() );
        arg1.setIsMenu( arg0.getIsMenu() );
        arg1.setIsLeaf( arg0.getIsLeaf() );
    }

    @Override
    public void updateDto(ResourcePO arg0, ResourceDTO arg1) {
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
        arg1.setIcon( arg0.getIcon() );
        arg1.setIsLeaf( arg0.getIsLeaf() );
        arg1.setIsMenu( arg0.getIsMenu() );
        arg1.setName( arg0.getName() );
        arg1.setPid( arg0.getPid() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setSort( arg0.getSort() );
        arg1.setUrl( arg0.getUrl() );
    }

    @Override
    public ResourceDTO convertToResourceDTO(ResourceAddReqDTO resourceAddReqDTO) {
        if ( resourceAddReqDTO == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setCode( resourceAddReqDTO.getCode() );
        resourceDTO.setIcon( resourceAddReqDTO.getIcon() );
        resourceDTO.setIsLeaf( resourceAddReqDTO.getIsLeaf() );
        resourceDTO.setIsMenu( resourceAddReqDTO.getIsMenu() );
        resourceDTO.setName( resourceAddReqDTO.getName() );
        resourceDTO.setPid( resourceAddReqDTO.getPid() );
        resourceDTO.setRemark( resourceAddReqDTO.getRemark() );
        resourceDTO.setSort( resourceAddReqDTO.getSort() );
        resourceDTO.setUrl( resourceAddReqDTO.getUrl() );

        return resourceDTO;
    }

    @Override
    public ResourceDTO convertToResourceDTO(ResourceModifyReqDTO resourceModifyReqDTO) {
        if ( resourceModifyReqDTO == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resourceModifyReqDTO.getId() );
        resourceDTO.setCode( resourceModifyReqDTO.getCode() );
        resourceDTO.setIcon( resourceModifyReqDTO.getIcon() );
        resourceDTO.setIsLeaf( resourceModifyReqDTO.getIsLeaf() );
        resourceDTO.setIsMenu( resourceModifyReqDTO.getIsMenu() );
        resourceDTO.setName( resourceModifyReqDTO.getName() );
        resourceDTO.setPid( resourceModifyReqDTO.getPid() );
        resourceDTO.setRemark( resourceModifyReqDTO.getRemark() );
        resourceDTO.setSort( resourceModifyReqDTO.getSort() );
        resourceDTO.setUrl( resourceModifyReqDTO.getUrl() );

        return resourceDTO;
    }

    @Override
    public ResourceDTO convertToResourceDTO(ResourceRemoveReqDTO resourceRemoveReqDTO) {
        if ( resourceRemoveReqDTO == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resourceRemoveReqDTO.getId() );
        resourceDTO.setIsDel( resourceRemoveReqDTO.getIsDel() );
        resourceDTO.setIsTest( resourceRemoveReqDTO.getIsTest() );
        resourceDTO.setCreateAt( resourceRemoveReqDTO.getCreateAt() );
        resourceDTO.setCreateBy( resourceRemoveReqDTO.getCreateBy() );
        resourceDTO.setCreateName( resourceRemoveReqDTO.getCreateName() );
        resourceDTO.setUpdateAt( resourceRemoveReqDTO.getUpdateAt() );
        resourceDTO.setUpdateBy( resourceRemoveReqDTO.getUpdateBy() );
        resourceDTO.setUpdateName( resourceRemoveReqDTO.getUpdateName() );
        resourceDTO.setCode( resourceRemoveReqDTO.getCode() );
        resourceDTO.setIcon( resourceRemoveReqDTO.getIcon() );
        resourceDTO.setIsLeaf( resourceRemoveReqDTO.getIsLeaf() );
        resourceDTO.setIsMenu( resourceRemoveReqDTO.getIsMenu() );
        resourceDTO.setName( resourceRemoveReqDTO.getName() );
        resourceDTO.setPid( resourceRemoveReqDTO.getPid() );
        resourceDTO.setRemark( resourceRemoveReqDTO.getRemark() );
        resourceDTO.setSort( resourceRemoveReqDTO.getSort() );
        resourceDTO.setUrl( resourceRemoveReqDTO.getUrl() );

        return resourceDTO;
    }

    @Override
    public ResourceDTO convertToResourceDTO(ResourceListReqDTO resourceListReqDTO) {
        if ( resourceListReqDTO == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resourceListReqDTO.getId() );
        resourceDTO.setIsDel( resourceListReqDTO.getIsDel() );
        resourceDTO.setIsTest( resourceListReqDTO.getIsTest() );
        resourceDTO.setCreateAt( resourceListReqDTO.getCreateAt() );
        resourceDTO.setCreateBy( resourceListReqDTO.getCreateBy() );
        resourceDTO.setCreateName( resourceListReqDTO.getCreateName() );
        resourceDTO.setUpdateAt( resourceListReqDTO.getUpdateAt() );
        resourceDTO.setUpdateBy( resourceListReqDTO.getUpdateBy() );
        resourceDTO.setUpdateName( resourceListReqDTO.getUpdateName() );
        resourceDTO.setCode( resourceListReqDTO.getCode() );
        resourceDTO.setIcon( resourceListReqDTO.getIcon() );
        resourceDTO.setIsLeaf( resourceListReqDTO.getIsLeaf() );
        resourceDTO.setIsMenu( resourceListReqDTO.getIsMenu() );
        resourceDTO.setName( resourceListReqDTO.getName() );
        resourceDTO.setPid( resourceListReqDTO.getPid() );
        resourceDTO.setRemark( resourceListReqDTO.getRemark() );
        resourceDTO.setSort( resourceListReqDTO.getSort() );
        resourceDTO.setUrl( resourceListReqDTO.getUrl() );

        return resourceDTO;
    }

    @Override
    public ResourceDTO convertToResourceDTO(ResourcePageReqDTO resourcePageReqDTO) {
        if ( resourcePageReqDTO == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resourcePageReqDTO.getId() );
        resourceDTO.setIsDel( resourcePageReqDTO.getIsDel() );
        resourceDTO.setIsTest( resourcePageReqDTO.getIsTest() );
        resourceDTO.setCreateAt( resourcePageReqDTO.getCreateAt() );
        resourceDTO.setCreateBy( resourcePageReqDTO.getCreateBy() );
        resourceDTO.setCreateName( resourcePageReqDTO.getCreateName() );
        resourceDTO.setUpdateAt( resourcePageReqDTO.getUpdateAt() );
        resourceDTO.setUpdateBy( resourcePageReqDTO.getUpdateBy() );
        resourceDTO.setUpdateName( resourcePageReqDTO.getUpdateName() );
        resourceDTO.setCode( resourcePageReqDTO.getCode() );
        resourceDTO.setIcon( resourcePageReqDTO.getIcon() );
        resourceDTO.setIsLeaf( resourcePageReqDTO.getIsLeaf() );
        resourceDTO.setIsMenu( resourcePageReqDTO.getIsMenu() );
        resourceDTO.setName( resourcePageReqDTO.getName() );
        resourceDTO.setPid( resourcePageReqDTO.getPid() );
        resourceDTO.setRemark( resourcePageReqDTO.getRemark() );
        resourceDTO.setSort( resourcePageReqDTO.getSort() );
        resourceDTO.setUrl( resourcePageReqDTO.getUrl() );

        return resourceDTO;
    }

    @Override
    public ResourceShowResDTO convertToResourceShowResDTO(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        ResourceShowResDTO resourceShowResDTO = new ResourceShowResDTO();

        resourceShowResDTO.setCode( resourceDTO.getCode() );
        resourceShowResDTO.setIcon( resourceDTO.getIcon() );
        resourceShowResDTO.setId( resourceDTO.getId() );
        resourceShowResDTO.setIsLeaf( resourceDTO.getIsLeaf() );
        resourceShowResDTO.setIsMenu( resourceDTO.getIsMenu() );
        resourceShowResDTO.setName( resourceDTO.getName() );
        resourceShowResDTO.setPid( resourceDTO.getPid() );
        resourceShowResDTO.setRemark( resourceDTO.getRemark() );
        resourceShowResDTO.setSort( resourceDTO.getSort() );
        resourceShowResDTO.setUrl( resourceDTO.getUrl() );

        return resourceShowResDTO;
    }

    @Override
    public List<ResourceShowResDTO> convertToResourceShowResDTOList(List<ResourceDTO> resourceDTOList) {
        if ( resourceDTOList == null ) {
            return null;
        }

        List<ResourceShowResDTO> list = new ArrayList<ResourceShowResDTO>( resourceDTOList.size() );
        for ( ResourceDTO resourceDTO : resourceDTOList ) {
            list.add( convertToResourceShowResDTO( resourceDTO ) );
        }

        return list;
    }

    @Override
    public ResourceListResDTO convertToResourceListResDTO(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        ResourceListResDTO resourceListResDTO = new ResourceListResDTO();

        resourceListResDTO.setId( resourceDTO.getId() );
        resourceListResDTO.setPid( resourceDTO.getPid() );

        return resourceListResDTO;
    }

    @Override
    public List<ResourceListResDTO> convertToResourceListResDTOList(List<ResourceDTO> resourceDTOList) {
        if ( resourceDTOList == null ) {
            return null;
        }

        List<ResourceListResDTO> list = new ArrayList<ResourceListResDTO>( resourceDTOList.size() );
        for ( ResourceDTO resourceDTO : resourceDTOList ) {
            list.add( convertToResourceListResDTO( resourceDTO ) );
        }

        return list;
    }

    @Override
    public List<ResourceDTO> convertToResourceDTOList(List<ResourceAddReqDTO> resourceAddReqDTOList) {
        if ( resourceAddReqDTOList == null ) {
            return null;
        }

        List<ResourceDTO> list = new ArrayList<ResourceDTO>( resourceAddReqDTOList.size() );
        for ( ResourceAddReqDTO resourceAddReqDTO : resourceAddReqDTOList ) {
            list.add( convertToResourceDTO( resourceAddReqDTO ) );
        }

        return list;
    }

    @Override
    public ResourcePageResDTO convertToResourcePageResDTO(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        ResourcePageResDTO resourcePageResDTO = new ResourcePageResDTO();

        resourcePageResDTO.setCode( resourceDTO.getCode() );
        resourcePageResDTO.setCreateAt( resourceDTO.getCreateAt() );
        resourcePageResDTO.setCreateBy( resourceDTO.getCreateBy() );
        resourcePageResDTO.setCreateName( resourceDTO.getCreateName() );
        resourcePageResDTO.setIcon( resourceDTO.getIcon() );
        resourcePageResDTO.setId( resourceDTO.getId() );
        resourcePageResDTO.setIsDel( resourceDTO.getIsDel() );
        resourcePageResDTO.setIsLeaf( resourceDTO.getIsLeaf() );
        resourcePageResDTO.setIsMenu( resourceDTO.getIsMenu() );
        resourcePageResDTO.setIsTest( resourceDTO.getIsTest() );
        resourcePageResDTO.setName( resourceDTO.getName() );
        resourcePageResDTO.setPid( resourceDTO.getPid() );
        resourcePageResDTO.setRemark( resourceDTO.getRemark() );
        resourcePageResDTO.setSort( resourceDTO.getSort() );
        resourcePageResDTO.setUpdateAt( resourceDTO.getUpdateAt() );
        resourcePageResDTO.setUpdateBy( resourceDTO.getUpdateBy() );
        resourcePageResDTO.setUpdateName( resourceDTO.getUpdateName() );
        resourcePageResDTO.setUrl( resourceDTO.getUrl() );

        return resourcePageResDTO;
    }

    @Override
    public List<ResourcePageResDTO> convertToResourcePageResDTOList(List<ResourceDTO> resourceDTOList) {
        if ( resourceDTOList == null ) {
            return null;
        }

        List<ResourcePageResDTO> list = new ArrayList<ResourcePageResDTO>( resourceDTOList.size() );
        for ( ResourceDTO resourceDTO : resourceDTOList ) {
            list.add( convertToResourcePageResDTO( resourceDTO ) );
        }

        return list;
    }

    @Override
    public UserMenuOperationDTO convertToUserMenuOperationDTO(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        UserMenuOperationDTO userMenuOperationDTO = new UserMenuOperationDTO();

        userMenuOperationDTO.setCode( resourceDTO.getCode() );
        userMenuOperationDTO.setIcon( resourceDTO.getIcon() );
        userMenuOperationDTO.setId( resourceDTO.getId() );
        userMenuOperationDTO.setName( resourceDTO.getName() );

        return userMenuOperationDTO;
    }

    @Override
    public UserMenuResourceDTO convertToUserMenuResourceDTO(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        UserMenuResourceDTO userMenuResourceDTO = new UserMenuResourceDTO();

        userMenuResourceDTO.setCode( resourceDTO.getCode() );
        userMenuResourceDTO.setIcon( resourceDTO.getIcon() );
        userMenuResourceDTO.setId( resourceDTO.getId() );
        userMenuResourceDTO.setName( resourceDTO.getName() );
        userMenuResourceDTO.setUrl( resourceDTO.getUrl() );

        return userMenuResourceDTO;
    }

    @Override
    public Page<ResourcePageResDTO> convertToResourcePageResDTOPage(Page<ResourceDTO> resourceDTOPage) {
        if ( resourceDTOPage == null ) {
            return null;
        }

        Page<ResourcePageResDTO> page = new Page<ResourcePageResDTO>();

        page.setRecords( convertToResourcePageResDTOList( resourceDTOPage.getRecords() ) );
        page.setTotal( resourceDTOPage.getTotal() );
        page.setSize( resourceDTOPage.getSize() );
        page.setCurrent( resourceDTOPage.getCurrent() );

        return page;
    }
}

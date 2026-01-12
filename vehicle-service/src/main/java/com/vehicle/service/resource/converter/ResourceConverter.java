package com.vehicle.service.resource.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface ResourceConverter extends BaseConverter<ResourceDTO, ResourcePO> {

    static ResourceConverter me() {
        return SpringUtil.getBean(ResourceConverter.class);
    }

    ResourceDTO convertToResourceDTO(ResourceAddReqDTO resourceAddReqDTO);

    ResourceDTO convertToResourceDTO(ResourceModifyReqDTO resourceModifyReqDTO);

    ResourceDTO convertToResourceDTO(ResourceRemoveReqDTO resourceRemoveReqDTO);

    ResourceDTO convertToResourceDTO(ResourceListReqDTO resourceListReqDTO);

    ResourceDTO convertToResourceDTO(ResourcePageReqDTO resourcePageReqDTO);

    ResourceShowResDTO convertToResourceShowResDTO(ResourceDTO resourceDTO);

    List<ResourceShowResDTO> convertToResourceShowResDTOList(List<ResourceDTO> resourceDTOList);

    ResourceListResDTO convertToResourceListResDTO(ResourceDTO resourceDTO);

    List<ResourceListResDTO> convertToResourceListResDTOList(List<ResourceDTO> resourceDTOList);

    List<ResourceDTO> convertToResourceDTOList(List<ResourceAddReqDTO> resourceAddReqDTOList);

    ResourcePageResDTO convertToResourcePageResDTO(ResourceDTO resourceDTO);

    List<ResourcePageResDTO> convertToResourcePageResDTOList(List<ResourceDTO> resourceDTOList);

    UserMenuOperationDTO convertToUserMenuOperationDTO(ResourceDTO resourceDTO);

    UserMenuResourceDTO convertToUserMenuResourceDTO(ResourceDTO resourceDTO);

    Page<ResourcePageResDTO> convertToResourcePageResDTOPage(Page<ResourceDTO> resourceDTOPage);
}

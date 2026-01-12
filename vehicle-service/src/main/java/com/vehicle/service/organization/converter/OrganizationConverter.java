package com.vehicle.service.organization.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface OrganizationConverter extends BaseConverter<OrganizationDTO, OrganizationPO> {

    static OrganizationConverter me() {
        return SpringUtil.getBean(OrganizationConverter.class);
    }

    OrganizationDTO convertToOrganizationDTO(OrganizationAddReqDTO organizationAddReqDTO);

    OrganizationDTO convertToOrganizationDTO(OrganizationModifyReqDTO organizationModifyReqDTO);

    OrganizationDTO convertToOrganizationDTO(OrganizationRemoveReqDTO organizationRemoveReqDTO);

    OrganizationDTO convertToOrganizationDTO(OrganizationListReqDTO organizationListReqDTO);

    OrganizationDTO convertToOrganizationDTO(OrganizationPageReqDTO organizationPageReqDTO);

    OrganizationShowResDTO convertToOrganizationShowResDTO(OrganizationDTO organizationDTO);

    List<OrganizationShowResDTO> convertToOrganizationShowResDTOList(List<OrganizationDTO> organizationDTOList);

    OrganizationListResDTO convertToOrganizationListResDTO(OrganizationDTO organizationDTO);

    List<OrganizationListResDTO> convertToOrganizationListResDTOList(List<OrganizationDTO> organizationDTOList);

    List<OrganizationDTO> convertToOrganizationDTOList(List<OrganizationAddReqDTO> organizationAddReqDTOList);

    OrganizationPageResDTO convertToOrganizationPageResDTO(OrganizationDTO organizationDTO);

    List<OrganizationPageResDTO> convertToOrganizationPageResDTOList(List<OrganizationDTO> organizationDTOList);

    Page<OrganizationPageResDTO> convertToOrganizationPageResDTOPage(Page<OrganizationDTO> organizationDTOPage);
}

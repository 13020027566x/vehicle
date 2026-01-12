package com.vehicle.service.organization.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.organization.po.OrganizationPO;
import com.vehicle.service.organization.OrganizationService;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import com.vehicle.service.organization.manager.OrganizationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationManager, OrganizationPO, OrganizationDTO>
    implements OrganizationService {

    @Override
    public List<OrganizationListResDTO> list(final OrganizationListReqDTO organizationListReqDTO) {
        return manager.list(organizationListReqDTO);
    }

    @Override
    public OrganizationListResDTO listOne(final OrganizationListReqDTO organizationListReqDTO) {
        return manager.listOne(organizationListReqDTO);
    }

    @Override
    public Page<OrganizationPageResDTO> pagination(final OrganizationPageReqDTO organizationPageReqDTO,
        final Integer current, final Integer size) {
        return manager.pagination(organizationPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final OrganizationAddReqDTO organizationAddReqDTO) {
        return manager.add(organizationAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final OrganizationAddReqDTO organizationAddReqDTO) {
        return manager.addAllColumn(organizationAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        return manager.addBatchAllColumn(organizationAddReqDTOList);
    }

    @Override
    public OrganizationShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<OrganizationShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        return manager.modify(organizationModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        return manager.modifyAllColumn(organizationModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        return manager.removeByParams(organizationRemoveReqDTO);
    }
}

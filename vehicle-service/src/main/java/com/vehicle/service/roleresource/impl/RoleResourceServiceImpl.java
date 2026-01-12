package com.vehicle.service.roleresource.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.roleresource.po.RoleResourcePO;
import com.vehicle.service.roleresource.RoleResourceService;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import com.vehicle.service.roleresource.manager.RoleResourceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色资源 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResourceManager, RoleResourcePO, RoleResourceDTO> implements RoleResourceService {

    @Override
    public List<RoleResourceListResDTO> list(final RoleResourceListReqDTO roleresourceListReqDTO) {
        return manager.list(roleresourceListReqDTO);
    }

    @Override
    public RoleResourceListResDTO listOne(final RoleResourceListReqDTO roleresourceListReqDTO) {
        return manager.listOne(roleresourceListReqDTO);
    }

    @Override
    public Page<RoleResourcePageResDTO> pagination(final RoleResourcePageReqDTO roleresourcePageReqDTO,
        final Integer current, final Integer size) {
        return manager.pagination(roleresourcePageReqDTO, current, size);
    }

    @Override
    public Boolean add(final RoleResourceAddReqDTO roleresourceAddReqDTO) {
        return manager.add(roleresourceAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final RoleResourceAddReqDTO roleresourceAddReqDTO) {
        return manager.addAllColumn(roleresourceAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<RoleResourceAddReqDTO> roleresourceAddReqDTOList) {
        return manager.addBatchAllColumn(roleresourceAddReqDTOList);
    }

    @Override
    public RoleResourceShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<RoleResourceShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final RoleResourceModifyReqDTO roleresourceModifyReqDTO) {
        return manager.modify(roleresourceModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final RoleResourceModifyReqDTO roleresourceModifyReqDTO) {
        return manager.modifyAllColumn(roleresourceModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final RoleResourceRemoveReqDTO roleresourceRemoveReqDTO) {
        return manager.removeByParams(roleresourceRemoveReqDTO);
    }

    @Override
    public List<RoleResourceDTO> findByRoleIds(final List<String> roleIdList) {
        return manager.findByRoleIds(roleIdList);
    }
}

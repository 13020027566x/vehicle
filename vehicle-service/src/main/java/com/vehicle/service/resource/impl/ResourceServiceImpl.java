package com.vehicle.service.resource.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.resource.po.ResourcePO;
import com.vehicle.service.resource.ResourceService;
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
import com.vehicle.service.resource.manager.ResourceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceManager, ResourcePO, ResourceDTO> implements ResourceService {

    @Override
    public List<ResourceListResDTO> list(final ResourceListReqDTO resourceListReqDTO) {
        return manager.list(resourceListReqDTO);
    }

    @Override
    public ResourceListResDTO listOne(final ResourceListReqDTO resourceListReqDTO) {
        return manager.listOne(resourceListReqDTO);
    }

    @Override
    public Page<ResourcePageResDTO> pagination(final ResourcePageReqDTO resourcePageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(resourcePageReqDTO, current, size);
    }

    @Override
    public Boolean add(final ResourceAddReqDTO resourceAddReqDTO) {
        return manager.add(resourceAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final ResourceAddReqDTO resourceAddReqDTO) {
        return manager.addAllColumn(resourceAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<ResourceAddReqDTO> resourceAddReqDTOList) {
        return manager.addBatchAllColumn(resourceAddReqDTOList);
    }

    @Override
    public ResourceShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<ResourceShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final ResourceModifyReqDTO resourceModifyReqDTO) {
        return manager.modify(resourceModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final ResourceModifyReqDTO resourceModifyReqDTO) {
        return manager.modifyAllColumn(resourceModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final ResourceRemoveReqDTO resourceRemoveReqDTO) {
        return manager.removeByParams(resourceRemoveReqDTO);
    }

    @Override
    public List<UserMenuResourceDTO> listUserMenu() {
        return manager.listUserMenu();
    }

    @Override
    public List<UserMenuOperationDTO> listUserMenuOperation(final String menuPath) {
        return manager.listUserMenuOperation(menuPath);
    }
}

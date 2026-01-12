package com.vehicle.service.role.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.role.po.RolePO;
import com.vehicle.service.role.RoleService;
import com.vehicle.service.role.dto.RoleAddReqDTO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.role.dto.RoleListPermissionReqDTO;
import com.vehicle.service.role.dto.RoleListPermissionResDTO;
import com.vehicle.service.role.dto.RoleListReqDTO;
import com.vehicle.service.role.dto.RoleListResDTO;
import com.vehicle.service.role.dto.RoleModifyPermissionReqDTO;
import com.vehicle.service.role.dto.RoleModifyReqDTO;
import com.vehicle.service.role.dto.RolePageReqDTO;
import com.vehicle.service.role.dto.RolePageResDTO;
import com.vehicle.service.role.dto.RoleRemoveReqDTO;
import com.vehicle.service.role.dto.RoleShowResDTO;
import com.vehicle.service.role.manager.RoleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleManager, RolePO, RoleDTO> implements RoleService {

    @Override
    public List<RoleListResDTO> list(final RoleListReqDTO roleListReqDTO) {
        return manager.list(roleListReqDTO);
    }

    @Override
    public RoleListResDTO listOne(final RoleListReqDTO roleListReqDTO) {
        return manager.listOne(roleListReqDTO);
    }

    @Override
    public Page<RolePageResDTO> pagination(final RolePageReqDTO rolePageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(rolePageReqDTO, current, size);
    }

    @Override
    public Boolean add(final RoleAddReqDTO roleAddReqDTO) {
        return manager.add(roleAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final RoleAddReqDTO roleAddReqDTO) {
        return manager.addAllColumn(roleAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<RoleAddReqDTO> roleAddReqDTOList) {
        return manager.addBatchAllColumn(roleAddReqDTOList);
    }

    @Override
    public RoleShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<RoleShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final RoleModifyReqDTO roleModifyReqDTO) {
        return manager.modify(roleModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final RoleModifyReqDTO roleModifyReqDTO) {
        return manager.modifyAllColumn(roleModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final RoleRemoveReqDTO roleRemoveReqDTO) {
        return manager.removeByParams(roleRemoveReqDTO);
    }

    @Override
    public List<RoleListPermissionResDTO> listPermission(RoleListPermissionReqDTO roleListPermissionReqDTO) {
        return manager.listPermission(roleListPermissionReqDTO);
    }

    @Override
    public Boolean modifyPermission(RoleModifyPermissionReqDTO roleModifyPermissionReqDTO) {
        return manager.modifyPermission(roleModifyPermissionReqDTO);
    }


}

package com.vehicle.service.userrole.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.userrole.po.UserRolePO;
import com.vehicle.service.userrole.UserRoleService;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import com.vehicle.service.userrole.manager.UserRoleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleManager, UserRolePO, UserRoleDTO> implements UserRoleService {

    @Override
    public List<UserRoleListResDTO> list(final UserRoleListReqDTO userroleListReqDTO) {
        return manager.list(userroleListReqDTO);
    }

    @Override
    public UserRoleListResDTO listOne(final UserRoleListReqDTO userroleListReqDTO) {
        return manager.listOne(userroleListReqDTO);
    }

    @Override
    public Page<UserRolePageResDTO> pagination(final UserRolePageReqDTO userrolePageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(userrolePageReqDTO, current, size);
    }

    @Override
    public Boolean add(final UserRoleAddReqDTO userroleAddReqDTO) {
        return manager.add(userroleAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final UserRoleAddReqDTO userroleAddReqDTO) {
        return manager.addAllColumn(userroleAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<UserRoleAddReqDTO> userroleAddReqDTOList) {
        return manager.addBatchAllColumn(userroleAddReqDTOList);
    }

    @Override
    public UserRoleShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<UserRoleShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final UserRoleModifyReqDTO userroleModifyReqDTO) {
        return manager.modify(userroleModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final UserRoleModifyReqDTO userroleModifyReqDTO) {
        return manager.modifyAllColumn(userroleModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final UserRoleRemoveReqDTO userroleRemoveReqDTO) {
        return manager.removeByParams(userroleRemoveReqDTO);
    }
}

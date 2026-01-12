package com.vehicle.service.userorg.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.userorg.po.UserOrgPO;
import com.vehicle.service.userorg.UserOrgService;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;
import com.vehicle.service.userorg.manager.UserOrgManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户机构 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class UserOrgServiceImpl extends BaseServiceImpl<UserOrgManager, UserOrgPO, UserOrgDTO>
    implements UserOrgService {

    @Override
    public List<UserOrgListResDTO> list(final UserOrgListReqDTO userorgListReqDTO) {
        return manager.list(userorgListReqDTO);
    }

    @Override
    public UserOrgListResDTO listOne(final UserOrgListReqDTO userorgListReqDTO) {
        return manager.listOne(userorgListReqDTO);
    }

    @Override
    public Page<UserOrgPageResDTO> pagination(final UserOrgPageReqDTO userorgPageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(userorgPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final UserOrgAddReqDTO userorgAddReqDTO) {
        return manager.add(userorgAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final UserOrgAddReqDTO userorgAddReqDTO) {
        return manager.addAllColumn(userorgAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<UserOrgAddReqDTO> userorgAddReqDTOList) {
        return manager.addBatchAllColumn(userorgAddReqDTOList);
    }

    @Override
    public UserOrgShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<UserOrgShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final UserOrgModifyReqDTO userorgModifyReqDTO) {
        return manager.modify(userorgModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final UserOrgModifyReqDTO userorgModifyReqDTO) {
        return manager.modifyAllColumn(userorgModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final UserOrgRemoveReqDTO userorgRemoveReqDTO) {
        return manager.removeByParams(userorgRemoveReqDTO);
    }
}

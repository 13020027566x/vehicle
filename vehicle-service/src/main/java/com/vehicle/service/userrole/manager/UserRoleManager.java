package com.vehicle.service.userrole.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userrole.UserRoleDAO;
import com.vehicle.dao.userrole.po.UserRolePO;
import com.vehicle.service.userrole.converter.UserRoleConverter;
import com.vehicle.service.userrole.domain.UserRoleDO;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户角色 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserRoleManager extends BaseManagerImpl<UserRoleDAO, UserRolePO, UserRoleDTO, UserRoleConverter> {

    public static UserRoleManager me() {
        return SpringUtil.getBean(UserRoleManager.class);
    }

    public List<UserRoleListResDTO> list(final UserRoleListReqDTO userRoleListReqDTO) {
        UserRoleDTO paramsDTO = UserRoleDO.me().buildListParamsDTO(userRoleListReqDTO);

        List<UserRoleDTO> userRoleDTOList = super.findList(paramsDTO);

        return UserRoleDO.me().transferUserRoleListResDTOList(userRoleDTOList);
    }

    public UserRoleListResDTO listOne(final UserRoleListReqDTO userRoleListReqDTO) {
        UserRoleDTO paramsDTO = UserRoleDO.me().buildListParamsDTO(userRoleListReqDTO);

        UserRoleDTO userRoleDTO = super.findOne(paramsDTO);

        return UserRoleDO.me().transferUserRoleListResDTO(userRoleDTO);
    }

    public Page<UserRolePageResDTO> pagination(final UserRolePageReqDTO userRolePageReqDTO, final Integer current, final Integer size) {
        UserRoleDTO paramsDTO = UserRoleDO.me().buildPageParamsDTO(userRolePageReqDTO);

        Page<UserRoleDTO> userRoleDTOPage = super.findPage(paramsDTO, current, size);

        return UserRoleDO.me().transferUserRolePageResDTOPage(userRoleDTOPage);
    }

    public Boolean add(final UserRoleAddReqDTO userRoleAddReqDTO) {
        UserRoleDO.me().checkUserRoleAddReqDTO(userRoleAddReqDTO);

        UserRoleDTO addUserRoleDTO = UserRoleDO.me().buildAddUserRoleDTO(userRoleAddReqDTO);

        return super.saveDTO(addUserRoleDTO);
    }

    public Boolean addAllColumn(final UserRoleAddReqDTO userRoleAddReqDTO) {
        UserRoleDO.me().checkUserRoleAddReqDTO(userRoleAddReqDTO);

        UserRoleDTO addUserRoleDTO = UserRoleDO.me().buildAddUserRoleDTO(userRoleAddReqDTO);

        return super.saveAllColumn(addUserRoleDTO);
    }

    public Boolean addBatchAllColumn(final List<UserRoleAddReqDTO> userRoleAddReqDTOList) {
        UserRoleDO.me().checkUserRoleAddReqDTOList(userRoleAddReqDTOList);

        List<UserRoleDTO> addBatchUserRoleDTOList = UserRoleDO.me().buildAddBatchUserRoleDTOList(userRoleAddReqDTOList);

        return super.saveBatchAllColumn(addBatchUserRoleDTOList);
    }

    public UserRoleShowResDTO show(final String id) {
        UserRoleDTO userRoleDTO = super.findById(id);

        return UserRoleDO.me().transferUserRoleShowResDTO(userRoleDTO);
    }

    public List<UserRoleShowResDTO> showByIds(final List<String> ids) {
        UserRoleDO.me().checkIds(ids);

        List<UserRoleDTO> userRoleDTOList = super.findBatchIds(ids);

        return UserRoleDO.me().transferUserRoleShowResDTOList(userRoleDTOList);
    }

    public Boolean modify(final UserRoleModifyReqDTO userRoleModifyReqDTO) {
        UserRoleDO.me().checkUserRoleModifyReqDTO(userRoleModifyReqDTO);

        UserRoleDTO modifyUserRoleDTO = UserRoleDO.me().buildModifyUserRoleDTO(userRoleModifyReqDTO);

        return super.modifyById(modifyUserRoleDTO);
    }

    public Boolean modifyAllColumn(final UserRoleModifyReqDTO userRoleModifyReqDTO) {
        UserRoleDO.me().checkUserRoleModifyReqDTO(userRoleModifyReqDTO);

        UserRoleDTO modifyUserRoleDTO = UserRoleDO.me().buildModifyUserRoleDTO(userRoleModifyReqDTO);

        return super.modifyAllColumnById(modifyUserRoleDTO);
    }

    public Boolean removeByParams(final UserRoleRemoveReqDTO userRoleRemoveReqDTO) {
        UserRoleDO.me().checkUserRoleRemoveReqDTO(userRoleRemoveReqDTO);

        UserRoleDTO removeUserRoleDTO = UserRoleDO.me().buildRemoveUserRoleDTO(userRoleRemoveReqDTO);

        return super.remove(removeUserRoleDTO);
    }

    @Override
    protected UserRolePO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserRolePO();
        }

        return BeanUtil.toBean(map, UserRolePO.class);
    }

    @Override
    protected UserRoleDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserRoleDTO();
        }

        return BeanUtil.toBean(map, UserRoleDTO.class);
    }
}

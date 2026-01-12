package com.vehicle.service.userorg.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userorg.UserOrgDAO;
import com.vehicle.dao.userorg.po.UserOrgPO;
import com.vehicle.service.userorg.converter.UserOrgConverter;
import com.vehicle.service.userorg.domain.UserOrgDO;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户机构 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserOrgManager extends BaseManagerImpl<UserOrgDAO, UserOrgPO, UserOrgDTO, UserOrgConverter> {

    public static UserOrgManager me() {
        return SpringUtil.getBean(UserOrgManager.class);
    }

    public List<UserOrgListResDTO> list(final UserOrgListReqDTO userOrgListReqDTO) {
        UserOrgDTO paramsDTO = UserOrgDO.me().buildListParamsDTO(userOrgListReqDTO);

        List<UserOrgDTO> userOrgDTOList = super.findList(paramsDTO);

        return UserOrgDO.me().transferUserOrgListResDTOList(userOrgDTOList);
    }

    public UserOrgListResDTO listOne(final UserOrgListReqDTO userOrgListReqDTO) {
        UserOrgDTO paramsDTO = UserOrgDO.me().buildListParamsDTO(userOrgListReqDTO);

        UserOrgDTO userOrgDTO = super.findOne(paramsDTO);

        return UserOrgDO.me().transferUserOrgListResDTO(userOrgDTO);
    }

    public Page<UserOrgPageResDTO> pagination(final UserOrgPageReqDTO userOrgPageReqDTO, final Integer current, final Integer size) {
        UserOrgDTO paramsDTO = UserOrgDO.me().buildPageParamsDTO(userOrgPageReqDTO);

        Page<UserOrgDTO> userOrgDTOPage = super.findPage(paramsDTO, current, size);

        return UserOrgDO.me().transferUserOrgPageResDTOPage(userOrgDTOPage);
    }

    public Boolean add(final UserOrgAddReqDTO userOrgAddReqDTO) {
        UserOrgDO.me().checkUserOrgAddReqDTO(userOrgAddReqDTO);

        UserOrgDTO addUserOrgDTO = UserOrgDO.me().buildAddUserOrgDTO(userOrgAddReqDTO);

        return super.saveDTO(addUserOrgDTO);
    }

    public Boolean addAllColumn(final UserOrgAddReqDTO userOrgAddReqDTO) {
        UserOrgDO.me().checkUserOrgAddReqDTO(userOrgAddReqDTO);

        UserOrgDTO addUserOrgDTO = UserOrgDO.me().buildAddUserOrgDTO(userOrgAddReqDTO);

        return super.saveAllColumn(addUserOrgDTO);
    }

    public Boolean addBatchAllColumn(final List<UserOrgAddReqDTO> userOrgAddReqDTOList) {
        UserOrgDO.me().checkUserOrgAddReqDTOList(userOrgAddReqDTOList);

        List<UserOrgDTO> addBatchUserOrgDTOList = UserOrgDO.me().buildAddBatchUserOrgDTOList(userOrgAddReqDTOList);

        return super.saveBatchAllColumn(addBatchUserOrgDTOList);
    }

    public UserOrgShowResDTO show(final String id) {
        UserOrgDTO userOrgDTO = super.findById(id);

        return UserOrgDO.me().transferUserOrgShowResDTO(userOrgDTO);
    }

    public List<UserOrgShowResDTO> showByIds(final List<String> ids) {
        UserOrgDO.me().checkIds(ids);

        List<UserOrgDTO> userOrgDTOList = super.findBatchIds(ids);

        return UserOrgDO.me().transferUserOrgShowResDTOList(userOrgDTOList);
    }

    public Boolean modify(final UserOrgModifyReqDTO userOrgModifyReqDTO) {
        UserOrgDO.me().checkUserOrgModifyReqDTO(userOrgModifyReqDTO);

        UserOrgDTO modifyUserOrgDTO = UserOrgDO.me().buildModifyUserOrgDTO(userOrgModifyReqDTO);

        return super.modifyById(modifyUserOrgDTO);
    }

    public Boolean modifyAllColumn(final UserOrgModifyReqDTO userOrgModifyReqDTO) {
        UserOrgDO.me().checkUserOrgModifyReqDTO(userOrgModifyReqDTO);

        UserOrgDTO modifyUserOrgDTO = UserOrgDO.me().buildModifyUserOrgDTO(userOrgModifyReqDTO);

        return super.modifyAllColumnById(modifyUserOrgDTO);
    }

    public Boolean removeByParams(final UserOrgRemoveReqDTO userOrgRemoveReqDTO) {
        UserOrgDO.me().checkUserOrgRemoveReqDTO(userOrgRemoveReqDTO);

        UserOrgDTO removeUserOrgDTO = UserOrgDO.me().buildRemoveUserOrgDTO(userOrgRemoveReqDTO);

        return super.remove(removeUserOrgDTO);
    }

    @Override
    protected UserOrgPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserOrgPO();
        }

        return BeanUtil.toBean(map, UserOrgPO.class);
    }

    @Override
    protected UserOrgDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new UserOrgDTO();
        }

        return BeanUtil.toBean(map, UserOrgDTO.class);
    }
}

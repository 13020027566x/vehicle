package com.vehicle.service.roleresource.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vehicle.dao.roleresource.RoleResourceDAO;
import com.vehicle.dao.roleresource.po.RoleResourcePO;
import com.vehicle.service.roleresource.converter.RoleResourceConverter;
import com.vehicle.service.roleresource.domain.RoleResourceDO;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 角色资源 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class RoleResourceManager extends BaseManagerImpl<RoleResourceDAO, RoleResourcePO, RoleResourceDTO, RoleResourceConverter> {

    public static RoleResourceManager me() {
        return SpringUtil.getBean(RoleResourceManager.class);
    }

    public List<RoleResourceListResDTO> list(final RoleResourceListReqDTO roleResourceListReqDTO) {
        RoleResourceDTO paramsDTO = RoleResourceDO.me().buildListParamsDTO(roleResourceListReqDTO);

        List<RoleResourceDTO> roleResourceDTOList = super.findList(paramsDTO);

        return RoleResourceDO.me().transferRoleResourceListResDTOList(roleResourceDTOList);
    }

    public RoleResourceListResDTO listOne(final RoleResourceListReqDTO roleResourceListReqDTO) {
        RoleResourceDTO paramsDTO = RoleResourceDO.me().buildListParamsDTO(roleResourceListReqDTO);

        RoleResourceDTO roleResourceDTO = super.findOne(paramsDTO);

        return RoleResourceDO.me().transferRoleResourceListResDTO(roleResourceDTO);
    }

    public Page<RoleResourcePageResDTO> pagination(final RoleResourcePageReqDTO roleResourcePageReqDTO, final Integer current, final Integer size) {
        RoleResourceDTO paramsDTO = RoleResourceDO.me().buildPageParamsDTO(roleResourcePageReqDTO);

        Page<RoleResourceDTO> roleResourceDTOPage = super.findPage(paramsDTO, current, size);

        return RoleResourceDO.me().transferRoleResourcePageResDTOPage(roleResourceDTOPage);
    }

    public Boolean add(final RoleResourceAddReqDTO roleResourceAddReqDTO) {
        RoleResourceDO.me().checkRoleResourceAddReqDTO(roleResourceAddReqDTO);

        RoleResourceDTO addRoleResourceDTO = RoleResourceDO.me().buildAddRoleResourceDTO(roleResourceAddReqDTO);

        return super.saveDTO(addRoleResourceDTO);
    }

    public Boolean addAllColumn(final RoleResourceAddReqDTO roleResourceAddReqDTO) {
        RoleResourceDO.me().checkRoleResourceAddReqDTO(roleResourceAddReqDTO);

        RoleResourceDTO addRoleResourceDTO = RoleResourceDO.me().buildAddRoleResourceDTO(roleResourceAddReqDTO);

        return super.saveAllColumn(addRoleResourceDTO);
    }

    public Boolean addBatchAllColumn(final List<RoleResourceAddReqDTO> roleResourceAddReqDTOList) {
        RoleResourceDO.me().checkRoleResourceAddReqDTOList(roleResourceAddReqDTOList);

        List<RoleResourceDTO> addBatchRoleResourceDTOList = RoleResourceDO.me().buildAddBatchRoleResourceDTO(roleResourceAddReqDTOList);

        return super.saveBatchAllColumn(addBatchRoleResourceDTOList);
    }

    public RoleResourceShowResDTO show(final String id) {
        RoleResourceDTO roleResourceDTO = super.findById(id);

        return RoleResourceDO.me().transferRoleResourceShowResDTO(roleResourceDTO);
    }

    public List<RoleResourceShowResDTO> showByIds(final List<String> ids) {
        RoleResourceDO.me().checkIds(ids);

        List<RoleResourceDTO> roleResourceDTOList = super.findBatchIds(ids);

        return RoleResourceDO.me().transferRoleResourceShowResDTOList(roleResourceDTOList);
    }

    public Boolean modify(final RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        RoleResourceDO.me().checkRoleResourceModifyReqDTO(roleResourceModifyReqDTO);

        RoleResourceDTO modifyRoleResourceDTO = RoleResourceDO.me().buildModifyRoleResourceDTO(roleResourceModifyReqDTO);

        return super.modifyById(modifyRoleResourceDTO);
    }

    public Boolean modifyAllColumn(final RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        RoleResourceDO.me().checkRoleResourceModifyReqDTO(roleResourceModifyReqDTO);

        RoleResourceDTO modifyRoleResourceDTO = RoleResourceDO.me().buildModifyRoleResourceDTO(roleResourceModifyReqDTO);

        return super.modifyAllColumnById(modifyRoleResourceDTO);
    }

    public Boolean removeByParams(final RoleResourceRemoveReqDTO roleResourceRemoveReqDTO) {
        RoleResourceDO.me().checkRoleResourceRemoveReqDTO(roleResourceRemoveReqDTO);

        RoleResourceDTO removeRoleResourceDTO = RoleResourceDO.me().buildRemoveRoleResourceDTO(roleResourceRemoveReqDTO);

        return super.remove(removeRoleResourceDTO);
    }

    public List<RoleResourceDTO> findByRoleIds(final List<String> roleIdList) {
        RoleResourceDO.me().checkRoleIdList(roleIdList);

        return super.findList(new QueryWrapper<RoleResourcePO>().in(RoleResourcePO.DB_COL_ROLE_ID, roleIdList));
    }

    @Override
    protected RoleResourcePO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new RoleResourcePO();
        }

        return BeanUtil.toBean(map, RoleResourcePO.class);
    }

    @Override
    protected RoleResourceDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new RoleResourceDTO();
        }

        return BeanUtil.toBean(map, RoleResourceDTO.class);
    }
}

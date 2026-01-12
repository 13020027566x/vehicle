package com.vehicle.service.role.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.vehicle.dao.role.RoleDAO;
import com.vehicle.dao.role.po.RolePO;
import com.vehicle.service.resource.dto.ResourceDTO;
import com.vehicle.service.resource.manager.ResourceManager;
import com.vehicle.service.role.converter.RoleConverter;
import com.vehicle.service.role.domain.RoleDO;
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
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.manager.RoleResourceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 角色 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class RoleManager extends BaseManagerImpl<RoleDAO, RolePO, RoleDTO, RoleConverter> {

    public static RoleManager me() {
        return SpringUtil.getBean(RoleManager.class);
    }

    public List<RoleListResDTO> list(final RoleListReqDTO roleListReqDTO) {
        RoleDTO paramsDTO = RoleDO.me().buildListParamsDTO(roleListReqDTO);

        List<RoleDTO> roleDTOList = super.findList(paramsDTO);

        return RoleDO.me().transferRoleListResDTOList(roleDTOList);
    }

    public RoleListResDTO listOne(final RoleListReqDTO roleListReqDTO) {
        RoleDTO paramsDTO = RoleDO.me().buildListParamsDTO(roleListReqDTO);

        RoleDTO roleDTO = super.findOne(paramsDTO);

        return RoleDO.me().transferRoleListResDTO(roleDTO);
    }

    public Page<RolePageResDTO> pagination(final RolePageReqDTO rolePageReqDTO, final Integer current, final Integer size) {
        QueryWrapper<RolePO> queryWrapper = RoleDO.me().buildRoleQueryWrapper(rolePageReqDTO);

        Page<RoleDTO> roleDTOPage = super.findPage(queryWrapper, current, size);

        return RoleDO.me().transferRolePageResDTOPage(roleDTOPage);
    }

    public Boolean add(final RoleAddReqDTO roleAddReqDTO) {
        RoleDO.me().checkRoleAddReqDTO(roleAddReqDTO);

        QueryWrapper<RolePO> queryWrapper = RoleDO.me().buildRoleQueryWrapper(roleAddReqDTO);
        Integer count = RoleManager.me().findCount(queryWrapper);

        RoleDO.me().checkResultCount(count);

        List<RoleDTO> roleDTOList = Lists.newArrayList();
        for (int i= 0; i < 2; i++) {
            RoleDTO addRoleDTO = RoleDO.me().buildAddRoleDTO(roleAddReqDTO);
            addRoleDTO.setName("name" + i);
            roleDTOList.add(addRoleDTO);
        }

        return super.saveBatchAllColumn(roleDTOList);
    }

    public Boolean addAllColumn(final RoleAddReqDTO roleAddReqDTO) {
        RoleDO.me().checkRoleAddReqDTO(roleAddReqDTO);

        RoleDTO addRoleDTO = RoleDO.me().buildAddRoleDTO(roleAddReqDTO);

        return super.saveAllColumn(addRoleDTO);
    }

    public Boolean addBatchAllColumn(final List<RoleAddReqDTO> roleAddReqDTOList) {
        RoleDO.me().checkRoleAddReqDTOList(roleAddReqDTOList);

        List<RoleDTO> addBatchRoleDTOList = RoleDO.me().buildAddBatchRoleDTOList(roleAddReqDTOList);

        return super.saveBatchAllColumn(addBatchRoleDTOList);
    }

    public RoleShowResDTO show(final String id) {
        RoleDTO roleDTO = super.findById(id);

        return RoleDO.me().transferRoleShowResDTO(roleDTO);
    }

    public List<RoleShowResDTO> showByIds(final List<String> ids) {
        RoleDO.me().checkIds(ids);

        List<RoleDTO> roleDTOList = super.findBatchIds(ids);

        return RoleDO.me().transferRoleShowResDTOList(roleDTOList);
    }

    public Boolean modify(final RoleModifyReqDTO roleModifyReqDTO) {
        RoleDO.me().checkRoleModifyReqDTO(roleModifyReqDTO);

        QueryWrapper<RolePO> queryWrapper = RoleDO.me().buildRoleQueryWrapper(roleModifyReqDTO);
        Integer count = RoleManager.me().findCount(queryWrapper);

        RoleDO.me().checkResultCount(count);

        RoleDTO modifyRoleDTO = RoleDO.me().buildModifyRoleDTO(roleModifyReqDTO);

        return super.modifyById(modifyRoleDTO);
    }

    public Boolean modifyAllColumn(final RoleModifyReqDTO roleModifyReqDTO) {
        RoleDO.me().checkRoleModifyReqDTO(roleModifyReqDTO);

        RoleDTO modifyRoleDTO = RoleDO.me().buildModifyRoleDTO(roleModifyReqDTO);

        return super.modifyAllColumnById(modifyRoleDTO);
    }

    public Boolean removeByParams(final RoleRemoveReqDTO roleRemoveReqDTO) {
        RoleDO.me().checkRoleRemoveReqDTO(roleRemoveReqDTO);

        RoleDTO removeRoleDTO = RoleDO.me().buildRemoveRoleDTO(roleRemoveReqDTO);

        return super.remove(removeRoleDTO);
    }

    public List<RoleListPermissionResDTO> listPermission(final RoleListPermissionReqDTO roleListPermissionReqDTO) {
        RoleResourceDTO roleResourceDTO = RoleDO.me().buildRoleResourceDTO(roleListPermissionReqDTO);

        List<RoleResourceDTO> roleResourceDTOList = RoleResourceManager.me().findList(roleResourceDTO);
        List<ResourceDTO> resourceDTOList = ResourceManager.me().findList(new ResourceDTO());

        return RoleDO.me().transferRoleListPermissionResDTO(roleResourceDTOList, resourceDTOList);
    }

    public Boolean modifyPermission(final RoleModifyPermissionReqDTO roleModifyPermissionReqDTO) {
        RoleDTO roleDTO = RoleManager.me().findById(roleModifyPermissionReqDTO.getRoleId());

        RoleDO.me().checkRoleDTO(roleDTO);

        RoleResourceDTO roleResourceDTO = RoleDO.me().buildRoleResourceDTO(roleModifyPermissionReqDTO);
        RoleResourceManager.me().remove(roleResourceDTO);

        if (Func.isNotEmpty(roleModifyPermissionReqDTO.getPermissionIds())) {
            List<ResourceDTO> resourceDTOList = ResourceManager.me().findBatchIds(roleModifyPermissionReqDTO.getPermissionIds());
            List<RoleResourceDTO> roleResourceDTOList = RoleDO.me().buildRoleResourceDTOList(roleDTO, resourceDTOList);
            return RoleResourceManager.me().saveBatchAllColumn(roleResourceDTOList);
        }

        return true;
    }

    @Override
    protected RolePO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new RolePO();
        }

        return BeanUtil.toBean(map, RolePO.class);
    }

    @Override
    protected RoleDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new RoleDTO();
        }

        return BeanUtil.toBean(map, RoleDTO.class);
    }
}

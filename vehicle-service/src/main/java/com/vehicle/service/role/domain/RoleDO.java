package com.vehicle.service.role.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vehicle.dao.role.po.RolePO;
import com.vehicle.service.resource.dto.ResourceDTO;
import com.vehicle.service.role.converter.RoleConverter;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class RoleDO extends BaseDO<RoleDTO, RolePO, RoleConverter> {

    public static RoleDO me() {
        return SpringUtil.getBean(RoleDO.class);
    }

    public void checkRoleAddReqDTO(final RoleAddReqDTO roleAddReqDTO) {
        if (Func.isEmpty(roleAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkResultCount(final Integer count) {
        if (!Func.isNullOrZero(count)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "名称或编码已存在");
        }
    }

    public void checkRoleAddReqDTOList(final List<RoleAddReqDTO> roleAddReqDTOList) {
        if (Func.isEmpty(roleAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkRoleModifyReqDTO(final RoleModifyReqDTO roleModifyReqDTO) {
        if (Func.isEmpty(roleModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkRoleRemoveReqDTO(final RoleRemoveReqDTO roleRemoveReqDTO) {
        if (Func.isEmpty(roleRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkRoleDTO(final RoleDTO roleDTO) {
        if (Func.isEmpty(roleDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "角色ID参数错误");
        }
    }

    public QueryWrapper<RolePO> buildRoleQueryWrapper(final RoleAddReqDTO roleAddReqDTO) {
        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(roleAddReqDTO.getName())) {
            queryWrapper.eq(RolePO.DB_COL_NAME, roleAddReqDTO.getName());
        }

        if (Func.isNotEmpty(roleAddReqDTO.getCode())) {
            queryWrapper.or().eq(RolePO.DB_COL_CODE, roleAddReqDTO.getCode());
        }

        return queryWrapper;
    }

    public QueryWrapper<RolePO> buildRoleQueryWrapper(final RoleModifyReqDTO roleModifyReqDTO) {
        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.ne(RolePO.DB_COL_ID, roleModifyReqDTO.getId());

        if (Func.isNotEmpty(roleModifyReqDTO.getName()) && Func.isNotEmpty(roleModifyReqDTO.getCode())) {
            queryWrapper.and(
                i -> i.eq(RolePO.DB_COL_NAME, roleModifyReqDTO.getName()).or().eq(RolePO.DB_COL_CODE,
                    roleModifyReqDTO.getCode()));
            return queryWrapper;
        }

        if (Func.isNotEmpty(roleModifyReqDTO.getName())) {
            queryWrapper.eq(RolePO.DB_COL_NAME, roleModifyReqDTO.getName());
        }

        if (Func.isNotEmpty(roleModifyReqDTO.getCode())) {
            queryWrapper.eq(RolePO.DB_COL_CODE, roleModifyReqDTO.getCode());
        }

        return queryWrapper;
    }

    public QueryWrapper<RolePO> buildRoleQueryWrapper(final RolePageReqDTO rolePageReqDTO) {
        RoleDTO roleDTO = converter.convertToRoleDTO(rolePageReqDTO);
        RolePO rolePO = converter.dtoToPO(roleDTO);

        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>(rolePO);
        if (Func.isNotEmpty(rolePageReqDTO.getUpdateStartAt())) {
            queryWrapper.ge(RolePO.DB_COL_UPDATE_AT, DateUtil.millisecond(DateUtil.parseDateTime(rolePageReqDTO.getUpdateStartAt())));
        }

        if (Func.isNotEmpty(rolePageReqDTO.getUpdateEndAt())) {
            queryWrapper.le(RolePO.DB_COL_UPDATE_AT, DateUtil.millisecond(DateUtil.parseDateTime(rolePageReqDTO.getUpdateEndAt())));
        }

        queryWrapper.orderByDesc(RolePO.DB_COL_ID);

        return queryWrapper;
    }

    public RoleDTO buildAddRoleDTO(final RoleAddReqDTO roleAddReqDTO) {
        return converter.convertToRoleDTO(roleAddReqDTO);
    }

    public RoleResourceDTO buildRoleResourceDTO(final RoleListPermissionReqDTO roleListPermissionReqDTO) {
        return converter.convertToRoleResourceDTO(roleListPermissionReqDTO);
    }

    public RoleResourceDTO buildRoleResourceDTO(final RoleModifyPermissionReqDTO roleModifyPermissionReqDTO) {
        return converter.convertToRoleResourceDTO(roleModifyPermissionReqDTO);
    }

    public List<RoleResourceDTO> buildRoleResourceDTOList(final RoleDTO roleDTO, final List<ResourceDTO> resourceDTOList) {
        List<RoleResourceDTO> roleResourceDTOList = Lists.newArrayList();

        for (ResourceDTO resourceDTO : resourceDTOList) {
            RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
            roleResourceDTO.setRoleCode(roleDTO.getCode());
            roleResourceDTO.setRoleName(roleDTO.getName());
            roleResourceDTO.setRoleId(roleDTO.getId());
            roleResourceDTO.setResourceName(resourceDTO.getName());
            roleResourceDTO.setResourceCode(resourceDTO.getCode());
            roleResourceDTO.setResourceId(resourceDTO.getId());
            roleResourceDTOList.add(roleResourceDTO);
        }

        return roleResourceDTOList;
    }

    public RoleDTO buildListParamsDTO(final RoleListReqDTO roleListReqDTO) {
        return converter.convertToRoleDTO(roleListReqDTO);
    }

    public List<RoleDTO> buildAddBatchRoleDTOList(final List<RoleAddReqDTO> roleAddReqDTOList) {
        return converter.convertToRoleDTOList(roleAddReqDTOList);
    }

    public RoleDTO buildModifyRoleDTO(final RoleModifyReqDTO roleModifyReqDTO) {
        return converter.convertToRoleDTO(roleModifyReqDTO);
    }

    public RoleDTO buildRemoveRoleDTO(final RoleRemoveReqDTO roleRemoveReqDTO) {
        return converter.convertToRoleDTO(roleRemoveReqDTO);
    }

    public RolePageResDTO transferRolePageResDTO(final RoleDTO roleDTO) {
        RolePageResDTO rolePageResDTO = converter.convertToRolePageResDTO(roleDTO);

        if (Func.isNotEmpty(roleDTO.getUpdateAt())) {
            rolePageResDTO.setUpdateAt(DateUtil.formatDateTime(DateUtil.date(roleDTO.getUpdateAt())));
        }

        return rolePageResDTO;
    }

    public List<RoleListPermissionResDTO> transferRoleListPermissionResDTO(final List<RoleResourceDTO> roleResourceDTOList, final List<ResourceDTO> resourceDTOList) {
        Set<String> roleResourceIdSet = Sets.newHashSet();
        if (Func.isNotEmpty(roleResourceDTOList)) {
            for (RoleResourceDTO roleResourceDTO : roleResourceDTOList) {
                roleResourceIdSet.add(roleResourceDTO.getResourceId());
            }
        }

        Map<String, RoleListPermissionResDTO> roleListPermissionResDTOMap = Maps.newHashMap();
        if (Func.isNotEmpty(resourceDTOList)) {
            for (ResourceDTO resourceDTO : resourceDTOList) {
                RoleListPermissionResDTO roleListPermissionResDTO = new RoleListPermissionResDTO();
                roleListPermissionResDTO.setId(resourceDTO.getId());
                roleListPermissionResDTO.setTitle(resourceDTO.getName());
                roleListPermissionResDTO.setPid(resourceDTO.getPid());
                roleListPermissionResDTO.setChecked(roleResourceIdSet.contains(resourceDTO.getId()));
                roleListPermissionResDTOMap.put(resourceDTO.getId(), roleListPermissionResDTO);
            }
        }

        List<RoleListPermissionResDTO> roleListPermissionResDTOList = Lists.newArrayList();
        for (ResourceDTO resourceDTO : resourceDTOList) {
            RoleListPermissionResDTO roleListPermissionResDTO = roleListPermissionResDTOMap.get(resourceDTO.getId());
            if (StrUtil.isBlank(resourceDTO.getPid())) {
                roleListPermissionResDTOList.add(roleListPermissionResDTO);
            } else {
                RoleListPermissionResDTO parentRoleListPermissionResDTO = roleListPermissionResDTOMap.get(resourceDTO.getPid());
                List<RoleListPermissionResDTO> children = parentRoleListPermissionResDTO.getChildren();
                if (children == null) {
                    children = Lists.newArrayList();
                    parentRoleListPermissionResDTO.setChildren(children);
                }
                children.add(roleListPermissionResDTO);
            }
        }

        return roleListPermissionResDTOList;
    }

    public List<RoleListResDTO> transferRoleListResDTOList(final List<RoleDTO> roleDTOList) {
        if (Func.isEmpty(roleDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleListResDTOList(roleDTOList);
    }

    public RoleListResDTO transferRoleListResDTO(final RoleDTO roleDTO) {
        if (Func.isEmpty(roleDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleListResDTO(roleDTO);
    }

    public Page<RolePageResDTO> transferRolePageResDTOPage(final Page<RoleDTO> roleDTOPage) {
        if (Func.isEmpty(roleDTOPage) || Func.isEmpty(roleDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        Page<RolePageResDTO> rolePageResDTOPage = converter.convertToRolePageResDTOPage(roleDTOPage);

        List<RolePageResDTO> rolePageResDTOList = Lists.newArrayList();
        for (RoleDTO roleDTO : roleDTOPage.getRecords()) {
            rolePageResDTOList.add(RoleDO.me().transferRolePageResDTO(roleDTO));
        }
        rolePageResDTOPage.setRecords(rolePageResDTOList);

        return rolePageResDTOPage;
    }

    public RoleShowResDTO transferRoleShowResDTO(final RoleDTO roleDTO) {
        if (Func.isEmpty(roleDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleShowResDTO(roleDTO);
    }

    public List<RoleShowResDTO> transferRoleShowResDTOList(final List<RoleDTO> roleDTOList) {
        if (Func.isEmpty(roleDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleShowResDTOList(roleDTOList);
    }
}

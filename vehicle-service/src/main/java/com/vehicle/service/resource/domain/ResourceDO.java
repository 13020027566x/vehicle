package com.vehicle.service.resource.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vehicle.dao.resource.po.ResourcePO;
import com.vehicle.service.resource.converter.ResourceConverter;
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
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class ResourceDO extends BaseDO<ResourceDTO, ResourcePO, ResourceConverter> {

    public static ResourceDO me() {
        return SpringUtil.getBean(ResourceDO.class);
    }

    public void checkResourceDTO(final ResourceDTO resourceDTO) {
        if (Func.isEmpty(resourceDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }
    }

    public void checkResourceAddReqDTO(final ResourceAddReqDTO resourceAddReqDTO) {
        if (Func.isEmpty(resourceAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkResultCount(final Integer count) {
        if (!Func.isNullOrZero(count)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "编码已存在");
        }
    }

    public void checkResourceAddReqDTOList(final List<ResourceAddReqDTO> resourceAddReqDTOList) {
        if (Func.isEmpty(resourceAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkResourceModifyReqDTO(final ResourceModifyReqDTO resourceModifyReqDTO) {
        if (Func.isEmpty(resourceModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkResourceRemoveReqDTO(final ResourceRemoveReqDTO resourceRemoveReqDTO) {
        if (Func.isEmpty(resourceRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkMenuResourceDTO(final ResourceDTO menuResourceDTO) {
        if (Func.isEmpty(menuResourceDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "当前菜单不存在");
        }
    }

    public void checkSubResourceDTOList(final List<ResourceDTO> subResourceDTOList) {
        if (Func.isNotEmpty(subResourceDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "请先删除子菜单");
        }
    }

    public List<String> buildUserRoleIdList(final List<UserRoleDTO> userRoleDTOs) {
        List<String> roleIdList = Lists.newArrayList();

        if (Func.isNotEmpty(userRoleDTOs)) {
            for (UserRoleDTO userRoleDTO : userRoleDTOs) {
                roleIdList.add(userRoleDTO.getRoleId());
            }
        }

        return roleIdList;
    }

    public UserRoleDTO buildUserRoleDTO(final String userId) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setUserId(userId);

        return userRoleDTO;
    }

    public List<String> buildUserResourceIdList(final List<RoleResourceDTO> roleResourceDTOList) {
        List<String> resourceIdList = Lists.newArrayList();

        if (Func.isNotEmpty(roleResourceDTOList)) {
            for (RoleResourceDTO roleResourceDTO : roleResourceDTOList) {
                resourceIdList.add(roleResourceDTO.getResourceId());
            }
        }

        return resourceIdList;
    }

    public ResourceDTO buildMenuResourceParamDTO(final String menuPath) {
        ResourceDTO resourceParamDTO = new ResourceDTO();

        resourceParamDTO.setUrl(menuPath);

        return resourceParamDTO;
    }

    public QueryWrapper<ResourcePO> buildParentQueryWrapper(final String pid) {
        QueryWrapper<ResourcePO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(ResourcePO.DB_COL_PID, pid);

        return queryWrapper;
    }

    public ResourceDTO buildListParamsDTO(final ResourceListReqDTO resourceListReqDTO) {
        return converter.convertToResourceDTO(resourceListReqDTO);
    }

    public ResourceDTO buildPageParamsDTO(final ResourcePageReqDTO resourcePageReqDTO) {
        return converter.convertToResourceDTO(resourcePageReqDTO);
    }

    public ResourceDTO buildAddResourceDTO(final ResourceAddReqDTO resourceAddReqDTO) {
        return converter.convertToResourceDTO(resourceAddReqDTO);
    }

    public List<ResourceDTO> buildAddBatchResourceDTOList(final List<ResourceAddReqDTO> resourceAddReqDTOList) {
        return converter.convertToResourceDTOList(resourceAddReqDTOList);
    }

    public ResourceDTO buildModifyResourceDTO(final ResourceModifyReqDTO resourceModifyReqDTO) {
        return converter.convertToResourceDTO(resourceModifyReqDTO);
    }

    public ResourceDTO buildRemoveResourceDTO(final ResourceRemoveReqDTO resourceRemoveReqDTO) {
        return converter.convertToResourceDTO(resourceRemoveReqDTO);
    }

    public List<UserMenuResourceDTO> transferUserMenuResourceDTOList(final List<ResourceDTO> resourceDTOList) {
        List<UserMenuResourceDTO> userMenuResourceDTOList = Lists.newArrayList();
        Map<String, UserMenuResourceDTO> userMenuResourceDTOMap = Maps.newHashMap();

        if (Func.isNotEmpty(resourceDTOList)) {
            for (ResourceDTO resourceDTO : resourceDTOList) {
                UserMenuResourceDTO userMenuResourceDTO = converter.convertToUserMenuResourceDTO(resourceDTO);
                userMenuResourceDTOMap.put(userMenuResourceDTO.getId(), userMenuResourceDTO);
            }

            for (ResourceDTO resourceDTO : resourceDTOList) {
                UserMenuResourceDTO userMenuResourceDTO = userMenuResourceDTOMap.get(resourceDTO.getId());
                if (StrUtil.isBlank(resourceDTO.getPid())) {
                    userMenuResourceDTOList.add(userMenuResourceDTO);
                } else {
                    UserMenuResourceDTO parentUserMenuResourceDTO = userMenuResourceDTOMap.get(resourceDTO.getPid());
                    List<UserMenuResourceDTO> childrens = parentUserMenuResourceDTO.getChildrens();
                    if (childrens == null) {
                        childrens = Lists.newArrayList();
                        parentUserMenuResourceDTO.setChildrens(childrens);
                    }
                    childrens.add(userMenuResourceDTO);
                }
            }
        }

        return userMenuResourceDTOList;
    }

    public List<ResourceListResDTO> transferResourceListResDTOList(final List<ResourceDTO> resourceDTOList) {
        if (Func.isEmpty(resourceDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        Map<String, ResourceListResDTO> resourceListResDTOMap = Maps.newHashMap();
        if (Func.isNotEmpty(resourceDTOList)) {
            for (ResourceDTO resourceDTO : resourceDTOList) {
                ResourceListResDTO resourceListResDTO = new ResourceListResDTO();
                resourceListResDTO.setId(resourceDTO.getId());
                resourceListResDTO.setTitle(resourceDTO.getName());
                resourceListResDTO.setPid(resourceDTO.getPid());
                resourceListResDTOMap.put(resourceDTO.getId(), resourceListResDTO);
            }
        }

        List<ResourceListResDTO> resourceListResDTOList = Lists.newArrayList();
        for (ResourceDTO resourceDTO : resourceDTOList) {
            ResourceListResDTO resourceListResDTO = resourceListResDTOMap.get(resourceDTO.getId());
            if (StrUtil.isBlank(resourceDTO.getPid())) {
                resourceListResDTOList.add(resourceListResDTO);
            } else {
                ResourceListResDTO parentResourceListResDTO = resourceListResDTOMap.get(resourceDTO.getPid());
                List<ResourceListResDTO> children = parentResourceListResDTO.getChildren();
                if (children == null) {
                    children = Lists.newArrayList();
                    parentResourceListResDTO.setChildren(children);
                }
                children.add(resourceListResDTO);
            }
        }

        return resourceListResDTOList;
    }

    public ResourceShowResDTO transferResourceShowResDTO(final ResourceDTO resourceDTO, final ResourceDTO parentResourceDTO) {
        ResourceShowResDTO resourceShowResDTO = converter.convertToResourceShowResDTO(resourceDTO);

        if (Func.isNotEmpty(parentResourceDTO)) {
            resourceShowResDTO.setPname(parentResourceDTO.getName());
        }

        return resourceShowResDTO;
    }

    public QueryWrapper<ResourcePO> transferResourceQueryWrapper(final ResourceAddReqDTO resourceAddReqDTO) {
        QueryWrapper<ResourcePO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(resourceAddReqDTO.getCode())) {
            queryWrapper.eq(ResourcePO.DB_COL_CODE, resourceAddReqDTO.getCode());
        }

        return queryWrapper;
    }

    public QueryWrapper<ResourcePO> transferResourceQueryWrapper(final ResourceModifyReqDTO resourceModifyReqDTO) {
        QueryWrapper<ResourcePO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(resourceModifyReqDTO.getCode())) {
            queryWrapper.eq(ResourcePO.DB_COL_CODE, resourceModifyReqDTO.getCode());
        }

        queryWrapper.ne(ResourcePO.DB_COL_ID, resourceModifyReqDTO.getId());

        return queryWrapper;
    }

    public ResourceListResDTO transferResourceListResDTO(final ResourceDTO resourceDTO) {
        return converter.convertToResourceListResDTO(resourceDTO);
    }

    public Page<ResourcePageResDTO> transferResourcePageResDTOPage(final Page<ResourceDTO> resourceDTOPage) {
        if (Func.isNotEmpty(resourceDTOPage) && Func.isNotEmpty(resourceDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToResourcePageResDTOPage(resourceDTOPage);
    }

    public List<ResourceShowResDTO> transferResourceShowResDTOList(final List<ResourceDTO> resourceDTOList) {
        if (Func.isEmpty(resourceDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToResourceShowResDTOList(resourceDTOList);
    }

    public List<UserMenuOperationDTO> transferUserMenuOperationDTOList(final List<ResourceDTO> resourceDTOList, final ResourceDTO menuResourceDTO) {
        List<UserMenuOperationDTO> userMenuOperationDTOList = Lists.newArrayList();

        if (Func.isNotEmpty(resourceDTOList)) {
            for (ResourceDTO resourceDTO : resourceDTOList) {
                if (resourceDTO.getPid().equals(menuResourceDTO.getId())) {
                    userMenuOperationDTOList.add(converter.convertToUserMenuOperationDTO(resourceDTO));
                }
            }
        }

        return userMenuOperationDTOList;
    }
}

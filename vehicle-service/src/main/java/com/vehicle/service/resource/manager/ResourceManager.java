package com.vehicle.service.resource.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.vehicle.dao.resource.ResourceDAO;
import com.vehicle.dao.resource.po.ResourcePO;
import com.vehicle.service.resource.converter.ResourceConverter;
import com.vehicle.service.resource.domain.ResourceDO;
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
import com.vehicle.service.roleresource.manager.RoleResourceManager;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.manager.UserRoleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class ResourceManager extends BaseManagerImpl<ResourceDAO, ResourcePO, ResourceDTO, ResourceConverter> {

    public static ResourceManager me() {
        return SpringUtil.getBean(ResourceManager.class);
    }

    public List<ResourceListResDTO> list(final ResourceListReqDTO resourceListReqDTO) {
        ResourceDTO paramsDTO = ResourceDO.me().buildListParamsDTO(resourceListReqDTO);

        List<ResourceDTO> resourceDTOList = super.findList(paramsDTO);

        return ResourceDO.me().transferResourceListResDTOList(resourceDTOList);
    }

    public ResourceListResDTO listOne(final ResourceListReqDTO resourceListReqDTO) {
        ResourceDTO paramsDTO = ResourceDO.me().buildListParamsDTO(resourceListReqDTO);

        ResourceDTO resourceDTO = super.findOne(paramsDTO);
        ResourceDO.me().checkResourceDTO(resourceDTO);

        return ResourceDO.me().transferResourceListResDTO(resourceDTO);
    }

    public Page<ResourcePageResDTO> pagination(final ResourcePageReqDTO resourcePageReqDTO, final Integer current, final Integer size) {
        ResourceDTO paramsDTO = ResourceDO.me().buildPageParamsDTO(resourcePageReqDTO);

        Page<ResourceDTO> resourceDTOPage = super.findPage(paramsDTO, current, size);

        return ResourceDO.me().transferResourcePageResDTOPage(resourceDTOPage);
    }

    public Boolean add(final ResourceAddReqDTO resourceAddReqDTO) {
        ResourceDO.me().checkResourceAddReqDTO(resourceAddReqDTO);

        QueryWrapper<ResourcePO> queryWrapper = ResourceDO.me().transferResourceQueryWrapper(resourceAddReqDTO);
        Integer count = ResourceManager.me().findCount(queryWrapper);

        ResourceDO.me().checkResultCount(count);

        ResourceDTO addResourceDTO = ResourceDO.me().buildAddResourceDTO(resourceAddReqDTO);

        return super.saveDTO(addResourceDTO);
    }

    public Boolean addAllColumn(final ResourceAddReqDTO resourceAddReqDTO) {
        ResourceDO.me().checkResourceAddReqDTO(resourceAddReqDTO);

        ResourceDTO addResourceDTO = ResourceDO.me().buildAddResourceDTO(resourceAddReqDTO);

        return super.saveAllColumn(addResourceDTO);
    }

    public Boolean addBatchAllColumn(final List<ResourceAddReqDTO> resourceAddReqDTOList) {
        ResourceDO.me().checkResourceAddReqDTOList(resourceAddReqDTOList);

        List<ResourceDTO> addBatchResourceDTOList = ResourceDO.me().buildAddBatchResourceDTOList(resourceAddReqDTOList);

        return super.saveBatchAllColumn(addBatchResourceDTOList);
    }

    public ResourceShowResDTO show(final String id) {
        ResourceDTO resourceDTO = super.findById(id);

        ResourceDO.me().checkResourceDTO(resourceDTO);

        ResourceDTO parentResourceDTO = super.findById(resourceDTO.getPid());
        return ResourceDO.me().transferResourceShowResDTO(resourceDTO, parentResourceDTO);
    }

    public List<ResourceShowResDTO> showByIds(final List<String> ids) {
        ResourceDO.me().checkIds(ids);

        List<ResourceDTO> resourceDTOList = super.findBatchIds(ids);

        return ResourceDO.me().transferResourceShowResDTOList(resourceDTOList);
    }

    public Boolean modify(final ResourceModifyReqDTO resourceModifyReqDTO) {
        ResourceDO.me().checkResourceModifyReqDTO(resourceModifyReqDTO);

        QueryWrapper<ResourcePO> queryWrapper = ResourceDO.me().transferResourceQueryWrapper(resourceModifyReqDTO);
        Integer count = ResourceManager.me().findCount(queryWrapper);

        ResourceDO.me().checkResultCount(count);

        ResourceDTO modifyResourceDTO = ResourceDO.me().buildModifyResourceDTO(resourceModifyReqDTO);

        return super.modifyById(modifyResourceDTO);
    }

    public Boolean modifyAllColumn(final ResourceModifyReqDTO resourceModifyReqDTO) {
        ResourceDO.me().checkResourceModifyReqDTO(resourceModifyReqDTO);

        ResourceDTO modifyResourceDTO = ResourceDO.me().buildModifyResourceDTO(resourceModifyReqDTO);

        return super.modifyAllColumnById(modifyResourceDTO);
    }

    public Boolean removeByParams(final ResourceRemoveReqDTO resourceRemoveReqDTO) {
        ResourceDO.me().checkResourceRemoveReqDTO(resourceRemoveReqDTO);

        ResourceDTO removeResourceDTO = ResourceDO.me().buildRemoveResourceDTO(resourceRemoveReqDTO);

        return super.remove(removeResourceDTO);
    }

    public List<UserMenuResourceDTO> listUserMenu() {
        List<ResourceDTO> resourceDTOList = findUserResourceDTOList();

        return ResourceDO.me().transferUserMenuResourceDTOList(resourceDTOList);
    }

    private List<ResourceDTO> findUserResourceDTOList() {
        UserRoleDTO userRoleDTO = ResourceDO.me().buildUserRoleDTO(getCurrentUser().getId());

        List<UserRoleDTO> userRoleDTOList = UserRoleManager.me().findList(userRoleDTO);
        List<String> roleIdList = ResourceDO.me().buildUserRoleIdList(userRoleDTOList);

        List<ResourceDTO> resourceDTOList = Lists.newArrayList();
        if (Func.isNotEmpty(roleIdList)) {
            List<RoleResourceDTO> roleResourceDTOList = RoleResourceManager.me().findByRoleIds(roleIdList);
            List<String> resourceIdList = ResourceDO.me().buildUserResourceIdList(roleResourceDTOList);
            if (Func.isNotEmpty(resourceIdList)) {
                resourceDTOList = super.findBatchIds(resourceIdList);
            }
        }

        return resourceDTOList;
    }

    public List<UserMenuOperationDTO> listUserMenuOperation(final String menuPath) {
        ResourceDTO paramsDTO = ResourceDO.me().buildMenuResourceParamDTO(menuPath);

        ResourceDTO menuResourceDTO = super.findOne(paramsDTO);

        ResourceDO.me().checkMenuResourceDTO(menuResourceDTO);

        List<ResourceDTO> resourceDTOList = findUserResourceDTOList();

        return ResourceDO.me().transferUserMenuOperationDTOList(resourceDTOList, menuResourceDTO);
    }

    @Override
    public Boolean removeById(final String id) {
        QueryWrapper<ResourcePO> parentResourceWrapper = ResourceDO.me().buildParentQueryWrapper(id);

        if (Func.isNotEmpty(parentResourceWrapper)) {
            List<ResourceDTO> subResourceDTOList = super.findList(parentResourceWrapper);
            ResourceDO.me().checkSubResourceDTOList(subResourceDTOList);
        }

        return super.removeById(id);
    }

    @Override
    protected ResourcePO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new ResourcePO();
        }

        return BeanUtil.toBean(map, ResourcePO.class);
    }

    @Override
    protected ResourceDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new ResourceDTO();
        }

        return BeanUtil.toBean(map, ResourceDTO.class);
    }
}

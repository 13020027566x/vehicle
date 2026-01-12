package com.vehicle.service.resource;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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

import java.util.List;

/**
 * 菜单 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface ResourceService extends BaseService<ResourceDTO> {

    static ResourceService me() {
        return SpringUtil.getBean(ResourceService.class);
    }

    /**
     * 列表
     *
     * @param resourceListReqDTO 入参DTO
     * @return
     */
    List<ResourceListResDTO> list(ResourceListReqDTO resourceListReqDTO);

    /**
     * First查询
     *
     * @param resourceListReqDTO 入参DTO
     * @return
     */
    ResourceListResDTO listOne(ResourceListReqDTO resourceListReqDTO);

    /**
     * 分页
     *
     * @param resourcePageReqDTO 入参DTO
     * @param current            当前页
     * @param size               每页大小
     * @return
     */
    Page<ResourcePageResDTO> pagination(ResourcePageReqDTO resourcePageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param resourceAddReqDTO 入参DTO
     * @return
     */
    Boolean add(ResourceAddReqDTO resourceAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param resourceAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(ResourceAddReqDTO resourceAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param resourceAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<ResourceAddReqDTO> resourceAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    ResourceShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<ResourceShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param resourceModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(ResourceModifyReqDTO resourceModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param resourceModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(ResourceModifyReqDTO resourceModifyReqDTO);

    /**
     * 参数删除
     *
     * @param resourceRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(ResourceRemoveReqDTO resourceRemoveReqDTO);

    /**
     * 获取当前用户的菜单权限
     *
     * @return
     */
    List<UserMenuResourceDTO> listUserMenu();

    /**
     * 获取菜单页面操作集合
     *
     * @param menuId
     * @return
     */
    List<UserMenuOperationDTO> listUserMenuOperation(String menuId);
}

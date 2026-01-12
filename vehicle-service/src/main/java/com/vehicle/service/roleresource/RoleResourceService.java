package com.vehicle.service.roleresource;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;

import java.util.List;

/**
 * 角色资源 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface RoleResourceService extends BaseService<RoleResourceDTO> {

    static RoleResourceService me() {
        return SpringUtil.getBean(RoleResourceService.class);
    }

    /**
     * 列表
     *
     * @param roleresourceListReqDTO 入参DTO
     * @return
     */
    List<RoleResourceListResDTO> list(RoleResourceListReqDTO roleresourceListReqDTO);

    /**
     * First查询
     *
     * @param roleresourceListReqDTO 入参DTO
     * @return
     */
    RoleResourceListResDTO listOne(RoleResourceListReqDTO roleresourceListReqDTO);

    /**
     * 分页
     *
     * @param roleresourcePageReqDTO 入参DTO
     * @param current                当前页
     * @param size                   每页大小
     * @return
     */
    Page<RoleResourcePageResDTO> pagination(RoleResourcePageReqDTO roleresourcePageReqDTO, Integer current,
        Integer size);

    /**
     * 新增
     *
     * @param roleresourceAddReqDTO 入参DTO
     * @return
     */
    Boolean add(RoleResourceAddReqDTO roleresourceAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param roleresourceAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(RoleResourceAddReqDTO roleresourceAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param roleresourceAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<RoleResourceAddReqDTO> roleresourceAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    RoleResourceShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<RoleResourceShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param roleresourceModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(RoleResourceModifyReqDTO roleresourceModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param roleresourceModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(RoleResourceModifyReqDTO roleresourceModifyReqDTO);

    /**
     * 参数删除
     *
     * @param roleresourceRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(RoleResourceRemoveReqDTO roleresourceRemoveReqDTO);

    /**
     * findByRoleIds
     *
     * @param roleIdList
     * @return
     */
    List<RoleResourceDTO> findByRoleIds(List<String> roleIdList);
}

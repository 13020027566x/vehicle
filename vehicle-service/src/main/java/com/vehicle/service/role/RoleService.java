package com.vehicle.service.role;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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

import java.util.List;

/**
 * 角色 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface RoleService extends BaseService<RoleDTO> {

    static RoleService me() {
        return SpringUtil.getBean(RoleService.class);
    }

    /**
     * 列表
     *
     * @param roleListReqDTO 入参DTO
     * @return
     */
    List<RoleListResDTO> list(RoleListReqDTO roleListReqDTO);

    /**
     * First查询
     *
     * @param roleListReqDTO 入参DTO
     * @return
     */
    RoleListResDTO listOne(RoleListReqDTO roleListReqDTO);

    /**
     * 分页
     *
     * @param rolePageReqDTO 入参DTO
     * @param current        当前页
     * @param size           每页大小
     * @return
     */
    Page<RolePageResDTO> pagination(RolePageReqDTO rolePageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param roleAddReqDTO 入参DTO
     * @return
     */
    Boolean add(RoleAddReqDTO roleAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param roleAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(RoleAddReqDTO roleAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param roleAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<RoleAddReqDTO> roleAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    RoleShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<RoleShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param roleModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(RoleModifyReqDTO roleModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param roleModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(RoleModifyReqDTO roleModifyReqDTO);

    /**
     * 参数删除
     *
     * @param roleRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(RoleRemoveReqDTO roleRemoveReqDTO);

    /**
     * 获取角色权限列表
     *
     * @param roleListPermissionReqDTO
     * @return
     */
    List<RoleListPermissionResDTO> listPermission(RoleListPermissionReqDTO roleListPermissionReqDTO);

    /**
     * 修改角色权限
     *
     * @param roleModifyPermissionReqDTO
     * @return
     */
    Boolean modifyPermission(RoleModifyPermissionReqDTO roleModifyPermissionReqDTO);
}

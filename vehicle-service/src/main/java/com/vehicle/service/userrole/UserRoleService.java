package com.vehicle.service.userrole;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;

import java.util.List;

/**
 * 用户角色 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface UserRoleService extends BaseService<UserRoleDTO> {

    static UserRoleService me() {
        return SpringUtil.getBean(UserRoleService.class);
    }

    /**
     * 列表
     *
     * @param userroleListReqDTO 入参DTO
     * @return
     */
    List<UserRoleListResDTO> list(UserRoleListReqDTO userroleListReqDTO);

    /**
     * First查询
     *
     * @param userroleListReqDTO 入参DTO
     * @return
     */
    UserRoleListResDTO listOne(UserRoleListReqDTO userroleListReqDTO);

    /**
     * 分页
     *
     * @param userrolePageReqDTO 入参DTO
     * @param current            当前页
     * @param size               每页大小
     * @return
     */
    Page<UserRolePageResDTO> pagination(UserRolePageReqDTO userrolePageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param userroleAddReqDTO 入参DTO
     * @return
     */
    Boolean add(UserRoleAddReqDTO userroleAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param userroleAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(UserRoleAddReqDTO userroleAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param userroleAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<UserRoleAddReqDTO> userroleAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    UserRoleShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<UserRoleShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param userroleModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(UserRoleModifyReqDTO userroleModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param userroleModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(UserRoleModifyReqDTO userroleModifyReqDTO);

    /**
     * 参数删除
     *
     * @param userroleRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(UserRoleRemoveReqDTO userroleRemoveReqDTO);
}

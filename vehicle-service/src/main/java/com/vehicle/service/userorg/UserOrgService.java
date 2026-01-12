package com.vehicle.service.userorg;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;

import java.util.List;

/**
 * 用户机构 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface UserOrgService extends BaseService<UserOrgDTO> {

    static UserOrgService me() {
        return SpringUtil.getBean(UserOrgService.class);
    }

    /**
     * 列表
     *
     * @param userorgListReqDTO 入参DTO
     * @return
     */
    List<UserOrgListResDTO> list(UserOrgListReqDTO userorgListReqDTO);

    /**
     * First查询
     *
     * @param userorgListReqDTO 入参DTO
     * @return
     */
    UserOrgListResDTO listOne(UserOrgListReqDTO userorgListReqDTO);

    /**
     * 分页
     *
     * @param userorgPageReqDTO 入参DTO
     * @param current           当前页
     * @param size              每页大小
     * @return
     */
    Page<UserOrgPageResDTO> pagination(UserOrgPageReqDTO userorgPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param userorgAddReqDTO 入参DTO
     * @return
     */
    Boolean add(UserOrgAddReqDTO userorgAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param userorgAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(UserOrgAddReqDTO userorgAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param userorgAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<UserOrgAddReqDTO> userorgAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    UserOrgShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<UserOrgShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param userorgModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(UserOrgModifyReqDTO userorgModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param userorgModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(UserOrgModifyReqDTO userorgModifyReqDTO);

    /**
     * 参数删除
     *
     * @param userorgRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(UserOrgRemoveReqDTO userorgRemoveReqDTO);
}

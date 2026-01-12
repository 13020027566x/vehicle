package com.vehicle.service.organization;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;

import java.util.List;

/**
 * 机构 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface OrganizationService extends BaseService<OrganizationDTO> {

    static OrganizationService me() {
        return SpringUtil.getBean(OrganizationService.class);
    }

    /**
     * 列表
     *
     * @param organizationListReqDTO 入参DTO
     * @return
     */
    List<OrganizationListResDTO> list(OrganizationListReqDTO organizationListReqDTO);

    /**
     * First查询
     *
     * @param organizationListReqDTO 入参DTO
     * @return
     */
    OrganizationListResDTO listOne(OrganizationListReqDTO organizationListReqDTO);

    /**
     * 分页
     *
     * @param organizationPageReqDTO 入参DTO
     * @param current                当前页
     * @param size                   每页大小
     * @return
     */
    Page<OrganizationPageResDTO> pagination(OrganizationPageReqDTO organizationPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param organizationAddReqDTO 入参DTO
     * @return
     */
    Boolean add(OrganizationAddReqDTO organizationAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param organizationAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(OrganizationAddReqDTO organizationAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param organizationAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<OrganizationAddReqDTO> organizationAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    OrganizationShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<OrganizationShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param organizationModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(OrganizationModifyReqDTO organizationModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param organizationModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(OrganizationModifyReqDTO organizationModifyReqDTO);

    /**
     * 参数删除
     *
     * @param organizationRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(OrganizationRemoveReqDTO organizationRemoveReqDTO);
}

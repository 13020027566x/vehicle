package com.vehicle.service.organization.domain;

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
import com.vehicle.dao.organization.po.OrganizationPO;
import com.vehicle.service.organization.converter.OrganizationConverter;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 机构 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class OrganizationDO extends BaseDO<OrganizationDTO, OrganizationPO, OrganizationConverter> {

    public static OrganizationDO me() {
        return SpringUtil.getBean(OrganizationDO.class);
    }

    public void checkOrganizationModifyReqDTO(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        if (Func.isEmpty(organizationModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkOrganizationAddReqDTO(final OrganizationAddReqDTO organizationAddReqDTO) {
        if (Func.isEmpty(organizationAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkResultCount(final Integer count) {
        if (!Func.isNullOrZero(count)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "名称或编码已存在");
        }
    }

    public void checkOrganizationAddReqDTOList(final List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        if (Func.isEmpty(organizationAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkOrganizationDTO(final OrganizationDTO organizationDTO) {
        if (Func.isEmpty(organizationDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkOrganizationRemoveReqDTO(final OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        if (Func.isEmpty(organizationRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkSubOrganizationDTOList(final List<OrganizationDTO> subOrganizationDTOList) {
        if (Func.isNotEmpty(subOrganizationDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "请先删除子机构");
        }
    }

    public QueryWrapper<OrganizationPO> buildOrganizationQueryWrapper(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        QueryWrapper<OrganizationPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(OrganizationPO.DB_COL_ID, organizationModifyReqDTO.getId());

        if (Func.isNotEmpty(organizationModifyReqDTO.getName()) && Func.isNotEmpty(organizationModifyReqDTO.getCode())) {
            queryWrapper.and(i -> i.eq(OrganizationPO.DB_COL_NAME, organizationModifyReqDTO.getName()).or().eq(
                OrganizationPO.DB_COL_CODE, organizationModifyReqDTO.getCode()));
            return queryWrapper;
        }

        if (Func.isNotEmpty(organizationModifyReqDTO.getName())) {
            queryWrapper.eq(OrganizationPO.DB_COL_NAME, organizationModifyReqDTO.getName());
        }

        if (Func.isNotEmpty(organizationModifyReqDTO.getCode())) {
            queryWrapper.eq(OrganizationPO.DB_COL_CODE, organizationModifyReqDTO.getCode());
        }

        return queryWrapper;
    }

    public QueryWrapper<OrganizationPO> buildOrganizationQueryWrapper(final OrganizationAddReqDTO organizationAddReqDTO) {
        QueryWrapper<OrganizationPO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(organizationAddReqDTO.getName()) && Func.isNotEmpty(organizationAddReqDTO.getCode())) {
            queryWrapper.and(i -> i.eq(OrganizationPO.DB_COL_NAME, organizationAddReqDTO.getName()).or().eq(
                OrganizationPO.DB_COL_CODE, organizationAddReqDTO.getCode()));
            return queryWrapper;
        }

        if (Func.isNotEmpty(organizationAddReqDTO.getName())) {
            queryWrapper.eq(OrganizationPO.DB_COL_NAME, organizationAddReqDTO.getName());
        }

        if (Func.isNotEmpty(organizationAddReqDTO.getCode())) {
            queryWrapper.eq(OrganizationPO.DB_COL_CODE, organizationAddReqDTO.getCode());
        }

        return queryWrapper;
    }

    public QueryWrapper<OrganizationPO> buildParentQueryWrapper(final String pid) {
        QueryWrapper<OrganizationPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(OrganizationPO.DB_COL_PID, pid);

        return queryWrapper;
    }

    public OrganizationDTO buildListParamsDTO(final OrganizationListReqDTO organizationListReqDTO) {
        return converter.convertToOrganizationDTO(organizationListReqDTO);
    }

    public OrganizationDTO buildPageParamsDTO(final OrganizationPageReqDTO organizationPageReqDTO) {
        return converter.convertToOrganizationDTO(organizationPageReqDTO);
    }

    public OrganizationDTO buildAddOrganizationDTO(final OrganizationAddReqDTO organizationAddReqDTO) {
        return converter.convertToOrganizationDTO(organizationAddReqDTO);
    }

    public List<OrganizationDTO> buildAddBatchOrganizationDTOList(final List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        return converter.convertToOrganizationDTOList(organizationAddReqDTOList);
    }

    public OrganizationDTO buildModifyOrganizationDTO(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        return converter.convertToOrganizationDTO(organizationModifyReqDTO);
    }

    public OrganizationDTO buildRemoveOrganizationDTO(final OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        return converter.convertToOrganizationDTO(organizationRemoveReqDTO);
    }

    public List<OrganizationListResDTO> transferOrganizationListResDTOList(final List<OrganizationDTO> organizationDTOList) {
        if (Func.isEmpty(organizationDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        Map<String, OrganizationListResDTO> organizationListResDTOMap = Maps.newHashMap();
        if (Func.isNotEmpty(organizationDTOList)) {
            for (OrganizationDTO organizationDTO : organizationDTOList) {
                OrganizationListResDTO organizationListResDTO = new OrganizationListResDTO();
                organizationListResDTO.setId(organizationDTO.getId());
                organizationListResDTO.setTitle(organizationDTO.getName());
                organizationListResDTO.setPid(organizationDTO.getPid());
                organizationListResDTOMap.put(organizationDTO.getId(), organizationListResDTO);
            }
        }

        List<OrganizationListResDTO> organizationListResDTOList = Lists.newArrayList();
        for (OrganizationDTO organizationDTO : organizationDTOList) {
            OrganizationListResDTO organizationListResDTO = organizationListResDTOMap.get(organizationDTO.getId());
            if (StrUtil.isBlank(organizationDTO.getPid())) {
                organizationListResDTOList.add(organizationListResDTO);
            } else {
                OrganizationListResDTO parentOrganizationListResDTO = organizationListResDTOMap.get(organizationDTO.getPid());
                List<OrganizationListResDTO> children = parentOrganizationListResDTO.getChildren();
                if (children == null) {
                    children = Lists.newArrayList();
                    parentOrganizationListResDTO.setChildren(children);
                }
                children.add(organizationListResDTO);
            }
        }

        return organizationListResDTOList;
    }

    public OrganizationShowResDTO transferOrganizationShowResDTO(final OrganizationDTO organizationDTO,
        final OrganizationDTO parentOrganizationDTO) {
        OrganizationShowResDTO organizationShowResDTO = converter.convertToOrganizationShowResDTO(organizationDTO);

        if (Func.isNotEmpty(parentOrganizationDTO)) {
            organizationShowResDTO.setPname(parentOrganizationDTO.getName());
        }

        return organizationShowResDTO;
    }

    public OrganizationListResDTO transferOrganizationListResDTO(final OrganizationDTO organizationDTO) {
        if (Func.isEmpty(organizationDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToOrganizationListResDTO(organizationDTO);
    }

    public Page<OrganizationPageResDTO> transferOrganizationPageResDTOPage(final Page<OrganizationDTO> organizationDTOPage) {
        if (Func.isEmpty(organizationDTOPage) || Func.isEmpty(organizationDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToOrganizationPageResDTOPage(organizationDTOPage);
    }

    public List<OrganizationShowResDTO> transferOrganizationShowResDTOList(final List<OrganizationDTO> organizationDTOList) {
        if (Func.isEmpty(organizationDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToOrganizationShowResDTOList(organizationDTOList);
    }
}

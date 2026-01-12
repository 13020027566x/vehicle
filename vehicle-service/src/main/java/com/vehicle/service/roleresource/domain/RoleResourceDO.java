package com.vehicle.service.roleresource.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.roleresource.po.RoleResourcePO;
import com.vehicle.service.roleresource.converter.RoleResourceConverter;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色资源 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class RoleResourceDO extends BaseDO<RoleResourceDTO, RoleResourcePO, RoleResourceConverter> {

    public static RoleResourceDO me() {
        return SpringUtil.getBean(RoleResourceDO.class);
    }

    public void checkRoleResourceAddReqDTO(final RoleResourceAddReqDTO roleResourceAddReqDTO) {
        if (Func.isEmpty(roleResourceAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkRoleResourceAddReqDTOList(final List<RoleResourceAddReqDTO> roleResourceAddReqDTOList) {
        if (Func.isEmpty(roleResourceAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkRoleResourceModifyReqDTO(final RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        if (Func.isEmpty(roleResourceModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkRoleResourceRemoveReqDTO(final RoleResourceRemoveReqDTO roleResourceRemoveReqDTO) {
        if (Func.isEmpty(roleResourceRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkRoleIdList(final List<String> roleIdList) {
        if (Func.isEmpty(roleIdList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public RoleResourceDTO buildListParamsDTO(final RoleResourceListReqDTO roleResourceListReqDTO) {
        return converter.convertToRoleResourceDTO(roleResourceListReqDTO);
    }

    public RoleResourceDTO buildPageParamsDTO(final RoleResourcePageReqDTO roleResourcePageReqDTO) {
        return converter.convertToRoleResourceDTO(roleResourcePageReqDTO);
    }

    public RoleResourceDTO buildAddRoleResourceDTO(final RoleResourceAddReqDTO roleResourceAddReqDTO) {
        return converter.convertToRoleResourceDTO(roleResourceAddReqDTO);
    }

    public List<RoleResourceDTO> buildAddBatchRoleResourceDTO(final List<RoleResourceAddReqDTO> roleResourceAddReqDTOList) {
        return converter.convertToRoleResourceDTOList(roleResourceAddReqDTOList);
    }

    public RoleResourceDTO buildModifyRoleResourceDTO(final RoleResourceModifyReqDTO roleResourceModifyReqDTO) {
        return converter.convertToRoleResourceDTO(roleResourceModifyReqDTO);
    }

    public RoleResourceDTO buildRemoveRoleResourceDTO(final RoleResourceRemoveReqDTO roleResourceRemoveReqDTO) {
        return converter.convertToRoleResourceDTO(roleResourceRemoveReqDTO);
    }

    public List<RoleResourceListResDTO> transferRoleResourceListResDTOList(final List<RoleResourceDTO> roleResourceDTOList) {
        if (Func.isEmpty(roleResourceDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleResourceListResDTOList(roleResourceDTOList);
    }

    public RoleResourceListResDTO transferRoleResourceListResDTO(final RoleResourceDTO roleResourceDTO) {
        if (Func.isEmpty(roleResourceDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleResourceListResDTO(roleResourceDTO);
    }

    public Page<RoleResourcePageResDTO> transferRoleResourcePageResDTOPage(final Page<RoleResourceDTO> roleResourceDTOPage) {
        if (Func.isEmpty(roleResourceDTOPage) || Func.isEmpty(roleResourceDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleResourcePageResDTOPage(roleResourceDTOPage);
    }

    public RoleResourceShowResDTO transferRoleResourceShowResDTO(final RoleResourceDTO roleResourceDTO) {
        if (Func.isEmpty(roleResourceDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleResourceShowResDTO(roleResourceDTO);
    }

    public List<RoleResourceShowResDTO> transferRoleResourceShowResDTOList(final List<RoleResourceDTO> roleResourceDTOList) {
        if (Func.isEmpty(roleResourceDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToRoleResourceShowResDTOList(roleResourceDTOList);
    }
}

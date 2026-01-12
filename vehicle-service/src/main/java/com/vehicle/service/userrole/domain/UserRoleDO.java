package com.vehicle.service.userrole.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userrole.po.UserRolePO;
import com.vehicle.service.userrole.converter.UserRoleConverter;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户角色 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserRoleDO extends BaseDO<UserRoleDTO, UserRolePO, UserRoleConverter> {

    public static UserRoleDO me() {
        return SpringUtil.getBean(UserRoleDO.class);
    }

    public void checkUserRoleAddReqDTO(final UserRoleAddReqDTO userRoleAddReqDTO) {
        if (Func.isEmpty(userRoleAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserRoleAddReqDTOList(final List<UserRoleAddReqDTO> userRoleAddReqDTOList) {
        if (Func.isEmpty(userRoleAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkUserRoleModifyReqDTO(final UserRoleModifyReqDTO userRoleModifyReqDTO) {
        if (Func.isEmpty(userRoleModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserRoleRemoveReqDTO(final UserRoleRemoveReqDTO userRoleRemoveReqDTO) {
        if (Func.isEmpty(userRoleRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public UserRoleDTO buildListParamsDTO(final UserRoleListReqDTO userRoleListReqDTO) {
        return converter.convertToUserRoleDTO(userRoleListReqDTO);
    }

    public UserRoleDTO buildPageParamsDTO(final UserRolePageReqDTO userRolePageReqDTO) {
        return converter.convertToUserRoleDTO(userRolePageReqDTO);
    }

    public UserRoleDTO buildAddUserRoleDTO(final UserRoleAddReqDTO userRoleAddReqDTO) {
        return converter.convertToUserRoleDTO(userRoleAddReqDTO);
    }

    public List<UserRoleDTO> buildAddBatchUserRoleDTOList(final List<UserRoleAddReqDTO> userRoleAddReqDTOList) {
        return converter.convertToUserRoleDTOList(userRoleAddReqDTOList);
    }

    public UserRoleDTO buildModifyUserRoleDTO(final UserRoleModifyReqDTO userRoleModifyReqDTO) {
        return converter.convertToUserRoleDTO(userRoleModifyReqDTO);
    }

    public UserRoleDTO buildRemoveUserRoleDTO(final UserRoleRemoveReqDTO userRoleRemoveReqDTO) {
        return converter.convertToUserRoleDTO(userRoleRemoveReqDTO);
    }

    public List<UserRoleListResDTO> transferUserRoleListResDTOList(final List<UserRoleDTO> userRoleDTOList) {
        if (Func.isEmpty(userRoleDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserRoleListResDTOList(userRoleDTOList);
    }

    public UserRoleListResDTO transferUserRoleListResDTO(final UserRoleDTO userRoleDTO) {
        if (Func.isEmpty(userRoleDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserRoleListResDTO(userRoleDTO);
    }

    public Page<UserRolePageResDTO> transferUserRolePageResDTOPage(final Page<UserRoleDTO> userRoleDTOPage) {
        if (Func.isEmpty(userRoleDTOPage) || Func.isEmpty(userRoleDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserRolePageResDTOPage(userRoleDTOPage);
    }

    public UserRoleShowResDTO transferUserRoleShowResDTO(final UserRoleDTO userRoleDTO) {
        if (Func.isEmpty(userRoleDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserRoleShowResDTO(userRoleDTO);
    }

    public List<UserRoleShowResDTO> transferUserRoleShowResDTOList(final List<UserRoleDTO> userRoleDTOList) {
        if (Func.isEmpty(userRoleDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserRoleShowResDTOList(userRoleDTOList);
    }
}

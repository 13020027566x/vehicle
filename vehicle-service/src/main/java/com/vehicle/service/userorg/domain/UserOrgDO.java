package com.vehicle.service.userorg.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userorg.po.UserOrgPO;
import com.vehicle.service.userorg.converter.UserOrgConverter;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户机构 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class UserOrgDO extends BaseDO<UserOrgDTO, UserOrgPO, UserOrgConverter> {

    public static UserOrgDO me() {
        return SpringUtil.getBean(UserOrgDO.class);
    }

    public void checkUserOrgAddReqDTO(final UserOrgAddReqDTO userOrgAddReqDTO) {
        if (Func.isEmpty(userOrgAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserOrgAddReqDTOList(final List<UserOrgAddReqDTO> userOrgAddReqDTOList) {
        if (Func.isEmpty(userOrgAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkUserOrgModifyReqDTO(final UserOrgModifyReqDTO userOrgModifyReqDTO) {
        if (Func.isEmpty(userOrgModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkUserOrgRemoveReqDTO(final UserOrgRemoveReqDTO userOrgRemoveReqDTO) {
        if (Func.isEmpty(userOrgRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public UserOrgDTO buildListParamsDTO(final UserOrgListReqDTO userOrgListReqDTO) {
        return converter.convertToUserOrgDTO(userOrgListReqDTO);
    }

    public UserOrgDTO buildPageParamsDTO(final UserOrgPageReqDTO userOrgPageReqDTO) {
        return converter.convertToUserOrgDTO(userOrgPageReqDTO);
    }

    public UserOrgDTO buildAddUserOrgDTO(final UserOrgAddReqDTO userOrgAddReqDTO) {
        return converter.convertToUserOrgDTO(userOrgAddReqDTO);
    }

    public List<UserOrgDTO> buildAddBatchUserOrgDTOList(final List<UserOrgAddReqDTO> userOrgAddReqDTOList) {
        return converter.convertToUserOrgDTOList(userOrgAddReqDTOList);
    }

    public UserOrgDTO buildModifyUserOrgDTO(final UserOrgModifyReqDTO userOrgModifyReqDTO) {
        return converter.convertToUserOrgDTO(userOrgModifyReqDTO);
    }

    public UserOrgDTO buildRemoveUserOrgDTO(final UserOrgRemoveReqDTO userOrgRemoveReqDTO) {
        return converter.convertToUserOrgDTO(userOrgRemoveReqDTO);
    }

    public List<UserOrgListResDTO> transferUserOrgListResDTOList(final List<UserOrgDTO> userOrgDTOList) {
        if (Func.isEmpty(userOrgDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserOrgListResDTOList(userOrgDTOList);
    }

    public UserOrgListResDTO transferUserOrgListResDTO(final UserOrgDTO userOrgDTO) {
        if (Func.isEmpty(userOrgDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserOrgListResDTO(userOrgDTO);
    }

    public Page<UserOrgPageResDTO> transferUserOrgPageResDTOPage(final Page<UserOrgDTO> userOrgDTOPage) {
        if (Func.isEmpty(userOrgDTOPage) || Func.isEmpty(userOrgDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserOrgPageResDTOPage(userOrgDTOPage);
    }

    public UserOrgShowResDTO transferUserOrgShowResDTO(final UserOrgDTO userOrgDTO) {
        if (Func.isEmpty(userOrgDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserOrgShowResDTO(userOrgDTO);
    }

    public List<UserOrgShowResDTO> transferUserOrgShowResDTOList(final List<UserOrgDTO> userOrgDTOList) {
        if (Func.isEmpty(userOrgDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToUserOrgShowResDTOList(userOrgDTOList);
    }
}

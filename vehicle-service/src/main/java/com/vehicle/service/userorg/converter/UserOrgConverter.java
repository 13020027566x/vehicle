package com.vehicle.service.userorg.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.userorg.po.UserOrgPO;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface UserOrgConverter extends BaseConverter<UserOrgDTO, UserOrgPO> {

    static UserOrgConverter me() {
        return SpringUtil.getBean(UserOrgConverter.class);
    }

    UserOrgDTO convertToUserOrgDTO(UserOrgAddReqDTO userOrgAddReqDTO);

    UserOrgDTO convertToUserOrgDTO(UserOrgModifyReqDTO userOrgModifyReqDTO);

    UserOrgDTO convertToUserOrgDTO(UserOrgRemoveReqDTO userOrgRemoveReqDTO);

    UserOrgDTO convertToUserOrgDTO(UserOrgListReqDTO userOrgListReqDTO);

    UserOrgDTO convertToUserOrgDTO(UserOrgPageReqDTO userOrgPageReqDTO);

    UserOrgShowResDTO convertToUserOrgShowResDTO(UserOrgDTO userOrgDTO);

    List<UserOrgShowResDTO> convertToUserOrgShowResDTOList(List<UserOrgDTO> userOrgDTOList);

    UserOrgListResDTO convertToUserOrgListResDTO(UserOrgDTO userOrgDTO);

    List<UserOrgListResDTO> convertToUserOrgListResDTOList(List<UserOrgDTO> userOrgDTOList);

    List<UserOrgDTO> convertToUserOrgDTOList(List<UserOrgAddReqDTO> userOrgAddReqDTOList);

    UserOrgPageResDTO convertToUserOrgPageResDTO(UserOrgDTO userOrgDTO);

    List<UserOrgPageResDTO> convertToUserOrgPageResDTOList(List<UserOrgDTO> userOrgDTOList);

    Page<UserOrgPageResDTO> convertToUserOrgPageResDTOPage(Page<UserOrgDTO> userOrgDTOPage);
}

package com.vehicle.service.userorg.converter;

import com.finhub.framework.core.page.Page;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserOrgConverterImpl implements UserOrgConverter {

    @Override
    public UserOrgDTO poToDTO(UserOrgPO po) {
        if ( po == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setId( po.getId() );
        userOrgDTO.setIsDel( po.getIsDel() );
        userOrgDTO.setIsTest( po.getIsTest() );
        userOrgDTO.setCreateAt( po.getCreateAt() );
        userOrgDTO.setCreateTime( po.getCreateTime() );
        userOrgDTO.setCreateBy( po.getCreateBy() );
        userOrgDTO.setCreateName( po.getCreateName() );
        userOrgDTO.setUpdateAt( po.getUpdateAt() );
        userOrgDTO.setUpdateTime( po.getUpdateTime() );
        userOrgDTO.setUpdateBy( po.getUpdateBy() );
        userOrgDTO.setUpdateName( po.getUpdateName() );
        userOrgDTO.setOrgCode( po.getOrgCode() );
        userOrgDTO.setOrgId( po.getOrgId() );
        userOrgDTO.setOrgName( po.getOrgName() );
        userOrgDTO.setUserCode( po.getUserCode() );
        userOrgDTO.setUserId( po.getUserId() );
        userOrgDTO.setUserName( po.getUserName() );

        return userOrgDTO;
    }

    @Override
    public List<UserOrgDTO> poToDTOList(List<UserOrgPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserOrgDTO> list = new ArrayList<UserOrgDTO>( arg0.size() );
        for ( UserOrgPO userOrgPO : arg0 ) {
            list.add( poToDTO( userOrgPO ) );
        }

        return list;
    }

    @Override
    public UserOrgPO dtoToPO(UserOrgDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserOrgPO userOrgPO = new UserOrgPO();

        userOrgPO.setId( arg0.getId() );
        userOrgPO.setIsDel( arg0.getIsDel() );
        userOrgPO.setIsTest( arg0.getIsTest() );
        userOrgPO.setCreateAt( arg0.getCreateAt() );
        userOrgPO.setCreateTime( arg0.getCreateTime() );
        userOrgPO.setCreateBy( arg0.getCreateBy() );
        userOrgPO.setCreateName( arg0.getCreateName() );
        userOrgPO.setUpdateAt( arg0.getUpdateAt() );
        userOrgPO.setUpdateTime( arg0.getUpdateTime() );
        userOrgPO.setUpdateBy( arg0.getUpdateBy() );
        userOrgPO.setUpdateName( arg0.getUpdateName() );
        userOrgPO.setUserId( arg0.getUserId() );
        userOrgPO.setUserName( arg0.getUserName() );
        userOrgPO.setUserCode( arg0.getUserCode() );
        userOrgPO.setOrgId( arg0.getOrgId() );
        userOrgPO.setOrgName( arg0.getOrgName() );
        userOrgPO.setOrgCode( arg0.getOrgCode() );

        return userOrgPO;
    }

    @Override
    public List<UserOrgPO> dtoToPOList(List<UserOrgDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserOrgPO> list = new ArrayList<UserOrgPO>( arg0.size() );
        for ( UserOrgDTO userOrgDTO : arg0 ) {
            list.add( dtoToPO( userOrgDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(UserOrgDTO arg0, UserOrgPO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setUserId( arg0.getUserId() );
        arg1.setUserName( arg0.getUserName() );
        arg1.setUserCode( arg0.getUserCode() );
        arg1.setOrgId( arg0.getOrgId() );
        arg1.setOrgName( arg0.getOrgName() );
        arg1.setOrgCode( arg0.getOrgCode() );
    }

    @Override
    public void updateDto(UserOrgPO arg0, UserOrgDTO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setOrgCode( arg0.getOrgCode() );
        arg1.setOrgId( arg0.getOrgId() );
        arg1.setOrgName( arg0.getOrgName() );
        arg1.setUserCode( arg0.getUserCode() );
        arg1.setUserId( arg0.getUserId() );
        arg1.setUserName( arg0.getUserName() );
    }

    @Override
    public UserOrgDTO convertToUserOrgDTO(UserOrgAddReqDTO userOrgAddReqDTO) {
        if ( userOrgAddReqDTO == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setIsDel( userOrgAddReqDTO.getIsDel() );
        userOrgDTO.setIsTest( userOrgAddReqDTO.getIsTest() );
        userOrgDTO.setCreateAt( userOrgAddReqDTO.getCreateAt() );
        userOrgDTO.setCreateBy( userOrgAddReqDTO.getCreateBy() );
        userOrgDTO.setCreateName( userOrgAddReqDTO.getCreateName() );
        userOrgDTO.setUpdateAt( userOrgAddReqDTO.getUpdateAt() );
        userOrgDTO.setUpdateBy( userOrgAddReqDTO.getUpdateBy() );
        userOrgDTO.setUpdateName( userOrgAddReqDTO.getUpdateName() );
        userOrgDTO.setOrgCode( userOrgAddReqDTO.getOrgCode() );
        userOrgDTO.setOrgId( userOrgAddReqDTO.getOrgId() );
        userOrgDTO.setOrgName( userOrgAddReqDTO.getOrgName() );
        userOrgDTO.setUserCode( userOrgAddReqDTO.getUserCode() );
        userOrgDTO.setUserId( userOrgAddReqDTO.getUserId() );
        userOrgDTO.setUserName( userOrgAddReqDTO.getUserName() );

        return userOrgDTO;
    }

    @Override
    public UserOrgDTO convertToUserOrgDTO(UserOrgModifyReqDTO userOrgModifyReqDTO) {
        if ( userOrgModifyReqDTO == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setId( userOrgModifyReqDTO.getId() );
        userOrgDTO.setIsDel( userOrgModifyReqDTO.getIsDel() );
        userOrgDTO.setIsTest( userOrgModifyReqDTO.getIsTest() );
        userOrgDTO.setCreateAt( userOrgModifyReqDTO.getCreateAt() );
        userOrgDTO.setCreateBy( userOrgModifyReqDTO.getCreateBy() );
        userOrgDTO.setCreateName( userOrgModifyReqDTO.getCreateName() );
        userOrgDTO.setUpdateAt( userOrgModifyReqDTO.getUpdateAt() );
        userOrgDTO.setUpdateBy( userOrgModifyReqDTO.getUpdateBy() );
        userOrgDTO.setUpdateName( userOrgModifyReqDTO.getUpdateName() );
        userOrgDTO.setOrgCode( userOrgModifyReqDTO.getOrgCode() );
        userOrgDTO.setOrgId( userOrgModifyReqDTO.getOrgId() );
        userOrgDTO.setOrgName( userOrgModifyReqDTO.getOrgName() );
        userOrgDTO.setUserCode( userOrgModifyReqDTO.getUserCode() );
        userOrgDTO.setUserId( userOrgModifyReqDTO.getUserId() );
        userOrgDTO.setUserName( userOrgModifyReqDTO.getUserName() );

        return userOrgDTO;
    }

    @Override
    public UserOrgDTO convertToUserOrgDTO(UserOrgRemoveReqDTO userOrgRemoveReqDTO) {
        if ( userOrgRemoveReqDTO == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setId( userOrgRemoveReqDTO.getId() );
        userOrgDTO.setIsDel( userOrgRemoveReqDTO.getIsDel() );
        userOrgDTO.setIsTest( userOrgRemoveReqDTO.getIsTest() );
        userOrgDTO.setCreateAt( userOrgRemoveReqDTO.getCreateAt() );
        userOrgDTO.setCreateBy( userOrgRemoveReqDTO.getCreateBy() );
        userOrgDTO.setCreateName( userOrgRemoveReqDTO.getCreateName() );
        userOrgDTO.setUpdateAt( userOrgRemoveReqDTO.getUpdateAt() );
        userOrgDTO.setUpdateBy( userOrgRemoveReqDTO.getUpdateBy() );
        userOrgDTO.setUpdateName( userOrgRemoveReqDTO.getUpdateName() );
        userOrgDTO.setOrgCode( userOrgRemoveReqDTO.getOrgCode() );
        userOrgDTO.setOrgId( userOrgRemoveReqDTO.getOrgId() );
        userOrgDTO.setOrgName( userOrgRemoveReqDTO.getOrgName() );
        userOrgDTO.setUserCode( userOrgRemoveReqDTO.getUserCode() );
        userOrgDTO.setUserId( userOrgRemoveReqDTO.getUserId() );
        userOrgDTO.setUserName( userOrgRemoveReqDTO.getUserName() );

        return userOrgDTO;
    }

    @Override
    public UserOrgDTO convertToUserOrgDTO(UserOrgListReqDTO userOrgListReqDTO) {
        if ( userOrgListReqDTO == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setId( userOrgListReqDTO.getId() );
        userOrgDTO.setIsDel( userOrgListReqDTO.getIsDel() );
        userOrgDTO.setIsTest( userOrgListReqDTO.getIsTest() );
        userOrgDTO.setCreateAt( userOrgListReqDTO.getCreateAt() );
        userOrgDTO.setCreateBy( userOrgListReqDTO.getCreateBy() );
        userOrgDTO.setCreateName( userOrgListReqDTO.getCreateName() );
        userOrgDTO.setUpdateAt( userOrgListReqDTO.getUpdateAt() );
        userOrgDTO.setUpdateBy( userOrgListReqDTO.getUpdateBy() );
        userOrgDTO.setUpdateName( userOrgListReqDTO.getUpdateName() );
        userOrgDTO.setOrgCode( userOrgListReqDTO.getOrgCode() );
        userOrgDTO.setOrgId( userOrgListReqDTO.getOrgId() );
        userOrgDTO.setOrgName( userOrgListReqDTO.getOrgName() );
        userOrgDTO.setUserCode( userOrgListReqDTO.getUserCode() );
        userOrgDTO.setUserId( userOrgListReqDTO.getUserId() );
        userOrgDTO.setUserName( userOrgListReqDTO.getUserName() );

        return userOrgDTO;
    }

    @Override
    public UserOrgDTO convertToUserOrgDTO(UserOrgPageReqDTO userOrgPageReqDTO) {
        if ( userOrgPageReqDTO == null ) {
            return null;
        }

        UserOrgDTO userOrgDTO = new UserOrgDTO();

        userOrgDTO.setId( userOrgPageReqDTO.getId() );
        userOrgDTO.setIsDel( userOrgPageReqDTO.getIsDel() );
        userOrgDTO.setIsTest( userOrgPageReqDTO.getIsTest() );
        userOrgDTO.setCreateAt( userOrgPageReqDTO.getCreateAt() );
        userOrgDTO.setCreateBy( userOrgPageReqDTO.getCreateBy() );
        userOrgDTO.setCreateName( userOrgPageReqDTO.getCreateName() );
        userOrgDTO.setUpdateAt( userOrgPageReqDTO.getUpdateAt() );
        userOrgDTO.setUpdateBy( userOrgPageReqDTO.getUpdateBy() );
        userOrgDTO.setUpdateName( userOrgPageReqDTO.getUpdateName() );
        userOrgDTO.setOrgCode( userOrgPageReqDTO.getOrgCode() );
        userOrgDTO.setOrgId( userOrgPageReqDTO.getOrgId() );
        userOrgDTO.setOrgName( userOrgPageReqDTO.getOrgName() );
        userOrgDTO.setUserCode( userOrgPageReqDTO.getUserCode() );
        userOrgDTO.setUserId( userOrgPageReqDTO.getUserId() );
        userOrgDTO.setUserName( userOrgPageReqDTO.getUserName() );

        return userOrgDTO;
    }

    @Override
    public UserOrgShowResDTO convertToUserOrgShowResDTO(UserOrgDTO userOrgDTO) {
        if ( userOrgDTO == null ) {
            return null;
        }

        UserOrgShowResDTO userOrgShowResDTO = new UserOrgShowResDTO();

        userOrgShowResDTO.setCreateAt( userOrgDTO.getCreateAt() );
        userOrgShowResDTO.setCreateBy( userOrgDTO.getCreateBy() );
        userOrgShowResDTO.setCreateName( userOrgDTO.getCreateName() );
        userOrgShowResDTO.setId( userOrgDTO.getId() );
        userOrgShowResDTO.setIsDel( userOrgDTO.getIsDel() );
        userOrgShowResDTO.setIsTest( userOrgDTO.getIsTest() );
        userOrgShowResDTO.setOrgCode( userOrgDTO.getOrgCode() );
        userOrgShowResDTO.setOrgId( userOrgDTO.getOrgId() );
        userOrgShowResDTO.setOrgName( userOrgDTO.getOrgName() );
        userOrgShowResDTO.setUpdateAt( userOrgDTO.getUpdateAt() );
        userOrgShowResDTO.setUpdateBy( userOrgDTO.getUpdateBy() );
        userOrgShowResDTO.setUpdateName( userOrgDTO.getUpdateName() );
        userOrgShowResDTO.setUserCode( userOrgDTO.getUserCode() );
        userOrgShowResDTO.setUserId( userOrgDTO.getUserId() );
        userOrgShowResDTO.setUserName( userOrgDTO.getUserName() );

        return userOrgShowResDTO;
    }

    @Override
    public List<UserOrgShowResDTO> convertToUserOrgShowResDTOList(List<UserOrgDTO> userOrgDTOList) {
        if ( userOrgDTOList == null ) {
            return null;
        }

        List<UserOrgShowResDTO> list = new ArrayList<UserOrgShowResDTO>( userOrgDTOList.size() );
        for ( UserOrgDTO userOrgDTO : userOrgDTOList ) {
            list.add( convertToUserOrgShowResDTO( userOrgDTO ) );
        }

        return list;
    }

    @Override
    public UserOrgListResDTO convertToUserOrgListResDTO(UserOrgDTO userOrgDTO) {
        if ( userOrgDTO == null ) {
            return null;
        }

        UserOrgListResDTO userOrgListResDTO = new UserOrgListResDTO();

        userOrgListResDTO.setCreateAt( userOrgDTO.getCreateAt() );
        userOrgListResDTO.setCreateBy( userOrgDTO.getCreateBy() );
        userOrgListResDTO.setCreateName( userOrgDTO.getCreateName() );
        userOrgListResDTO.setId( userOrgDTO.getId() );
        userOrgListResDTO.setIsDel( userOrgDTO.getIsDel() );
        userOrgListResDTO.setIsTest( userOrgDTO.getIsTest() );
        userOrgListResDTO.setOrgCode( userOrgDTO.getOrgCode() );
        userOrgListResDTO.setOrgId( userOrgDTO.getOrgId() );
        userOrgListResDTO.setOrgName( userOrgDTO.getOrgName() );
        userOrgListResDTO.setUpdateAt( userOrgDTO.getUpdateAt() );
        userOrgListResDTO.setUpdateBy( userOrgDTO.getUpdateBy() );
        userOrgListResDTO.setUpdateName( userOrgDTO.getUpdateName() );
        userOrgListResDTO.setUserCode( userOrgDTO.getUserCode() );
        userOrgListResDTO.setUserId( userOrgDTO.getUserId() );
        userOrgListResDTO.setUserName( userOrgDTO.getUserName() );

        return userOrgListResDTO;
    }

    @Override
    public List<UserOrgListResDTO> convertToUserOrgListResDTOList(List<UserOrgDTO> userOrgDTOList) {
        if ( userOrgDTOList == null ) {
            return null;
        }

        List<UserOrgListResDTO> list = new ArrayList<UserOrgListResDTO>( userOrgDTOList.size() );
        for ( UserOrgDTO userOrgDTO : userOrgDTOList ) {
            list.add( convertToUserOrgListResDTO( userOrgDTO ) );
        }

        return list;
    }

    @Override
    public List<UserOrgDTO> convertToUserOrgDTOList(List<UserOrgAddReqDTO> userOrgAddReqDTOList) {
        if ( userOrgAddReqDTOList == null ) {
            return null;
        }

        List<UserOrgDTO> list = new ArrayList<UserOrgDTO>( userOrgAddReqDTOList.size() );
        for ( UserOrgAddReqDTO userOrgAddReqDTO : userOrgAddReqDTOList ) {
            list.add( convertToUserOrgDTO( userOrgAddReqDTO ) );
        }

        return list;
    }

    @Override
    public UserOrgPageResDTO convertToUserOrgPageResDTO(UserOrgDTO userOrgDTO) {
        if ( userOrgDTO == null ) {
            return null;
        }

        UserOrgPageResDTO userOrgPageResDTO = new UserOrgPageResDTO();

        userOrgPageResDTO.setCreateAt( userOrgDTO.getCreateAt() );
        userOrgPageResDTO.setCreateBy( userOrgDTO.getCreateBy() );
        userOrgPageResDTO.setCreateName( userOrgDTO.getCreateName() );
        userOrgPageResDTO.setId( userOrgDTO.getId() );
        userOrgPageResDTO.setIsDel( userOrgDTO.getIsDel() );
        userOrgPageResDTO.setIsTest( userOrgDTO.getIsTest() );
        userOrgPageResDTO.setOrgCode( userOrgDTO.getOrgCode() );
        userOrgPageResDTO.setOrgId( userOrgDTO.getOrgId() );
        userOrgPageResDTO.setOrgName( userOrgDTO.getOrgName() );
        userOrgPageResDTO.setUpdateAt( userOrgDTO.getUpdateAt() );
        userOrgPageResDTO.setUpdateBy( userOrgDTO.getUpdateBy() );
        userOrgPageResDTO.setUpdateName( userOrgDTO.getUpdateName() );
        userOrgPageResDTO.setUserCode( userOrgDTO.getUserCode() );
        userOrgPageResDTO.setUserId( userOrgDTO.getUserId() );
        userOrgPageResDTO.setUserName( userOrgDTO.getUserName() );

        return userOrgPageResDTO;
    }

    @Override
    public List<UserOrgPageResDTO> convertToUserOrgPageResDTOList(List<UserOrgDTO> userOrgDTOList) {
        if ( userOrgDTOList == null ) {
            return null;
        }

        List<UserOrgPageResDTO> list = new ArrayList<UserOrgPageResDTO>( userOrgDTOList.size() );
        for ( UserOrgDTO userOrgDTO : userOrgDTOList ) {
            list.add( convertToUserOrgPageResDTO( userOrgDTO ) );
        }

        return list;
    }

    @Override
    public Page<UserOrgPageResDTO> convertToUserOrgPageResDTOPage(Page<UserOrgDTO> userOrgDTOPage) {
        if ( userOrgDTOPage == null ) {
            return null;
        }

        Page<UserOrgPageResDTO> page = new Page<UserOrgPageResDTO>();

        page.setRecords( convertToUserOrgPageResDTOList( userOrgDTOPage.getRecords() ) );
        page.setTotal( userOrgDTOPage.getTotal() );
        page.setSize( userOrgDTOPage.getSize() );
        page.setCurrent( userOrgDTOPage.getCurrent() );

        return page;
    }
}

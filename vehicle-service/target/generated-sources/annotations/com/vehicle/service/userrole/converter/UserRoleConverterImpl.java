package com.vehicle.service.userrole.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.userrole.po.UserRolePO;
import com.vehicle.service.userrole.dto.UserRoleAddReqDTO;
import com.vehicle.service.userrole.dto.UserRoleDTO;
import com.vehicle.service.userrole.dto.UserRoleListReqDTO;
import com.vehicle.service.userrole.dto.UserRoleListResDTO;
import com.vehicle.service.userrole.dto.UserRoleModifyReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageReqDTO;
import com.vehicle.service.userrole.dto.UserRolePageResDTO;
import com.vehicle.service.userrole.dto.UserRoleRemoveReqDTO;
import com.vehicle.service.userrole.dto.UserRoleShowResDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserRoleConverterImpl implements UserRoleConverter {

    @Override
    public UserRoleDTO poToDTO(UserRolePO po) {
        if ( po == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId( po.getId() );
        userRoleDTO.setIsDel( po.getIsDel() );
        userRoleDTO.setIsTest( po.getIsTest() );
        userRoleDTO.setCreateAt( po.getCreateAt() );
        userRoleDTO.setCreateTime( po.getCreateTime() );
        userRoleDTO.setCreateBy( po.getCreateBy() );
        userRoleDTO.setCreateName( po.getCreateName() );
        userRoleDTO.setUpdateAt( po.getUpdateAt() );
        userRoleDTO.setUpdateTime( po.getUpdateTime() );
        userRoleDTO.setUpdateBy( po.getUpdateBy() );
        userRoleDTO.setUpdateName( po.getUpdateName() );
        userRoleDTO.setRoleCode( po.getRoleCode() );
        userRoleDTO.setRoleId( po.getRoleId() );
        userRoleDTO.setRoleName( po.getRoleName() );
        userRoleDTO.setUserCode( po.getUserCode() );
        userRoleDTO.setUserId( po.getUserId() );
        userRoleDTO.setUserName( po.getUserName() );

        return userRoleDTO;
    }

    @Override
    public List<UserRoleDTO> poToDTOList(List<UserRolePO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserRoleDTO> list = new ArrayList<UserRoleDTO>( arg0.size() );
        for ( UserRolePO userRolePO : arg0 ) {
            list.add( poToDTO( userRolePO ) );
        }

        return list;
    }

    @Override
    public UserRolePO dtoToPO(UserRoleDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserRolePO userRolePO = new UserRolePO();

        userRolePO.setId( arg0.getId() );
        userRolePO.setIsDel( arg0.getIsDel() );
        userRolePO.setIsTest( arg0.getIsTest() );
        userRolePO.setCreateAt( arg0.getCreateAt() );
        userRolePO.setCreateTime( arg0.getCreateTime() );
        userRolePO.setCreateBy( arg0.getCreateBy() );
        userRolePO.setCreateName( arg0.getCreateName() );
        userRolePO.setUpdateAt( arg0.getUpdateAt() );
        userRolePO.setUpdateTime( arg0.getUpdateTime() );
        userRolePO.setUpdateBy( arg0.getUpdateBy() );
        userRolePO.setUpdateName( arg0.getUpdateName() );
        userRolePO.setUserId( arg0.getUserId() );
        userRolePO.setUserName( arg0.getUserName() );
        userRolePO.setUserCode( arg0.getUserCode() );
        userRolePO.setRoleId( arg0.getRoleId() );
        userRolePO.setRoleName( arg0.getRoleName() );
        userRolePO.setRoleCode( arg0.getRoleCode() );

        return userRolePO;
    }

    @Override
    public List<UserRolePO> dtoToPOList(List<UserRoleDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserRolePO> list = new ArrayList<UserRolePO>( arg0.size() );
        for ( UserRoleDTO userRoleDTO : arg0 ) {
            list.add( dtoToPO( userRoleDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(UserRoleDTO arg0, UserRolePO arg1) {
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
        arg1.setRoleId( arg0.getRoleId() );
        arg1.setRoleName( arg0.getRoleName() );
        arg1.setRoleCode( arg0.getRoleCode() );
    }

    @Override
    public void updateDto(UserRolePO arg0, UserRoleDTO arg1) {
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
        arg1.setRoleCode( arg0.getRoleCode() );
        arg1.setRoleId( arg0.getRoleId() );
        arg1.setRoleName( arg0.getRoleName() );
        arg1.setUserCode( arg0.getUserCode() );
        arg1.setUserId( arg0.getUserId() );
        arg1.setUserName( arg0.getUserName() );
    }

    @Override
    public UserRoleDTO convertToUserRoleDTO(UserRoleAddReqDTO userRoleAddReqDTO) {
        if ( userRoleAddReqDTO == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setIsDel( userRoleAddReqDTO.getIsDel() );
        userRoleDTO.setIsTest( userRoleAddReqDTO.getIsTest() );
        userRoleDTO.setCreateAt( userRoleAddReqDTO.getCreateAt() );
        userRoleDTO.setCreateBy( userRoleAddReqDTO.getCreateBy() );
        userRoleDTO.setCreateName( userRoleAddReqDTO.getCreateName() );
        userRoleDTO.setUpdateAt( userRoleAddReqDTO.getUpdateAt() );
        userRoleDTO.setUpdateBy( userRoleAddReqDTO.getUpdateBy() );
        userRoleDTO.setUpdateName( userRoleAddReqDTO.getUpdateName() );
        userRoleDTO.setRoleCode( userRoleAddReqDTO.getRoleCode() );
        userRoleDTO.setRoleId( userRoleAddReqDTO.getRoleId() );
        userRoleDTO.setRoleName( userRoleAddReqDTO.getRoleName() );
        userRoleDTO.setUserCode( userRoleAddReqDTO.getUserCode() );
        userRoleDTO.setUserId( userRoleAddReqDTO.getUserId() );
        userRoleDTO.setUserName( userRoleAddReqDTO.getUserName() );

        return userRoleDTO;
    }

    @Override
    public UserRoleDTO convertToUserRoleDTO(UserRoleModifyReqDTO userRoleModifyReqDTO) {
        if ( userRoleModifyReqDTO == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId( userRoleModifyReqDTO.getId() );
        userRoleDTO.setIsDel( userRoleModifyReqDTO.getIsDel() );
        userRoleDTO.setIsTest( userRoleModifyReqDTO.getIsTest() );
        userRoleDTO.setCreateAt( userRoleModifyReqDTO.getCreateAt() );
        userRoleDTO.setCreateBy( userRoleModifyReqDTO.getCreateBy() );
        userRoleDTO.setCreateName( userRoleModifyReqDTO.getCreateName() );
        userRoleDTO.setUpdateAt( userRoleModifyReqDTO.getUpdateAt() );
        userRoleDTO.setUpdateBy( userRoleModifyReqDTO.getUpdateBy() );
        userRoleDTO.setUpdateName( userRoleModifyReqDTO.getUpdateName() );
        userRoleDTO.setRoleCode( userRoleModifyReqDTO.getRoleCode() );
        userRoleDTO.setRoleId( userRoleModifyReqDTO.getRoleId() );
        userRoleDTO.setRoleName( userRoleModifyReqDTO.getRoleName() );
        userRoleDTO.setUserCode( userRoleModifyReqDTO.getUserCode() );
        userRoleDTO.setUserId( userRoleModifyReqDTO.getUserId() );
        userRoleDTO.setUserName( userRoleModifyReqDTO.getUserName() );

        return userRoleDTO;
    }

    @Override
    public UserRoleDTO convertToUserRoleDTO(UserRoleRemoveReqDTO userRoleRemoveReqDTO) {
        if ( userRoleRemoveReqDTO == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId( userRoleRemoveReqDTO.getId() );
        userRoleDTO.setIsDel( userRoleRemoveReqDTO.getIsDel() );
        userRoleDTO.setIsTest( userRoleRemoveReqDTO.getIsTest() );
        userRoleDTO.setCreateAt( userRoleRemoveReqDTO.getCreateAt() );
        userRoleDTO.setCreateBy( userRoleRemoveReqDTO.getCreateBy() );
        userRoleDTO.setCreateName( userRoleRemoveReqDTO.getCreateName() );
        userRoleDTO.setUpdateAt( userRoleRemoveReqDTO.getUpdateAt() );
        userRoleDTO.setUpdateBy( userRoleRemoveReqDTO.getUpdateBy() );
        userRoleDTO.setUpdateName( userRoleRemoveReqDTO.getUpdateName() );
        userRoleDTO.setRoleCode( userRoleRemoveReqDTO.getRoleCode() );
        userRoleDTO.setRoleId( userRoleRemoveReqDTO.getRoleId() );
        userRoleDTO.setRoleName( userRoleRemoveReqDTO.getRoleName() );
        userRoleDTO.setUserCode( userRoleRemoveReqDTO.getUserCode() );
        userRoleDTO.setUserId( userRoleRemoveReqDTO.getUserId() );
        userRoleDTO.setUserName( userRoleRemoveReqDTO.getUserName() );

        return userRoleDTO;
    }

    @Override
    public UserRoleDTO convertToUserRoleDTO(UserRoleListReqDTO userRoleListReqDTO) {
        if ( userRoleListReqDTO == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId( userRoleListReqDTO.getId() );
        userRoleDTO.setIsDel( userRoleListReqDTO.getIsDel() );
        userRoleDTO.setIsTest( userRoleListReqDTO.getIsTest() );
        userRoleDTO.setCreateAt( userRoleListReqDTO.getCreateAt() );
        userRoleDTO.setCreateBy( userRoleListReqDTO.getCreateBy() );
        userRoleDTO.setCreateName( userRoleListReqDTO.getCreateName() );
        userRoleDTO.setUpdateAt( userRoleListReqDTO.getUpdateAt() );
        userRoleDTO.setUpdateBy( userRoleListReqDTO.getUpdateBy() );
        userRoleDTO.setUpdateName( userRoleListReqDTO.getUpdateName() );
        userRoleDTO.setRoleCode( userRoleListReqDTO.getRoleCode() );
        userRoleDTO.setRoleId( userRoleListReqDTO.getRoleId() );
        userRoleDTO.setRoleName( userRoleListReqDTO.getRoleName() );
        userRoleDTO.setUserCode( userRoleListReqDTO.getUserCode() );
        userRoleDTO.setUserId( userRoleListReqDTO.getUserId() );
        userRoleDTO.setUserName( userRoleListReqDTO.getUserName() );

        return userRoleDTO;
    }

    @Override
    public UserRoleDTO convertToUserRoleDTO(UserRolePageReqDTO userRolePageReqDTO) {
        if ( userRolePageReqDTO == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setId( userRolePageReqDTO.getId() );
        userRoleDTO.setIsDel( userRolePageReqDTO.getIsDel() );
        userRoleDTO.setIsTest( userRolePageReqDTO.getIsTest() );
        userRoleDTO.setCreateAt( userRolePageReqDTO.getCreateAt() );
        userRoleDTO.setCreateBy( userRolePageReqDTO.getCreateBy() );
        userRoleDTO.setCreateName( userRolePageReqDTO.getCreateName() );
        userRoleDTO.setUpdateAt( userRolePageReqDTO.getUpdateAt() );
        userRoleDTO.setUpdateBy( userRolePageReqDTO.getUpdateBy() );
        userRoleDTO.setUpdateName( userRolePageReqDTO.getUpdateName() );
        userRoleDTO.setRoleCode( userRolePageReqDTO.getRoleCode() );
        userRoleDTO.setRoleId( userRolePageReqDTO.getRoleId() );
        userRoleDTO.setRoleName( userRolePageReqDTO.getRoleName() );
        userRoleDTO.setUserCode( userRolePageReqDTO.getUserCode() );
        userRoleDTO.setUserId( userRolePageReqDTO.getUserId() );
        userRoleDTO.setUserName( userRolePageReqDTO.getUserName() );

        return userRoleDTO;
    }

    @Override
    public UserRoleShowResDTO convertToUserRoleShowResDTO(UserRoleDTO userRoleDTO) {
        if ( userRoleDTO == null ) {
            return null;
        }

        UserRoleShowResDTO userRoleShowResDTO = new UserRoleShowResDTO();

        userRoleShowResDTO.setCreateAt( userRoleDTO.getCreateAt() );
        userRoleShowResDTO.setCreateBy( userRoleDTO.getCreateBy() );
        userRoleShowResDTO.setCreateName( userRoleDTO.getCreateName() );
        userRoleShowResDTO.setId( userRoleDTO.getId() );
        userRoleShowResDTO.setIsDel( userRoleDTO.getIsDel() );
        userRoleShowResDTO.setIsTest( userRoleDTO.getIsTest() );
        userRoleShowResDTO.setRoleCode( userRoleDTO.getRoleCode() );
        userRoleShowResDTO.setRoleId( userRoleDTO.getRoleId() );
        userRoleShowResDTO.setRoleName( userRoleDTO.getRoleName() );
        userRoleShowResDTO.setUpdateAt( userRoleDTO.getUpdateAt() );
        userRoleShowResDTO.setUpdateBy( userRoleDTO.getUpdateBy() );
        userRoleShowResDTO.setUpdateName( userRoleDTO.getUpdateName() );
        userRoleShowResDTO.setUserCode( userRoleDTO.getUserCode() );
        userRoleShowResDTO.setUserId( userRoleDTO.getUserId() );
        userRoleShowResDTO.setUserName( userRoleDTO.getUserName() );

        return userRoleShowResDTO;
    }

    @Override
    public List<UserRoleShowResDTO> convertToUserRoleShowResDTOList(List<UserRoleDTO> userRoleDTOList) {
        if ( userRoleDTOList == null ) {
            return null;
        }

        List<UserRoleShowResDTO> list = new ArrayList<UserRoleShowResDTO>( userRoleDTOList.size() );
        for ( UserRoleDTO userRoleDTO : userRoleDTOList ) {
            list.add( convertToUserRoleShowResDTO( userRoleDTO ) );
        }

        return list;
    }

    @Override
    public UserRoleListResDTO convertToUserRoleListResDTO(UserRoleDTO userRoleDTO) {
        if ( userRoleDTO == null ) {
            return null;
        }

        UserRoleListResDTO userRoleListResDTO = new UserRoleListResDTO();

        userRoleListResDTO.setCreateAt( userRoleDTO.getCreateAt() );
        userRoleListResDTO.setCreateBy( userRoleDTO.getCreateBy() );
        userRoleListResDTO.setCreateName( userRoleDTO.getCreateName() );
        userRoleListResDTO.setId( userRoleDTO.getId() );
        userRoleListResDTO.setIsDel( userRoleDTO.getIsDel() );
        userRoleListResDTO.setIsTest( userRoleDTO.getIsTest() );
        userRoleListResDTO.setRoleCode( userRoleDTO.getRoleCode() );
        userRoleListResDTO.setRoleId( userRoleDTO.getRoleId() );
        userRoleListResDTO.setRoleName( userRoleDTO.getRoleName() );
        userRoleListResDTO.setUpdateAt( userRoleDTO.getUpdateAt() );
        userRoleListResDTO.setUpdateBy( userRoleDTO.getUpdateBy() );
        userRoleListResDTO.setUpdateName( userRoleDTO.getUpdateName() );
        userRoleListResDTO.setUserCode( userRoleDTO.getUserCode() );
        userRoleListResDTO.setUserId( userRoleDTO.getUserId() );
        userRoleListResDTO.setUserName( userRoleDTO.getUserName() );

        return userRoleListResDTO;
    }

    @Override
    public List<UserRoleListResDTO> convertToUserRoleListResDTOList(List<UserRoleDTO> userRoleDTOList) {
        if ( userRoleDTOList == null ) {
            return null;
        }

        List<UserRoleListResDTO> list = new ArrayList<UserRoleListResDTO>( userRoleDTOList.size() );
        for ( UserRoleDTO userRoleDTO : userRoleDTOList ) {
            list.add( convertToUserRoleListResDTO( userRoleDTO ) );
        }

        return list;
    }

    @Override
    public List<UserRoleDTO> convertToUserRoleDTOList(List<UserRoleAddReqDTO> userRoleAddReqDTOList) {
        if ( userRoleAddReqDTOList == null ) {
            return null;
        }

        List<UserRoleDTO> list = new ArrayList<UserRoleDTO>( userRoleAddReqDTOList.size() );
        for ( UserRoleAddReqDTO userRoleAddReqDTO : userRoleAddReqDTOList ) {
            list.add( convertToUserRoleDTO( userRoleAddReqDTO ) );
        }

        return list;
    }

    @Override
    public UserRolePageResDTO convertToUserRolePageResDTO(UserRoleDTO userRoleDTO) {
        if ( userRoleDTO == null ) {
            return null;
        }

        UserRolePageResDTO userRolePageResDTO = new UserRolePageResDTO();

        userRolePageResDTO.setCreateAt( userRoleDTO.getCreateAt() );
        userRolePageResDTO.setCreateBy( userRoleDTO.getCreateBy() );
        userRolePageResDTO.setCreateName( userRoleDTO.getCreateName() );
        userRolePageResDTO.setId( userRoleDTO.getId() );
        userRolePageResDTO.setIsDel( userRoleDTO.getIsDel() );
        userRolePageResDTO.setIsTest( userRoleDTO.getIsTest() );
        userRolePageResDTO.setRoleCode( userRoleDTO.getRoleCode() );
        userRolePageResDTO.setRoleId( userRoleDTO.getRoleId() );
        userRolePageResDTO.setRoleName( userRoleDTO.getRoleName() );
        userRolePageResDTO.setUpdateAt( userRoleDTO.getUpdateAt() );
        userRolePageResDTO.setUpdateBy( userRoleDTO.getUpdateBy() );
        userRolePageResDTO.setUpdateName( userRoleDTO.getUpdateName() );
        userRolePageResDTO.setUserCode( userRoleDTO.getUserCode() );
        userRolePageResDTO.setUserId( userRoleDTO.getUserId() );
        userRolePageResDTO.setUserName( userRoleDTO.getUserName() );

        return userRolePageResDTO;
    }

    @Override
    public List<UserRolePageResDTO> convertToUserRolePageResDTOList(List<UserRoleDTO> userRoleDTOList) {
        if ( userRoleDTOList == null ) {
            return null;
        }

        List<UserRolePageResDTO> list = new ArrayList<UserRolePageResDTO>( userRoleDTOList.size() );
        for ( UserRoleDTO userRoleDTO : userRoleDTOList ) {
            list.add( convertToUserRolePageResDTO( userRoleDTO ) );
        }

        return list;
    }

    @Override
    public Page<UserRolePageResDTO> convertToUserRolePageResDTOPage(Page<UserRoleDTO> userRoleDTOPage) {
        if ( userRoleDTOPage == null ) {
            return null;
        }

        Page<UserRolePageResDTO> page = new Page<UserRolePageResDTO>();

        page.setRecords( convertToUserRolePageResDTOList( userRoleDTOPage.getRecords() ) );
        page.setTotal( userRoleDTOPage.getTotal() );
        page.setSize( userRoleDTOPage.getSize() );
        page.setCurrent( userRoleDTOPage.getCurrent() );

        return page;
    }
}

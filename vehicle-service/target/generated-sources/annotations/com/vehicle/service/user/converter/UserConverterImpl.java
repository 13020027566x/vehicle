package com.vehicle.service.user.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.user.po.UserPO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserDTO;
import com.vehicle.service.user.dto.UserListReqDTO;
import com.vehicle.service.user.dto.UserListResDTO;
import com.vehicle.service.user.dto.UserModifyReqDTO;
import com.vehicle.service.user.dto.UserPageReqDTO;
import com.vehicle.service.user.dto.UserPageResDTO;
import com.vehicle.service.user.dto.UserPageRoleResDTO;
import com.vehicle.service.user.dto.UserRemoveReqDTO;
import com.vehicle.service.user.dto.UserShowResDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDTO poToDTO(UserPO po) {
        if ( po == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( po.getId() );
        userDTO.setIsDel( po.getIsDel() );
        userDTO.setIsTest( po.getIsTest() );
        userDTO.setCreateAt( po.getCreateAt() );
        userDTO.setCreateTime( po.getCreateTime() );
        userDTO.setCreateBy( po.getCreateBy() );
        userDTO.setCreateName( po.getCreateName() );
        userDTO.setUpdateAt( po.getUpdateAt() );
        userDTO.setUpdateTime( po.getUpdateTime() );
        userDTO.setUpdateBy( po.getUpdateBy() );
        userDTO.setUpdateName( po.getUpdateName() );
        userDTO.setCode( po.getCode() );
        userDTO.setLastAccessTime( po.getLastAccessTime() );
        userDTO.setLoginName( po.getLoginName() );
        userDTO.setMobile( po.getMobile() );
        userDTO.setName( po.getName() );
        userDTO.setPwd( po.getPwd() );
        userDTO.setRemark( po.getRemark() );
        userDTO.setSalt( po.getSalt() );
        userDTO.setStatusCode( po.getStatusCode() );
        userDTO.setStatusVal( po.getStatusVal() );
        userDTO.setTotalAmount( po.getTotalAmount() );

        return userDTO;
    }

    @Override
    public List<UserDTO> poToDTOList(List<UserPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( arg0.size() );
        for ( UserPO userPO : arg0 ) {
            list.add( poToDTO( userPO ) );
        }

        return list;
    }

    @Override
    public UserPO dtoToPO(UserDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setId( arg0.getId() );
        userPO.setIsDel( arg0.getIsDel() );
        userPO.setIsTest( arg0.getIsTest() );
        userPO.setCreateAt( arg0.getCreateAt() );
        userPO.setCreateTime( arg0.getCreateTime() );
        userPO.setCreateBy( arg0.getCreateBy() );
        userPO.setCreateName( arg0.getCreateName() );
        userPO.setUpdateAt( arg0.getUpdateAt() );
        userPO.setUpdateTime( arg0.getUpdateTime() );
        userPO.setUpdateBy( arg0.getUpdateBy() );
        userPO.setUpdateName( arg0.getUpdateName() );
        userPO.setName( arg0.getName() );
        userPO.setLoginName( arg0.getLoginName() );
        userPO.setCode( arg0.getCode() );
        userPO.setPwd( arg0.getPwd() );
        userPO.setSalt( arg0.getSalt() );
        userPO.setMobile( arg0.getMobile() );
        userPO.setStatusVal( arg0.getStatusVal() );
        userPO.setStatusCode( arg0.getStatusCode() );
        userPO.setLastAccessTime( arg0.getLastAccessTime() );
        userPO.setTotalAmount( arg0.getTotalAmount() );
        userPO.setRemark( arg0.getRemark() );

        return userPO;
    }

    @Override
    public List<UserPO> dtoToPOList(List<UserDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserPO> list = new ArrayList<UserPO>( arg0.size() );
        for ( UserDTO userDTO : arg0 ) {
            list.add( dtoToPO( userDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(UserDTO arg0, UserPO arg1) {
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
        arg1.setName( arg0.getName() );
        arg1.setLoginName( arg0.getLoginName() );
        arg1.setCode( arg0.getCode() );
        arg1.setPwd( arg0.getPwd() );
        arg1.setSalt( arg0.getSalt() );
        arg1.setMobile( arg0.getMobile() );
        arg1.setStatusVal( arg0.getStatusVal() );
        arg1.setStatusCode( arg0.getStatusCode() );
        arg1.setLastAccessTime( arg0.getLastAccessTime() );
        arg1.setTotalAmount( arg0.getTotalAmount() );
        arg1.setRemark( arg0.getRemark() );
    }

    @Override
    public void updateDto(UserPO arg0, UserDTO arg1) {
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
        arg1.setCode( arg0.getCode() );
        arg1.setLastAccessTime( arg0.getLastAccessTime() );
        arg1.setLoginName( arg0.getLoginName() );
        arg1.setMobile( arg0.getMobile() );
        arg1.setName( arg0.getName() );
        arg1.setPwd( arg0.getPwd() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setSalt( arg0.getSalt() );
        arg1.setStatusCode( arg0.getStatusCode() );
        arg1.setStatusVal( arg0.getStatusVal() );
        arg1.setTotalAmount( arg0.getTotalAmount() );
    }

    @Override
    public UserDTO convertToUserDTO(UserAddReqDTO userAddReqDTO) {
        if ( userAddReqDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setLastAccessTime( userAddReqDTO.getLastAccessTime() );
        userDTO.setLoginName( userAddReqDTO.getLoginName() );
        userDTO.setMobile( userAddReqDTO.getMobile() );
        userDTO.setName( userAddReqDTO.getName() );
        userDTO.setPwd( userAddReqDTO.getPwd() );
        userDTO.setRemark( userAddReqDTO.getRemark() );
        userDTO.setSalt( userAddReqDTO.getSalt() );
        userDTO.setStatusCode( userAddReqDTO.getStatusCode() );
        userDTO.setTotalAmount( userAddReqDTO.getTotalAmount() );

        return userDTO;
    }

    @Override
    public UserDTO convertToUserDTO(UserModifyReqDTO userModifyReqDTO) {
        if ( userModifyReqDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userModifyReqDTO.getId() );
        userDTO.setLastAccessTime( userModifyReqDTO.getLastAccessTime() );
        userDTO.setLoginName( userModifyReqDTO.getLoginName() );
        userDTO.setMobile( userModifyReqDTO.getMobile() );
        userDTO.setName( userModifyReqDTO.getName() );
        userDTO.setPwd( userModifyReqDTO.getPwd() );
        userDTO.setRemark( userModifyReqDTO.getRemark() );
        userDTO.setSalt( userModifyReqDTO.getSalt() );
        userDTO.setStatusCode( userModifyReqDTO.getStatusCode() );
        userDTO.setTotalAmount( userModifyReqDTO.getTotalAmount() );

        return userDTO;
    }

    @Override
    public UserDTO convertToUserDTO(UserRemoveReqDTO userRemoveReqDTO) {
        if ( userRemoveReqDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userRemoveReqDTO.getId() );
        userDTO.setIsDel( userRemoveReqDTO.getIsDel() );
        userDTO.setIsTest( userRemoveReqDTO.getIsTest() );
        userDTO.setCreateAt( userRemoveReqDTO.getCreateAt() );
        userDTO.setCreateBy( userRemoveReqDTO.getCreateBy() );
        userDTO.setCreateName( userRemoveReqDTO.getCreateName() );
        userDTO.setUpdateAt( userRemoveReqDTO.getUpdateAt() );
        userDTO.setUpdateBy( userRemoveReqDTO.getUpdateBy() );
        userDTO.setUpdateName( userRemoveReqDTO.getUpdateName() );
        userDTO.setCode( userRemoveReqDTO.getCode() );
        userDTO.setLastAccessTime( userRemoveReqDTO.getLastAccessTime() );
        userDTO.setLoginName( userRemoveReqDTO.getLoginName() );
        userDTO.setMobile( userRemoveReqDTO.getMobile() );
        userDTO.setName( userRemoveReqDTO.getName() );
        userDTO.setPwd( userRemoveReqDTO.getPwd() );
        userDTO.setRemark( userRemoveReqDTO.getRemark() );
        userDTO.setSalt( userRemoveReqDTO.getSalt() );
        userDTO.setStatusCode( userRemoveReqDTO.getStatusCode() );
        userDTO.setStatusVal( userRemoveReqDTO.getStatusVal() );
        userDTO.setTotalAmount( userRemoveReqDTO.getTotalAmount() );

        return userDTO;
    }

    @Override
    public UserDTO convertToUserDTO(UserListReqDTO userListReqDTO) {
        if ( userListReqDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( userListReqDTO.getId() );
        userDTO.setIsDel( userListReqDTO.getIsDel() );
        userDTO.setIsTest( userListReqDTO.getIsTest() );
        userDTO.setCreateAt( userListReqDTO.getCreateAt() );
        userDTO.setCreateBy( userListReqDTO.getCreateBy() );
        userDTO.setCreateName( userListReqDTO.getCreateName() );
        userDTO.setUpdateAt( userListReqDTO.getUpdateAt() );
        userDTO.setUpdateBy( userListReqDTO.getUpdateBy() );
        userDTO.setUpdateName( userListReqDTO.getUpdateName() );
        userDTO.setCode( userListReqDTO.getCode() );
        userDTO.setLastAccessTime( userListReqDTO.getLastAccessTime() );
        userDTO.setLoginName( userListReqDTO.getLoginName() );
        userDTO.setMobile( userListReqDTO.getMobile() );
        userDTO.setName( userListReqDTO.getName() );
        userDTO.setPwd( userListReqDTO.getPwd() );
        userDTO.setRemark( userListReqDTO.getRemark() );
        userDTO.setSalt( userListReqDTO.getSalt() );
        userDTO.setStatusCode( userListReqDTO.getStatusCode() );
        userDTO.setStatusVal( userListReqDTO.getStatusVal() );
        userDTO.setTotalAmount( userListReqDTO.getTotalAmount() );

        return userDTO;
    }

    @Override
    public UserDTO convertToUserDTO(UserPageReqDTO userPageReqDTO) {
        if ( userPageReqDTO == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setLoginName( userPageReqDTO.getLoginName() );
        userDTO.setMobile( userPageReqDTO.getMobile() );
        userDTO.setName( userPageReqDTO.getName() );
        userDTO.setStatusCode( userPageReqDTO.getStatusCode() );

        return userDTO;
    }

    @Override
    public UserShowResDTO convertToUserShowResDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserShowResDTO userShowResDTO = new UserShowResDTO();

        userShowResDTO.setId( userDTO.getId() );
        userShowResDTO.setLastAccessTime( userDTO.getLastAccessTime() );
        userShowResDTO.setLoginName( userDTO.getLoginName() );
        userShowResDTO.setMobile( userDTO.getMobile() );
        userShowResDTO.setName( userDTO.getName() );
        userShowResDTO.setRemark( userDTO.getRemark() );
        userShowResDTO.setSalt( userDTO.getSalt() );
        userShowResDTO.setStatusCode( userDTO.getStatusCode() );
        userShowResDTO.setStatusVal( userDTO.getStatusVal() );
        userShowResDTO.setTotalAmount( userDTO.getTotalAmount() );

        return userShowResDTO;
    }

    @Override
    public List<UserShowResDTO> convertToUserShowResDTOList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<UserShowResDTO> list = new ArrayList<UserShowResDTO>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( convertToUserShowResDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public UserListResDTO convertToUserListResDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserListResDTO userListResDTO = new UserListResDTO();

        userListResDTO.setCode( userDTO.getCode() );
        userListResDTO.setCreateAt( userDTO.getCreateAt() );
        userListResDTO.setCreateBy( userDTO.getCreateBy() );
        userListResDTO.setCreateName( userDTO.getCreateName() );
        userListResDTO.setId( userDTO.getId() );
        userListResDTO.setIsDel( userDTO.getIsDel() );
        userListResDTO.setIsTest( userDTO.getIsTest() );
        userListResDTO.setLastAccessTime( userDTO.getLastAccessTime() );
        userListResDTO.setLoginName( userDTO.getLoginName() );
        userListResDTO.setMobile( userDTO.getMobile() );
        userListResDTO.setName( userDTO.getName() );
        userListResDTO.setPwd( userDTO.getPwd() );
        userListResDTO.setRemark( userDTO.getRemark() );
        userListResDTO.setSalt( userDTO.getSalt() );
        userListResDTO.setStatusCode( userDTO.getStatusCode() );
        userListResDTO.setStatusVal( userDTO.getStatusVal() );
        userListResDTO.setTotalAmount( userDTO.getTotalAmount() );
        userListResDTO.setUpdateAt( userDTO.getUpdateAt() );
        userListResDTO.setUpdateBy( userDTO.getUpdateBy() );
        userListResDTO.setUpdateName( userDTO.getUpdateName() );

        return userListResDTO;
    }

    @Override
    public List<UserListResDTO> convertToUserListResDTOList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<UserListResDTO> list = new ArrayList<UserListResDTO>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( convertToUserListResDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> convertToUserDTOList(List<UserAddReqDTO> userAddReqDTOList) {
        if ( userAddReqDTOList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userAddReqDTOList.size() );
        for ( UserAddReqDTO userAddReqDTO : userAddReqDTOList ) {
            list.add( convertToUserDTO( userAddReqDTO ) );
        }

        return list;
    }

    @Override
    public UserPageResDTO convertToUserPageResDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserPageResDTO userPageResDTO = new UserPageResDTO();

        userPageResDTO.setId( userDTO.getId() );
        userPageResDTO.setLoginName( userDTO.getLoginName() );
        userPageResDTO.setMobile( userDTO.getMobile() );
        userPageResDTO.setName( userDTO.getName() );
        if ( userDTO.getUpdateAt() != null ) {
            userPageResDTO.setUpdateAt( String.valueOf( userDTO.getUpdateAt() ) );
        }
        userPageResDTO.setUpdateName( userDTO.getUpdateName() );

        return userPageResDTO;
    }

    @Override
    public List<UserPageResDTO> convertToUserPageResDTOList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<UserPageResDTO> list = new ArrayList<UserPageResDTO>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( convertToUserPageResDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public UserPageRoleResDTO convertToUserPageRoleResDTO(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        UserPageRoleResDTO userPageRoleResDTO = new UserPageRoleResDTO();

        userPageRoleResDTO.setCode( roleDTO.getCode() );
        userPageRoleResDTO.setId( roleDTO.getId() );
        userPageRoleResDTO.setName( roleDTO.getName() );
        userPageRoleResDTO.setRemark( roleDTO.getRemark() );
        if ( roleDTO.getUpdateAt() != null ) {
            userPageRoleResDTO.setUpdateAt( String.valueOf( roleDTO.getUpdateAt() ) );
        }
        userPageRoleResDTO.setUpdateName( roleDTO.getUpdateName() );

        return userPageRoleResDTO;
    }

    @Override
    public Page<UserPageRoleResDTO> convertToUserPageRoleResDTOPage(Page<RoleDTO> roleDTOPage) {
        if ( roleDTOPage == null ) {
            return null;
        }

        Page<UserPageRoleResDTO> page = new Page<UserPageRoleResDTO>();

        page.setRecords( roleDTOListToUserPageRoleResDTOList( roleDTOPage.getRecords() ) );
        page.setTotal( roleDTOPage.getTotal() );
        page.setSize( roleDTOPage.getSize() );
        page.setCurrent( roleDTOPage.getCurrent() );

        return page;
    }

    protected List<UserPageRoleResDTO> roleDTOListToUserPageRoleResDTOList(List<RoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserPageRoleResDTO> list1 = new ArrayList<UserPageRoleResDTO>( list.size() );
        for ( RoleDTO roleDTO : list ) {
            list1.add( convertToUserPageRoleResDTO( roleDTO ) );
        }

        return list1;
    }
}

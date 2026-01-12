package com.vehicle.service.user.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.google.common.collect.Maps;
import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.dictionary.dto.DictionaryDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.user.dto.UserAddReqDTO;
import com.vehicle.service.user.dto.UserDTO;
import com.vehicle.service.user.dto.UserListReqDTO;
import com.vehicle.service.user.dto.UserListResDTO;
import com.vehicle.service.user.dto.UserModifyReqDTO;
import com.vehicle.service.user.dto.UserPageResDTO;
import com.vehicle.service.user.dto.UserRemoveReqDTO;
import com.vehicle.service.user.dto.UserShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 用户 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class UserDOTest extends BaseJUnitTester<UserDO> {

    @Test(expected = MessageException.class)
    public void testCheckUserAddReqDTO() {
        domain.checkUserAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserAddReqDTOList() {
        domain.checkUserAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckUserModifyReqDTO() {
        domain.checkUserModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserRemoveReqDTO() {
        domain.checkUserRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        UserListReqDTO userListReqDTO = new UserListReqDTO();
        userListReqDTO.setId("1");

        UserDTO userDTO = domain.buildListParamsDTO(userListReqDTO);

        Assert.assertEquals(userListReqDTO.getId(), userDTO.getId());
    }

    @Test
    public void testBuildAddUserDTO() {
        Map<String, DictionaryDTO> dictionaryDTOMap = Maps.newHashMap();

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setCode("YHKQ");
        dictionaryDTO.setValue(2000);
        dictionaryDTOMap.put("YHKQ", dictionaryDTO);

        UserAddReqDTO userAddReqDTO = new UserAddReqDTO();
        userAddReqDTO.setName("1L");
        userAddReqDTO.setPwd("pwd");
        userAddReqDTO.setStatusCode("YHKQ");

        UserDTO userDTO = domain.buildAddUserDTO(userAddReqDTO, dictionaryDTOMap);

        Assert.assertEquals(userAddReqDTO.getName(), userDTO.getName());
    }

    @Test
    public void testBuildAddBatchUserDTOList() {
        List<UserAddReqDTO> userAddReqDTOList = Lists.newArrayList();
        UserAddReqDTO userAddReqDTO1 = new UserAddReqDTO();
        userAddReqDTO1.setName("1L");
        UserAddReqDTO userAddReqDTO2 = new UserAddReqDTO();
        userAddReqDTO1.setName("2L");
        userAddReqDTOList.add(userAddReqDTO1);
        userAddReqDTOList.add(userAddReqDTO2);

        List<UserDTO> userDTOList = domain.buildAddBatchUserDTOList(userAddReqDTOList);

        Assert.assertEquals(userAddReqDTOList.size(), userDTOList.size());
        Assert.assertEquals(userAddReqDTOList.get(0).getName(), userDTOList.get(0).getName());
        Assert.assertEquals(userAddReqDTOList.get(1).getName(), userDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyUserDTO() {
        Map<String, DictionaryDTO> dictionaryDTOMap = Maps.newHashMap();

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setCode("YHKQ");
        dictionaryDTO.setValue(2000);
        dictionaryDTOMap.put("YHKQ", dictionaryDTO);

        UserModifyReqDTO userModifyReqDTO = new UserModifyReqDTO();
        userModifyReqDTO.setId("1");
        userModifyReqDTO.setStatusCode("YHKQ");

        UserDTO userDTO = domain.buildModifyUserDTO(userModifyReqDTO, dictionaryDTOMap);

        Assert.assertEquals(userModifyReqDTO.getId(), userDTO.getId());
    }

    @Test
    public void testBuildRemoveUserDTO() {
        UserRemoveReqDTO userRemoveReqDTO = new UserRemoveReqDTO();
        userRemoveReqDTO.setId("1");

        UserDTO userDTO = domain.buildRemoveUserDTO(userRemoveReqDTO);

        Assert.assertEquals(userRemoveReqDTO.getId(), userDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserListResDTOList() {
        List<UserDTO> userDTOList = Lists.newArrayList();

        domain.transferUserListResDTOList(userDTOList);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId("1");
        UserDTO userDTO2 = new UserDTO();
        userDTO1.setId("2");
        userDTOList.add(userDTO1);
        userDTOList.add(userDTO2);

        List<UserListResDTO> userListResDTOList = domain.transferUserListResDTOList(userDTOList);

        Assert.assertEquals(userDTOList.size(), userListResDTOList.size());
        Assert.assertEquals(userDTOList.get(0).getId(), userListResDTOList.get(0).getId());
        Assert.assertEquals(userDTOList.get(1).getId(), userListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserListResDTO() {
        domain.transferUserListResDTO(null);

        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");

        UserListResDTO userListResDTO = domain.transferUserListResDTO(userDTO);

        Assert.assertEquals(userDTO.getId(), userListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserPageResDTOPage() {
        Page<UserDTO> userDTOPage = new Page<>();

        domain.transferUserPageResDTOPage(userDTOPage);

        userDTOPage.setTotal(100);
        userDTOPage.setCurrent(10);
        userDTOPage.setSize(10);

        List<UserDTO> userDTOList = Lists.newArrayList();
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId("1");
        UserDTO userDTO2 = new UserDTO();
        userDTO1.setId("2");
        userDTOList.add(userDTO1);
        userDTOList.add(userDTO2);

        userDTOPage.setRecords(userDTOList);


        Page<UserPageResDTO> userPageResDTOPage = domain.transferUserPageResDTOPage(userDTOPage);

        Assert.assertEquals(userDTOPage.getTotal(), userPageResDTOPage.getTotal());
        Assert.assertEquals(userDTOPage.getCurrent(), userPageResDTOPage.getCurrent());
        Assert.assertEquals(userDTOPage.getSize(), userPageResDTOPage.getSize());
        Assert.assertEquals(userDTOPage.getRecords().size(), userPageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserShowResDTO() {
        domain.transferUserShowResDTO(null, null, null, null);

        Map<String, DictionaryDTO> dictionaryDTOMap = Maps.newHashMap();

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setCode("YHKQ");
        dictionaryDTO.setValue(2000);
        dictionaryDTOMap.put("YHKQ", dictionaryDTO);

        List<RoleDTO> roleDTOList = Lists.newArrayList();
        List<OrganizationDTO> organizationDTOList = Lists.newArrayList();
        UserDTO userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setStatusCode("YHKQ");

        UserShowResDTO userShowResDTO = domain.transferUserShowResDTO(userDTO, roleDTOList, organizationDTOList, dictionaryDTOMap);

        Assert.assertEquals(userDTO.getId(), userShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserShowResDTOList() {
        List<UserDTO> userDTOList = Lists.newArrayList();

        domain.transferUserShowResDTOList(userDTOList);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId("1");
        UserDTO userDTO2 = new UserDTO();
        userDTO1.setId("2");
        userDTOList.add(userDTO1);
        userDTOList.add(userDTO2);

        List<UserShowResDTO> userShowResDTOList = domain.transferUserShowResDTOList(userDTOList);

        Assert.assertEquals(userDTOList.size(), userShowResDTOList.size());
        Assert.assertEquals(userDTOList.get(0).getId(), userShowResDTOList.get(0).getId());
        Assert.assertEquals(userDTOList.get(1).getId(), userShowResDTOList.get(1).getId());
    }
}

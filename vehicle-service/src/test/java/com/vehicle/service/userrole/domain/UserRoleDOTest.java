package com.vehicle.service.userrole.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
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
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 用户角色 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class UserRoleDOTest extends BaseJUnitTester<UserRoleDO> {

    @Test(expected = MessageException.class)
    public void testCheckUserRoleAddReqDTO() {
        domain.checkUserRoleAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserRoleAddReqDTOList() {
        domain.checkUserRoleAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckUserRoleModifyReqDTO() {
        domain.checkUserRoleModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserRoleRemoveReqDTO() {
        domain.checkUserRoleRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        UserRoleListReqDTO userRoleListReqDTO = new UserRoleListReqDTO();
        userRoleListReqDTO.setId("1");

        UserRoleDTO userRoleDTO = domain.buildListParamsDTO(userRoleListReqDTO);

        Assert.assertEquals(userRoleListReqDTO.getId(), userRoleDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        UserRolePageReqDTO userRolePageReqDTO = new UserRolePageReqDTO();
        userRolePageReqDTO.setId("1");

        UserRoleDTO userRoleDTO = domain.buildPageParamsDTO(userRolePageReqDTO);

        Assert.assertEquals(userRolePageReqDTO.getId(), userRoleDTO.getId());
    }

    @Test
    public void testBuildAddUserRoleDTO() {
        UserRoleAddReqDTO userRoleAddReqDTO = new UserRoleAddReqDTO();
        userRoleAddReqDTO.setUserName("111");
        userRoleAddReqDTO.setRoleName("222");

        UserRoleDTO userRoleDTO = domain.buildAddUserRoleDTO(userRoleAddReqDTO);

        Assert.assertEquals(userRoleAddReqDTO.getUserName(), userRoleDTO.getUserName());
        Assert.assertEquals(userRoleAddReqDTO.getRoleName(), userRoleDTO.getRoleName());
    }

    @Test
    public void testBuildAddBatchUserRoleDTOList() {
        List<UserRoleAddReqDTO> userRoleAddReqDTOList = Lists.newArrayList();
        UserRoleAddReqDTO userRoleAddReqDTO1 = new UserRoleAddReqDTO();
        userRoleAddReqDTO1.setUserName("111");
        UserRoleAddReqDTO userRoleAddReqDTO2 = new UserRoleAddReqDTO();
        userRoleAddReqDTO1.setUserName("222");
        userRoleAddReqDTOList.add(userRoleAddReqDTO1);
        userRoleAddReqDTOList.add(userRoleAddReqDTO2);

        List<UserRoleDTO> userRoleDTOList = domain.buildAddBatchUserRoleDTOList(userRoleAddReqDTOList);

        Assert.assertEquals(userRoleAddReqDTOList.size(), userRoleDTOList.size());
        Assert.assertEquals(userRoleAddReqDTOList.get(0).getUserName(), userRoleDTOList.get(0).getUserName());
        Assert.assertEquals(userRoleAddReqDTOList.get(1).getUserName(), userRoleDTOList.get(1).getUserName());
    }

    @Test
    public void testBuildModifyUserRoleDTO() {
        UserRoleModifyReqDTO userRoleModifyReqDTO = new UserRoleModifyReqDTO();
        userRoleModifyReqDTO.setId("1");

        UserRoleDTO userRoleDTO = domain.buildModifyUserRoleDTO(userRoleModifyReqDTO);

        Assert.assertEquals(userRoleModifyReqDTO.getId(), userRoleDTO.getId());
    }

    @Test
    public void testBuildRemoveUserRoleDTO() {
        UserRoleRemoveReqDTO userRoleRemoveReqDTO = new UserRoleRemoveReqDTO();
        userRoleRemoveReqDTO.setId("1");

        UserRoleDTO userRoleDTO = domain.buildRemoveUserRoleDTO(userRoleRemoveReqDTO);

        Assert.assertEquals(userRoleRemoveReqDTO.getId(), userRoleDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserRoleListResDTOList() {
        List<UserRoleDTO> userRoleDTOList = Lists.newArrayList();

        domain.transferUserRoleListResDTOList(userRoleDTOList);

        UserRoleDTO userRoleDTO1 = new UserRoleDTO();
        userRoleDTO1.setId("1");
        UserRoleDTO userRoleDTO2 = new UserRoleDTO();
        userRoleDTO1.setId("2");
        userRoleDTOList.add(userRoleDTO1);
        userRoleDTOList.add(userRoleDTO2);

        List<UserRoleListResDTO> userRoleListResDTOList = domain.transferUserRoleListResDTOList(userRoleDTOList);

        Assert.assertEquals(userRoleDTOList.size(), userRoleListResDTOList.size());
        Assert.assertEquals(userRoleDTOList.get(0).getId(), userRoleListResDTOList.get(0).getId());
        Assert.assertEquals(userRoleDTOList.get(1).getId(), userRoleListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserRoleListResDTO() {
        domain.transferUserRoleListResDTO(null);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId("1");

        UserRoleListResDTO userRoleListResDTO = domain.transferUserRoleListResDTO(userRoleDTO);

        Assert.assertEquals(userRoleDTO.getId(), userRoleListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserRolePageResDTOPage() {
        Page<UserRoleDTO> userRoleDTOPage = new Page<>();

        domain.transferUserRolePageResDTOPage(userRoleDTOPage);

        userRoleDTOPage.setTotal(100);
        userRoleDTOPage.setCurrent(10);
        userRoleDTOPage.setSize(10);

        List<UserRoleDTO> userRoleDTOList = Lists.newArrayList();
        UserRoleDTO userRoleDTO1 = new UserRoleDTO();
        userRoleDTO1.setId("1");
        UserRoleDTO userRoleDTO2 = new UserRoleDTO();
        userRoleDTO1.setId("2");
        userRoleDTOList.add(userRoleDTO1);
        userRoleDTOList.add(userRoleDTO2);

        userRoleDTOPage.setRecords(userRoleDTOList);


        Page<UserRolePageResDTO> userRolePageResDTOPage = domain.transferUserRolePageResDTOPage(userRoleDTOPage);

        Assert.assertEquals(userRoleDTOPage.getTotal(), userRolePageResDTOPage.getTotal());
        Assert.assertEquals(userRoleDTOPage.getCurrent(), userRolePageResDTOPage.getCurrent());
        Assert.assertEquals(userRoleDTOPage.getSize(), userRolePageResDTOPage.getSize());
        Assert.assertEquals(userRoleDTOPage.getRecords().size(), userRolePageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserRoleShowResDTO() {
        domain.transferUserRoleShowResDTO(null);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId("1");

        UserRoleShowResDTO userRoleShowResDTO = domain.transferUserRoleShowResDTO(userRoleDTO);

        Assert.assertEquals(userRoleDTO.getId(), userRoleShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserRoleShowResDTOList() {
        List<UserRoleDTO> userRoleDTOList = Lists.newArrayList();

        domain.transferUserRoleShowResDTOList(userRoleDTOList);

        UserRoleDTO userRoleDTO1 = new UserRoleDTO();
        userRoleDTO1.setId("1");
        UserRoleDTO userRoleDTO2 = new UserRoleDTO();
        userRoleDTO1.setId("2");
        userRoleDTOList.add(userRoleDTO1);
        userRoleDTOList.add(userRoleDTO2);

        List<UserRoleShowResDTO> userRoleShowResDTOList = domain.transferUserRoleShowResDTOList(userRoleDTOList);

        Assert.assertEquals(userRoleDTOList.size(), userRoleShowResDTOList.size());
        Assert.assertEquals(userRoleDTOList.get(0).getId(), userRoleShowResDTOList.get(0).getId());
        Assert.assertEquals(userRoleDTOList.get(1).getId(), userRoleShowResDTOList.get(1).getId());
    }
}

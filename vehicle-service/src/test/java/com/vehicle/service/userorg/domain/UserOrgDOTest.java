package com.vehicle.service.userorg.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
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
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 用户机构 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class UserOrgDOTest extends BaseJUnitTester<UserOrgDO> {

    @Test(expected = MessageException.class)
    public void testCheckUserOrgAddReqDTO() {
        domain.checkUserOrgAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserOrgAddReqDTOList() {
        domain.checkUserOrgAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckUserOrgModifyReqDTO() {
        domain.checkUserOrgModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckUserOrgRemoveReqDTO() {
        domain.checkUserOrgRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        UserOrgListReqDTO userOrgListReqDTO = new UserOrgListReqDTO();
        userOrgListReqDTO.setId("1");

        UserOrgDTO userOrgDTO = domain.buildListParamsDTO(userOrgListReqDTO);

        Assert.assertEquals(userOrgListReqDTO.getId(), userOrgDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        UserOrgPageReqDTO userOrgPageReqDTO = new UserOrgPageReqDTO();
        userOrgPageReqDTO.setId("1");

        UserOrgDTO userOrgDTO = domain.buildPageParamsDTO(userOrgPageReqDTO);

        Assert.assertEquals(userOrgPageReqDTO.getId(), userOrgDTO.getId());
    }

    @Test
    public void testBuildAddUserOrgDTO() {
        UserOrgAddReqDTO userOrgAddReqDTO = new UserOrgAddReqDTO();
        userOrgAddReqDTO.setOrgName("111");

        UserOrgDTO userOrgDTO = domain.buildAddUserOrgDTO(userOrgAddReqDTO);

        Assert.assertEquals(userOrgAddReqDTO.getOrgName(), userOrgDTO.getOrgName());
    }

    @Test
    public void testBuildAddBatchUserOrgDTOList() {
        List<UserOrgAddReqDTO> userOrgAddReqDTOList = Lists.newArrayList();
        UserOrgAddReqDTO userOrgAddReqDTO1 = new UserOrgAddReqDTO();
        userOrgAddReqDTO1.setOrgName("111");
        UserOrgAddReqDTO userOrgAddReqDTO2 = new UserOrgAddReqDTO();
        userOrgAddReqDTO1.setOrgName("222");
        userOrgAddReqDTOList.add(userOrgAddReqDTO1);
        userOrgAddReqDTOList.add(userOrgAddReqDTO2);

        List<UserOrgDTO> userOrgDTOList = domain.buildAddBatchUserOrgDTOList(userOrgAddReqDTOList);

        Assert.assertEquals(userOrgAddReqDTOList.size(), userOrgDTOList.size());
        Assert.assertEquals(userOrgAddReqDTOList.get(0).getOrgName(), userOrgDTOList.get(0).getOrgName());
        Assert.assertEquals(userOrgAddReqDTOList.get(1).getOrgName(), userOrgDTOList.get(1).getOrgName());
    }

    @Test
    public void testBuildModifyUserOrgDTO() {
        UserOrgModifyReqDTO userOrgModifyReqDTO = new UserOrgModifyReqDTO();
        userOrgModifyReqDTO.setId("1");

        UserOrgDTO userOrgDTO = domain.buildModifyUserOrgDTO(userOrgModifyReqDTO);

        Assert.assertEquals(userOrgModifyReqDTO.getId(), userOrgDTO.getId());
    }

    @Test
    public void testBuildRemoveUserOrgDTO() {
        UserOrgRemoveReqDTO userOrgRemoveReqDTO = new UserOrgRemoveReqDTO();
        userOrgRemoveReqDTO.setId("1");

        UserOrgDTO userOrgDTO = domain.buildRemoveUserOrgDTO(userOrgRemoveReqDTO);

        Assert.assertEquals(userOrgRemoveReqDTO.getId(), userOrgDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserOrgListResDTOList() {
        List<UserOrgDTO> userOrgDTOList = Lists.newArrayList();

        domain.transferUserOrgListResDTOList(userOrgDTOList);

        UserOrgDTO userOrgDTO1 = new UserOrgDTO();
        userOrgDTO1.setId("1");
        UserOrgDTO userOrgDTO2 = new UserOrgDTO();
        userOrgDTO1.setId("2");
        userOrgDTOList.add(userOrgDTO1);
        userOrgDTOList.add(userOrgDTO2);

        List<UserOrgListResDTO> userOrgListResDTOList = domain.transferUserOrgListResDTOList(userOrgDTOList);

        Assert.assertEquals(userOrgDTOList.size(), userOrgListResDTOList.size());
        Assert.assertEquals(userOrgDTOList.get(0).getId(), userOrgListResDTOList.get(0).getId());
        Assert.assertEquals(userOrgDTOList.get(1).getId(), userOrgListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserOrgListResDTO() {
        domain.transferUserOrgListResDTO(null);

        UserOrgDTO userOrgDTO = new UserOrgDTO();
        userOrgDTO.setId("1");

        UserOrgListResDTO userOrgListResDTO = domain.transferUserOrgListResDTO(userOrgDTO);

        Assert.assertEquals(userOrgDTO.getId(), userOrgListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserOrgPageResDTOPage() {
        Page<UserOrgDTO> userOrgDTOPage = new Page<>();

        domain.transferUserOrgPageResDTOPage(userOrgDTOPage);

        userOrgDTOPage.setTotal(100);
        userOrgDTOPage.setCurrent(10);
        userOrgDTOPage.setSize(10);

        List<UserOrgDTO> userOrgDTOList = Lists.newArrayList();
        UserOrgDTO userOrgDTO1 = new UserOrgDTO();
        userOrgDTO1.setId("1");
        UserOrgDTO userOrgDTO2 = new UserOrgDTO();
        userOrgDTO1.setId("2");
        userOrgDTOList.add(userOrgDTO1);
        userOrgDTOList.add(userOrgDTO2);

        userOrgDTOPage.setRecords(userOrgDTOList);


        Page<UserOrgPageResDTO> userOrgPageResDTOPage = domain.transferUserOrgPageResDTOPage(userOrgDTOPage);

        Assert.assertEquals(userOrgDTOPage.getTotal(), userOrgPageResDTOPage.getTotal());
        Assert.assertEquals(userOrgDTOPage.getCurrent(), userOrgPageResDTOPage.getCurrent());
        Assert.assertEquals(userOrgDTOPage.getSize(), userOrgPageResDTOPage.getSize());
        Assert.assertEquals(userOrgDTOPage.getRecords().size(), userOrgPageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserOrgShowResDTO() {
        domain.transferUserOrgShowResDTO(null);

        UserOrgDTO userOrgDTO = new UserOrgDTO();
        userOrgDTO.setId("1");

        UserOrgShowResDTO userOrgShowResDTO = domain.transferUserOrgShowResDTO(userOrgDTO);

        Assert.assertEquals(userOrgDTO.getId(), userOrgShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferUserOrgShowResDTOList() {
        List<UserOrgDTO> userOrgDTOList = Lists.newArrayList();

        domain.transferUserOrgShowResDTOList(userOrgDTOList);

        UserOrgDTO userOrgDTO1 = new UserOrgDTO();
        userOrgDTO1.setId("1");
        UserOrgDTO userOrgDTO2 = new UserOrgDTO();
        userOrgDTO1.setId("2");
        userOrgDTOList.add(userOrgDTO1);
        userOrgDTOList.add(userOrgDTO2);

        List<UserOrgShowResDTO> userOrgShowResDTOList = domain.transferUserOrgShowResDTOList(userOrgDTOList);

        Assert.assertEquals(userOrgDTOList.size(), userOrgShowResDTOList.size());
        Assert.assertEquals(userOrgDTOList.get(0).getId(), userOrgShowResDTOList.get(0).getId());
        Assert.assertEquals(userOrgDTOList.get(1).getId(), userOrgShowResDTOList.get(1).getId());
    }
}

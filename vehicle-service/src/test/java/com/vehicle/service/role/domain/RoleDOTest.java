package com.vehicle.service.role.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.role.dto.RoleAddReqDTO;
import com.vehicle.service.role.dto.RoleDTO;
import com.vehicle.service.role.dto.RoleListReqDTO;
import com.vehicle.service.role.dto.RoleListResDTO;
import com.vehicle.service.role.dto.RoleModifyReqDTO;
import com.vehicle.service.role.dto.RolePageResDTO;
import com.vehicle.service.role.dto.RoleRemoveReqDTO;
import com.vehicle.service.role.dto.RoleShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 角色 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class RoleDOTest extends BaseJUnitTester<RoleDO> {

    @Test(expected = MessageException.class)
    public void testCheckRoleAddReqDTO() {
        domain.checkRoleAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleAddReqDTOList() {
        domain.checkRoleAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleModifyReqDTO() {
        domain.checkRoleModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleRemoveReqDTO() {
        domain.checkRoleRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        RoleListReqDTO roleListReqDTO = new RoleListReqDTO();
        roleListReqDTO.setId("1");

        RoleDTO roleDTO = domain.buildListParamsDTO(roleListReqDTO);

        Assert.assertEquals(roleListReqDTO.getId(), roleDTO.getId());
    }

    @Test
    public void testBuildAddRoleDTO() {
        RoleAddReqDTO roleAddReqDTO = new RoleAddReqDTO();
        roleAddReqDTO.setName("1L");

        RoleDTO roleDTO = domain.buildAddRoleDTO(roleAddReqDTO);

        Assert.assertEquals(roleAddReqDTO.getName(), roleDTO.getName());
    }

    @Test
    public void testBuildAddBatchRoleDTOList() {
        List<RoleAddReqDTO> roleAddReqDTOList = Lists.newArrayList();
        RoleAddReqDTO roleAddReqDTO1 = new RoleAddReqDTO();
        roleAddReqDTO1.setName("1L");
        RoleAddReqDTO roleAddReqDTO2 = new RoleAddReqDTO();
        roleAddReqDTO1.setName("2L");
        roleAddReqDTOList.add(roleAddReqDTO1);
        roleAddReqDTOList.add(roleAddReqDTO2);

        List<RoleDTO> roleDTOList = domain.buildAddBatchRoleDTOList(roleAddReqDTOList);

        Assert.assertEquals(roleAddReqDTOList.size(), roleDTOList.size());
        Assert.assertEquals(roleAddReqDTOList.get(0).getName(), roleDTOList.get(0).getName());
        Assert.assertEquals(roleAddReqDTOList.get(1).getName(), roleDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyRoleDTO() {
        RoleModifyReqDTO roleModifyReqDTO = new RoleModifyReqDTO();
        roleModifyReqDTO.setId("1");

        RoleDTO roleDTO = domain.buildModifyRoleDTO(roleModifyReqDTO);

        Assert.assertEquals(roleModifyReqDTO.getId(), roleDTO.getId());
    }

    @Test
    public void testBuildRemoveRoleDTO() {
        RoleRemoveReqDTO roleRemoveReqDTO = new RoleRemoveReqDTO();
        roleRemoveReqDTO.setId("1");

        RoleDTO roleDTO = domain.buildRemoveRoleDTO(roleRemoveReqDTO);

        Assert.assertEquals(roleRemoveReqDTO.getId(), roleDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleListResDTOList() {
        List<RoleDTO> roleDTOList = Lists.newArrayList();

        domain.transferRoleListResDTOList(roleDTOList);

        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId("1");
        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO1.setId("2");
        roleDTOList.add(roleDTO1);
        roleDTOList.add(roleDTO2);

        List<RoleListResDTO> roleListResDTOList = domain.transferRoleListResDTOList(roleDTOList);

        Assert.assertEquals(roleDTOList.size(), roleListResDTOList.size());
        Assert.assertEquals(roleDTOList.get(0).getId(), roleListResDTOList.get(0).getId());
        Assert.assertEquals(roleDTOList.get(1).getId(), roleListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleListResDTO() {
        domain.transferRoleListResDTO(null);

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId("1");

        RoleListResDTO roleListResDTO = domain.transferRoleListResDTO(roleDTO);

        Assert.assertEquals(roleDTO.getId(), roleListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRolePageResDTOPage() {
        Page<RoleDTO> roleDTOPage = new Page<>();

        domain.transferRolePageResDTOPage(roleDTOPage);

        roleDTOPage.setTotal(100);
        roleDTOPage.setCurrent(10);
        roleDTOPage.setSize(10);

        List<RoleDTO> roleDTOList = Lists.newArrayList();
        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId("1");
        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO1.setId("2");
        roleDTOList.add(roleDTO1);
        roleDTOList.add(roleDTO2);

        roleDTOPage.setRecords(roleDTOList);


        Page<RolePageResDTO> rolePageResDTOPage = domain.transferRolePageResDTOPage(roleDTOPage);

        Assert.assertEquals(roleDTOPage.getTotal(), rolePageResDTOPage.getTotal());
        Assert.assertEquals(roleDTOPage.getCurrent(), rolePageResDTOPage.getCurrent());
        Assert.assertEquals(roleDTOPage.getSize(), rolePageResDTOPage.getSize());
        Assert.assertEquals(roleDTOPage.getRecords().size(), rolePageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleShowResDTO() {
        domain.transferRoleShowResDTO(null);

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId("1");

        RoleShowResDTO roleShowResDTO = domain.transferRoleShowResDTO(roleDTO);

        Assert.assertEquals(roleDTO.getId(), roleShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleShowResDTOList() {
        List<RoleDTO> roleDTOList = Lists.newArrayList();

        domain.transferRoleShowResDTOList(roleDTOList);

        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId("1");
        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO1.setId("2");
        roleDTOList.add(roleDTO1);
        roleDTOList.add(roleDTO2);

        List<RoleShowResDTO> roleShowResDTOList = domain.transferRoleShowResDTOList(roleDTOList);

        Assert.assertEquals(roleDTOList.size(), roleShowResDTOList.size());
        Assert.assertEquals(roleDTOList.get(0).getId(), roleShowResDTOList.get(0).getId());
        Assert.assertEquals(roleDTOList.get(1).getId(), roleShowResDTOList.get(1).getId());
    }
}

package com.vehicle.service.roleresource.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.roleresource.dto.RoleResourceAddReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceListResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceModifyReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourcePageResDTO;
import com.vehicle.service.roleresource.dto.RoleResourceRemoveReqDTO;
import com.vehicle.service.roleresource.dto.RoleResourceShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 角色资源 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class RoleResourceDOTest extends BaseJUnitTester<RoleResourceDO> {

    @Test(expected = MessageException.class)
    public void testCheckRoleResourceAddReqDTO() {
        domain.checkRoleResourceAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleResourceAddReqDTOList() {
        domain.checkRoleResourceAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleResourceModifyReqDTO() {
        domain.checkRoleResourceModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckRoleResourceRemoveReqDTO() {
        domain.checkRoleResourceRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        RoleResourceListReqDTO roleResourceListReqDTO = new RoleResourceListReqDTO();
        roleResourceListReqDTO.setId("1");

        RoleResourceDTO roleResourceDTO = domain.buildListParamsDTO(roleResourceListReqDTO);

        Assert.assertEquals(roleResourceListReqDTO.getId(), roleResourceDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        RoleResourcePageReqDTO roleResourcePageReqDTO = new RoleResourcePageReqDTO();
        roleResourcePageReqDTO.setId("1");

        RoleResourceDTO roleResourceDTO = domain.buildPageParamsDTO(roleResourcePageReqDTO);

        Assert.assertEquals(roleResourcePageReqDTO.getId(), roleResourceDTO.getId());
    }

    @Test
    public void testBuildAddRoleResourceDTO() {
        RoleResourceAddReqDTO roleResourceAddReqDTO = new RoleResourceAddReqDTO();
        roleResourceAddReqDTO.setRoleName("111");

        RoleResourceDTO roleResourceDTO = domain.buildAddRoleResourceDTO(roleResourceAddReqDTO);

        Assert.assertEquals(roleResourceAddReqDTO.getRoleName(), roleResourceDTO.getRoleName());
    }

    @Test
    public void testBuildModifyRoleResourceDTO() {
        RoleResourceModifyReqDTO roleResourceModifyReqDTO = new RoleResourceModifyReqDTO();
        roleResourceModifyReqDTO.setId("1");

        RoleResourceDTO roleResourceDTO = domain.buildModifyRoleResourceDTO(roleResourceModifyReqDTO);

        Assert.assertEquals(roleResourceModifyReqDTO.getId(), roleResourceDTO.getId());
    }

    @Test
    public void testBuildRemoveRoleResourceDTO() {
        RoleResourceRemoveReqDTO roleResourceRemoveReqDTO = new RoleResourceRemoveReqDTO();
        roleResourceRemoveReqDTO.setId("1");

        RoleResourceDTO roleResourceDTO = domain.buildRemoveRoleResourceDTO(roleResourceRemoveReqDTO);

        Assert.assertEquals(roleResourceRemoveReqDTO.getId(), roleResourceDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleResourceListResDTOList() {
        List<RoleResourceDTO> roleResourceDTOList = Lists.newArrayList();

        domain.transferRoleResourceListResDTOList(roleResourceDTOList);

        RoleResourceDTO roleResourceDTO1 = new RoleResourceDTO();
        roleResourceDTO1.setId("1");
        RoleResourceDTO roleResourceDTO2 = new RoleResourceDTO();
        roleResourceDTO1.setId("2");
        roleResourceDTOList.add(roleResourceDTO1);
        roleResourceDTOList.add(roleResourceDTO2);

        List<RoleResourceListResDTO> roleResourceListResDTOList = domain.transferRoleResourceListResDTOList(roleResourceDTOList);

        Assert.assertEquals(roleResourceDTOList.size(), roleResourceListResDTOList.size());
        Assert.assertEquals(roleResourceDTOList.get(0).getId(), roleResourceListResDTOList.get(0).getId());
        Assert.assertEquals(roleResourceDTOList.get(1).getId(), roleResourceListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleResourceListResDTO() {
        domain.transferRoleResourceListResDTO(null);

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setId("1");

        RoleResourceListResDTO roleResourceListResDTO = domain.transferRoleResourceListResDTO(roleResourceDTO);

        Assert.assertEquals(roleResourceDTO.getId(), roleResourceListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleResourcePageResDTOPage() {
        Page<RoleResourceDTO> roleResourceDTOPage = new Page<>();

        domain.transferRoleResourcePageResDTOPage(roleResourceDTOPage);

        roleResourceDTOPage.setTotal(100);
        roleResourceDTOPage.setCurrent(10);
        roleResourceDTOPage.setSize(10);

        List<RoleResourceDTO> roleResourceDTOList = Lists.newArrayList();
        RoleResourceDTO roleResourceDTO1 = new RoleResourceDTO();
        roleResourceDTO1.setId("1");
        RoleResourceDTO roleResourceDTO2 = new RoleResourceDTO();
        roleResourceDTO1.setId("2");
        roleResourceDTOList.add(roleResourceDTO1);
        roleResourceDTOList.add(roleResourceDTO2);

        roleResourceDTOPage.setRecords(roleResourceDTOList);


        Page<RoleResourcePageResDTO> roleResourcePageResDTOPage = domain.transferRoleResourcePageResDTOPage(roleResourceDTOPage);

        Assert.assertEquals(roleResourceDTOPage.getTotal(), roleResourcePageResDTOPage.getTotal());
        Assert.assertEquals(roleResourceDTOPage.getCurrent(), roleResourcePageResDTOPage.getCurrent());
        Assert.assertEquals(roleResourceDTOPage.getSize(), roleResourcePageResDTOPage.getSize());
        Assert.assertEquals(roleResourceDTOPage.getRecords().size(), roleResourcePageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleResourceShowResDTO() {
        domain.transferRoleResourceShowResDTO(null);

        RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
        roleResourceDTO.setId("1");

        RoleResourceShowResDTO roleResourceShowResDTO = domain.transferRoleResourceShowResDTO(roleResourceDTO);

        Assert.assertEquals(roleResourceDTO.getId(), roleResourceShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferRoleResourceShowResDTOList() {
        List<RoleResourceDTO> roleResourceDTOList = Lists.newArrayList();

        domain.transferRoleResourceShowResDTOList(roleResourceDTOList);

        RoleResourceDTO roleResourceDTO1 = new RoleResourceDTO();
        roleResourceDTO1.setId("1");
        RoleResourceDTO roleResourceDTO2 = new RoleResourceDTO();
        roleResourceDTO1.setId("2");
        roleResourceDTOList.add(roleResourceDTO1);
        roleResourceDTOList.add(roleResourceDTO2);

        List<RoleResourceShowResDTO> roleResourceShowResDTOList = domain.transferRoleResourceShowResDTOList(roleResourceDTOList);

        Assert.assertEquals(roleResourceDTOList.size(), roleResourceShowResDTOList.size());
        Assert.assertEquals(roleResourceDTOList.get(0).getId(), roleResourceShowResDTOList.get(0).getId());
        Assert.assertEquals(roleResourceDTOList.get(1).getId(), roleResourceShowResDTOList.get(1).getId());
    }
}

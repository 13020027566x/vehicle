package com.vehicle.service.organization.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 机构 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class OrganizationDOTest extends BaseJUnitTester<OrganizationDO> {

    @Test(expected = MessageException.class)
    public void testCheckOrganizationAddReqDTO() {
        domain.checkOrganizationAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckOrganizationAddReqDTOList() {
        domain.checkOrganizationAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckOrganizationModifyReqDTO() {
        domain.checkOrganizationModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckOrganizationRemoveReqDTO() {
        domain.checkOrganizationRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        OrganizationListReqDTO organizationListReqDTO = new OrganizationListReqDTO();
        organizationListReqDTO.setId("1");

        OrganizationDTO organizationDTO = domain.buildListParamsDTO(organizationListReqDTO);

        Assert.assertEquals(organizationListReqDTO.getId(), organizationDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        OrganizationPageReqDTO organizationPageReqDTO = new OrganizationPageReqDTO();
        organizationPageReqDTO.setId("1");

        OrganizationDTO organizationDTO = domain.buildPageParamsDTO(organizationPageReqDTO);

        Assert.assertEquals(organizationPageReqDTO.getId(), organizationDTO.getId());
    }

    @Test
    public void testBuildAddOrganizationDTO() {
        OrganizationAddReqDTO organizationAddReqDTO = new OrganizationAddReqDTO();
        organizationAddReqDTO.setName("111");

        OrganizationDTO organizationDTO = domain.buildAddOrganizationDTO(organizationAddReqDTO);

        Assert.assertEquals(organizationAddReqDTO.getName(), organizationDTO.getName());
    }

    @Test
    public void testBuildAddBatchOrganizationDTOList() {
        List<OrganizationAddReqDTO> organizationAddReqDTOList = Lists.newArrayList();
        OrganizationAddReqDTO organizationAddReqDTO1 = new OrganizationAddReqDTO();
        organizationAddReqDTO1.setName("111");
        OrganizationAddReqDTO organizationAddReqDTO2 = new OrganizationAddReqDTO();
        organizationAddReqDTO1.setName("222");
        organizationAddReqDTOList.add(organizationAddReqDTO1);
        organizationAddReqDTOList.add(organizationAddReqDTO2);

        List<OrganizationDTO> organizationDTOList = domain.buildAddBatchOrganizationDTOList(organizationAddReqDTOList);

        Assert.assertEquals(organizationAddReqDTOList.size(), organizationDTOList.size());
        Assert.assertEquals(organizationAddReqDTOList.get(0).getName(), organizationDTOList.get(0).getName());
        Assert.assertEquals(organizationAddReqDTOList.get(1).getName(), organizationDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyOrganizationDTO() {
        OrganizationModifyReqDTO organizationModifyReqDTO = new OrganizationModifyReqDTO();
        organizationModifyReqDTO.setId("1");

        OrganizationDTO organizationDTO = domain.buildModifyOrganizationDTO(organizationModifyReqDTO);

        Assert.assertEquals(organizationModifyReqDTO.getId(), organizationDTO.getId());
    }

    @Test
    public void testBuildRemoveOrganizationDTO() {
        OrganizationRemoveReqDTO organizationRemoveReqDTO = new OrganizationRemoveReqDTO();
        organizationRemoveReqDTO.setId("1");

        OrganizationDTO organizationDTO = domain.buildRemoveOrganizationDTO(organizationRemoveReqDTO);

        Assert.assertEquals(organizationRemoveReqDTO.getId(), organizationDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferOrganizationListResDTOList() {
        List<OrganizationDTO> organizationDTOList = Lists.newArrayList();

        domain.transferOrganizationListResDTOList(organizationDTOList);

        OrganizationDTO organizationDTO1 = new OrganizationDTO();
        organizationDTO1.setId("1");
        OrganizationDTO organizationDTO2 = new OrganizationDTO();
        organizationDTO1.setId("2");
        organizationDTOList.add(organizationDTO1);
        organizationDTOList.add(organizationDTO2);

        List<OrganizationListResDTO> organizationListResDTOList = domain.transferOrganizationListResDTOList(organizationDTOList);

        Assert.assertEquals(organizationDTOList.size(), organizationListResDTOList.size());
        Assert.assertEquals(organizationDTOList.get(0).getId(), organizationListResDTOList.get(0).getId());
        Assert.assertEquals(organizationDTOList.get(1).getId(), organizationListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferOrganizationListResDTO() {
        domain.transferOrganizationListResDTO(null);

        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId("1");

        OrganizationListResDTO organizationListResDTO = domain.transferOrganizationListResDTO(organizationDTO);

        Assert.assertEquals(organizationDTO.getId(), organizationListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferOrganizationPageResDTOPage() {
        Page<OrganizationDTO> organizationDTOPage = new Page<>();

        domain.transferOrganizationPageResDTOPage(organizationDTOPage);

        organizationDTOPage.setTotal(100);
        organizationDTOPage.setCurrent(10);
        organizationDTOPage.setSize(10);

        List<OrganizationDTO> organizationDTOList = Lists.newArrayList();
        OrganizationDTO organizationDTO1 = new OrganizationDTO();
        organizationDTO1.setId("1");
        OrganizationDTO organizationDTO2 = new OrganizationDTO();
        organizationDTO1.setId("2");
        organizationDTOList.add(organizationDTO1);
        organizationDTOList.add(organizationDTO2);

        organizationDTOPage.setRecords(organizationDTOList);


        Page<OrganizationPageResDTO> organizationPageResDTOPage = domain.transferOrganizationPageResDTOPage(organizationDTOPage);

        Assert.assertEquals(organizationDTOPage.getTotal(), organizationPageResDTOPage.getTotal());
        Assert.assertEquals(organizationDTOPage.getCurrent(), organizationPageResDTOPage.getCurrent());
        Assert.assertEquals(organizationDTOPage.getSize(), organizationPageResDTOPage.getSize());
        Assert.assertEquals(organizationDTOPage.getRecords().size(), organizationPageResDTOPage.getRecords().size());
    }

    @Test
    public void testTransferOrganizationShowResDTO() {
        OrganizationDTO parentOrganizationDTO = new OrganizationDTO();
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId("1");

        OrganizationShowResDTO organizationShowResDTO = domain.transferOrganizationShowResDTO(organizationDTO, parentOrganizationDTO);

        Assert.assertEquals(organizationDTO.getId(), organizationShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferOrganizationShowResDTOList() {
        List<OrganizationDTO> organizationDTOList = Lists.newArrayList();

        domain.transferOrganizationShowResDTOList(organizationDTOList);

        OrganizationDTO organizationDTO1 = new OrganizationDTO();
        organizationDTO1.setId("1");
        OrganizationDTO organizationDTO2 = new OrganizationDTO();
        organizationDTO1.setId("2");
        organizationDTOList.add(organizationDTO1);
        organizationDTOList.add(organizationDTO2);

        List<OrganizationShowResDTO> organizationShowResDTOList = domain.transferOrganizationShowResDTOList(organizationDTOList);

        Assert.assertEquals(organizationDTOList.size(), organizationShowResDTOList.size());
        Assert.assertEquals(organizationDTOList.get(0).getId(), organizationShowResDTOList.get(0).getId());
        Assert.assertEquals(organizationDTOList.get(1).getId(), organizationShowResDTOList.get(1).getId());
    }
}

package com.vehicle.service.resource.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.resource.dto.ResourceAddReqDTO;
import com.vehicle.service.resource.dto.ResourceDTO;
import com.vehicle.service.resource.dto.ResourceListReqDTO;
import com.vehicle.service.resource.dto.ResourceListResDTO;
import com.vehicle.service.resource.dto.ResourceModifyReqDTO;
import com.vehicle.service.resource.dto.ResourcePageReqDTO;
import com.vehicle.service.resource.dto.ResourcePageResDTO;
import com.vehicle.service.resource.dto.ResourceRemoveReqDTO;
import com.vehicle.service.resource.dto.ResourceShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 菜单 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class ResourceDOTest extends BaseJUnitTester<ResourceDO> {

    @Test(expected = MessageException.class)
    public void testCheckResourceAddReqDTO() {
        domain.checkResourceAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckResourceAddReqDTOList() {
        domain.checkResourceAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckResourceModifyReqDTO() {
        domain.checkResourceModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckResourceRemoveReqDTO() {
        domain.checkResourceRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        ResourceListReqDTO resourceListReqDTO = new ResourceListReqDTO();
        resourceListReqDTO.setId("1");

        ResourceDTO resourceDTO = domain.buildListParamsDTO(resourceListReqDTO);

        Assert.assertEquals(resourceListReqDTO.getId(), resourceDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        ResourcePageReqDTO resourcePageReqDTO = new ResourcePageReqDTO();
        resourcePageReqDTO.setId("1");

        ResourceDTO resourceDTO = domain.buildPageParamsDTO(resourcePageReqDTO);

        Assert.assertEquals(resourcePageReqDTO.getId(), resourceDTO.getId());
    }

    @Test
    public void testBuildAddResourceDTO() {
        ResourceAddReqDTO resourceAddReqDTO = new ResourceAddReqDTO();
        resourceAddReqDTO.setName("111");

        ResourceDTO resourceDTO = domain.buildAddResourceDTO(resourceAddReqDTO);

        Assert.assertEquals(resourceAddReqDTO.getName(), resourceDTO.getName());
    }

    @Test
    public void testBuildAddBatchResourceDTOList() {
        List<ResourceAddReqDTO> resourceAddReqDTOList = Lists.newArrayList();
        ResourceAddReqDTO resourceAddReqDTO1 = new ResourceAddReqDTO();
        resourceAddReqDTO1.setName("1");
        ResourceAddReqDTO resourceAddReqDTO2 = new ResourceAddReqDTO();
        resourceAddReqDTO1.setName("2");
        resourceAddReqDTOList.add(resourceAddReqDTO1);
        resourceAddReqDTOList.add(resourceAddReqDTO2);

        List<ResourceDTO> resourceDTOList = domain.buildAddBatchResourceDTOList(resourceAddReqDTOList);

        Assert.assertEquals(resourceAddReqDTOList.size(), resourceDTOList.size());
        Assert.assertEquals(resourceAddReqDTOList.get(0).getName(), resourceDTOList.get(0).getName());
        Assert.assertEquals(resourceAddReqDTOList.get(1).getName(), resourceDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyResourceDTO() {
        ResourceModifyReqDTO resourceModifyReqDTO = new ResourceModifyReqDTO();
        resourceModifyReqDTO.setId("1");

        ResourceDTO resourceDTO = domain.buildModifyResourceDTO(resourceModifyReqDTO);

        Assert.assertEquals(resourceModifyReqDTO.getId(), resourceDTO.getId());
    }

    @Test
    public void testBuildRemoveResourceDTO() {
        ResourceRemoveReqDTO resourceRemoveReqDTO = new ResourceRemoveReqDTO();
        resourceRemoveReqDTO.setId("1");

        ResourceDTO resourceDTO = domain.buildRemoveResourceDTO(resourceRemoveReqDTO);

        Assert.assertEquals(resourceRemoveReqDTO.getId(), resourceDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferResourceListResDTOList() {
        List<ResourceDTO> resourceDTOList = Lists.newArrayList();

        domain.transferResourceListResDTOList(resourceDTOList);

        ResourceDTO resourceDTO1 = new ResourceDTO();
        resourceDTO1.setId("1");
        ResourceDTO resourceDTO2 = new ResourceDTO();
        resourceDTO1.setId("2");
        resourceDTOList.add(resourceDTO1);
        resourceDTOList.add(resourceDTO2);

        List<ResourceListResDTO> resourceListResDTOList = domain.transferResourceListResDTOList(resourceDTOList);

        Assert.assertEquals(resourceDTOList.size(), resourceListResDTOList.size());
        Assert.assertEquals(resourceDTOList.get(0).getId(), resourceListResDTOList.get(0).getId());
        Assert.assertEquals(resourceDTOList.get(1).getId(), resourceListResDTOList.get(1).getId());
    }

    @Test
    public void testTransferResourceListResDTO() {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId("1");

        ResourceListResDTO resourceListResDTO = domain.transferResourceListResDTO(resourceDTO);

        Assert.assertEquals(resourceDTO.getId(), resourceListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferResourcePageResDTOPage() {
        Page<ResourceDTO> resourceDTOPage = new Page<>();

        domain.transferResourcePageResDTOPage(resourceDTOPage);

        resourceDTOPage.setTotal(100);
        resourceDTOPage.setCurrent(10);
        resourceDTOPage.setSize(10);

        List<ResourceDTO> resourceDTOList = Lists.newArrayList();
        ResourceDTO resourceDTO1 = new ResourceDTO();
        resourceDTO1.setId("1");
        ResourceDTO resourceDTO2 = new ResourceDTO();
        resourceDTO1.setId("2");
        resourceDTOList.add(resourceDTO1);
        resourceDTOList.add(resourceDTO2);

        resourceDTOPage.setRecords(resourceDTOList);


        Page<ResourcePageResDTO> resourcePageResDTOPage = domain.transferResourcePageResDTOPage(resourceDTOPage);

        Assert.assertEquals(resourceDTOPage.getTotal(), resourcePageResDTOPage.getTotal());
        Assert.assertEquals(resourceDTOPage.getCurrent(), resourcePageResDTOPage.getCurrent());
        Assert.assertEquals(resourceDTOPage.getSize(), resourcePageResDTOPage.getSize());
        Assert.assertEquals(resourceDTOPage.getRecords().size(), resourcePageResDTOPage.getRecords().size());
    }

    @Test
    public void testTransferResourceShowResDTO() {
        ResourceDTO parentResourceDTO = new ResourceDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId("1");

        ResourceShowResDTO resourceShowResDTO = domain.transferResourceShowResDTO(resourceDTO, parentResourceDTO);

        Assert.assertEquals(resourceDTO.getId(), resourceShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferResourceShowResDTOList() {
        List<ResourceDTO> resourceDTOList = Lists.newArrayList();

        domain.transferResourceShowResDTOList(resourceDTOList);

        ResourceDTO resourceDTO1 = new ResourceDTO();
        resourceDTO1.setId("1");
        ResourceDTO resourceDTO2 = new ResourceDTO();
        resourceDTO1.setId("2");
        resourceDTOList.add(resourceDTO1);
        resourceDTOList.add(resourceDTO2);

        List<ResourceShowResDTO> resourceShowResDTOList = domain.transferResourceShowResDTOList(resourceDTOList);

        Assert.assertEquals(resourceDTOList.size(), resourceShowResDTOList.size());
        Assert.assertEquals(resourceDTOList.get(0).getId(), resourceShowResDTOList.get(0).getId());
        Assert.assertEquals(resourceDTOList.get(1).getId(), resourceShowResDTOList.get(1).getId());
    }
}

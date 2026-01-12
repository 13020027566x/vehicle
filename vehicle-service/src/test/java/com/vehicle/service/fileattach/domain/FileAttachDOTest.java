package com.vehicle.service.fileattach.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 文件附件 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
public class FileAttachDOTest extends BaseJUnitTester<FileAttachDO> {

    @Test(expected = MessageException.class)
    public void testCheckFileAttachAddReqDTO() {
        domain.checkFileAttachAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckFileAttachAddReqDTOList() {
        domain.checkFileAttachAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckFileAttachModifyReqDTO() {
        domain.checkFileAttachModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckFileAttachRemoveReqDTO() {
        domain.checkFileAttachRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        FileAttachListReqDTO fileAttachListReqDTO = new FileAttachListReqDTO();
        fileAttachListReqDTO.setId("1");

        FileAttachDTO fileAttachDTO = domain.buildListParamsDTO(fileAttachListReqDTO);

        Assert.assertEquals(fileAttachListReqDTO.getId(), fileAttachDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        FileAttachPageReqDTO fileAttachPageReqDTO = new FileAttachPageReqDTO();
        fileAttachPageReqDTO.setId("1");

        FileAttachDTO fileAttachDTO = domain.buildPageParamsDTO(fileAttachPageReqDTO);

        Assert.assertEquals(fileAttachPageReqDTO.getId(), fileAttachDTO.getId());
    }

    @Test
    public void testBuildAddFileAttachDTO() {
        FileAttachAddReqDTO fileAttachAddReqDTO = new FileAttachAddReqDTO();
        fileAttachAddReqDTO.setName("111");

        FileAttachDTO fileAttachDTO = domain.buildAddFileAttachDTO(fileAttachAddReqDTO);

        Assert.assertEquals(fileAttachAddReqDTO.getName(), fileAttachDTO.getName());
    }

    @Test
    public void testBuildAddBatchFileAttachDTOList() {
        List<FileAttachAddReqDTO> fileAttachAddReqDTOList = Lists.newArrayList();
        FileAttachAddReqDTO fileAttachAddReqDTO1 = new FileAttachAddReqDTO();
        fileAttachAddReqDTO1.setName("111");
        FileAttachAddReqDTO fileAttachAddReqDTO2 = new FileAttachAddReqDTO();
        fileAttachAddReqDTO1.setName("222");
        fileAttachAddReqDTOList.add(fileAttachAddReqDTO1);
        fileAttachAddReqDTOList.add(fileAttachAddReqDTO2);

        List<FileAttachDTO> fileAttachDTOList = domain.buildAddBatchFileAttachDTOList(fileAttachAddReqDTOList);

        Assert.assertEquals(fileAttachAddReqDTOList.size(), fileAttachDTOList.size());
        Assert.assertEquals(fileAttachAddReqDTOList.get(0).getName(), fileAttachDTOList.get(0).getName());
        Assert.assertEquals(fileAttachAddReqDTOList.get(1).getName(), fileAttachDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyFileAttachDTO() {
        FileAttachModifyReqDTO fileAttachModifyReqDTO = new FileAttachModifyReqDTO();
        fileAttachModifyReqDTO.setId("1");

        FileAttachDTO fileAttachDTO = domain.buildModifyFileAttachDTO(fileAttachModifyReqDTO);

        Assert.assertEquals(fileAttachModifyReqDTO.getId(), fileAttachDTO.getId());
    }

    @Test
    public void testBuildRemoveFileAttachDTO() {
        FileAttachRemoveReqDTO fileAttachRemoveReqDTO = new FileAttachRemoveReqDTO();
        fileAttachRemoveReqDTO.setId("1");

        FileAttachDTO fileAttachDTO = domain.buildRemoveFileAttachDTO(fileAttachRemoveReqDTO);

        Assert.assertEquals(fileAttachRemoveReqDTO.getId(), fileAttachDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferFileAttachListResDTOList() {
        List<FileAttachDTO> fileAttachDTOList = Lists.newArrayList();

        domain.transferFileAttachListResDTOList(fileAttachDTOList);

        FileAttachDTO fileAttachDTO1 = new FileAttachDTO();
        fileAttachDTO1.setId("1");
        FileAttachDTO fileAttachDTO2 = new FileAttachDTO();
        fileAttachDTO1.setId("2");
        fileAttachDTOList.add(fileAttachDTO1);
        fileAttachDTOList.add(fileAttachDTO2);

        List<FileAttachListResDTO> fileAttachListResDTOList = domain.transferFileAttachListResDTOList(fileAttachDTOList);

        Assert.assertEquals(fileAttachDTOList.size(), fileAttachListResDTOList.size());
        Assert.assertEquals(fileAttachDTOList.get(0).getId(), fileAttachListResDTOList.get(0).getId());
        Assert.assertEquals(fileAttachDTOList.get(1).getId(), fileAttachListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferFileAttachListResDTO() {
        domain.transferFileAttachListResDTO(null);

        FileAttachDTO fileAttachDTO = new FileAttachDTO();
        fileAttachDTO.setId("1");

        FileAttachListResDTO fileAttachListResDTO = domain.transferFileAttachListResDTO(fileAttachDTO);

        Assert.assertEquals(fileAttachDTO.getId(), fileAttachListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferFileAttachPageResDTOPage() {
        Page<FileAttachDTO> fileAttachDTOPage = new Page<>();

        domain.transferFileAttachPageResDTOPage(fileAttachDTOPage);

        fileAttachDTOPage.setTotal(100);
        fileAttachDTOPage.setCurrent(10);
        fileAttachDTOPage.setSize(10);

        List<FileAttachDTO> fileAttachDTOList = Lists.newArrayList();
        FileAttachDTO fileAttachDTO1 = new FileAttachDTO();
        fileAttachDTO1.setId("1");
        FileAttachDTO fileAttachDTO2 = new FileAttachDTO();
        fileAttachDTO1.setId("2");
        fileAttachDTOList.add(fileAttachDTO1);
        fileAttachDTOList.add(fileAttachDTO2);

        fileAttachDTOPage.setRecords(fileAttachDTOList);


        Page<FileAttachPageResDTO> fileAttachPageResDTOPage = domain.transferFileAttachPageResDTOPage(fileAttachDTOPage);

        Assert.assertEquals(fileAttachDTOPage.getTotal(), fileAttachPageResDTOPage.getTotal());
        Assert.assertEquals(fileAttachDTOPage.getCurrent(), fileAttachPageResDTOPage.getCurrent());
        Assert.assertEquals(fileAttachDTOPage.getSize(), fileAttachPageResDTOPage.getSize());
        Assert.assertEquals(fileAttachDTOPage.getRecords().size(), fileAttachPageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferFileAttachShowResDTO() {
        domain.transferFileAttachShowResDTO(null);

        FileAttachDTO fileAttachDTO = new FileAttachDTO();
        fileAttachDTO.setId("1");

        FileAttachShowResDTO fileAttachShowResDTO = domain.transferFileAttachShowResDTO(fileAttachDTO);

        Assert.assertEquals(fileAttachDTO.getId(), fileAttachShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferFileAttachShowResDTOList() {
        List<FileAttachDTO> fileAttachDTOList = Lists.newArrayList();

        domain.transferFileAttachShowResDTOList(fileAttachDTOList);

        FileAttachDTO fileAttachDTO1 = new FileAttachDTO();
        fileAttachDTO1.setId("1");
        FileAttachDTO fileAttachDTO2 = new FileAttachDTO();
        fileAttachDTO1.setId("2");
        fileAttachDTOList.add(fileAttachDTO1);
        fileAttachDTOList.add(fileAttachDTO2);

        List<FileAttachShowResDTO> fileAttachShowResDTOList = domain.transferFileAttachShowResDTOList(fileAttachDTOList);

        Assert.assertEquals(fileAttachDTOList.size(), fileAttachShowResDTOList.size());
        Assert.assertEquals(fileAttachDTOList.get(0).getId(), fileAttachShowResDTOList.get(0).getId());
        Assert.assertEquals(fileAttachDTOList.get(1).getId(), fileAttachShowResDTOList.get(1).getId());
    }
}

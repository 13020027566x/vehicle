package com.vehicle.service.dictionary.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.dictionary.dto.DictionaryAddReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryDTO;
import com.vehicle.service.dictionary.dto.DictionaryListReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryListResDTO;
import com.vehicle.service.dictionary.dto.DictionaryModifyReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageResDTO;
import com.vehicle.service.dictionary.dto.DictionaryRemoveReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 字典 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
public class DictionaryDOTest extends BaseJUnitTester<DictionaryDO> {

    @Test(expected = MessageException.class)
    public void testCheckDictionaryAddReqDTO() {
        domain.checkDictionaryAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckDictionaryAddReqDTOList() {
        domain.checkDictionaryAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckDictionaryModifyReqDTO() {
        domain.checkDictionaryModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckDictionaryRemoveReqDTO() {
        domain.checkDictionaryRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        DictionaryListReqDTO dictionaryListReqDTO = new DictionaryListReqDTO();
        dictionaryListReqDTO.setId("1");

        DictionaryDTO dictionaryDTO = domain.buildListParamsDTO(dictionaryListReqDTO);

        Assert.assertEquals(dictionaryListReqDTO.getId(), dictionaryDTO.getId());
    }

    @Test
    public void testBuildAddDictionaryDTO() {
        DictionaryAddReqDTO dictionaryAddReqDTO = new DictionaryAddReqDTO();
        dictionaryAddReqDTO.setName("1L");

        DictionaryDTO dictionaryDTO = domain.buildAddDictionaryDTO(dictionaryAddReqDTO);

        Assert.assertEquals(dictionaryAddReqDTO.getName(), dictionaryDTO.getName());
    }

    @Test
    public void testBuildAddBatchDictionaryDTOList() {
        List<DictionaryAddReqDTO> dictionaryAddReqDTOList = Lists.newArrayList();
        DictionaryAddReqDTO dictionaryAddReqDTO1 = new DictionaryAddReqDTO();
        dictionaryAddReqDTO1.setName("1L");
        DictionaryAddReqDTO dictionaryAddReqDTO2 = new DictionaryAddReqDTO();
        dictionaryAddReqDTO1.setName("2L");
        dictionaryAddReqDTOList.add(dictionaryAddReqDTO1);
        dictionaryAddReqDTOList.add(dictionaryAddReqDTO2);

        List<DictionaryDTO> dictionaryDTOList = domain.buildAddBatchDictionaryDTOList(dictionaryAddReqDTOList);

        Assert.assertEquals(dictionaryAddReqDTOList.size(), dictionaryDTOList.size());
        Assert.assertEquals(dictionaryAddReqDTOList.get(0).getName(), dictionaryDTOList.get(0).getName());
        Assert.assertEquals(dictionaryAddReqDTOList.get(1).getName(), dictionaryDTOList.get(1).getName());
    }

    @Test
    public void testBuildModifyDictionaryDTO() {
        DictionaryModifyReqDTO dictionaryModifyReqDTO = new DictionaryModifyReqDTO();
        dictionaryModifyReqDTO.setId("1");

        DictionaryDTO dictionaryDTO = domain.buildModifyDictionaryDTO(dictionaryModifyReqDTO);

        Assert.assertEquals(dictionaryModifyReqDTO.getId(), dictionaryDTO.getId());
    }

    @Test
    public void testBuildRemoveDictionaryDTO() {
        DictionaryRemoveReqDTO dictionaryRemoveReqDTO = new DictionaryRemoveReqDTO();
        dictionaryRemoveReqDTO.setId("1");

        DictionaryDTO dictionaryDTO = domain.buildRemoveDictionaryDTO(dictionaryRemoveReqDTO);

        Assert.assertEquals(dictionaryRemoveReqDTO.getId(), dictionaryDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferDictionaryListResDTOList() {
        List<DictionaryDTO> dictionaryDTOList = Lists.newArrayList();

        domain.transferDictionaryListResDTOList(dictionaryDTOList);

        DictionaryDTO dictionaryDTO1 = new DictionaryDTO();
        dictionaryDTO1.setId("1");
        DictionaryDTO dictionaryDTO2 = new DictionaryDTO();
        dictionaryDTO1.setId("2");
        dictionaryDTOList.add(dictionaryDTO1);
        dictionaryDTOList.add(dictionaryDTO2);

        List<DictionaryListResDTO> dictionaryListResDTOList = domain.transferDictionaryListResDTOList(dictionaryDTOList);

        Assert.assertEquals(dictionaryDTOList.size(), dictionaryListResDTOList.size());
        Assert.assertEquals(dictionaryDTOList.get(0).getId(), dictionaryListResDTOList.get(0).getId());
        Assert.assertEquals(dictionaryDTOList.get(1).getId(), dictionaryListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferDictionaryListResDTO() {
        domain.transferDictionaryListResDTO(null);

        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setId("1");

        DictionaryListResDTO dictionaryListResDTO = domain.transferDictionaryListResDTO(dictionaryDTO);

        Assert.assertEquals(dictionaryDTO.getId(), dictionaryListResDTO.getId());
    }

    @Test
    public void testTransferDictionaryPageResDTOPage() {
        List<DictionaryDTO> parentDictionaryDTOList = Lists.newArrayList();
        Page<DictionaryDTO> dictionaryDTOPage = new Page<>();

        dictionaryDTOPage.setTotal(100);
        dictionaryDTOPage.setCurrent(10);
        dictionaryDTOPage.setSize(10);

        List<DictionaryDTO> dictionaryDTOList = Lists.newArrayList();
        DictionaryDTO dictionaryDTO1 = new DictionaryDTO();
        dictionaryDTO1.setId("1");
        dictionaryDTO1.setUpdateAt(System.currentTimeMillis());
        DictionaryDTO dictionaryDTO2 = new DictionaryDTO();
        dictionaryDTO1.setId("2");
        dictionaryDTO2.setUpdateAt(System.currentTimeMillis());
        dictionaryDTOList.add(dictionaryDTO1);
        dictionaryDTOList.add(dictionaryDTO2);

        dictionaryDTOPage.setRecords(dictionaryDTOList);


        Page<DictionaryPageResDTO> dictionaryPageResDTOPage = domain.transferDictionaryPageResDTOPage(dictionaryDTOPage, parentDictionaryDTOList);

        Assert.assertEquals(dictionaryDTOPage.getTotal(), dictionaryPageResDTOPage.getTotal());
        Assert.assertEquals(dictionaryDTOPage.getCurrent(), dictionaryPageResDTOPage.getCurrent());
        Assert.assertEquals(dictionaryDTOPage.getSize(), dictionaryPageResDTOPage.getSize());
        Assert.assertEquals(dictionaryDTOPage.getRecords().size(), dictionaryPageResDTOPage.getRecords().size());
    }

    @Test
    public void testTransferDictionaryShowResDTO() {
        DictionaryDTO parentDictionaryDTO = new DictionaryDTO();
        DictionaryDTO dictionaryDTO = new DictionaryDTO();
        dictionaryDTO.setId("1");

        DictionaryShowResDTO dictionaryShowResDTO = domain.transferDictionaryShowResDTO(dictionaryDTO, parentDictionaryDTO);

        Assert.assertEquals(dictionaryDTO.getId(), dictionaryShowResDTO.getId());
    }

    @Test
    public void testTransferDictionaryShowResDTOList() {
        List<DictionaryDTO> dictionaryDTOList = Lists.newArrayList();

        DictionaryDTO dictionaryDTO1 = new DictionaryDTO();
        dictionaryDTO1.setId("1");
        DictionaryDTO dictionaryDTO2 = new DictionaryDTO();
        dictionaryDTO1.setId("2");
        dictionaryDTOList.add(dictionaryDTO1);
        dictionaryDTOList.add(dictionaryDTO2);

        List<DictionaryShowResDTO> dictionaryShowResDTOList = domain.transferDictionaryShowResDTOList(dictionaryDTOList);

        Assert.assertEquals(dictionaryDTOList.size(), dictionaryShowResDTOList.size());
        Assert.assertEquals(dictionaryDTOList.get(0).getId(), dictionaryShowResDTOList.get(0).getId());
        Assert.assertEquals(dictionaryDTOList.get(1).getId(), dictionaryShowResDTOList.get(1).getId());
    }
}

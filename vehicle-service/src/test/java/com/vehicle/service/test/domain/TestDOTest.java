package com.vehicle.service.test.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 测试 DOTest
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Slf4j
public class TestDOTest extends BaseJUnitTester<TestDO> {

    @Test(expected = MessageException.class)
    public void testCheckTestAddReqDTO() {
        domain.checkTestAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckTestAddReqDTOList() {
        domain.checkTestAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckTestModifyReqDTO() {
        domain.checkTestModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckTestRemoveReqDTO() {
        domain.checkTestRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        TestListReqDTO testListReqDTO = new TestListReqDTO();
        // testListReqDTO.setId("1");

        TestDTO testDTO = domain.buildListParamsDTO(testListReqDTO);

        // Assert.assertEquals(testListReqDTO.getId(), testDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        TestPageReqDTO testPageReqDTO = new TestPageReqDTO();
        // testPageReqDTO.setId("1");

        TestDTO testDTO = domain.buildPageParamsDTO(testPageReqDTO);

        // Assert.assertEquals(testPageReqDTO.getId(), testDTO.getId());
    }

    @Test
    public void testBuildAddTestDTO() {
        TestAddReqDTO testAddReqDTO = new TestAddReqDTO();

        TestDTO testDTO = domain.buildAddTestDTO(testAddReqDTO);
    }

    @Test
    public void testBuildAddBatchTestDTOList() {
        List<TestAddReqDTO> testAddReqDTOList = Lists.newArrayList();
        TestAddReqDTO testAddReqDTO1 = new TestAddReqDTO();
        TestAddReqDTO testAddReqDTO2 = new TestAddReqDTO();
        testAddReqDTOList.add(testAddReqDTO1);
        testAddReqDTOList.add(testAddReqDTO2);

        List<TestDTO> testDTOList = domain.buildAddBatchTestDTOList(testAddReqDTOList);

        Assert.assertEquals(testAddReqDTOList.size(), testDTOList.size());
    }

    @Test
    public void testBuildModifyTestDTO() {
        TestModifyReqDTO testModifyReqDTO = new TestModifyReqDTO();
        // testModifyReqDTO.setId("1");

        TestDTO testDTO = domain.buildModifyTestDTO(testModifyReqDTO);

        // Assert.assertEquals(testModifyReqDTO.getId(), testDTO.getId());
    }

    @Test
    public void testBuildRemoveTestDTO() {
        TestRemoveReqDTO testRemoveReqDTO = new TestRemoveReqDTO();
        // testRemoveReqDTO.setId("1");

        TestDTO testDTO = domain.buildRemoveTestDTO(testRemoveReqDTO);

        // Assert.assertEquals(testRemoveReqDTO.getId(), testDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferTestListResDTOList() {
        List<TestDTO> testDTOList = Lists.newArrayList();

        domain.transferTestListResDTOList(testDTOList);

        TestDTO testDTO1 = new TestDTO();
        // testDTO1.setId("1");
        TestDTO testDTO2 = new TestDTO();
        // testDTO1.setId("2");
        testDTOList.add(testDTO1);
        testDTOList.add(testDTO2);

        List<TestListResDTO> testListResDTOList = domain.transferTestListResDTOList(testDTOList);

        Assert.assertEquals(testDTOList.size(), testListResDTOList.size());
        // Assert.assertEquals(testDTOList.get(0).getId(), testListResDTOList.get(0).getId());
        // Assert.assertEquals(testDTOList.get(1).getId(), testListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferTestListResDTO() {
        domain.transferTestListResDTO(null);

        TestDTO testDTO = new TestDTO();
        // testDTO.setId("1");

        TestListResDTO testListResDTO = domain.transferTestListResDTO(testDTO);

        // Assert.assertEquals(testDTO.getId(), testListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferTestPageResDTOPage() {
        Page<TestDTO> testDTOPage = new Page<>();

        domain.transferTestPageResDTOPage(testDTOPage);

        testDTOPage.setTotal(100);
        testDTOPage.setCurrent(10);
        testDTOPage.setSize(10);

        List<TestDTO> testDTOList = Lists.newArrayList();
        TestDTO testDTO1 = new TestDTO();
        // testDTO1.setId("1");
        TestDTO testDTO2 = new TestDTO();
        // testDTO1.setId("2");
        testDTOList.add(testDTO1);
        testDTOList.add(testDTO2);

        testDTOPage.setRecords(testDTOList);


        Page<TestPageResDTO> testPageResDTOPage = domain.transferTestPageResDTOPage(testDTOPage);

        Assert.assertEquals(testDTOPage.getTotal(), testPageResDTOPage.getTotal());
        Assert.assertEquals(testDTOPage.getCurrent(), testPageResDTOPage.getCurrent());
        Assert.assertEquals(testDTOPage.getSize(), testPageResDTOPage.getSize());
        Assert.assertEquals(testDTOPage.getRecords().size(), testPageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferTestShowResDTO() {
        domain.transferTestShowResDTO(null);

        TestDTO testDTO = new TestDTO();
        // testDTO.setId("1");

        TestShowResDTO testShowResDTO = domain.transferTestShowResDTO(testDTO);

        // Assert.assertEquals(testDTO.getId(), testShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferTestShowResDTOList() {
        List<TestDTO> testDTOList = Lists.newArrayList();

        domain.transferTestShowResDTOList(testDTOList);

        TestDTO testDTO1 = new TestDTO();
        // testDTO1.setId("1");
        TestDTO testDTO2 = new TestDTO();
        // testDTO1.setId("2");
        testDTOList.add(testDTO1);
        testDTOList.add(testDTO2);

        List<TestShowResDTO> testShowResDTOList = domain.transferTestShowResDTOList(testDTOList);

        Assert.assertEquals(testDTOList.size(), testShowResDTOList.size());
        // Assert.assertEquals(testDTOList.get(0).getId(), testShowResDTOList.get(0).getId());
        // Assert.assertEquals(testDTOList.get(1).getId(), testShowResDTOList.get(1).getId());
    }
}

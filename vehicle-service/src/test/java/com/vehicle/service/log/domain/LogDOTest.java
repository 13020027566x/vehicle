package com.vehicle.service.log.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import com.vehicle.service.BaseJUnitTester;
import com.vehicle.service.log.dto.LogAddReqDTO;
import com.vehicle.service.log.dto.LogDTO;
import com.vehicle.service.log.dto.LogListReqDTO;
import com.vehicle.service.log.dto.LogListResDTO;
import com.vehicle.service.log.dto.LogModifyReqDTO;
import com.vehicle.service.log.dto.LogPageReqDTO;
import com.vehicle.service.log.dto.LogPageResDTO;
import com.vehicle.service.log.dto.LogRemoveReqDTO;
import com.vehicle.service.log.dto.LogShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 日志 BizJUnitTest
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
public class LogDOTest extends BaseJUnitTester<LogDO> {

    @Test(expected = MessageException.class)
    public void testCheckLogAddReqDTO() {
        domain.checkLogAddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckLogAddReqDTOList() {
        domain.checkLogAddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckLogModifyReqDTO() {
        domain.checkLogModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheckLogRemoveReqDTO() {
        domain.checkLogRemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        LogListReqDTO logListReqDTO = new LogListReqDTO();
        logListReqDTO.setId("1");

        LogDTO logDTO = domain.buildListParamsDTO(logListReqDTO);

        Assert.assertEquals(logListReqDTO.getId(), logDTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        LogPageReqDTO logPageReqDTO = new LogPageReqDTO();
        logPageReqDTO.setId("1");

        LogDTO logDTO = domain.buildPageParamsDTO(logPageReqDTO);

        Assert.assertEquals(logPageReqDTO.getId(), logDTO.getId());
    }

    @Test
    public void testBuildAddLogDTO() {
        LogAddReqDTO logAddReqDTO = new LogAddReqDTO();
        logAddReqDTO.setFieldName("111");

        LogDTO logDTO = domain.buildAddLogDTO(logAddReqDTO);

        Assert.assertEquals(logAddReqDTO.getFieldName(), logDTO.getFieldName());
    }

    @Test
    public void testBuildAddBatchLogDTOList() {
        List<LogAddReqDTO> logAddReqDTOList = Lists.newArrayList();
        LogAddReqDTO logAddReqDTO1 = new LogAddReqDTO();
        logAddReqDTO1.setFieldName("111");
        LogAddReqDTO logAddReqDTO2 = new LogAddReqDTO();
        logAddReqDTO1.setFieldName("222");
        logAddReqDTOList.add(logAddReqDTO1);
        logAddReqDTOList.add(logAddReqDTO2);

        List<LogDTO> logDTOList = domain.buildAddBatchLogDTOList(logAddReqDTOList);

        Assert.assertEquals(logAddReqDTOList.size(), logDTOList.size());
        Assert.assertEquals(logAddReqDTOList.get(0).getFieldName(), logDTOList.get(0).getFieldName());
        Assert.assertEquals(logAddReqDTOList.get(1).getFieldName(), logDTOList.get(1).getFieldName());
    }

    @Test
    public void testBuildModifyLogDTO() {
        LogModifyReqDTO logModifyReqDTO = new LogModifyReqDTO();
        logModifyReqDTO.setId("1");

        LogDTO logDTO = domain.buildModifyLogDTO(logModifyReqDTO);

        Assert.assertEquals(logModifyReqDTO.getId(), logDTO.getId());
    }

    @Test
    public void testBuildRemoveLogDTO() {
        LogRemoveReqDTO logRemoveReqDTO = new LogRemoveReqDTO();
        logRemoveReqDTO.setId("1");

        LogDTO logDTO = domain.buildRemoveLogDTO(logRemoveReqDTO);

        Assert.assertEquals(logRemoveReqDTO.getId(), logDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferLogListResDTOList() {
        List<LogDTO> logDTOList = Lists.newArrayList();

        domain.transferLogListResDTOList(logDTOList);

        LogDTO logDTO1 = new LogDTO();
        logDTO1.setId("1");
        LogDTO logDTO2 = new LogDTO();
        logDTO1.setId("2");
        logDTOList.add(logDTO1);
        logDTOList.add(logDTO2);

        List<LogListResDTO> logListResDTOList = domain.transferLogListResDTOList(logDTOList);

        Assert.assertEquals(logDTOList.size(), logListResDTOList.size());
        Assert.assertEquals(logDTOList.get(0).getId(), logListResDTOList.get(0).getId());
        Assert.assertEquals(logDTOList.get(1).getId(), logListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferLogListResDTO() {
        domain.transferLogListResDTO(null);

        LogDTO logDTO = new LogDTO();
        logDTO.setId("1");

        LogListResDTO logListResDTO = domain.transferLogListResDTO(logDTO);

        Assert.assertEquals(logDTO.getId(), logListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferLogPageResDTOPage() {
        Page<LogDTO> logDTOPage = new Page<>();

        domain.transferLogPageResDTOPage(logDTOPage);

        logDTOPage.setTotal(100);
        logDTOPage.setCurrent(10);
        logDTOPage.setSize(10);

        List<LogDTO> logDTOList = Lists.newArrayList();
        LogDTO logDTO1 = new LogDTO();
        logDTO1.setId("1");
        LogDTO logDTO2 = new LogDTO();
        logDTO1.setId("2");
        logDTOList.add(logDTO1);
        logDTOList.add(logDTO2);

        logDTOPage.setRecords(logDTOList);


        Page<LogPageResDTO> logPageResDTOPage = domain.transferLogPageResDTOPage(logDTOPage);

        Assert.assertEquals(logDTOPage.getTotal(), logPageResDTOPage.getTotal());
        Assert.assertEquals(logDTOPage.getCurrent(), logPageResDTOPage.getCurrent());
        Assert.assertEquals(logDTOPage.getSize(), logPageResDTOPage.getSize());
        Assert.assertEquals(logDTOPage.getRecords().size(), logPageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransferLogShowResDTO() {
        domain.transferLogShowResDTO(null);

        LogDTO logDTO = new LogDTO();
        logDTO.setId("1");

        LogShowResDTO logShowResDTO = domain.transferLogShowResDTO(logDTO);

        Assert.assertEquals(logDTO.getId(), logShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransferLogShowResDTOList() {
        List<LogDTO> logDTOList = Lists.newArrayList();

        domain.transferLogShowResDTOList(logDTOList);

        LogDTO logDTO1 = new LogDTO();
        logDTO1.setId("1");
        LogDTO logDTO2 = new LogDTO();
        logDTO1.setId("2");
        logDTOList.add(logDTO1);
        logDTOList.add(logDTO2);

        List<LogShowResDTO> logShowResDTOList = domain.transferLogShowResDTOList(logDTOList);

        Assert.assertEquals(logDTOList.size(), logShowResDTOList.size());
        Assert.assertEquals(logDTOList.get(0).getId(), logShowResDTOList.get(0).getId());
        Assert.assertEquals(logDTOList.get(1).getId(), logShowResDTOList.get(1).getId());
    }
}

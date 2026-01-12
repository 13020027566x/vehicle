package com.vehicle.service.log.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.log.po.LogPO;
import com.vehicle.service.log.converter.LogConverter;
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
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 日志 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Component
public class LogDO extends BaseDO<LogDTO, LogPO, LogConverter> {

    public static LogDO me() {
        return SpringUtil.getBean(LogDO.class);
    }

    public void checkLogAddReqDTO(final LogAddReqDTO logAddReqDTO) {
        if (Func.isEmpty(logAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkLogAddReqDTOList(final List<LogAddReqDTO> logAddReqDTOList) {
        if (Func.isEmpty(logAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkLogModifyReqDTO(final LogModifyReqDTO logModifyReqDTO) {
        if (Func.isEmpty(logModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkLogRemoveReqDTO(final LogRemoveReqDTO logRemoveReqDTO) {
        if (Func.isEmpty(logRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public LogDTO buildListParamsDTO(final LogListReqDTO logListReqDTO) {
        return converter.convertToLogDTO(logListReqDTO);
    }

    public LogDTO buildPageParamsDTO(final LogPageReqDTO logPageReqDTO) {
        return converter.convertToLogDTO(logPageReqDTO);
    }

    public LogDTO buildAddLogDTO(final LogAddReqDTO logAddReqDTO) {
        return converter.convertToLogDTO(logAddReqDTO);
    }

    public List<LogDTO> buildAddBatchLogDTOList(final List<LogAddReqDTO> logAddReqDTOList) {
        return converter.convertToLogDTOList(logAddReqDTOList);
    }

    public LogDTO buildModifyLogDTO(final LogModifyReqDTO logModifyReqDTO) {
        return converter.convertToLogDTO(logModifyReqDTO);
    }

    public LogDTO buildRemoveLogDTO(final LogRemoveReqDTO logRemoveReqDTO) {
        return converter.convertToLogDTO(logRemoveReqDTO);
    }

    public List<LogListResDTO> transferLogListResDTOList(final List<LogDTO> logDTOList) {
        if (Func.isEmpty(logDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToLogListResDTOList(logDTOList);
    }

    public LogListResDTO transferLogListResDTO(final LogDTO logDTO) {
        if (Func.isEmpty(logDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToLogListResDTO(logDTO);
    }

    public Page<LogPageResDTO> transferLogPageResDTOPage(final Page<LogDTO> logDTOPage) {
        if (Func.isEmpty(logDTOPage) || Func.isEmpty(logDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToLogPageResDTOPage(logDTOPage);
    }

    public LogShowResDTO transferLogShowResDTO(final LogDTO logDTO) {
        if (Func.isEmpty(logDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToLogShowResDTO(logDTO);
    }

    public List<LogShowResDTO> transferLogShowResDTOList(final List<LogDTO> logDTOList) {
        if (Func.isEmpty(logDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToLogShowResDTOList(logDTOList);
    }
}

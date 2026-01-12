package com.vehicle.service.log.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.log.po.LogPO;
import com.vehicle.service.log.dto.LogAddReqDTO;
import com.vehicle.service.log.dto.LogDTO;
import com.vehicle.service.log.dto.LogListReqDTO;
import com.vehicle.service.log.dto.LogListResDTO;
import com.vehicle.service.log.dto.LogModifyReqDTO;
import com.vehicle.service.log.dto.LogPageReqDTO;
import com.vehicle.service.log.dto.LogPageResDTO;
import com.vehicle.service.log.dto.LogRemoveReqDTO;
import com.vehicle.service.log.dto.LogShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface LogConverter extends BaseConverter<LogDTO, LogPO> {

    static LogConverter me() {
        return SpringUtil.getBean(LogConverter.class);
    }

    LogDTO convertToLogDTO(LogAddReqDTO logAddReqDTO);

    LogDTO convertToLogDTO(LogModifyReqDTO logModifyReqDTO);

    LogDTO convertToLogDTO(LogRemoveReqDTO logRemoveReqDTO);

    LogDTO convertToLogDTO(LogListReqDTO logListReqDTO);

    LogDTO convertToLogDTO(LogPageReqDTO logPageReqDTO);

    LogShowResDTO convertToLogShowResDTO(LogDTO logDTO);

    List<LogShowResDTO> convertToLogShowResDTOList(List<LogDTO> logDTOList);

    LogListResDTO convertToLogListResDTO(LogDTO logDTO);

    List<LogListResDTO> convertToLogListResDTOList(List<LogDTO> logDTOList);

    List<LogDTO> convertToLogDTOList(List<LogAddReqDTO> logAddReqDTOList);

    LogPageResDTO convertToLogPageResDTO(LogDTO logDTO);

    List<LogPageResDTO> convertToLogPageResDTOList(List<LogDTO> logDTOList);

    Page<LogPageResDTO> convertToLogPageResDTOPage(Page<LogDTO> logDTOPage);
}

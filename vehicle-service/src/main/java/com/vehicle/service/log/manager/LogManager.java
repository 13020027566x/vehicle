package com.vehicle.service.log.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.log.LogDAO;
import com.vehicle.dao.log.po.LogPO;
import com.vehicle.service.log.converter.LogConverter;
import com.vehicle.service.log.domain.LogDO;
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
import java.util.Map;

/**
 * 日志 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Component
public class LogManager extends BaseManagerImpl<LogDAO, LogPO, LogDTO, LogConverter> {

    public static LogManager me() {
        return SpringUtil.getBean(LogManager.class);
    }

    public List<LogListResDTO> list(final LogListReqDTO logListReqDTO) {
        LogDTO paramsDTO = LogDO.me().buildListParamsDTO(logListReqDTO);

        List<LogDTO> logDTOList = super.findList(paramsDTO);

        return LogDO.me().transferLogListResDTOList(logDTOList);
    }

    public LogListResDTO listOne(final LogListReqDTO logListReqDTO) {
        LogDTO paramsDTO = LogDO.me().buildListParamsDTO(logListReqDTO);

        LogDTO logDTO = super.findOne(paramsDTO);

        return LogDO.me().transferLogListResDTO(logDTO);
    }

    public Page<LogPageResDTO> pagination(final LogPageReqDTO logPageReqDTO, final Integer current, final Integer size) {
        LogDTO paramsDTO = LogDO.me().buildPageParamsDTO(logPageReqDTO);

        Page<LogDTO> logDTOPage = super.findPage(paramsDTO, current, size);

        return LogDO.me().transferLogPageResDTOPage(logDTOPage);
    }

    public Boolean add(final LogAddReqDTO logAddReqDTO) {
        LogDO.me().checkLogAddReqDTO(logAddReqDTO);

        LogDTO addLogDTO = LogDO.me().buildAddLogDTO(logAddReqDTO);

        return super.saveDTO(addLogDTO);
    }

    public Boolean addAllColumn(final LogAddReqDTO logAddReqDTO) {
        LogDO.me().checkLogAddReqDTO(logAddReqDTO);

        LogDTO addLogDTO = LogDO.me().buildAddLogDTO(logAddReqDTO);

        return super.saveAllColumn(addLogDTO);
    }

    public Boolean addBatchAllColumn(final List<LogAddReqDTO> logAddReqDTOList) {
        LogDO.me().checkLogAddReqDTOList(logAddReqDTOList);

        List<LogDTO> addBatchLogDTOList = LogDO.me().buildAddBatchLogDTOList(logAddReqDTOList);

        return super.saveBatchAllColumn(addBatchLogDTOList);
    }

    public LogShowResDTO show(final String id) {
        LogDTO logDTO = super.findById(id);

        return LogDO.me().transferLogShowResDTO(logDTO);
    }

    public List<LogShowResDTO> showByIds(final List<String> ids) {
        List<LogDTO> logDTOList = super.findBatchIds(ids);

        return LogDO.me().transferLogShowResDTOList(logDTOList);
    }

    public Boolean modify(final LogModifyReqDTO logModifyReqDTO) {
        LogDO.me().checkLogModifyReqDTO(logModifyReqDTO);

        LogDTO modifyLogDTO = LogDO.me().buildModifyLogDTO(logModifyReqDTO);

        return super.modifyById(modifyLogDTO);
    }

    public Boolean modifyAllColumn(final LogModifyReqDTO logModifyReqDTO) {
        LogDO.me().checkLogModifyReqDTO(logModifyReqDTO);

        LogDTO modifyLogDTO = LogDO.me().buildModifyLogDTO(logModifyReqDTO);

        return super.modifyAllColumnById(modifyLogDTO);
    }

    public Boolean removeByParams(final LogRemoveReqDTO logRemoveReqDTO) {
        LogDO.me().checkLogRemoveReqDTO(logRemoveReqDTO);

        LogDTO removeLogDTO = LogDO.me().buildRemoveLogDTO(logRemoveReqDTO);

        return super.remove(removeLogDTO);
    }

    @Override
    protected LogPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new LogPO();
        }

        return BeanUtil.toBean(map, LogPO.class);
    }

    @Override
    protected LogDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new LogDTO();
        }

        return BeanUtil.toBean(map, LogDTO.class);
    }
}

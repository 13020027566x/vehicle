package com.vehicle.service.log.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.log.po.LogPO;
import com.vehicle.service.log.LogService;
import com.vehicle.service.log.dto.LogAddReqDTO;
import com.vehicle.service.log.dto.LogDTO;
import com.vehicle.service.log.dto.LogListReqDTO;
import com.vehicle.service.log.dto.LogListResDTO;
import com.vehicle.service.log.dto.LogModifyReqDTO;
import com.vehicle.service.log.dto.LogPageReqDTO;
import com.vehicle.service.log.dto.LogPageResDTO;
import com.vehicle.service.log.dto.LogRemoveReqDTO;
import com.vehicle.service.log.dto.LogShowResDTO;
import com.vehicle.service.log.manager.LogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Service
public class LogServiceImpl extends BaseServiceImpl<LogManager, LogPO, LogDTO> implements LogService {

    @Override
    public List<LogListResDTO> list(final LogListReqDTO logListReqDTO) {
        return manager.list(logListReqDTO);
    }

    @Override
    public LogListResDTO listOne(final LogListReqDTO logListReqDTO) {
        return manager.listOne(logListReqDTO);
    }

    @Override
    public Page<LogPageResDTO> pagination(final LogPageReqDTO logPageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(logPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final LogAddReqDTO logAddReqDTO) {
        return manager.add(logAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final LogAddReqDTO logAddReqDTO) {
        return manager.addAllColumn(logAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<LogAddReqDTO> logAddReqDTOList) {
        return manager.addBatchAllColumn(logAddReqDTOList);
    }

    @Override
    public LogShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<LogShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final LogModifyReqDTO logModifyReqDTO) {
        return manager.modify(logModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final LogModifyReqDTO logModifyReqDTO) {
        return manager.modifyAllColumn(logModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final LogRemoveReqDTO logRemoveReqDTO) {
        return manager.removeByParams(logRemoveReqDTO);
    }
}

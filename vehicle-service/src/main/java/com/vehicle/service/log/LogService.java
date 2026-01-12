package com.vehicle.service.log;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.log.dto.LogAddReqDTO;
import com.vehicle.service.log.dto.LogDTO;
import com.vehicle.service.log.dto.LogListReqDTO;
import com.vehicle.service.log.dto.LogListResDTO;
import com.vehicle.service.log.dto.LogModifyReqDTO;
import com.vehicle.service.log.dto.LogPageReqDTO;
import com.vehicle.service.log.dto.LogPageResDTO;
import com.vehicle.service.log.dto.LogRemoveReqDTO;
import com.vehicle.service.log.dto.LogShowResDTO;

import java.util.List;

/**
 * 日志 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
public interface LogService extends BaseService<LogDTO> {

    static LogService me() {
        return SpringUtil.getBean(LogService.class);
    }

    /**
     * 列表
     *
     * @param logListReqDTO 入参DTO
     * @return
     */
    List<LogListResDTO> list(LogListReqDTO logListReqDTO);

    /**
     * First查询
     *
     * @param logListReqDTO 入参DTO
     * @return
     */
    LogListResDTO listOne(LogListReqDTO logListReqDTO);

    /**
     * 分页
     *
     * @param logPageReqDTO 入参DTO
     * @param current       当前页
     * @param size          每页大小
     * @return
     */
    Page<LogPageResDTO> pagination(LogPageReqDTO logPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param logAddReqDTO 入参DTO
     * @return
     */
    Boolean add(LogAddReqDTO logAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param logAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(LogAddReqDTO logAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param logAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<LogAddReqDTO> logAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    LogShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<LogShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param logModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(LogModifyReqDTO logModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param logModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(LogModifyReqDTO logModifyReqDTO);

    /**
     * 参数删除
     *
     * @param logRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(LogRemoveReqDTO logRemoveReqDTO);
}

package com.vehicle.service.fileattach;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;

import java.util.List;

/**
 * 文件附件 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
public interface FileAttachService extends BaseService<FileAttachDTO> {

    static FileAttachService me() {
        return SpringUtil.getBean(FileAttachService.class);
    }

    /**
     * 列表
     *
     * @param fileAttachListReqDTO 入参DTO
     * @return
     */
    List<FileAttachListResDTO> list(FileAttachListReqDTO fileAttachListReqDTO);

    /**
     * First查询
     *
     * @param fileAttachListReqDTO 入参DTO
     * @return
     */
    FileAttachListResDTO listOne(FileAttachListReqDTO fileAttachListReqDTO);

    /**
     * 分页
     *
     * @param fileAttachPageReqDTO 入参DTO
     * @param current              当前页
     * @param size                 每页大小
     * @return
     */
    Page<FileAttachPageResDTO> pagination(FileAttachPageReqDTO fileAttachPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param fileAttachAddReqDTO 入参DTO
     * @return
     */
    Boolean add(FileAttachAddReqDTO fileAttachAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param fileAttachAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(FileAttachAddReqDTO fileAttachAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param fileAttachAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<FileAttachAddReqDTO> fileAttachAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    FileAttachShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<FileAttachShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param fileAttachModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(FileAttachModifyReqDTO fileAttachModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param fileAttachModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(FileAttachModifyReqDTO fileAttachModifyReqDTO);

    /**
     * 参数删除
     *
     * @param fileAttachRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(FileAttachRemoveReqDTO fileAttachRemoveReqDTO);
}

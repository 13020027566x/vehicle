package com.vehicle.service.fileattach.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.fileattach.po.FileAttachPO;
import com.vehicle.service.fileattach.FileAttachService;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;
import com.vehicle.service.fileattach.manager.FileAttachManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件附件 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Service
public class FileAttachServiceImpl extends BaseServiceImpl<FileAttachManager, FileAttachPO, FileAttachDTO> implements FileAttachService {

    @Override
    public List<FileAttachListResDTO> list(final FileAttachListReqDTO fileAttachListReqDTO) {
        return manager.list(fileAttachListReqDTO);
    }

    @Override
    public FileAttachListResDTO listOne(final FileAttachListReqDTO fileAttachListReqDTO) {
        return manager.listOne(fileAttachListReqDTO);
    }

    @Override
    public Page<FileAttachPageResDTO> pagination(final FileAttachPageReqDTO fileAttachPageReqDTO,
        final Integer current, final Integer size) {
        return manager.pagination(fileAttachPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        return manager.add(fileAttachAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        return manager.addAllColumn(fileAttachAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        return manager.addBatchAllColumn(fileAttachAddReqDTOList);
    }

    @Override
    public FileAttachShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<FileAttachShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        return manager.modify(fileAttachModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        return manager.modifyAllColumn(fileAttachModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        return manager.removeByParams(fileAttachRemoveReqDTO);
    }
}

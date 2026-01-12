package com.vehicle.service.fileattach.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.fileattach.FileAttachDAO;
import com.vehicle.dao.fileattach.po.FileAttachPO;
import com.vehicle.service.fileattach.converter.FileAttachConverter;
import com.vehicle.service.fileattach.domain.FileAttachDO;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 文件附件 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Component
public class FileAttachManager extends BaseManagerImpl<FileAttachDAO, FileAttachPO, FileAttachDTO, FileAttachConverter> {

    public static FileAttachManager me() {
        return SpringUtil.getBean(FileAttachManager.class);
    }

    public List<FileAttachListResDTO> list(final FileAttachListReqDTO fileAttachListReqDTO) {
        FileAttachDTO paramsDTO = FileAttachDO.me().buildListParamsDTO(fileAttachListReqDTO);

        List<FileAttachDTO> fileAttachDTOList = super.findList(paramsDTO);

        return FileAttachDO.me().transferFileAttachListResDTOList(fileAttachDTOList);
    }

    public FileAttachListResDTO listOne(final FileAttachListReqDTO fileAttachListReqDTO) {
        FileAttachDTO paramsDTO = FileAttachDO.me().buildListParamsDTO(fileAttachListReqDTO);

        FileAttachDTO fileAttachDTO = super.findOne(paramsDTO);

        return FileAttachDO.me().transferFileAttachListResDTO(fileAttachDTO);
    }

    public Page<FileAttachPageResDTO> pagination(final FileAttachPageReqDTO fileAttachPageReqDTO, final Integer current, final Integer size) {
        FileAttachDTO paramsDTO = FileAttachDO.me().buildPageParamsDTO(fileAttachPageReqDTO);

        Page<FileAttachDTO> fileAttachDTOPage = super.findPage(paramsDTO, current, size);

        return FileAttachDO.me().transferFileAttachPageResDTOPage(fileAttachDTOPage);
    }

    public Boolean add(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        FileAttachDO.me().checkFileAttachAddReqDTO(fileAttachAddReqDTO);

        FileAttachDTO addFileAttachDTO = FileAttachDO.me().buildAddFileAttachDTO(fileAttachAddReqDTO);

        return super.saveDTO(addFileAttachDTO);
    }

    public Boolean addAllColumn(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        FileAttachDO.me().checkFileAttachAddReqDTO(fileAttachAddReqDTO);

        FileAttachDTO addFileAttachDTO = FileAttachDO.me().buildAddFileAttachDTO(fileAttachAddReqDTO);

        return super.saveAllColumn(addFileAttachDTO);
    }

    public Boolean addBatchAllColumn(final List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        FileAttachDO.me().checkFileAttachAddReqDTOList(fileAttachAddReqDTOList);

        List<FileAttachDTO> addBatchFileAttachDTOList = FileAttachDO.me().buildAddBatchFileAttachDTOList(fileAttachAddReqDTOList);

        return super.saveBatchAllColumn(addBatchFileAttachDTOList);
    }

    public FileAttachShowResDTO show(final String id) {
        FileAttachDTO fileAttachDTO = super.findById(id);

        return FileAttachDO.me().transferFileAttachShowResDTO(fileAttachDTO);
    }

    public List<FileAttachShowResDTO> showByIds(final List<String> ids) {
        List<FileAttachDTO> fileAttachDTOList = super.findBatchIds(ids);

        return FileAttachDO.me().transferFileAttachShowResDTOList(fileAttachDTOList);
    }

    public Boolean modify(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        FileAttachDO.me().checkFileAttachModifyReqDTO(fileAttachModifyReqDTO);

        FileAttachDTO modifyFileAttachDTO = FileAttachDO.me().buildModifyFileAttachDTO(fileAttachModifyReqDTO);

        return super.modifyById(modifyFileAttachDTO);
    }

    public Boolean modifyAllColumn(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        FileAttachDO.me().checkFileAttachModifyReqDTO(fileAttachModifyReqDTO);

        FileAttachDTO modifyFileAttachDTO = FileAttachDO.me().buildModifyFileAttachDTO(fileAttachModifyReqDTO);

        return super.modifyAllColumnById(modifyFileAttachDTO);
    }

    public Boolean removeByParams(final FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        FileAttachDO.me().checkFileAttachRemoveReqDTO(fileAttachRemoveReqDTO);

        FileAttachDTO removeFileAttachDTO = FileAttachDO.me().buildRemoveFileAttachDTO(fileAttachRemoveReqDTO);

        return super.remove(removeFileAttachDTO);
    }

    @Override
    protected FileAttachPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new FileAttachPO();
        }

        return BeanUtil.toBean(map, FileAttachPO.class);
    }

    @Override
    protected FileAttachDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new FileAttachDTO();
        }

        return BeanUtil.toBean(map, FileAttachDTO.class);
    }
}

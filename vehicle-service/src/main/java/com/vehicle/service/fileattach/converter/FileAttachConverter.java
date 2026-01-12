package com.vehicle.service.fileattach.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.fileattach.po.FileAttachPO;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface FileAttachConverter extends BaseConverter<FileAttachDTO, FileAttachPO> {

    static FileAttachConverter me() {
        return SpringUtil.getBean(FileAttachConverter.class);
    }

    FileAttachDTO convertToFileAttachDTO(FileAttachAddReqDTO fileAttachAddReqDTO);

    FileAttachDTO convertToFileAttachDTO(FileAttachModifyReqDTO fileAttachModifyReqDTO);

    FileAttachDTO convertToFileAttachDTO(FileAttachRemoveReqDTO fileAttachRemoveReqDTO);

    FileAttachDTO convertToFileAttachDTO(FileAttachListReqDTO fileAttachListReqDTO);

    FileAttachDTO convertToFileAttachDTO(FileAttachPageReqDTO fileAttachPageReqDTO);

    FileAttachShowResDTO convertToFileAttachShowResDTO(FileAttachDTO fileAttachDTO);

    List<FileAttachShowResDTO> convertToFileAttachShowResDTOList(List<FileAttachDTO> fileAttachDTOList);

    FileAttachListResDTO convertToFileAttachListResDTO(FileAttachDTO fileAttachDTO);

    List<FileAttachListResDTO> convertToFileAttachListResDTOList(List<FileAttachDTO> fileAttachDTOList);

    List<FileAttachDTO> convertToFileAttachDTOList(List<FileAttachAddReqDTO> fileAttachAddReqDTOList);

    FileAttachPageResDTO convertToFileAttachPageResDTO(FileAttachDTO fileAttachDTO);

    List<FileAttachPageResDTO> convertToFileAttachPageResDTOList(List<FileAttachDTO> fileAttachDTOList);

    Page<FileAttachPageResDTO> convertToFileAttachPageResDTOPage(Page<FileAttachDTO> fileAttachDTOPage);
}

package com.vehicle.service.fileattach.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.dao.fileattach.po.FileAttachPO;
import com.vehicle.service.fileattach.converter.FileAttachConverter;
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

/**
 * 文件附件 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Slf4j
@Component
public class FileAttachDO extends BaseDO<FileAttachDTO, FileAttachPO, FileAttachConverter> {

    public static FileAttachDO me() {
        return SpringUtil.getBean(FileAttachDO.class);
    }

    public void checkFileAttachAddReqDTO(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        if (Func.isEmpty(fileAttachAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkFileAttachAddReqDTOList(final List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        if (Func.isEmpty(fileAttachAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkFileAttachModifyReqDTO(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        if (Func.isEmpty(fileAttachModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkFileAttachRemoveReqDTO(final FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        if (Func.isEmpty(fileAttachRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public FileAttachDTO buildListParamsDTO(final FileAttachListReqDTO fileAttachListReqDTO) {
        return converter.convertToFileAttachDTO(fileAttachListReqDTO);
    }

    public FileAttachDTO buildPageParamsDTO(final FileAttachPageReqDTO fileAttachPageReqDTO) {
        return converter.convertToFileAttachDTO(fileAttachPageReqDTO);
    }

    public FileAttachDTO buildAddFileAttachDTO(final FileAttachAddReqDTO fileAttachAddReqDTO) {
        return converter.convertToFileAttachDTO(fileAttachAddReqDTO);
    }

    public List<FileAttachDTO> buildAddBatchFileAttachDTOList(final List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        return converter.convertToFileAttachDTOList(fileAttachAddReqDTOList);
    }

    public FileAttachDTO buildModifyFileAttachDTO(final FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        return converter.convertToFileAttachDTO(fileAttachModifyReqDTO);
    }

    public FileAttachDTO buildRemoveFileAttachDTO(final FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        return converter.convertToFileAttachDTO(fileAttachRemoveReqDTO);
    }

    public FileAttachListResDTO transferFileAttachListResDTO(final FileAttachDTO fileAttachDTO) {
        if (Func.isEmpty(fileAttachDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToFileAttachListResDTO(fileAttachDTO);
    }

    public List<FileAttachListResDTO> transferFileAttachListResDTOList(final List<FileAttachDTO> fileAttachDTOList) {
        if (Func.isEmpty(fileAttachDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToFileAttachListResDTOList(fileAttachDTOList);
    }

    public Page<FileAttachPageResDTO> transferFileAttachPageResDTOPage(final Page<FileAttachDTO> fileAttachDTOPage) {
        if (Func.isEmpty(fileAttachDTOPage) || Func.isEmpty(fileAttachDTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToFileAttachPageResDTOPage(fileAttachDTOPage);
    }

    public FileAttachShowResDTO transferFileAttachShowResDTO(final FileAttachDTO fileAttachDTO) {
        if (Func.isEmpty(fileAttachDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToFileAttachShowResDTO(fileAttachDTO);
    }

    public List<FileAttachShowResDTO> transferFileAttachShowResDTOList(final List<FileAttachDTO> fileAttachDTOList) {
        if (Func.isEmpty(fileAttachDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertToFileAttachShowResDTOList(fileAttachDTOList);
    }
}

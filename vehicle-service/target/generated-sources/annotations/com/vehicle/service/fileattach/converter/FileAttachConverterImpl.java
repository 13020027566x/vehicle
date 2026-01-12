package com.vehicle.service.fileattach.converter;

import com.finhub.framework.core.page.Page;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class FileAttachConverterImpl implements FileAttachConverter {

    @Override
    public FileAttachDTO poToDTO(FileAttachPO po) {
        if ( po == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setId( po.getId() );
        fileAttachDTO.setIsDel( po.getIsDel() );
        fileAttachDTO.setIsTest( po.getIsTest() );
        fileAttachDTO.setCreateAt( po.getCreateAt() );
        fileAttachDTO.setCreateTime( po.getCreateTime() );
        fileAttachDTO.setCreateBy( po.getCreateBy() );
        fileAttachDTO.setCreateName( po.getCreateName() );
        fileAttachDTO.setUpdateAt( po.getUpdateAt() );
        fileAttachDTO.setUpdateTime( po.getUpdateTime() );
        fileAttachDTO.setUpdateBy( po.getUpdateBy() );
        fileAttachDTO.setUpdateName( po.getUpdateName() );
        fileAttachDTO.setIconUrl( po.getIconUrl() );
        fileAttachDTO.setName( po.getName() );
        fileAttachDTO.setPreviewUrl( po.getPreviewUrl() );
        fileAttachDTO.setRecordId( po.getRecordId() );
        fileAttachDTO.setTableName( po.getTableName() );
        fileAttachDTO.setUrl( po.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public List<FileAttachDTO> poToDTOList(List<FileAttachPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<FileAttachDTO> list = new ArrayList<FileAttachDTO>( arg0.size() );
        for ( FileAttachPO fileAttachPO : arg0 ) {
            list.add( poToDTO( fileAttachPO ) );
        }

        return list;
    }

    @Override
    public FileAttachPO dtoToPO(FileAttachDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        FileAttachPO fileAttachPO = new FileAttachPO();

        fileAttachPO.setId( arg0.getId() );
        fileAttachPO.setIsDel( arg0.getIsDel() );
        fileAttachPO.setIsTest( arg0.getIsTest() );
        fileAttachPO.setCreateAt( arg0.getCreateAt() );
        fileAttachPO.setCreateTime( arg0.getCreateTime() );
        fileAttachPO.setCreateBy( arg0.getCreateBy() );
        fileAttachPO.setCreateName( arg0.getCreateName() );
        fileAttachPO.setUpdateAt( arg0.getUpdateAt() );
        fileAttachPO.setUpdateTime( arg0.getUpdateTime() );
        fileAttachPO.setUpdateBy( arg0.getUpdateBy() );
        fileAttachPO.setUpdateName( arg0.getUpdateName() );
        fileAttachPO.setTableName( arg0.getTableName() );
        fileAttachPO.setRecordId( arg0.getRecordId() );
        fileAttachPO.setName( arg0.getName() );
        fileAttachPO.setUrl( arg0.getUrl() );
        fileAttachPO.setIconUrl( arg0.getIconUrl() );
        fileAttachPO.setPreviewUrl( arg0.getPreviewUrl() );

        return fileAttachPO;
    }

    @Override
    public List<FileAttachPO> dtoToPOList(List<FileAttachDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<FileAttachPO> list = new ArrayList<FileAttachPO>( arg0.size() );
        for ( FileAttachDTO fileAttachDTO : arg0 ) {
            list.add( dtoToPO( fileAttachDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(FileAttachDTO arg0, FileAttachPO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setTableName( arg0.getTableName() );
        arg1.setRecordId( arg0.getRecordId() );
        arg1.setName( arg0.getName() );
        arg1.setUrl( arg0.getUrl() );
        arg1.setIconUrl( arg0.getIconUrl() );
        arg1.setPreviewUrl( arg0.getPreviewUrl() );
    }

    @Override
    public void updateDto(FileAttachPO arg0, FileAttachDTO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setIconUrl( arg0.getIconUrl() );
        arg1.setName( arg0.getName() );
        arg1.setPreviewUrl( arg0.getPreviewUrl() );
        arg1.setRecordId( arg0.getRecordId() );
        arg1.setTableName( arg0.getTableName() );
        arg1.setUrl( arg0.getUrl() );
    }

    @Override
    public FileAttachDTO convertToFileAttachDTO(FileAttachAddReqDTO fileAttachAddReqDTO) {
        if ( fileAttachAddReqDTO == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setIsDel( fileAttachAddReqDTO.getIsDel() );
        fileAttachDTO.setIsTest( fileAttachAddReqDTO.getIsTest() );
        fileAttachDTO.setCreateAt( fileAttachAddReqDTO.getCreateAt() );
        fileAttachDTO.setCreateBy( fileAttachAddReqDTO.getCreateBy() );
        fileAttachDTO.setCreateName( fileAttachAddReqDTO.getCreateName() );
        fileAttachDTO.setUpdateAt( fileAttachAddReqDTO.getUpdateAt() );
        fileAttachDTO.setUpdateBy( fileAttachAddReqDTO.getUpdateBy() );
        fileAttachDTO.setUpdateName( fileAttachAddReqDTO.getUpdateName() );
        fileAttachDTO.setIconUrl( fileAttachAddReqDTO.getIconUrl() );
        fileAttachDTO.setName( fileAttachAddReqDTO.getName() );
        fileAttachDTO.setPreviewUrl( fileAttachAddReqDTO.getPreviewUrl() );
        fileAttachDTO.setRecordId( fileAttachAddReqDTO.getRecordId() );
        fileAttachDTO.setTableName( fileAttachAddReqDTO.getTableName() );
        fileAttachDTO.setUrl( fileAttachAddReqDTO.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public FileAttachDTO convertToFileAttachDTO(FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        if ( fileAttachModifyReqDTO == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setId( fileAttachModifyReqDTO.getId() );
        fileAttachDTO.setIsDel( fileAttachModifyReqDTO.getIsDel() );
        fileAttachDTO.setIsTest( fileAttachModifyReqDTO.getIsTest() );
        fileAttachDTO.setCreateAt( fileAttachModifyReqDTO.getCreateAt() );
        fileAttachDTO.setCreateBy( fileAttachModifyReqDTO.getCreateBy() );
        fileAttachDTO.setCreateName( fileAttachModifyReqDTO.getCreateName() );
        fileAttachDTO.setUpdateAt( fileAttachModifyReqDTO.getUpdateAt() );
        fileAttachDTO.setUpdateBy( fileAttachModifyReqDTO.getUpdateBy() );
        fileAttachDTO.setUpdateName( fileAttachModifyReqDTO.getUpdateName() );
        fileAttachDTO.setIconUrl( fileAttachModifyReqDTO.getIconUrl() );
        fileAttachDTO.setName( fileAttachModifyReqDTO.getName() );
        fileAttachDTO.setPreviewUrl( fileAttachModifyReqDTO.getPreviewUrl() );
        fileAttachDTO.setRecordId( fileAttachModifyReqDTO.getRecordId() );
        fileAttachDTO.setTableName( fileAttachModifyReqDTO.getTableName() );
        fileAttachDTO.setUrl( fileAttachModifyReqDTO.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public FileAttachDTO convertToFileAttachDTO(FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        if ( fileAttachRemoveReqDTO == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setId( fileAttachRemoveReqDTO.getId() );
        fileAttachDTO.setIsDel( fileAttachRemoveReqDTO.getIsDel() );
        fileAttachDTO.setIsTest( fileAttachRemoveReqDTO.getIsTest() );
        fileAttachDTO.setCreateAt( fileAttachRemoveReqDTO.getCreateAt() );
        fileAttachDTO.setCreateBy( fileAttachRemoveReqDTO.getCreateBy() );
        fileAttachDTO.setCreateName( fileAttachRemoveReqDTO.getCreateName() );
        fileAttachDTO.setUpdateAt( fileAttachRemoveReqDTO.getUpdateAt() );
        fileAttachDTO.setUpdateBy( fileAttachRemoveReqDTO.getUpdateBy() );
        fileAttachDTO.setUpdateName( fileAttachRemoveReqDTO.getUpdateName() );
        fileAttachDTO.setIconUrl( fileAttachRemoveReqDTO.getIconUrl() );
        fileAttachDTO.setName( fileAttachRemoveReqDTO.getName() );
        fileAttachDTO.setPreviewUrl( fileAttachRemoveReqDTO.getPreviewUrl() );
        fileAttachDTO.setRecordId( fileAttachRemoveReqDTO.getRecordId() );
        fileAttachDTO.setTableName( fileAttachRemoveReqDTO.getTableName() );
        fileAttachDTO.setUrl( fileAttachRemoveReqDTO.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public FileAttachDTO convertToFileAttachDTO(FileAttachListReqDTO fileAttachListReqDTO) {
        if ( fileAttachListReqDTO == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setId( fileAttachListReqDTO.getId() );
        fileAttachDTO.setIsDel( fileAttachListReqDTO.getIsDel() );
        fileAttachDTO.setIsTest( fileAttachListReqDTO.getIsTest() );
        fileAttachDTO.setCreateAt( fileAttachListReqDTO.getCreateAt() );
        fileAttachDTO.setCreateBy( fileAttachListReqDTO.getCreateBy() );
        fileAttachDTO.setCreateName( fileAttachListReqDTO.getCreateName() );
        fileAttachDTO.setUpdateAt( fileAttachListReqDTO.getUpdateAt() );
        fileAttachDTO.setUpdateBy( fileAttachListReqDTO.getUpdateBy() );
        fileAttachDTO.setUpdateName( fileAttachListReqDTO.getUpdateName() );
        fileAttachDTO.setIconUrl( fileAttachListReqDTO.getIconUrl() );
        fileAttachDTO.setName( fileAttachListReqDTO.getName() );
        fileAttachDTO.setPreviewUrl( fileAttachListReqDTO.getPreviewUrl() );
        fileAttachDTO.setRecordId( fileAttachListReqDTO.getRecordId() );
        fileAttachDTO.setTableName( fileAttachListReqDTO.getTableName() );
        fileAttachDTO.setUrl( fileAttachListReqDTO.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public FileAttachDTO convertToFileAttachDTO(FileAttachPageReqDTO fileAttachPageReqDTO) {
        if ( fileAttachPageReqDTO == null ) {
            return null;
        }

        FileAttachDTO fileAttachDTO = new FileAttachDTO();

        fileAttachDTO.setId( fileAttachPageReqDTO.getId() );
        fileAttachDTO.setIsDel( fileAttachPageReqDTO.getIsDel() );
        fileAttachDTO.setIsTest( fileAttachPageReqDTO.getIsTest() );
        fileAttachDTO.setCreateAt( fileAttachPageReqDTO.getCreateAt() );
        fileAttachDTO.setCreateBy( fileAttachPageReqDTO.getCreateBy() );
        fileAttachDTO.setCreateName( fileAttachPageReqDTO.getCreateName() );
        fileAttachDTO.setUpdateAt( fileAttachPageReqDTO.getUpdateAt() );
        fileAttachDTO.setUpdateBy( fileAttachPageReqDTO.getUpdateBy() );
        fileAttachDTO.setUpdateName( fileAttachPageReqDTO.getUpdateName() );
        fileAttachDTO.setIconUrl( fileAttachPageReqDTO.getIconUrl() );
        fileAttachDTO.setName( fileAttachPageReqDTO.getName() );
        fileAttachDTO.setPreviewUrl( fileAttachPageReqDTO.getPreviewUrl() );
        fileAttachDTO.setRecordId( fileAttachPageReqDTO.getRecordId() );
        fileAttachDTO.setTableName( fileAttachPageReqDTO.getTableName() );
        fileAttachDTO.setUrl( fileAttachPageReqDTO.getUrl() );

        return fileAttachDTO;
    }

    @Override
    public FileAttachShowResDTO convertToFileAttachShowResDTO(FileAttachDTO fileAttachDTO) {
        if ( fileAttachDTO == null ) {
            return null;
        }

        FileAttachShowResDTO fileAttachShowResDTO = new FileAttachShowResDTO();

        fileAttachShowResDTO.setCreateAt( fileAttachDTO.getCreateAt() );
        fileAttachShowResDTO.setCreateBy( fileAttachDTO.getCreateBy() );
        fileAttachShowResDTO.setCreateName( fileAttachDTO.getCreateName() );
        fileAttachShowResDTO.setIconUrl( fileAttachDTO.getIconUrl() );
        fileAttachShowResDTO.setId( fileAttachDTO.getId() );
        fileAttachShowResDTO.setIsDel( fileAttachDTO.getIsDel() );
        fileAttachShowResDTO.setIsTest( fileAttachDTO.getIsTest() );
        fileAttachShowResDTO.setName( fileAttachDTO.getName() );
        fileAttachShowResDTO.setPreviewUrl( fileAttachDTO.getPreviewUrl() );
        fileAttachShowResDTO.setRecordId( fileAttachDTO.getRecordId() );
        fileAttachShowResDTO.setTableName( fileAttachDTO.getTableName() );
        fileAttachShowResDTO.setUpdateAt( fileAttachDTO.getUpdateAt() );
        fileAttachShowResDTO.setUpdateBy( fileAttachDTO.getUpdateBy() );
        fileAttachShowResDTO.setUpdateName( fileAttachDTO.getUpdateName() );
        fileAttachShowResDTO.setUrl( fileAttachDTO.getUrl() );

        return fileAttachShowResDTO;
    }

    @Override
    public List<FileAttachShowResDTO> convertToFileAttachShowResDTOList(List<FileAttachDTO> fileAttachDTOList) {
        if ( fileAttachDTOList == null ) {
            return null;
        }

        List<FileAttachShowResDTO> list = new ArrayList<FileAttachShowResDTO>( fileAttachDTOList.size() );
        for ( FileAttachDTO fileAttachDTO : fileAttachDTOList ) {
            list.add( convertToFileAttachShowResDTO( fileAttachDTO ) );
        }

        return list;
    }

    @Override
    public FileAttachListResDTO convertToFileAttachListResDTO(FileAttachDTO fileAttachDTO) {
        if ( fileAttachDTO == null ) {
            return null;
        }

        FileAttachListResDTO fileAttachListResDTO = new FileAttachListResDTO();

        fileAttachListResDTO.setCreateAt( fileAttachDTO.getCreateAt() );
        fileAttachListResDTO.setCreateBy( fileAttachDTO.getCreateBy() );
        fileAttachListResDTO.setCreateName( fileAttachDTO.getCreateName() );
        fileAttachListResDTO.setIconUrl( fileAttachDTO.getIconUrl() );
        fileAttachListResDTO.setId( fileAttachDTO.getId() );
        fileAttachListResDTO.setIsDel( fileAttachDTO.getIsDel() );
        fileAttachListResDTO.setIsTest( fileAttachDTO.getIsTest() );
        fileAttachListResDTO.setName( fileAttachDTO.getName() );
        fileAttachListResDTO.setPreviewUrl( fileAttachDTO.getPreviewUrl() );
        fileAttachListResDTO.setRecordId( fileAttachDTO.getRecordId() );
        fileAttachListResDTO.setTableName( fileAttachDTO.getTableName() );
        fileAttachListResDTO.setUpdateAt( fileAttachDTO.getUpdateAt() );
        fileAttachListResDTO.setUpdateBy( fileAttachDTO.getUpdateBy() );
        fileAttachListResDTO.setUpdateName( fileAttachDTO.getUpdateName() );
        fileAttachListResDTO.setUrl( fileAttachDTO.getUrl() );

        return fileAttachListResDTO;
    }

    @Override
    public List<FileAttachListResDTO> convertToFileAttachListResDTOList(List<FileAttachDTO> fileAttachDTOList) {
        if ( fileAttachDTOList == null ) {
            return null;
        }

        List<FileAttachListResDTO> list = new ArrayList<FileAttachListResDTO>( fileAttachDTOList.size() );
        for ( FileAttachDTO fileAttachDTO : fileAttachDTOList ) {
            list.add( convertToFileAttachListResDTO( fileAttachDTO ) );
        }

        return list;
    }

    @Override
    public List<FileAttachDTO> convertToFileAttachDTOList(List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        if ( fileAttachAddReqDTOList == null ) {
            return null;
        }

        List<FileAttachDTO> list = new ArrayList<FileAttachDTO>( fileAttachAddReqDTOList.size() );
        for ( FileAttachAddReqDTO fileAttachAddReqDTO : fileAttachAddReqDTOList ) {
            list.add( convertToFileAttachDTO( fileAttachAddReqDTO ) );
        }

        return list;
    }

    @Override
    public FileAttachPageResDTO convertToFileAttachPageResDTO(FileAttachDTO fileAttachDTO) {
        if ( fileAttachDTO == null ) {
            return null;
        }

        FileAttachPageResDTO fileAttachPageResDTO = new FileAttachPageResDTO();

        fileAttachPageResDTO.setCreateAt( fileAttachDTO.getCreateAt() );
        fileAttachPageResDTO.setCreateBy( fileAttachDTO.getCreateBy() );
        fileAttachPageResDTO.setCreateName( fileAttachDTO.getCreateName() );
        fileAttachPageResDTO.setIconUrl( fileAttachDTO.getIconUrl() );
        fileAttachPageResDTO.setId( fileAttachDTO.getId() );
        fileAttachPageResDTO.setIsDel( fileAttachDTO.getIsDel() );
        fileAttachPageResDTO.setIsTest( fileAttachDTO.getIsTest() );
        fileAttachPageResDTO.setName( fileAttachDTO.getName() );
        fileAttachPageResDTO.setPreviewUrl( fileAttachDTO.getPreviewUrl() );
        fileAttachPageResDTO.setRecordId( fileAttachDTO.getRecordId() );
        fileAttachPageResDTO.setTableName( fileAttachDTO.getTableName() );
        fileAttachPageResDTO.setUpdateAt( fileAttachDTO.getUpdateAt() );
        fileAttachPageResDTO.setUpdateBy( fileAttachDTO.getUpdateBy() );
        fileAttachPageResDTO.setUpdateName( fileAttachDTO.getUpdateName() );
        fileAttachPageResDTO.setUrl( fileAttachDTO.getUrl() );

        return fileAttachPageResDTO;
    }

    @Override
    public List<FileAttachPageResDTO> convertToFileAttachPageResDTOList(List<FileAttachDTO> fileAttachDTOList) {
        if ( fileAttachDTOList == null ) {
            return null;
        }

        List<FileAttachPageResDTO> list = new ArrayList<FileAttachPageResDTO>( fileAttachDTOList.size() );
        for ( FileAttachDTO fileAttachDTO : fileAttachDTOList ) {
            list.add( convertToFileAttachPageResDTO( fileAttachDTO ) );
        }

        return list;
    }

    @Override
    public Page<FileAttachPageResDTO> convertToFileAttachPageResDTOPage(Page<FileAttachDTO> fileAttachDTOPage) {
        if ( fileAttachDTOPage == null ) {
            return null;
        }

        Page<FileAttachPageResDTO> page = new Page<FileAttachPageResDTO>();

        page.setRecords( convertToFileAttachPageResDTOList( fileAttachDTOPage.getRecords() ) );
        page.setTotal( fileAttachDTOPage.getTotal() );
        page.setSize( fileAttachDTOPage.getSize() );
        page.setCurrent( fileAttachDTOPage.getCurrent() );

        return page;
    }
}

package com.vehicle.service.log.converter;

import com.finhub.framework.core.page.Page;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class LogConverterImpl implements LogConverter {

    @Override
    public LogDTO poToDTO(LogPO po) {
        if ( po == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setId( po.getId() );
        logDTO.setIsDel( po.getIsDel() );
        logDTO.setIsTest( po.getIsTest() );
        logDTO.setCreateAt( po.getCreateAt() );
        logDTO.setCreateTime( po.getCreateTime() );
        logDTO.setCreateBy( po.getCreateBy() );
        logDTO.setCreateName( po.getCreateName() );
        logDTO.setUpdateAt( po.getUpdateAt() );
        logDTO.setUpdateTime( po.getUpdateTime() );
        logDTO.setUpdateBy( po.getUpdateBy() );
        logDTO.setUpdateName( po.getUpdateName() );
        logDTO.setAfterValue( po.getAfterValue() );
        logDTO.setBeforeValue( po.getBeforeValue() );
        logDTO.setFieldName( po.getFieldName() );
        logDTO.setLogTypeCode( po.getLogTypeCode() );
        logDTO.setLogTypeVal( po.getLogTypeVal() );
        logDTO.setOptDesc( po.getOptDesc() );
        logDTO.setOptTypeCode( po.getOptTypeCode() );
        logDTO.setOptTypeVal( po.getOptTypeVal() );
        logDTO.setRecordId( po.getRecordId() );
        logDTO.setRemark( po.getRemark() );
        logDTO.setTableName( po.getTableName() );

        return logDTO;
    }

    @Override
    public List<LogDTO> poToDTOList(List<LogPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<LogDTO> list = new ArrayList<LogDTO>( arg0.size() );
        for ( LogPO logPO : arg0 ) {
            list.add( poToDTO( logPO ) );
        }

        return list;
    }

    @Override
    public LogPO dtoToPO(LogDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LogPO logPO = new LogPO();

        logPO.setId( arg0.getId() );
        logPO.setIsDel( arg0.getIsDel() );
        logPO.setIsTest( arg0.getIsTest() );
        logPO.setCreateAt( arg0.getCreateAt() );
        logPO.setCreateTime( arg0.getCreateTime() );
        logPO.setCreateBy( arg0.getCreateBy() );
        logPO.setCreateName( arg0.getCreateName() );
        logPO.setUpdateAt( arg0.getUpdateAt() );
        logPO.setUpdateTime( arg0.getUpdateTime() );
        logPO.setUpdateBy( arg0.getUpdateBy() );
        logPO.setUpdateName( arg0.getUpdateName() );
        logPO.setTableName( arg0.getTableName() );
        logPO.setRecordId( arg0.getRecordId() );
        logPO.setFieldName( arg0.getFieldName() );
        logPO.setLogTypeVal( arg0.getLogTypeVal() );
        logPO.setLogTypeCode( arg0.getLogTypeCode() );
        logPO.setOptTypeVal( arg0.getOptTypeVal() );
        logPO.setOptTypeCode( arg0.getOptTypeCode() );
        logPO.setOptDesc( arg0.getOptDesc() );
        logPO.setBeforeValue( arg0.getBeforeValue() );
        logPO.setAfterValue( arg0.getAfterValue() );
        logPO.setRemark( arg0.getRemark() );

        return logPO;
    }

    @Override
    public List<LogPO> dtoToPOList(List<LogDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<LogPO> list = new ArrayList<LogPO>( arg0.size() );
        for ( LogDTO logDTO : arg0 ) {
            list.add( dtoToPO( logDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(LogDTO arg0, LogPO arg1) {
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
        arg1.setFieldName( arg0.getFieldName() );
        arg1.setLogTypeVal( arg0.getLogTypeVal() );
        arg1.setLogTypeCode( arg0.getLogTypeCode() );
        arg1.setOptTypeVal( arg0.getOptTypeVal() );
        arg1.setOptTypeCode( arg0.getOptTypeCode() );
        arg1.setOptDesc( arg0.getOptDesc() );
        arg1.setBeforeValue( arg0.getBeforeValue() );
        arg1.setAfterValue( arg0.getAfterValue() );
        arg1.setRemark( arg0.getRemark() );
    }

    @Override
    public void updateDto(LogPO arg0, LogDTO arg1) {
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
        arg1.setAfterValue( arg0.getAfterValue() );
        arg1.setBeforeValue( arg0.getBeforeValue() );
        arg1.setFieldName( arg0.getFieldName() );
        arg1.setLogTypeCode( arg0.getLogTypeCode() );
        arg1.setLogTypeVal( arg0.getLogTypeVal() );
        arg1.setOptDesc( arg0.getOptDesc() );
        arg1.setOptTypeCode( arg0.getOptTypeCode() );
        arg1.setOptTypeVal( arg0.getOptTypeVal() );
        arg1.setRecordId( arg0.getRecordId() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setTableName( arg0.getTableName() );
    }

    @Override
    public LogDTO convertToLogDTO(LogAddReqDTO logAddReqDTO) {
        if ( logAddReqDTO == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setIsDel( logAddReqDTO.getIsDel() );
        logDTO.setIsTest( logAddReqDTO.getIsTest() );
        logDTO.setCreateAt( logAddReqDTO.getCreateAt() );
        logDTO.setCreateBy( logAddReqDTO.getCreateBy() );
        logDTO.setCreateName( logAddReqDTO.getCreateName() );
        logDTO.setUpdateAt( logAddReqDTO.getUpdateAt() );
        logDTO.setUpdateBy( logAddReqDTO.getUpdateBy() );
        logDTO.setUpdateName( logAddReqDTO.getUpdateName() );
        logDTO.setAfterValue( logAddReqDTO.getAfterValue() );
        logDTO.setBeforeValue( logAddReqDTO.getBeforeValue() );
        logDTO.setFieldName( logAddReqDTO.getFieldName() );
        logDTO.setLogTypeCode( logAddReqDTO.getLogTypeCode() );
        logDTO.setLogTypeVal( logAddReqDTO.getLogTypeVal() );
        logDTO.setOptDesc( logAddReqDTO.getOptDesc() );
        logDTO.setOptTypeCode( logAddReqDTO.getOptTypeCode() );
        logDTO.setOptTypeVal( logAddReqDTO.getOptTypeVal() );
        logDTO.setRecordId( logAddReqDTO.getRecordId() );
        logDTO.setRemark( logAddReqDTO.getRemark() );
        logDTO.setTableName( logAddReqDTO.getTableName() );

        return logDTO;
    }

    @Override
    public LogDTO convertToLogDTO(LogModifyReqDTO logModifyReqDTO) {
        if ( logModifyReqDTO == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setId( logModifyReqDTO.getId() );
        logDTO.setIsDel( logModifyReqDTO.getIsDel() );
        logDTO.setIsTest( logModifyReqDTO.getIsTest() );
        logDTO.setCreateAt( logModifyReqDTO.getCreateAt() );
        logDTO.setCreateBy( logModifyReqDTO.getCreateBy() );
        logDTO.setCreateName( logModifyReqDTO.getCreateName() );
        logDTO.setUpdateAt( logModifyReqDTO.getUpdateAt() );
        logDTO.setUpdateBy( logModifyReqDTO.getUpdateBy() );
        logDTO.setUpdateName( logModifyReqDTO.getUpdateName() );
        logDTO.setAfterValue( logModifyReqDTO.getAfterValue() );
        logDTO.setBeforeValue( logModifyReqDTO.getBeforeValue() );
        logDTO.setFieldName( logModifyReqDTO.getFieldName() );
        logDTO.setLogTypeCode( logModifyReqDTO.getLogTypeCode() );
        logDTO.setLogTypeVal( logModifyReqDTO.getLogTypeVal() );
        logDTO.setOptDesc( logModifyReqDTO.getOptDesc() );
        logDTO.setOptTypeCode( logModifyReqDTO.getOptTypeCode() );
        logDTO.setOptTypeVal( logModifyReqDTO.getOptTypeVal() );
        logDTO.setRecordId( logModifyReqDTO.getRecordId() );
        logDTO.setRemark( logModifyReqDTO.getRemark() );
        logDTO.setTableName( logModifyReqDTO.getTableName() );

        return logDTO;
    }

    @Override
    public LogDTO convertToLogDTO(LogRemoveReqDTO logRemoveReqDTO) {
        if ( logRemoveReqDTO == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setId( logRemoveReqDTO.getId() );
        logDTO.setIsDel( logRemoveReqDTO.getIsDel() );
        logDTO.setIsTest( logRemoveReqDTO.getIsTest() );
        logDTO.setCreateAt( logRemoveReqDTO.getCreateAt() );
        logDTO.setCreateBy( logRemoveReqDTO.getCreateBy() );
        logDTO.setCreateName( logRemoveReqDTO.getCreateName() );
        logDTO.setUpdateAt( logRemoveReqDTO.getUpdateAt() );
        logDTO.setUpdateBy( logRemoveReqDTO.getUpdateBy() );
        logDTO.setUpdateName( logRemoveReqDTO.getUpdateName() );
        logDTO.setAfterValue( logRemoveReqDTO.getAfterValue() );
        logDTO.setBeforeValue( logRemoveReqDTO.getBeforeValue() );
        logDTO.setFieldName( logRemoveReqDTO.getFieldName() );
        logDTO.setLogTypeCode( logRemoveReqDTO.getLogTypeCode() );
        logDTO.setLogTypeVal( logRemoveReqDTO.getLogTypeVal() );
        logDTO.setOptDesc( logRemoveReqDTO.getOptDesc() );
        logDTO.setOptTypeCode( logRemoveReqDTO.getOptTypeCode() );
        logDTO.setOptTypeVal( logRemoveReqDTO.getOptTypeVal() );
        logDTO.setRecordId( logRemoveReqDTO.getRecordId() );
        logDTO.setRemark( logRemoveReqDTO.getRemark() );
        logDTO.setTableName( logRemoveReqDTO.getTableName() );

        return logDTO;
    }

    @Override
    public LogDTO convertToLogDTO(LogListReqDTO logListReqDTO) {
        if ( logListReqDTO == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setId( logListReqDTO.getId() );
        logDTO.setIsDel( logListReqDTO.getIsDel() );
        logDTO.setIsTest( logListReqDTO.getIsTest() );
        logDTO.setCreateAt( logListReqDTO.getCreateAt() );
        logDTO.setCreateBy( logListReqDTO.getCreateBy() );
        logDTO.setCreateName( logListReqDTO.getCreateName() );
        logDTO.setUpdateAt( logListReqDTO.getUpdateAt() );
        logDTO.setUpdateBy( logListReqDTO.getUpdateBy() );
        logDTO.setUpdateName( logListReqDTO.getUpdateName() );
        logDTO.setAfterValue( logListReqDTO.getAfterValue() );
        logDTO.setBeforeValue( logListReqDTO.getBeforeValue() );
        logDTO.setFieldName( logListReqDTO.getFieldName() );
        logDTO.setLogTypeCode( logListReqDTO.getLogTypeCode() );
        logDTO.setLogTypeVal( logListReqDTO.getLogTypeVal() );
        logDTO.setOptDesc( logListReqDTO.getOptDesc() );
        logDTO.setOptTypeCode( logListReqDTO.getOptTypeCode() );
        logDTO.setOptTypeVal( logListReqDTO.getOptTypeVal() );
        logDTO.setRecordId( logListReqDTO.getRecordId() );
        logDTO.setRemark( logListReqDTO.getRemark() );
        logDTO.setTableName( logListReqDTO.getTableName() );

        return logDTO;
    }

    @Override
    public LogDTO convertToLogDTO(LogPageReqDTO logPageReqDTO) {
        if ( logPageReqDTO == null ) {
            return null;
        }

        LogDTO logDTO = new LogDTO();

        logDTO.setId( logPageReqDTO.getId() );
        logDTO.setIsDel( logPageReqDTO.getIsDel() );
        logDTO.setIsTest( logPageReqDTO.getIsTest() );
        logDTO.setCreateAt( logPageReqDTO.getCreateAt() );
        logDTO.setCreateBy( logPageReqDTO.getCreateBy() );
        logDTO.setCreateName( logPageReqDTO.getCreateName() );
        logDTO.setUpdateAt( logPageReqDTO.getUpdateAt() );
        logDTO.setUpdateBy( logPageReqDTO.getUpdateBy() );
        logDTO.setUpdateName( logPageReqDTO.getUpdateName() );
        logDTO.setAfterValue( logPageReqDTO.getAfterValue() );
        logDTO.setBeforeValue( logPageReqDTO.getBeforeValue() );
        logDTO.setFieldName( logPageReqDTO.getFieldName() );
        logDTO.setLogTypeCode( logPageReqDTO.getLogTypeCode() );
        logDTO.setLogTypeVal( logPageReqDTO.getLogTypeVal() );
        logDTO.setOptDesc( logPageReqDTO.getOptDesc() );
        logDTO.setOptTypeCode( logPageReqDTO.getOptTypeCode() );
        logDTO.setOptTypeVal( logPageReqDTO.getOptTypeVal() );
        logDTO.setRecordId( logPageReqDTO.getRecordId() );
        logDTO.setRemark( logPageReqDTO.getRemark() );
        logDTO.setTableName( logPageReqDTO.getTableName() );

        return logDTO;
    }

    @Override
    public LogShowResDTO convertToLogShowResDTO(LogDTO logDTO) {
        if ( logDTO == null ) {
            return null;
        }

        LogShowResDTO logShowResDTO = new LogShowResDTO();

        logShowResDTO.setAfterValue( logDTO.getAfterValue() );
        logShowResDTO.setBeforeValue( logDTO.getBeforeValue() );
        logShowResDTO.setCreateAt( logDTO.getCreateAt() );
        logShowResDTO.setCreateBy( logDTO.getCreateBy() );
        logShowResDTO.setCreateName( logDTO.getCreateName() );
        logShowResDTO.setFieldName( logDTO.getFieldName() );
        logShowResDTO.setId( logDTO.getId() );
        logShowResDTO.setIsDel( logDTO.getIsDel() );
        logShowResDTO.setIsTest( logDTO.getIsTest() );
        logShowResDTO.setLogTypeCode( logDTO.getLogTypeCode() );
        logShowResDTO.setLogTypeVal( logDTO.getLogTypeVal() );
        logShowResDTO.setOptDesc( logDTO.getOptDesc() );
        logShowResDTO.setOptTypeCode( logDTO.getOptTypeCode() );
        logShowResDTO.setOptTypeVal( logDTO.getOptTypeVal() );
        logShowResDTO.setRecordId( logDTO.getRecordId() );
        logShowResDTO.setRemark( logDTO.getRemark() );
        logShowResDTO.setTableName( logDTO.getTableName() );
        logShowResDTO.setUpdateAt( logDTO.getUpdateAt() );
        logShowResDTO.setUpdateBy( logDTO.getUpdateBy() );
        logShowResDTO.setUpdateName( logDTO.getUpdateName() );

        return logShowResDTO;
    }

    @Override
    public List<LogShowResDTO> convertToLogShowResDTOList(List<LogDTO> logDTOList) {
        if ( logDTOList == null ) {
            return null;
        }

        List<LogShowResDTO> list = new ArrayList<LogShowResDTO>( logDTOList.size() );
        for ( LogDTO logDTO : logDTOList ) {
            list.add( convertToLogShowResDTO( logDTO ) );
        }

        return list;
    }

    @Override
    public LogListResDTO convertToLogListResDTO(LogDTO logDTO) {
        if ( logDTO == null ) {
            return null;
        }

        LogListResDTO logListResDTO = new LogListResDTO();

        logListResDTO.setAfterValue( logDTO.getAfterValue() );
        logListResDTO.setBeforeValue( logDTO.getBeforeValue() );
        logListResDTO.setCreateAt( logDTO.getCreateAt() );
        logListResDTO.setCreateBy( logDTO.getCreateBy() );
        logListResDTO.setCreateName( logDTO.getCreateName() );
        logListResDTO.setFieldName( logDTO.getFieldName() );
        logListResDTO.setId( logDTO.getId() );
        logListResDTO.setIsDel( logDTO.getIsDel() );
        logListResDTO.setIsTest( logDTO.getIsTest() );
        logListResDTO.setLogTypeCode( logDTO.getLogTypeCode() );
        logListResDTO.setLogTypeVal( logDTO.getLogTypeVal() );
        logListResDTO.setOptDesc( logDTO.getOptDesc() );
        logListResDTO.setOptTypeCode( logDTO.getOptTypeCode() );
        logListResDTO.setOptTypeVal( logDTO.getOptTypeVal() );
        logListResDTO.setRecordId( logDTO.getRecordId() );
        logListResDTO.setRemark( logDTO.getRemark() );
        logListResDTO.setTableName( logDTO.getTableName() );
        logListResDTO.setUpdateAt( logDTO.getUpdateAt() );
        logListResDTO.setUpdateBy( logDTO.getUpdateBy() );
        logListResDTO.setUpdateName( logDTO.getUpdateName() );

        return logListResDTO;
    }

    @Override
    public List<LogListResDTO> convertToLogListResDTOList(List<LogDTO> logDTOList) {
        if ( logDTOList == null ) {
            return null;
        }

        List<LogListResDTO> list = new ArrayList<LogListResDTO>( logDTOList.size() );
        for ( LogDTO logDTO : logDTOList ) {
            list.add( convertToLogListResDTO( logDTO ) );
        }

        return list;
    }

    @Override
    public List<LogDTO> convertToLogDTOList(List<LogAddReqDTO> logAddReqDTOList) {
        if ( logAddReqDTOList == null ) {
            return null;
        }

        List<LogDTO> list = new ArrayList<LogDTO>( logAddReqDTOList.size() );
        for ( LogAddReqDTO logAddReqDTO : logAddReqDTOList ) {
            list.add( convertToLogDTO( logAddReqDTO ) );
        }

        return list;
    }

    @Override
    public LogPageResDTO convertToLogPageResDTO(LogDTO logDTO) {
        if ( logDTO == null ) {
            return null;
        }

        LogPageResDTO logPageResDTO = new LogPageResDTO();

        logPageResDTO.setAfterValue( logDTO.getAfterValue() );
        logPageResDTO.setBeforeValue( logDTO.getBeforeValue() );
        logPageResDTO.setCreateAt( logDTO.getCreateAt() );
        logPageResDTO.setCreateBy( logDTO.getCreateBy() );
        logPageResDTO.setCreateName( logDTO.getCreateName() );
        logPageResDTO.setFieldName( logDTO.getFieldName() );
        logPageResDTO.setId( logDTO.getId() );
        logPageResDTO.setIsDel( logDTO.getIsDel() );
        logPageResDTO.setIsTest( logDTO.getIsTest() );
        logPageResDTO.setLogTypeCode( logDTO.getLogTypeCode() );
        logPageResDTO.setLogTypeVal( logDTO.getLogTypeVal() );
        logPageResDTO.setOptDesc( logDTO.getOptDesc() );
        logPageResDTO.setOptTypeCode( logDTO.getOptTypeCode() );
        logPageResDTO.setOptTypeVal( logDTO.getOptTypeVal() );
        logPageResDTO.setRecordId( logDTO.getRecordId() );
        logPageResDTO.setRemark( logDTO.getRemark() );
        logPageResDTO.setTableName( logDTO.getTableName() );
        logPageResDTO.setUpdateAt( logDTO.getUpdateAt() );
        logPageResDTO.setUpdateBy( logDTO.getUpdateBy() );
        logPageResDTO.setUpdateName( logDTO.getUpdateName() );

        return logPageResDTO;
    }

    @Override
    public List<LogPageResDTO> convertToLogPageResDTOList(List<LogDTO> logDTOList) {
        if ( logDTOList == null ) {
            return null;
        }

        List<LogPageResDTO> list = new ArrayList<LogPageResDTO>( logDTOList.size() );
        for ( LogDTO logDTO : logDTOList ) {
            list.add( convertToLogPageResDTO( logDTO ) );
        }

        return list;
    }

    @Override
    public Page<LogPageResDTO> convertToLogPageResDTOPage(Page<LogDTO> logDTOPage) {
        if ( logDTOPage == null ) {
            return null;
        }

        Page<LogPageResDTO> page = new Page<LogPageResDTO>();

        page.setRecords( convertToLogPageResDTOList( logDTOPage.getRecords() ) );
        page.setTotal( logDTOPage.getTotal() );
        page.setSize( logDTOPage.getSize() );
        page.setCurrent( logDTOPage.getCurrent() );

        return page;
    }
}

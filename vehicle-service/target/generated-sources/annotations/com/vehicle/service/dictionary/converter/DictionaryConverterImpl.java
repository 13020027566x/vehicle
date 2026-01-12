package com.vehicle.service.dictionary.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.dictionary.po.DictionaryPO;
import com.vehicle.service.dictionary.dto.DictionaryAddReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryDTO;
import com.vehicle.service.dictionary.dto.DictionaryListReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryListResDTO;
import com.vehicle.service.dictionary.dto.DictionaryModifyReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageResDTO;
import com.vehicle.service.dictionary.dto.DictionaryRemoveReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryShowResDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class DictionaryConverterImpl implements DictionaryConverter {

    @Override
    public DictionaryDTO poToDTO(DictionaryPO po) {
        if ( po == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setId( po.getId() );
        dictionaryDTO.setIsDel( po.getIsDel() );
        dictionaryDTO.setIsTest( po.getIsTest() );
        dictionaryDTO.setCreateAt( po.getCreateAt() );
        dictionaryDTO.setCreateTime( po.getCreateTime() );
        dictionaryDTO.setCreateBy( po.getCreateBy() );
        dictionaryDTO.setCreateName( po.getCreateName() );
        dictionaryDTO.setUpdateAt( po.getUpdateAt() );
        dictionaryDTO.setUpdateTime( po.getUpdateTime() );
        dictionaryDTO.setUpdateBy( po.getUpdateBy() );
        dictionaryDTO.setUpdateName( po.getUpdateName() );
        dictionaryDTO.setCode( po.getCode() );
        dictionaryDTO.setName( po.getName() );
        dictionaryDTO.setPcode( po.getPcode() );
        dictionaryDTO.setPid( po.getPid() );
        dictionaryDTO.setSort( po.getSort() );
        dictionaryDTO.setType( po.getType() );
        dictionaryDTO.setTypeName( po.getTypeName() );
        dictionaryDTO.setValue( po.getValue() );

        return dictionaryDTO;
    }

    @Override
    public List<DictionaryDTO> poToDTOList(List<DictionaryPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictionaryDTO> list = new ArrayList<DictionaryDTO>( arg0.size() );
        for ( DictionaryPO dictionaryPO : arg0 ) {
            list.add( poToDTO( dictionaryPO ) );
        }

        return list;
    }

    @Override
    public DictionaryPO dtoToPO(DictionaryDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        DictionaryPO dictionaryPO = new DictionaryPO();

        dictionaryPO.setId( arg0.getId() );
        dictionaryPO.setIsDel( arg0.getIsDel() );
        dictionaryPO.setIsTest( arg0.getIsTest() );
        dictionaryPO.setCreateAt( arg0.getCreateAt() );
        dictionaryPO.setCreateTime( arg0.getCreateTime() );
        dictionaryPO.setCreateBy( arg0.getCreateBy() );
        dictionaryPO.setCreateName( arg0.getCreateName() );
        dictionaryPO.setUpdateAt( arg0.getUpdateAt() );
        dictionaryPO.setUpdateTime( arg0.getUpdateTime() );
        dictionaryPO.setUpdateBy( arg0.getUpdateBy() );
        dictionaryPO.setUpdateName( arg0.getUpdateName() );
        dictionaryPO.setName( arg0.getName() );
        dictionaryPO.setCode( arg0.getCode() );
        dictionaryPO.setValue( arg0.getValue() );
        dictionaryPO.setType( arg0.getType() );
        dictionaryPO.setTypeName( arg0.getTypeName() );
        dictionaryPO.setSort( arg0.getSort() );
        dictionaryPO.setPid( arg0.getPid() );
        dictionaryPO.setPcode( arg0.getPcode() );

        return dictionaryPO;
    }

    @Override
    public List<DictionaryPO> dtoToPOList(List<DictionaryDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<DictionaryPO> list = new ArrayList<DictionaryPO>( arg0.size() );
        for ( DictionaryDTO dictionaryDTO : arg0 ) {
            list.add( dtoToPO( dictionaryDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(DictionaryDTO arg0, DictionaryPO arg1) {
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
        arg1.setName( arg0.getName() );
        arg1.setCode( arg0.getCode() );
        arg1.setValue( arg0.getValue() );
        arg1.setType( arg0.getType() );
        arg1.setTypeName( arg0.getTypeName() );
        arg1.setSort( arg0.getSort() );
        arg1.setPid( arg0.getPid() );
        arg1.setPcode( arg0.getPcode() );
    }

    @Override
    public void updateDto(DictionaryPO arg0, DictionaryDTO arg1) {
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
        arg1.setCode( arg0.getCode() );
        arg1.setName( arg0.getName() );
        arg1.setPcode( arg0.getPcode() );
        arg1.setPid( arg0.getPid() );
        arg1.setSort( arg0.getSort() );
        arg1.setType( arg0.getType() );
        arg1.setTypeName( arg0.getTypeName() );
        arg1.setValue( arg0.getValue() );
    }

    @Override
    public DictionaryDTO convertToDictionaryDTO(DictionaryAddReqDTO dictionaryAddReqDTO) {
        if ( dictionaryAddReqDTO == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setCode( dictionaryAddReqDTO.getCode() );
        dictionaryDTO.setName( dictionaryAddReqDTO.getName() );
        dictionaryDTO.setPid( dictionaryAddReqDTO.getPid() );
        dictionaryDTO.setType( dictionaryAddReqDTO.getType() );
        dictionaryDTO.setTypeName( dictionaryAddReqDTO.getTypeName() );
        dictionaryDTO.setValue( dictionaryAddReqDTO.getValue() );

        return dictionaryDTO;
    }

    @Override
    public DictionaryDTO convertToDictionaryDTO(DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        if ( dictionaryModifyReqDTO == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setId( dictionaryModifyReqDTO.getId() );
        dictionaryDTO.setCode( dictionaryModifyReqDTO.getCode() );
        dictionaryDTO.setName( dictionaryModifyReqDTO.getName() );
        dictionaryDTO.setPcode( dictionaryModifyReqDTO.getPcode() );
        dictionaryDTO.setPid( dictionaryModifyReqDTO.getPid() );
        dictionaryDTO.setType( dictionaryModifyReqDTO.getType() );
        dictionaryDTO.setTypeName( dictionaryModifyReqDTO.getTypeName() );
        dictionaryDTO.setValue( dictionaryModifyReqDTO.getValue() );

        return dictionaryDTO;
    }

    @Override
    public DictionaryDTO convertToDictionaryDTO(DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        if ( dictionaryRemoveReqDTO == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setId( dictionaryRemoveReqDTO.getId() );
        dictionaryDTO.setIsDel( dictionaryRemoveReqDTO.getIsDel() );
        dictionaryDTO.setIsTest( dictionaryRemoveReqDTO.getIsTest() );
        dictionaryDTO.setCreateAt( dictionaryRemoveReqDTO.getCreateAt() );
        dictionaryDTO.setCreateBy( dictionaryRemoveReqDTO.getCreateBy() );
        dictionaryDTO.setCreateName( dictionaryRemoveReqDTO.getCreateName() );
        dictionaryDTO.setUpdateAt( dictionaryRemoveReqDTO.getUpdateAt() );
        dictionaryDTO.setUpdateBy( dictionaryRemoveReqDTO.getUpdateBy() );
        dictionaryDTO.setUpdateName( dictionaryRemoveReqDTO.getUpdateName() );
        dictionaryDTO.setCode( dictionaryRemoveReqDTO.getCode() );
        dictionaryDTO.setName( dictionaryRemoveReqDTO.getName() );
        dictionaryDTO.setPcode( dictionaryRemoveReqDTO.getPcode() );
        dictionaryDTO.setPid( dictionaryRemoveReqDTO.getPid() );
        dictionaryDTO.setSort( dictionaryRemoveReqDTO.getSort() );
        dictionaryDTO.setType( dictionaryRemoveReqDTO.getType() );
        dictionaryDTO.setValue( dictionaryRemoveReqDTO.getValue() );

        return dictionaryDTO;
    }

    @Override
    public DictionaryDTO convertToDictionaryDTO(DictionaryListReqDTO dictionaryListReqDTO) {
        if ( dictionaryListReqDTO == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setId( dictionaryListReqDTO.getId() );
        dictionaryDTO.setCode( dictionaryListReqDTO.getCode() );
        dictionaryDTO.setName( dictionaryListReqDTO.getName() );
        dictionaryDTO.setPid( dictionaryListReqDTO.getPid() );
        dictionaryDTO.setType( dictionaryListReqDTO.getType() );
        dictionaryDTO.setTypeName( dictionaryListReqDTO.getTypeName() );
        dictionaryDTO.setValue( dictionaryListReqDTO.getValue() );

        return dictionaryDTO;
    }

    @Override
    public DictionaryDTO convertToDictionaryDTO(DictionaryPageReqDTO dictionaryPageReqDTO) {
        if ( dictionaryPageReqDTO == null ) {
            return null;
        }

        DictionaryDTO dictionaryDTO = new DictionaryDTO();

        dictionaryDTO.setCode( dictionaryPageReqDTO.getCode() );
        dictionaryDTO.setName( dictionaryPageReqDTO.getName() );
        dictionaryDTO.setPcode( dictionaryPageReqDTO.getPcode() );
        dictionaryDTO.setType( dictionaryPageReqDTO.getType() );
        dictionaryDTO.setValue( dictionaryPageReqDTO.getValue() );

        return dictionaryDTO;
    }

    @Override
    public DictionaryShowResDTO convertToDictionaryShowResDTO(DictionaryDTO dictionaryDTO) {
        if ( dictionaryDTO == null ) {
            return null;
        }

        DictionaryShowResDTO dictionaryShowResDTO = new DictionaryShowResDTO();

        dictionaryShowResDTO.setCode( dictionaryDTO.getCode() );
        dictionaryShowResDTO.setId( dictionaryDTO.getId() );
        dictionaryShowResDTO.setName( dictionaryDTO.getName() );
        dictionaryShowResDTO.setPcode( dictionaryDTO.getPcode() );
        dictionaryShowResDTO.setPid( dictionaryDTO.getPid() );
        dictionaryShowResDTO.setType( dictionaryDTO.getType() );
        dictionaryShowResDTO.setTypeName( dictionaryDTO.getTypeName() );
        dictionaryShowResDTO.setValue( dictionaryDTO.getValue() );

        return dictionaryShowResDTO;
    }

    @Override
    public List<DictionaryShowResDTO> convertToDictionaryShowResDTOList(List<DictionaryDTO> dictionaryDTOList) {
        if ( dictionaryDTOList == null ) {
            return null;
        }

        List<DictionaryShowResDTO> list = new ArrayList<DictionaryShowResDTO>( dictionaryDTOList.size() );
        for ( DictionaryDTO dictionaryDTO : dictionaryDTOList ) {
            list.add( convertToDictionaryShowResDTO( dictionaryDTO ) );
        }

        return list;
    }

    @Override
    public DictionaryListResDTO convertToDictionaryListResDTO(DictionaryDTO dictionaryDTO) {
        if ( dictionaryDTO == null ) {
            return null;
        }

        DictionaryListResDTO dictionaryListResDTO = new DictionaryListResDTO();

        dictionaryListResDTO.setCode( dictionaryDTO.getCode() );
        dictionaryListResDTO.setId( dictionaryDTO.getId() );
        dictionaryListResDTO.setName( dictionaryDTO.getName() );
        dictionaryListResDTO.setPid( dictionaryDTO.getPid() );
        dictionaryListResDTO.setValue( dictionaryDTO.getValue() );

        return dictionaryListResDTO;
    }

    @Override
    public List<DictionaryListResDTO> convertToDictionaryListResDTOList(List<DictionaryDTO> dictionaryDTOList) {
        if ( dictionaryDTOList == null ) {
            return null;
        }

        List<DictionaryListResDTO> list = new ArrayList<DictionaryListResDTO>( dictionaryDTOList.size() );
        for ( DictionaryDTO dictionaryDTO : dictionaryDTOList ) {
            list.add( convertToDictionaryListResDTO( dictionaryDTO ) );
        }

        return list;
    }

    @Override
    public List<DictionaryDTO> convertToDictionaryDTOList(List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        if ( dictionaryAddReqDTOList == null ) {
            return null;
        }

        List<DictionaryDTO> list = new ArrayList<DictionaryDTO>( dictionaryAddReqDTOList.size() );
        for ( DictionaryAddReqDTO dictionaryAddReqDTO : dictionaryAddReqDTOList ) {
            list.add( convertToDictionaryDTO( dictionaryAddReqDTO ) );
        }

        return list;
    }

    @Override
    public DictionaryPageResDTO convertToDictionaryPageResDTO(DictionaryDTO dictionaryDTO) {
        if ( dictionaryDTO == null ) {
            return null;
        }

        DictionaryPageResDTO dictionaryPageResDTO = new DictionaryPageResDTO();

        dictionaryPageResDTO.setCode( dictionaryDTO.getCode() );
        dictionaryPageResDTO.setId( dictionaryDTO.getId() );
        dictionaryPageResDTO.setName( dictionaryDTO.getName() );
        dictionaryPageResDTO.setPcode( dictionaryDTO.getPcode() );
        dictionaryPageResDTO.setPid( dictionaryDTO.getPid() );
        dictionaryPageResDTO.setType( dictionaryDTO.getType() );
        dictionaryPageResDTO.setTypeName( dictionaryDTO.getTypeName() );
        if ( dictionaryDTO.getUpdateAt() != null ) {
            dictionaryPageResDTO.setUpdateAt( String.valueOf( dictionaryDTO.getUpdateAt() ) );
        }
        dictionaryPageResDTO.setValue( dictionaryDTO.getValue() );

        return dictionaryPageResDTO;
    }

    @Override
    public List<DictionaryPageResDTO> convertToDictionaryPageResDTOList(List<DictionaryDTO> dictionaryDTOList) {
        if ( dictionaryDTOList == null ) {
            return null;
        }

        List<DictionaryPageResDTO> list = new ArrayList<DictionaryPageResDTO>( dictionaryDTOList.size() );
        for ( DictionaryDTO dictionaryDTO : dictionaryDTOList ) {
            list.add( convertToDictionaryPageResDTO( dictionaryDTO ) );
        }

        return list;
    }

    @Override
    public Page<DictionaryPageResDTO> convertToDictionaryPageResDTOPage(Page<DictionaryDTO> dictionaryDTOPage) {
        if ( dictionaryDTOPage == null ) {
            return null;
        }

        Page<DictionaryPageResDTO> page = new Page<DictionaryPageResDTO>();

        page.setRecords( convertToDictionaryPageResDTOList( dictionaryDTOPage.getRecords() ) );
        page.setTotal( dictionaryDTOPage.getTotal() );
        page.setSize( dictionaryDTOPage.getSize() );
        page.setCurrent( dictionaryDTOPage.getCurrent() );

        return page;
    }
}

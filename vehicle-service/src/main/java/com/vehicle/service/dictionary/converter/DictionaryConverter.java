package com.vehicle.service.dictionary.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@Mapper(config = BaseConverterConfig.class)
public interface DictionaryConverter extends BaseConverter<DictionaryDTO, DictionaryPO> {

    static DictionaryConverter me() {
        return SpringUtil.getBean(DictionaryConverter.class);
    }

    DictionaryDTO convertToDictionaryDTO(DictionaryAddReqDTO dictionaryAddReqDTO);

    DictionaryDTO convertToDictionaryDTO(DictionaryModifyReqDTO dictionaryModifyReqDTO);

    DictionaryDTO convertToDictionaryDTO(DictionaryRemoveReqDTO dictionaryRemoveReqDTO);

    DictionaryDTO convertToDictionaryDTO(DictionaryListReqDTO dictionaryListReqDTO);

    DictionaryDTO convertToDictionaryDTO(DictionaryPageReqDTO dictionaryPageReqDTO);

    DictionaryShowResDTO convertToDictionaryShowResDTO(DictionaryDTO dictionaryDTO);

    List<DictionaryShowResDTO> convertToDictionaryShowResDTOList(List<DictionaryDTO> dictionaryDTOList);

    DictionaryListResDTO convertToDictionaryListResDTO(DictionaryDTO dictionaryDTO);

    List<DictionaryListResDTO> convertToDictionaryListResDTOList(List<DictionaryDTO> dictionaryDTOList);

    List<DictionaryDTO> convertToDictionaryDTOList(List<DictionaryAddReqDTO> dictionaryAddReqDTOList);

    DictionaryPageResDTO convertToDictionaryPageResDTO(DictionaryDTO dictionaryDTO);

    List<DictionaryPageResDTO> convertToDictionaryPageResDTOList(List<DictionaryDTO> dictionaryDTOList);

    Page<DictionaryPageResDTO> convertToDictionaryPageResDTOPage(Page<DictionaryDTO> dictionaryDTOPage);
}

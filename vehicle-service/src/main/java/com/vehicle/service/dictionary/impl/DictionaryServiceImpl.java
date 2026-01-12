package com.vehicle.service.dictionary.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import com.vehicle.dao.dictionary.po.DictionaryPO;
import com.vehicle.service.dictionary.DictionaryService;
import com.vehicle.service.dictionary.dto.DictionaryAddReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryDTO;
import com.vehicle.service.dictionary.dto.DictionaryListReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryListResDTO;
import com.vehicle.service.dictionary.dto.DictionaryModifyReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageResDTO;
import com.vehicle.service.dictionary.dto.DictionaryRemoveReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryShowResDTO;
import com.vehicle.service.dictionary.dto.DictionaryTypeListResDTO;
import com.vehicle.service.dictionary.manager.DictionaryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 ServiceImpl
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryManager, DictionaryPO, DictionaryDTO> implements DictionaryService {

    @Override
    public List<DictionaryListResDTO> list(final DictionaryListReqDTO dictionaryListReqDTO) {
        return manager.list(dictionaryListReqDTO);
    }

    @Override
    public DictionaryListResDTO listOne(final DictionaryListReqDTO dictionaryListReqDTO) {
        return manager.listOne(dictionaryListReqDTO);
    }

    @Override
    public Page<DictionaryPageResDTO> pagination(final DictionaryPageReqDTO dictionaryPageReqDTO, final Integer current, final Integer size) {
        return manager.pagination(dictionaryPageReqDTO, current, size);
    }

    @Override
    public Boolean add(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        return manager.add(dictionaryAddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        return manager.addAllColumn(dictionaryAddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        return manager.addBatchAllColumn(dictionaryAddReqDTOList);
    }

    @Override
    public DictionaryShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<DictionaryShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        return manager.modify(dictionaryModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        return manager.modifyAllColumn(dictionaryModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        return manager.removeByParams(dictionaryRemoveReqDTO);
    }

    @Override
    public List<DictionaryTypeListResDTO> listType() {
        return manager.listType();
    }
}

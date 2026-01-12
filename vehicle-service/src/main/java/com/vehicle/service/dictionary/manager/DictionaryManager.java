package com.vehicle.service.dictionary.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.vehicle.dao.dictionary.DictionaryDAO;
import com.vehicle.dao.dictionary.po.DictionaryPO;
import com.vehicle.service.dictionary.converter.DictionaryConverter;
import com.vehicle.service.dictionary.domain.DictionaryDO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 字典 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class DictionaryManager extends BaseManagerImpl<DictionaryDAO, DictionaryPO, DictionaryDTO, DictionaryConverter> {

    public static DictionaryManager me() {
        return SpringUtil.getBean(DictionaryManager.class);
    }

    public List<DictionaryListResDTO> list(final DictionaryListReqDTO dictionaryListReqDTO) {
        DictionaryDTO dictionaryParamsDTO = DictionaryDO.me().buildListParamsDTO(dictionaryListReqDTO);

        List<DictionaryDTO> dictionaryDTOList = super.findList(dictionaryParamsDTO);

        return DictionaryDO.me().transferDictionaryListResDTOList(dictionaryDTOList);
    }

    public DictionaryListResDTO listOne(final DictionaryListReqDTO dictionaryListReqDTO) {
        DictionaryDTO dictionaryParamsDTO = DictionaryDO.me().buildListParamsDTO(dictionaryListReqDTO);

        DictionaryDTO dictionaryDTO = super.findOne(dictionaryParamsDTO);

        return DictionaryDO.me().transferDictionaryListResDTO(dictionaryDTO);
    }

    public Page<DictionaryPageResDTO> pagination(final DictionaryPageReqDTO dictionaryPageReqDTO, final Integer current, final Integer size) {
        // 构建父查询条件
        QueryWrapper<DictionaryPO> parentQueryWrapper = DictionaryDO.me().buildParentQueryWrapper(dictionaryPageReqDTO);

        List<DictionaryDTO> parentDictionaryDTOList = Lists.newArrayList();
        // 若父查询条件不为空
        if (Func.isNotEmpty(parentQueryWrapper)) {
            parentDictionaryDTOList = super.findList(parentQueryWrapper);
            // 父查询条件，结果集为空
            if (Func.isEmpty(parentDictionaryDTOList)) {
                return new Page<>();
            }
        }

        QueryWrapper<DictionaryPO> queryWrapper = DictionaryDO.me().buildQueryWrapper(dictionaryPageReqDTO, parentDictionaryDTOList);

        Page<DictionaryDTO> dictionaryDTOPage = super.findPage(queryWrapper, current, size);
        List<String> parentIds = DictionaryDO.me().buildParentIds(dictionaryDTOPage);

        parentDictionaryDTOList = Lists.newArrayList();
        if (Func.isNotEmpty(parentIds)) {
            parentDictionaryDTOList = super.findBatchIds(parentIds);
        }

        return DictionaryDO.me().transferDictionaryPageResDTOPage(dictionaryDTOPage, parentDictionaryDTOList);
    }

    public Boolean add(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        DictionaryDTO addDictionaryDTO = DictionaryDO.me().buildAddDictionaryDTO(dictionaryAddReqDTO);

        DictionaryDTO parentDictionaryDTO = null;
        if (StrUtil.isNotBlank(addDictionaryDTO.getPid())) {
            parentDictionaryDTO = super.findById(addDictionaryDTO.getPid());
        }

        DictionaryDO.me().transferAddDictionaryDTO(addDictionaryDTO, parentDictionaryDTO);

        QueryWrapper<DictionaryPO> repeatCodeWrapper = DictionaryDO.me().buildRepeatCodeWrapper(addDictionaryDTO);
        Integer count = super.findCount(repeatCodeWrapper);

        DictionaryDO.me().checkResultCount(count);

        return super.saveDTO(addDictionaryDTO);
    }

    public DictionaryShowResDTO show(final String id) {
        DictionaryDTO dictionaryDTO = super.findById(id);

        DictionaryDTO parentDictionaryDTO = null;
        if (Func.isNotEmpty(dictionaryDTO) && StrUtil.isNotBlank(dictionaryDTO.getPid())) {
            parentDictionaryDTO = super.findById(dictionaryDTO.getPid());
        }

        return DictionaryDO.me().transferDictionaryShowResDTO(dictionaryDTO, parentDictionaryDTO);
    }

    public Boolean modify(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        DictionaryDTO modifyDictionaryDTO = DictionaryDO.me().buildModifyDictionaryDTO(dictionaryModifyReqDTO);

        QueryWrapper<DictionaryPO> repeatCodeWrapper = DictionaryDO.me().buildRepeatCodeWrapper(modifyDictionaryDTO);
        Integer count = super.findCount(repeatCodeWrapper);

        DictionaryDO.me().checkResultCount(count);

        // 更新子字典父编码
        QueryWrapper<DictionaryPO> parentQueryWrapper = DictionaryDO.me().buildParentQueryWrapper(modifyDictionaryDTO);
        if (Func.isNotEmpty(parentQueryWrapper)) {
            List<DictionaryDTO> childDictionaryDTOList = super.findList(parentQueryWrapper);
            List<DictionaryPO> dictionaryPOList = DictionaryDO.me().buildChildDictionaryPOList(childDictionaryDTOList, modifyDictionaryDTO);
            super.updateBatchById(dictionaryPOList);
        }

        // 判断是否是该字典类型的根字典，如果是，递归更新根字典下子字典
        if (StrUtil.isBlank(modifyDictionaryDTO.getPid())) {
            DictionaryDTO oldDictionaryDTO = super.findById(modifyDictionaryDTO.getId());
            if (!modifyDictionaryDTO.getType().equals(oldDictionaryDTO.getType()) || !modifyDictionaryDTO.getTypeName().equals(oldDictionaryDTO.getTypeName())) {
                List<DictionaryDTO> allChildDictionaryDTOList = findSubDictionaryList(modifyDictionaryDTO);
                List<DictionaryPO> dictionaryPOList = DictionaryDO.me().buildAllChildDictionaryPOList(allChildDictionaryDTOList, modifyDictionaryDTO);
                super.updateBatchById(dictionaryPOList);
            }
        }

        return super.modifyById(modifyDictionaryDTO);
    }

    private List<DictionaryDTO> findSubDictionaryList(final DictionaryDTO dictionaryDTO) {
        QueryWrapper<DictionaryPO> parentQueryWrapper = DictionaryDO.me().buildParentQueryWrapper(dictionaryDTO);

        List<DictionaryDTO> subDictionaryDTOList = super.findList(parentQueryWrapper);

        List<DictionaryDTO> dictionaryDTOList = Lists.newArrayList();
        if (Func.isNotEmpty(subDictionaryDTOList)) {
            dictionaryDTOList.addAll(subDictionaryDTOList);
            for (DictionaryDTO dictionary : subDictionaryDTOList) {
                dictionaryDTOList.addAll(findSubDictionaryList(dictionary));
            }
        }

        return dictionaryDTOList;
    }

    @Override
    public Boolean removeById(final String id) {
        QueryWrapper<DictionaryPO> parentQueryWrapper = DictionaryDO.me().buildParentQueryWrapper(id);

        if (Func.isNotEmpty(parentQueryWrapper)) {
            List<DictionaryDTO> subDictionaryDTOList = super.findList(parentQueryWrapper);
            DictionaryDO.me().checkChildren(subDictionaryDTOList);
        }

        return super.removeById(id);
    }

    public Boolean addAllColumn(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        DictionaryDO.me().checkDictionaryAddReqDTO(dictionaryAddReqDTO);

        DictionaryDTO addDictionaryDTO = DictionaryDO.me().buildAddDictionaryDTO(dictionaryAddReqDTO);

        return super.saveAllColumn(addDictionaryDTO);
    }

    public Boolean addBatchAllColumn(final List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        DictionaryDO.me().checkDictionaryAddReqDTOList(dictionaryAddReqDTOList);

        List<DictionaryDTO> addBatchDictionaryDTOList = DictionaryDO.me().buildAddBatchDictionaryDTOList(dictionaryAddReqDTOList);

        return super.saveBatchAllColumn(addBatchDictionaryDTOList);
    }

    public List<DictionaryShowResDTO> showByIds(final List<String> ids) {
        DictionaryDO.me().checkIds(ids);

        List<DictionaryDTO> dictionaryDTOList = super.findBatchIds(ids);
        DictionaryDO.me().checkDictionaryDTOList(dictionaryDTOList);

        return DictionaryDO.me().transferDictionaryShowResDTOList(dictionaryDTOList);
    }

    public Boolean modifyAllColumn(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        DictionaryDO.me().checkDictionaryModifyReqDTO(dictionaryModifyReqDTO);

        DictionaryDTO modifyDictionaryDTO = DictionaryDO.me().buildModifyDictionaryDTO(dictionaryModifyReqDTO);

        return super.modifyAllColumnById(modifyDictionaryDTO);
    }

    public Boolean removeByParams(final DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        DictionaryDO.me().checkDictionaryRemoveReqDTO(dictionaryRemoveReqDTO);

        DictionaryDTO removeDictionaryDTO = DictionaryDO.me().buildRemoveDictionaryDTO(dictionaryRemoveReqDTO);

        return super.remove(removeDictionaryDTO);
    }

    public DictionaryDTO getByCode(final String type, final String code) {
        DictionaryDTO paramDTO = DictionaryDO.me().buildDictionaryParamDTO(type, code);

        return super.findOne(paramDTO);
    }

    public List<DictionaryDTO> findByType(final String type) {
        DictionaryDTO paramDTO = DictionaryDO.me().buildDictionaryParamDTO(type, null);

        return super.findList(paramDTO);
    }

    public List<DictionaryTypeListResDTO> listType() {
        List<DictionaryDTO> dictionaryDTOList = findList(new DictionaryDTO());

        return DictionaryDO.me().transferDictionaryTypeResDTOList(dictionaryDTOList);
    }

    @Override
    protected DictionaryPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new DictionaryPO();
        }

        return BeanUtil.toBean(map, DictionaryPO.class);
    }

    @Override
    protected DictionaryDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new DictionaryDTO();
        }

        return BeanUtil.toBean(map, DictionaryDTO.class);
    }
}

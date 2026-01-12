package com.vehicle.service.dictionary.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vehicle.dao.dictionary.po.DictionaryPO;
import com.vehicle.service.dictionary.converter.DictionaryConverter;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字典 DO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class DictionaryDO extends BaseDO<DictionaryDTO, DictionaryPO, DictionaryConverter> {

    public static DictionaryDO me() {
        return SpringUtil.getBean(DictionaryDO.class);
    }

    public void checkResultCount(final Integer count) {
        if (!Func.isNullOrZero(count)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "字典编码已存在");
        }
    }

    public void checkChildren(final List<DictionaryDTO> subDictionaryDTOList) {
        if (Func.isNotEmpty(subDictionaryDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "请先删除子字典");
        }
    }

    public void checkDictionaryAddReqDTO(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        if (Func.isEmpty(dictionaryAddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkDictionaryDTOList(final List<DictionaryDTO> dictionaryDTOList) {
        if (Func.isEmpty(dictionaryDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }
    }

    public void checkDictionaryDTO(final DictionaryDTO dictionaryDTO) {
        if (Func.isEmpty(dictionaryDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }
    }

    public void checkDictionaryAddReqDTOList(final List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        if (Func.isEmpty(dictionaryAddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void checkDictionaryModifyReqDTO(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        if (Func.isEmpty(dictionaryModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkDictionaryRemoveReqDTO(final DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        if (Func.isEmpty(dictionaryRemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public DictionaryDTO buildListParamsDTO(final DictionaryListReqDTO dictionaryListReqDTO) {
        return converter.convertToDictionaryDTO(dictionaryListReqDTO);
    }

    /**
     * 根据查询入参，构建父字典查询条件
     *
     * @param dictionaryPageReqDTO 查询入参
     * @return
     */
    public QueryWrapper<DictionaryPO> buildParentQueryWrapper(final DictionaryPageReqDTO dictionaryPageReqDTO) {
        boolean isBuild = Func.isNotEmpty(dictionaryPageReqDTO) && (Func.isNotEmpty(dictionaryPageReqDTO.getPcode())
            || Func.isNotEmpty(dictionaryPageReqDTO.getPname()) || Func.isNotEmpty(dictionaryPageReqDTO.getPvalue()));
        if (!isBuild) {
            return null;
        }

        QueryWrapper<DictionaryPO> queryWrapper = new QueryWrapper<>();

        if (Func.isNotEmpty(dictionaryPageReqDTO.getPcode())) {
            queryWrapper.like(DictionaryPO.DB_COL_CODE, dictionaryPageReqDTO.getPcode().trim().toUpperCase());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getPname())) {
            queryWrapper.like(DictionaryPO.DB_COL_NAME, dictionaryPageReqDTO.getPname().trim().toUpperCase());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getPvalue())) {
            queryWrapper.eq(DictionaryPO.DB_COL_VALUE, dictionaryPageReqDTO.getPvalue());
        }

        return queryWrapper;
    }

    /**
     * 根据查询入参+父字典集合，构建字典查询条件
     *
     * @param dictionaryPageReqDTO    查询入参
     * @param parentDictionaryDTOList 父字典集合
     * @return
     */
    public QueryWrapper<DictionaryPO> buildQueryWrapper(final DictionaryPageReqDTO dictionaryPageReqDTO, final List<DictionaryDTO> parentDictionaryDTOList) {
        QueryWrapper<DictionaryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(DictionaryPO.DB_COL_ID);

        if (Func.isNotEmpty(dictionaryPageReqDTO.getName())) {
            queryWrapper.like(DictionaryPO.DB_COL_NAME, dictionaryPageReqDTO.getName().trim());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getCode())) {
            queryWrapper.like(DictionaryPO.DB_COL_CODE, dictionaryPageReqDTO.getCode().trim().toUpperCase());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getValue())) {
            queryWrapper.eq(DictionaryPO.DB_COL_VALUE, dictionaryPageReqDTO.getValue());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getType())) {
            queryWrapper.eq(DictionaryPO.DB_COL_TYPE, dictionaryPageReqDTO.getType());
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getUpdateStartAt())) {
            int updateStartAt = DateUtil.millisecond(DateUtil.parseDateTime(dictionaryPageReqDTO.getUpdateStartAt()));
            queryWrapper.ge(DictionaryPO.DB_COL_UPDATE_AT, updateStartAt);
        }

        if (Func.isNotEmpty(dictionaryPageReqDTO.getUpdateEndAt())) {
            int updateEndAt = DateUtil.millisecond(DateUtil.parseDateTime(dictionaryPageReqDTO.getUpdateEndAt()));
            queryWrapper.le(DictionaryPO.DB_COL_UPDATE_AT, updateEndAt);
        }

        if (Func.isNotEmpty(parentDictionaryDTOList)) {
            queryWrapper.in(DictionaryPO.DB_COL_PID, buildDictionaryIds(parentDictionaryDTOList));
        }

        return queryWrapper;
    }

    public List<String> buildParentIds(final Page<DictionaryDTO> dictionaryDTOPage) {
        return buildParentDictionaryIds(dictionaryDTOPage.getRecords());
    }

    public List<String> buildParentDictionaryIds(final List<DictionaryDTO> dictionaryDTOList) {
        if (Func.isEmpty(dictionaryDTOList)) {
            return null;
        }

        List<String> ids = Lists.newArrayList();
        for (DictionaryDTO dictionaryDTO : dictionaryDTOList) {
            ids.add(dictionaryDTO.getPid());
        }

        return ids;
    }

    public DictionaryDTO buildAddDictionaryDTO(final DictionaryAddReqDTO dictionaryAddReqDTO) {
        return converter.convertToDictionaryDTO(dictionaryAddReqDTO);
    }

    public List<DictionaryPO> buildChildDictionaryPOList(final List<DictionaryDTO> childDictionaryDTOList, final DictionaryDTO modifyDictionaryDTO) {
        List<DictionaryPO> dictionaryPOList = Lists.newArrayList();

        if (Func.isNotEmpty(childDictionaryDTOList)) {
            for (DictionaryDTO dictionary : childDictionaryDTOList) {
                dictionary.setPcode(modifyDictionaryDTO.getCode());
                dictionaryPOList.add(converter.dtoToPO(dictionary));
            }
        }

        return dictionaryPOList;
    }

    public List<DictionaryPO> buildAllChildDictionaryPOList(final List<DictionaryDTO> allChildDictionaryDTOList,
        final DictionaryDTO modifyDictionaryDTO) {
        List<DictionaryPO> dictionaryPOList = Lists.newArrayList();

        if (Func.isNotEmpty(allChildDictionaryDTOList)) {
            for (DictionaryDTO dictionary : allChildDictionaryDTOList) {
                dictionary.setType(modifyDictionaryDTO.getType());
                dictionary.setTypeName(modifyDictionaryDTO.getTypeName());
                dictionaryPOList.add(converter.dtoToPO(dictionary));
            }
        }

        return dictionaryPOList;
    }

    private List<String> buildDictionaryIds(final List<DictionaryDTO> dictionaryDTOList) {
        if (Func.isEmpty(dictionaryDTOList)) {
            return null;
        }

        List<String> ids = Lists.newArrayList();
        for (DictionaryDTO dictionaryDTO : dictionaryDTOList) {
            ids.add(dictionaryDTO.getId());
        }

        return ids;
    }

    public DictionaryDTO buildModifyDictionaryDTO(final DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        return converter.convertToDictionaryDTO(dictionaryModifyReqDTO);
    }

    public QueryWrapper<DictionaryPO> buildRepeatCodeWrapper(final DictionaryDTO dictionaryDTO) {
        QueryWrapper<DictionaryPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(DictionaryPO.DB_COL_CODE, dictionaryDTO.getCode());
        if (StrUtil.isNotBlank(dictionaryDTO.getId())) {
            queryWrapper.ne(DictionaryPO.DB_COL_ID, dictionaryDTO.getId());
        }

        return queryWrapper;
    }

    public QueryWrapper<DictionaryPO> buildParentQueryWrapper(final DictionaryDTO dictionaryDTO) {
        return buildParentQueryWrapper(dictionaryDTO.getId());
    }

    public QueryWrapper<DictionaryPO> buildParentQueryWrapper(final String pid) {
        QueryWrapper<DictionaryPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(DictionaryPO.DB_COL_PID, pid);

        return queryWrapper;
    }

    public List<DictionaryDTO> buildAddBatchDictionaryDTOList(final List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        return converter.convertToDictionaryDTOList(dictionaryAddReqDTOList);
    }

    public DictionaryDTO buildDictionaryParamDTO(final String type, String code) {
        DictionaryDTO paramDTO = new DictionaryDTO();

        paramDTO.setType(type);
        paramDTO.setCode(code);

        return paramDTO;
    }

    public DictionaryDTO buildRemoveDictionaryDTO(final DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        return converter.convertToDictionaryDTO(dictionaryRemoveReqDTO);
    }

    public void transferAddDictionaryDTO(final DictionaryDTO addDictionaryDTO,
        final DictionaryDTO parentDictionaryDTO) {
        addDictionaryDTO.setCode(addDictionaryDTO.getCode().toUpperCase());

        if (Func.isNotEmpty(parentDictionaryDTO)) {
            addDictionaryDTO.setPcode(parentDictionaryDTO.getCode());
        }

        addDictionaryDTO.setSort(0);
    }

    public List<DictionaryListResDTO> transferDictionaryListResDTOList(final List<DictionaryDTO> dictionaryDTOList) {
        checkDictionaryDTOList(dictionaryDTOList);

        return converter.convertToDictionaryListResDTOList(dictionaryDTOList);
    }

    public DictionaryListResDTO transferDictionaryListResDTO(final DictionaryDTO dictionaryDTO) {
        checkDictionaryDTO(dictionaryDTO);

        return converter.convertToDictionaryListResDTO(dictionaryDTO);
    }

    public Page<DictionaryPageResDTO> transferDictionaryPageResDTOPage(final Page<DictionaryDTO> dictionaryDTOPage, final List<DictionaryDTO> parentDictionaryDTOList) {
        Page<DictionaryPageResDTO> dictionaryPageResDTOPage = converter.convertToDictionaryPageResDTOPage(dictionaryDTOPage);

        if (Func.isEmpty(dictionaryDTOPage) || Func.isEmpty(dictionaryDTOPage.getRecords())) {
            return dictionaryPageResDTOPage;
        }

        Map<String, DictionaryDTO> parentDictionaryMap = Maps.newHashMap();
        if (Func.isNotEmpty(parentDictionaryDTOList)) {
            for (DictionaryDTO dictionaryDTO : parentDictionaryDTOList) {
                parentDictionaryMap.put(dictionaryDTO.getId(), dictionaryDTO);
            }
        }

        List<DictionaryPageResDTO> dictionaryPageResDTOList = Lists.newArrayList();
        for (DictionaryDTO dictionaryDTO : dictionaryDTOPage.getRecords()) {
            DictionaryPageResDTO dictionaryPageResDTO = converter.convertToDictionaryPageResDTO(dictionaryDTO);
            dictionaryPageResDTO.setUpdateAt(DateUtil.formatDateTime(DateUtil.date(dictionaryDTO.getUpdateAt())));

            DictionaryDTO parentDictionaryDTO = parentDictionaryMap.get(dictionaryPageResDTO.getPid());
            if (Func.isNotEmpty(parentDictionaryDTO)) {
                dictionaryPageResDTO.setPname(parentDictionaryDTO.getName());
                dictionaryPageResDTO.setPcode(parentDictionaryDTO.getCode());
                dictionaryPageResDTO.setPvalue(parentDictionaryDTO.getValue());
            }
            dictionaryPageResDTOList.add(dictionaryPageResDTO);
        }

        dictionaryPageResDTOPage.transfer(dictionaryDTOPage);
        dictionaryPageResDTOPage.setRecords(dictionaryPageResDTOList);

        return dictionaryPageResDTOPage;
    }

    public DictionaryShowResDTO transferDictionaryShowResDTO(final DictionaryDTO dictionaryDTO,
        final DictionaryDTO parentDictionaryDTO) {
        DictionaryShowResDTO dictionaryShowResDTO = converter.convertToDictionaryShowResDTO(dictionaryDTO);

        if (Func.isNotEmpty(parentDictionaryDTO)) {
            dictionaryShowResDTO.setPname(parentDictionaryDTO.getName());
            dictionaryShowResDTO.setPcode(parentDictionaryDTO.getCode());
            dictionaryShowResDTO.setPvalue(parentDictionaryDTO.getValue());
        }

        return dictionaryShowResDTO;
    }

    public List<DictionaryTypeListResDTO> transferDictionaryTypeResDTOList(final List<DictionaryDTO> dictionaryDTOList) {
        Set<String> dictionaryTypeCodeSet = Sets.newHashSet();
        List<DictionaryTypeListResDTO> dictionaryTypeListResDTOList = Lists.newArrayList();

        for (DictionaryDTO dictionary : dictionaryDTOList) {
            if (!dictionaryTypeCodeSet.contains(dictionary.getType())) {
                dictionaryTypeListResDTOList.add(new DictionaryTypeListResDTO(dictionary.getTypeName(), dictionary.getType()));
                dictionaryTypeCodeSet.add(dictionary.getType());
            }
        }

        return CollUtil.sort(dictionaryTypeListResDTOList, new Comparator<DictionaryTypeListResDTO>() {
            @Override
            public int compare(DictionaryTypeListResDTO o1, DictionaryTypeListResDTO o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public List<DictionaryShowResDTO> transferDictionaryShowResDTOList(final List<DictionaryDTO> dictionaryDTOList) {
        return converter.convertToDictionaryShowResDTOList(dictionaryDTOList);
    }
}

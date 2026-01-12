package com.vehicle.service.dictionary;

import com.finhub.framework.common.service.BaseService;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
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

import java.util.List;

/**
 * 字典 Service
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public interface DictionaryService extends BaseService<DictionaryDTO> {

    static DictionaryService me() {
        return SpringUtil.getBean(DictionaryService.class);
    }

    /**
     * 列表
     *
     * @param dictionaryListReqDTO 入参DTO
     * @return
     */
    List<DictionaryListResDTO> list(DictionaryListReqDTO dictionaryListReqDTO);

    /**
     * First查询
     *
     * @param dictionaryListReqDTO 入参DTO
     * @return
     */
    DictionaryListResDTO listOne(DictionaryListReqDTO dictionaryListReqDTO);

    /**
     * 分页
     *
     * @param dictionaryPageReqDTO 入参DTO
     * @param current              当前页
     * @param size                 每页大小
     * @return
     */
    Page<DictionaryPageResDTO> pagination(DictionaryPageReqDTO dictionaryPageReqDTO, Integer current, Integer size);

    /**
     * 新增
     *
     * @param dictionaryAddReqDTO 入参DTO
     * @return
     */
    Boolean add(DictionaryAddReqDTO dictionaryAddReqDTO);

    /**
     * 新增(所有字段)
     *
     * @param dictionaryAddReqDTO 入参DTO
     * @return
     */
    Boolean addAllColumn(DictionaryAddReqDTO dictionaryAddReqDTO);

    /**
     * 批量新增(所有字段)
     *
     * @param dictionaryAddReqDTOList 入参DTO
     * @return
     */
    Boolean addBatchAllColumn(List<DictionaryAddReqDTO> dictionaryAddReqDTOList);

    /**
     * 详情
     *
     * @param id 主键ID
     * @return
     */
    DictionaryShowResDTO show(String id);

    /**
     * 批量详情
     *
     * @param ids 主键IDs
     * @return
     */
    List<DictionaryShowResDTO> showByIds(List<String> ids);

    /**
     * 修改
     *
     * @param dictionaryModifyReqDTO 入参DTO
     * @return
     */
    Boolean modify(DictionaryModifyReqDTO dictionaryModifyReqDTO);

    /**
     * 修改(所有字段)
     *
     * @param dictionaryModifyReqDTO 入参DTO
     * @return
     */
    Boolean modifyAllColumn(DictionaryModifyReqDTO dictionaryModifyReqDTO);

    /**
     * 参数删除
     *
     * @param dictionaryRemoveReqDTO 入参DTO
     * @return
     */
    Boolean removeByParams(DictionaryRemoveReqDTO dictionaryRemoveReqDTO);

    /**
     * 字典类型列表
     *
     * @return
     */
    List<DictionaryTypeListResDTO> listType();
}

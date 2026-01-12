package com.vehicle.web.dictionary.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.dictionary.DictionaryService;
import com.vehicle.service.dictionary.dto.DictionaryAddReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryListReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryListResDTO;
import com.vehicle.service.dictionary.dto.DictionaryModifyReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryPageResDTO;
import com.vehicle.service.dictionary.dto.DictionaryRemoveReqDTO;
import com.vehicle.service.dictionary.dto.DictionaryShowResDTO;
import com.vehicle.service.dictionary.dto.DictionaryTypeListResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class DictionaryController extends AbstractController<DictionaryService> {

    /**
     * 字典-列表
     *
     * @param dictionaryListReqDTO 字典列表请求DTO
     * @return ItemsResult<DictionaryListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "dictionary/list", method = {RequestMethod.GET})
    public ItemsResult<DictionaryListResDTO> list(DictionaryListReqDTO dictionaryListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(dictionaryListReqDTO));
    }

    /**
     * 字典-First查询
     *
     * @param dictionaryListReqDTO 字典Fist查询请求DTO
     * @return ItemResult<DictionaryListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "dictionary/listOne", method = {RequestMethod.GET})
    public ItemResult<DictionaryListResDTO> listOne(DictionaryListReqDTO dictionaryListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(dictionaryListReqDTO));
    }

    /**
     * 字典-字典类型查询
     *
     * @return ItemsResult<DictionaryTypeListResDTO> 字典类型查询结果
     */
    @RequestMapping(value = "dictionary/listType", method = {RequestMethod.GET})
    public ItemsResult<DictionaryTypeListResDTO> listType() {
        return responseItems(ResponseCodeEnum.SUCCESS, service.listType());
    }

    /**
     * 字典-分页
     *
     * @param dictionaryPageReqDTO 字典分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<DictionaryPageResDTO> 分页结果
     * @undone
     */
    @RequiresPermissions(value = {"JCSJ:GGMB:CX"})
    @RequestMapping(value = "dictionary/page", method = {RequestMethod.GET})
    public PageResult<DictionaryPageResDTO> page(DictionaryPageReqDTO dictionaryPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<DictionaryPageResDTO> dictionaryPageResDTOPage = service.pagination(dictionaryPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, dictionaryPageResDTOPage.getTotal(), dictionaryPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 字典-新增
     *
     * @param dictionaryAddReqDTO 字典新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequiresPermissions(value = {"JCSJ:GGMB:XZ"})
    @RequestMapping(value = "dictionary/add", method = {RequestMethod.POST})
    public MessageResult add(DictionaryAddReqDTO dictionaryAddReqDTO) {
        Boolean isSuccess = service.add(dictionaryAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-新增(所有字段)
     *
     * @param dictionaryAddReqDTO 字典新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(DictionaryAddReqDTO dictionaryAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(dictionaryAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-批量新增(所有字段)
     *
     * @param dictionaryAddReqDTOList 字典批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<DictionaryAddReqDTO> dictionaryAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(dictionaryAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-详情
     *
     * @param id 主键 ID
     * @return ItemResult<DictionaryShowResDTO> 详情结果
     * @undone
     */
    @RequiresPermissions(value = {"JCSJ:GGMB:CK"})
    @RequestMapping(value = "dictionary/show", method = {RequestMethod.GET})
    public ItemResult<DictionaryShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 字典-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<DictionaryShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/showByIds", method = {RequestMethod.POST})
    public ItemsResult<DictionaryShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 字典-更新
     *
     * @param dictionaryModifyReqDTO 字典更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequiresPermissions(value = {"JCSJ:GGMB:BJ"})
    @RequestMapping(value = "dictionary/modify", method = {RequestMethod.POST})
    public MessageResult modify(DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        Boolean isSuccess = service.modify(dictionaryModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-更新(所有字段)
     *
     * @param dictionaryModifyReqDTO 字典更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(DictionaryModifyReqDTO dictionaryModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(dictionaryModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequiresPermissions(value = {"JCSJ:GGMB:SC"})
    @RequestMapping(value = "dictionary/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 字典-删除(参数)
     *
     * @param dictionaryRemoveReqDTO 字典删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "dictionary/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(DictionaryRemoveReqDTO dictionaryRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(dictionaryRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

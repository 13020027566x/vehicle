package com.vehicle.web.fileattach.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.fileattach.FileAttachService;
import com.vehicle.service.fileattach.dto.FileAttachAddReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachListResDTO;
import com.vehicle.service.fileattach.dto.FileAttachModifyReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachPageResDTO;
import com.vehicle.service.fileattach.dto.FileAttachRemoveReqDTO;
import com.vehicle.service.fileattach.dto.FileAttachShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文件附件 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class FileAttachController extends AbstractController<FileAttachService> {

    /**
     * 文件附件-列表
     *
     * @param fileAttachListReqDTO 文件附件列表请求DTO
     * @return ItemsResult<FileAttachListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/list", method = {RequestMethod.GET})
    public ItemsResult<FileAttachListResDTO> list(FileAttachListReqDTO fileAttachListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(fileAttachListReqDTO));
    }

    /**
     * 文件附件-First查询
     *
     * @param fileAttachListReqDTO 文件附件Fist查询请求DTO
     * @return ItemResult<FileAttachListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/listOne", method = {RequestMethod.GET})
    public ItemResult<FileAttachListResDTO> listOne(FileAttachListReqDTO fileAttachListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(fileAttachListReqDTO));
    }

    /**
     * 文件附件-分页
     *
     * @param fileAttachPageReqDTO 文件附件分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<FileAttachPageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/page", method = {RequestMethod.GET})
    public PageResult<FileAttachPageResDTO> page(FileAttachPageReqDTO fileAttachPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<FileAttachPageResDTO> fileAttachPageResDTOPage = service.pagination(fileAttachPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, fileAttachPageResDTOPage.getTotal(), fileAttachPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 文件附件-新增
     *
     * @param fileAttachAddReqDTO 文件附件新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/add", method = {RequestMethod.POST})
    public MessageResult add(FileAttachAddReqDTO fileAttachAddReqDTO) {
        Boolean isSuccess = service.add(fileAttachAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-新增(所有字段)
     *
     * @param fileAttachAddReqDTO 文件附件新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(FileAttachAddReqDTO fileAttachAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(fileAttachAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-批量新增(所有字段)
     *
     * @param fileAttachAddReqDTOList 文件附件批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<FileAttachAddReqDTO> fileAttachAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(fileAttachAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-详情
     *
     * @param id 主键 ID
     * @return ItemResult<FileAttachShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/show", method = {RequestMethod.GET})
    public ItemResult<FileAttachShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 文件附件-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<FileAttachShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/showByIds", method = {RequestMethod.POST})
    public ItemsResult<FileAttachShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 文件附件-更新
     *
     * @param fileAttachModifyReqDTO 文件附件更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/modify", method = {RequestMethod.POST})
    public MessageResult modify(FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        Boolean isSuccess = service.modify(fileAttachModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-更新(所有字段)
     *
     * @param fileAttachModifyReqDTO 文件附件更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(FileAttachModifyReqDTO fileAttachModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(fileAttachModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 文件附件-删除(参数)
     *
     * @param fileAttachRemoveReqDTO 文件附件删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "fileAttach/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(FileAttachRemoveReqDTO fileAttachRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(fileAttachRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

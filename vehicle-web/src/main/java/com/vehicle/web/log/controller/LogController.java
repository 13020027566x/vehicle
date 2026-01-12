package com.vehicle.web.log.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.log.LogService;
import com.vehicle.service.log.dto.LogAddReqDTO;
import com.vehicle.service.log.dto.LogListReqDTO;
import com.vehicle.service.log.dto.LogListResDTO;
import com.vehicle.service.log.dto.LogModifyReqDTO;
import com.vehicle.service.log.dto.LogPageReqDTO;
import com.vehicle.service.log.dto.LogPageResDTO;
import com.vehicle.service.log.dto.LogRemoveReqDTO;
import com.vehicle.service.log.dto.LogShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日志 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class LogController extends AbstractController<LogService> {

    /**
     * 日志-列表
     *
     * @param logListReqDTO 日志列表请求DTO
     * @return ItemsResult<LogListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "log/list", method = {RequestMethod.GET})
    public ItemsResult<LogListResDTO> list(LogListReqDTO logListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(logListReqDTO));
    }

    /**
     * 日志-First查询
     *
     * @param logListReqDTO 日志Fist查询请求DTO
     * @return ItemResult<LogListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "log/listOne", method = {RequestMethod.GET})
    public ItemResult<LogListResDTO> listOne(LogListReqDTO logListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(logListReqDTO));
    }

    /**
     * 日志-分页
     *
     * @param logPageReqDTO 日志分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<LogPageResDTO> 分页结果
     * @undone
     */
    @RequiresPermissions(value = {"RZJK:CZRZ:CX"})
    @RequestMapping(value = "log/page", method = {RequestMethod.GET})
    public PageResult<LogPageResDTO> page(LogPageReqDTO logPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<LogPageResDTO> logPageResDTOPage = service.pagination(logPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, logPageResDTOPage.getTotal(), logPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 日志-新增
     *
     * @param logAddReqDTO 日志新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequiresPermissions(value = {"RZJK:CZRZ:XZ"})
    @RequestMapping(value = "log/add", method = {RequestMethod.POST})
    public MessageResult add(LogAddReqDTO logAddReqDTO) {
        Boolean isSuccess = service.add(logAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-新增(所有字段)
     *
     * @param logAddReqDTO 日志新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "log/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(LogAddReqDTO logAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(logAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-批量新增(所有字段)
     *
     * @param logAddReqDTOList 日志批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "log/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<LogAddReqDTO> logAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(logAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-详情
     *
     * @param id 主键 ID
     * @return ItemResult<LogShowResDTO> 详情结果
     * @undone
     */
    @RequiresPermissions(value = {"RZJK:CZRZ:CK"})
    @RequestMapping(value = "log/show", method = {RequestMethod.GET})
    public ItemResult<LogShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 日志-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<LogShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "log/showByIds", method = {RequestMethod.POST})
    public ItemsResult<LogShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 日志-更新
     *
     * @param logModifyReqDTO 日志更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequiresPermissions(value = {"RZJK:CZRZ:BJ"})
    @RequestMapping(value = "log/modify", method = {RequestMethod.POST})
    public MessageResult modify(LogModifyReqDTO logModifyReqDTO) {
        Boolean isSuccess = service.modify(logModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-更新(所有字段)
     *
     * @param logModifyReqDTO 日志更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "log/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(LogModifyReqDTO logModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(logModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequiresPermissions(value = {"XXXX:XXXX:SC"})
    @RequestMapping(value = "log/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "log/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 日志-删除(参数)
     *
     * @param logRemoveReqDTO 日志删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "log/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(LogRemoveReqDTO logRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(logRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

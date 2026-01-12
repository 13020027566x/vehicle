package com.vehicle.web.userorg.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.userorg.UserOrgService;
import com.vehicle.service.userorg.dto.UserOrgAddReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListReqDTO;
import com.vehicle.service.userorg.dto.UserOrgListResDTO;
import com.vehicle.service.userorg.dto.UserOrgModifyReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageReqDTO;
import com.vehicle.service.userorg.dto.UserOrgPageResDTO;
import com.vehicle.service.userorg.dto.UserOrgRemoveReqDTO;
import com.vehicle.service.userorg.dto.UserOrgShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户机构 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class UserOrgController extends AbstractController<UserOrgService> {

    /**
     * 用户机构-列表
     *
     * @param userOrgListReqDTO 用户机构列表请求DTO
     * @return ItemsResult<UserOrgListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "userOrg/list", method = {RequestMethod.GET})
    public ItemsResult<UserOrgListResDTO> list(UserOrgListReqDTO userOrgListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(userOrgListReqDTO));
    }

    /**
     * 用户机构-First查询
     *
     * @param userOrgListReqDTO 用户机构Fist查询请求DTO
     * @return ItemResult<UserOrgListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "userOrg/listOne", method = {RequestMethod.GET})
    public ItemResult<UserOrgListResDTO> listOne(UserOrgListReqDTO userOrgListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(userOrgListReqDTO));
    }

    /**
     * 用户机构-分页
     *
     * @param userOrgPageReqDTO 用户机构分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<UserOrgPageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "userOrg/page", method = {RequestMethod.GET})
    public PageResult<UserOrgPageResDTO> page(UserOrgPageReqDTO userOrgPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<UserOrgPageResDTO> userOrgPageResDTOPage = service.pagination(userOrgPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, userOrgPageResDTOPage.getTotal(), userOrgPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 用户机构-新增
     *
     * @param userOrgAddReqDTO 用户机构新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequestMapping(value = "userOrg/add", method = {RequestMethod.POST})
    public MessageResult add(UserOrgAddReqDTO userOrgAddReqDTO) {
        Boolean isSuccess = service.add(userOrgAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-新增(所有字段)
     *
     * @param userOrgAddReqDTO 用户机构新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(UserOrgAddReqDTO userOrgAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(userOrgAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-批量新增(所有字段)
     *
     * @param userOrgAddReqDTOList 用户机构批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(@RequestBody List<UserOrgAddReqDTO> userOrgAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(userOrgAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-详情
     *
     * @param id 主键 ID
     * @return ItemResult<UserOrgShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "userOrg/show", method = {RequestMethod.GET})
    public ItemResult<UserOrgShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 用户机构-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<UserOrgShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/showByIds", method = {RequestMethod.POST})
    public ItemsResult<UserOrgShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 用户机构-更新
     *
     * @param userOrgModifyReqDTO 用户机构更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequestMapping(value = "userOrg/modify", method = {RequestMethod.POST})
    public MessageResult modify(UserOrgModifyReqDTO userOrgModifyReqDTO) {
        Boolean isSuccess = service.modify(userOrgModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-更新(所有字段)
     *
     * @param userOrgModifyReqDTO 用户机构更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(UserOrgModifyReqDTO userOrgModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(userOrgModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequestMapping(value = "userOrg/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 用户机构-删除(参数)
     *
     * @param userOrgRemoveReqDTO 用户机构删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "userOrg/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(UserOrgRemoveReqDTO userOrgRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(userOrgRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

package com.vehicle.web.organization.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageResult;

import com.vehicle.service.organization.OrganizationService;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 机构 Controller
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023-06-07
 */
@Slf4j
@Validated
@RestController
public class OrganizationController extends AbstractController<OrganizationService> {

    /**
     * 机构-列表
     *
     * @param organizationListReqDTO 机构列表请求DTO
     * @return ItemsResult<OrganizationListResDTO> 列表结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:ZZJG:CX"})
    @RequestMapping(value = "organization/list", method = {RequestMethod.GET})
    public ItemsResult<OrganizationListResDTO> list(OrganizationListReqDTO organizationListReqDTO) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.list(organizationListReqDTO));
    }

    /**
     * 机构-First查询
     *
     * @param organizationListReqDTO 机构Fist查询请求DTO
     * @return ItemResult<OrganizationListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "organization/listOne", method = {RequestMethod.GET})
    public ItemResult<OrganizationListResDTO> listOne(OrganizationListReqDTO organizationListReqDTO) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.listOne(organizationListReqDTO));
    }

    /**
     * 机构-分页
     *
     * @param organizationPageReqDTO 机构分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageResult<OrganizationPageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "organization/page", method = {RequestMethod.GET})
    public PageResult<OrganizationPageResDTO> page(OrganizationPageReqDTO organizationPageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<OrganizationPageResDTO> organizationPageResDTOPage = service.pagination(organizationPageReqDTO, current, size);
        return responsePage(ResponseCodeEnum.SUCCESS, organizationPageResDTOPage.getTotal(), organizationPageResDTOPage.getRecords(), size, current);
    }

    /**
     * 机构-新增
     *
     * @param organizationAddReqDTO 机构新增请求DTO
     * @return MessageResult 新增结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:ZZJG:XZ"})
    @RequestMapping(value = "organization/add", method = {RequestMethod.POST})
    public MessageResult add(OrganizationAddReqDTO organizationAddReqDTO) {
        Boolean isSuccess = service.add(organizationAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-新增(所有字段)
     *
     * @param organizationAddReqDTO 机构新增(所有字段)请求DTO
     * @return MessageResult 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "organization/addAllColumn", method = {RequestMethod.POST})
    public MessageResult addAllColumn(OrganizationAddReqDTO organizationAddReqDTO) {
        Boolean isSuccess = service.addAllColumn(organizationAddReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-批量新增(所有字段)
     *
     * @param organizationAddReqDTOList 机构批量新增(所有字段)请求DTO
     * @return MessageResult 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "organization/addBatchAllColumn", method = {RequestMethod.POST})
    public MessageResult addBatchAllColumn(List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(organizationAddReqDTOList);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-详情
     *
     * @param id 主键 ID
     * @return ItemResult<OrganizationShowResDTO> 详情结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:ZZJG:CK"})
    @RequestMapping(value = "organization/show", method = {RequestMethod.GET})
    public ItemResult<OrganizationShowResDTO> show(String id) {
        return responseItem(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * 机构-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsResult<OrganizationShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "organization/showByIds", method = {RequestMethod.POST})
    public ItemsResult<OrganizationShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItems(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * 机构-更新
     *
     * @param organizationModifyReqDTO 机构更新请求DTO
     * @return MessageResult 更新结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:ZZJG:BJ"})
    @RequestMapping(value = "organization/modify", method = {RequestMethod.POST})
    public MessageResult modify(OrganizationModifyReqDTO organizationModifyReqDTO) {
        Boolean isSuccess = service.modify(organizationModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-更新(所有字段)
     *
     * @param organizationModifyReqDTO 机构更新(所有字段)请求DTO
     * @return MessageResult 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "organization/modifySelective", method = {RequestMethod.POST})
    public MessageResult modifyAllColumn(OrganizationModifyReqDTO organizationModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(organizationModifyReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-删除
     *
     * @param id 主键 ID
     * @return MessageResult 删除结果
     * @undone
     */
    @RequiresPermissions(value = {"YHGL:ZZJG:SC"})
    @RequestMapping(value = "organization/remove", method = {RequestMethod.POST})
    public MessageResult remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageResult 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "organization/removeBatch", method = {RequestMethod.POST})
    public MessageResult removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * 机构-删除(参数)
     *
     * @param organizationRemoveReqDTO 机构删除(参数)请求DTO
     * @return MessageResult 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "organization/removeByParams", method = {RequestMethod.POST})
    public MessageResult removeByParams(OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(organizationRemoveReqDTO);

        return responseMessage(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

package ${conf.webPackageName}.${table.lowerCamelName}.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.web.controller.AbstractController;
import com.finhub.framework.web.vo.ItemR;
import com.finhub.framework.web.vo.ItemsR;
import com.finhub.framework.web.vo.ListParam;
import com.finhub.framework.web.vo.MessageR;
import com.finhub.framework.web.vo.PageR;

import ${conf.servicePackageName}.${table.lowerCamelName}.${table.className}Service;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}AddReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ModifyReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}RemoveReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ${table.comment} Controller
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Validated
@RestController
public class ${table.className}Controller extends AbstractController<${table.className}Service> {

    /**
     * ${table.comment}-列表
     *
     * @param ${table.camelName}ListReqDTO ${table.comment}列表请求DTO
     * @return ItemsR<${table.className}ListResDTO> 列表结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/list", method = {RequestMethod.GET})
    public ItemsR<${table.className}ListResDTO> list(${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        return responseItemsR(ResponseCodeEnum.SUCCESS, service.list(${table.camelName}ListReqDTO));
    }

    /**
     * ${table.comment}-First查询
     *
     * @param ${table.camelName}ListReqDTO ${table.comment}Fist查询请求DTO
     * @return ItemR<${table.className}ListResDTO> First查询结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/list_one", method = {RequestMethod.GET})
    public ItemR<${table.className}ListResDTO> listOne(${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        return responseItemR(ResponseCodeEnum.SUCCESS, service.listOne(${table.camelName}ListReqDTO));
    }

    /**
     * ${table.comment}-分页
     *
     * @param ${table.camelName}PageReqDTO ${table.comment}分页请求DTO
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return PageR<${table.className}PageResDTO> 分页结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/page", method = {RequestMethod.GET})
    public PageR<${table.className}PageResDTO> page(${table.className}PageReqDTO ${table.camelName}PageReqDTO, Integer pageNo, Integer pageSize) {
        int current = Func.isNullOrZero(pageNo) ? 1 : pageNo;
        int size = Func.isNullOrZero(pageSize) ? 10 : pageSize;

        Page<${table.className}PageResDTO> ${table.camelName}PageResDTOPage = service.pagination(${table.camelName}PageReqDTO, current, size);
        return responsePageR(ResponseCodeEnum.SUCCESS, ${table.camelName}PageResDTOPage.getTotal(), ${table.camelName}PageResDTOPage.getRecords(), size, current);
    }

    /**
     * ${table.comment}-新增
     *
     * @param ${table.camelName}AddReqDTO ${table.comment}新增请求DTO
     * @return MessageR 新增结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/add", method = {RequestMethod.POST})
    public MessageR add(${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        Boolean isSuccess = service.add(${table.camelName}AddReqDTO);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-新增(所有字段)
     *
     * @param ${table.camelName}AddReqDTO ${table.comment}新增(所有字段)请求DTO
     * @return MessageR 新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/add_all_column", method = {RequestMethod.POST})
    public MessageR addAllColumn(${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        Boolean isSuccess = service.addAllColumn(${table.camelName}AddReqDTO);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-批量新增(所有字段)
     *
     * @param ${table.camelName}AddReqDTOList ${table.comment}批量新增(所有字段)请求DTO
     * @return MessageR 批量新增(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/add_batch_all_column", method = {RequestMethod.POST})
    public MessageR addBatchAllColumn(List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList) {
        Boolean isSuccess = service.addBatchAllColumn(${table.camelName}AddReqDTOList);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-详情
     *
     * @param id 主键 ID
     * @return ItemR<${table.className}ShowResDTO> 详情结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/show", method = {RequestMethod.GET})
    public ItemR<${table.className}ShowResDTO> show(String id) {
        return responseItemR(ResponseCodeEnum.SUCCESS, service.show(id));
    }

    /**
     * ${table.comment}-详情(批量)
     *
     * @param ids 主键 ID 集合
     * @return ItemsR<${table.className}ShowResDTO> 详情(批量)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/show_by_ids", method = {RequestMethod.POST})
    public ItemsR<${table.className}ShowResDTO> showByIds(@RequestBody ListParam<String> ids) {
        return responseItemsR(ResponseCodeEnum.SUCCESS, service.showByIds(ids.getItems()));
    }

    /**
     * ${table.comment}-更新
     *
     * @param ${table.camelName}ModifyReqDTO ${table.comment}更新请求DTO
     * @return MessageR 更新结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/modify", method = {RequestMethod.POST})
    public MessageR modify(${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        Boolean isSuccess = service.modify(${table.camelName}ModifyReqDTO);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-更新(所有字段)
     *
     * @param ${table.camelName}ModifyReqDTO ${table.comment}更新(所有字段)请求DTO
     * @return MessageR 更新(所有字段)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/modify_selective", method = {RequestMethod.POST})
    public MessageR modifyAllColumn(${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        Boolean isSuccess = service.modifyAllColumn(${table.camelName}ModifyReqDTO);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-删除
     *
     * @param id 主键 ID
     * @return MessageR 删除结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/remove", method = {RequestMethod.POST})
    public MessageR remove(String id) {
        Boolean isSuccess = service.remove(id);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-删除(批量)
     *
     * @param ids 主键 ID 集合
     * @return MessageR 删除(批量)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/remove_batch", method = {RequestMethod.POST})
    public MessageR removeBatch(@RequestBody ListParam<String> ids) {
        Boolean isSuccess = service.removeBatch(ids.getItems());

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }

    /**
     * ${table.comment}-删除(参数)
     *
     * @param ${table.camelName}RemoveReqDTO ${table.comment}删除(参数)请求DTO
     * @return MessageR 删除(参数)结果
     * @undone
     */
    @RequestMapping(value = "${table.camelName}/remove_by_params", method = {RequestMethod.POST})
    public MessageR removeByParams(${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO) {
        Boolean isSuccess = service.removeByParams(${table.camelName}RemoveReqDTO);

        return responseMessageR(isSuccess ? ResponseCodeEnum.SUCCESS : ResponseCodeEnum.INTERNAL_ERROR);
    }
}

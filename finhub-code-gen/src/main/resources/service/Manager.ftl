package ${conf.servicePackageName}.${table.lowerCamelName}.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import ${conf.daoPackageName}.${table.lowerCamelName}.${table.className}DAO;
import ${conf.daoPackageName}.${table.lowerCamelName}.po.${table.className}PO;
import ${conf.servicePackageName}.${table.lowerCamelName}.domain.${table.className}DO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}AddReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}DTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ModifyReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}RemoveReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ShowResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.converter.${table.className}Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ${table.comment} Manager
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Component
public class ${table.className}Manager extends BaseManagerImpl<${table.className}DAO, ${table.className}PO, ${table.className}DTO, ${table.className}Converter> {

    public static ${table.className}Manager me() {
        return SpringUtil.getBean(${table.className}Manager.class);
    }

    public List<${table.className}ListResDTO> list(final ${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        ${table.className}DTO paramsDTO = ${table.className}DO.me().buildListParamsDTO(${table.camelName}ListReqDTO);

        List<${table.className}DTO> ${table.camelName}DTOList = super.findList(paramsDTO);

        return ${table.className}DO.me().transfer${table.className}ListResDTOList(${table.camelName}DTOList);
    }

    public ${table.className}ListResDTO listOne(final ${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        ${table.className}DTO paramsDTO = ${table.className}DO.me().buildListParamsDTO(${table.camelName}ListReqDTO);

        ${table.className}DTO ${table.camelName}DTO = super.findOne(paramsDTO);

        return ${table.className}DO.me().transfer${table.className}ListResDTO(${table.camelName}DTO);
    }

    public Page<${table.className}PageResDTO> pagination(final ${table.className}PageReqDTO ${table.camelName}PageReqDTO, final Integer current, final Integer size) {
        ${table.className}DTO paramsDTO = ${table.className}DO.me().buildPageParamsDTO(${table.camelName}PageReqDTO);

        Page<${table.className}DTO> ${table.camelName}DTOPage = super.findPage(paramsDTO, current, size);

        return ${table.className}DO.me().transfer${table.className}PageResDTOPage(${table.camelName}DTOPage);
    }

    public Boolean add(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        ${table.className}DO.me().check${table.className}AddReqDTO(${table.camelName}AddReqDTO);

        ${table.className}DTO add${table.className}DTO = ${table.className}DO.me().buildAdd${table.className}DTO(${table.camelName}AddReqDTO);

        return super.saveDTO(add${table.className}DTO);
    }

    public Boolean addAllColumn(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        ${table.className}DO.me().check${table.className}AddReqDTO(${table.camelName}AddReqDTO);

        ${table.className}DTO add${table.className}DTO = ${table.className}DO.me().buildAdd${table.className}DTO(${table.camelName}AddReqDTO);

        return super.saveAllColumn(add${table.className}DTO);
    }

    public Boolean addBatchAllColumn(final List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList) {
        ${table.className}DO.me().check${table.className}AddReqDTOList(${table.camelName}AddReqDTOList);

        List<${table.className}DTO> addBatch${table.className}DTOList = ${table.className}DO.me().buildAddBatch${table.className}DTOList(${table.camelName}AddReqDTOList);

        return super.saveBatchAllColumn(addBatch${table.className}DTOList);
    }

    public ${table.className}ShowResDTO show(final String id) {
        ${table.className}DTO ${table.camelName}DTO = super.findById(id);

        return ${table.className}DO.me().transfer${table.className}ShowResDTO(${table.camelName}DTO);
    }

    public List<${table.className}ShowResDTO> showByIds(final List<String> ids) {
        ${table.className}DO.me().checkIds(ids);

        List<${table.className}DTO> ${table.camelName}DTOList = super.findBatchIds(ids);

        return ${table.className}DO.me().transfer${table.className}ShowResDTOList(${table.camelName}DTOList);
    }

    public Boolean modify(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        ${table.className}DO.me().check${table.className}ModifyReqDTO(${table.camelName}ModifyReqDTO);

        ${table.className}DTO modify${table.className}DTO = ${table.className}DO.me().buildModify${table.className}DTO(${table.camelName}ModifyReqDTO);

        return super.modifyById(modify${table.className}DTO);
    }

    public Boolean modifyAllColumn(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        ${table.className}DO.me().check${table.className}ModifyReqDTO(${table.camelName}ModifyReqDTO);

        ${table.className}DTO modify${table.className}DTO = ${table.className}DO.me().buildModify${table.className}DTO(${table.camelName}ModifyReqDTO);

        return super.modifyAllColumnById(modify${table.className}DTO);
    }

    public Boolean removeByParams(final ${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO) {
        ${table.className}DO.me().check${table.className}RemoveReqDTO(${table.camelName}RemoveReqDTO);

        ${table.className}DTO remove${table.className}DTO = ${table.className}DO.me().buildRemove${table.className}DTO(${table.camelName}RemoveReqDTO);

        return super.remove(remove${table.className}DTO);
    }

    @Override
    protected ${table.className}PO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new ${table.className}PO();
        }

        return BeanUtil.toBean(map, ${table.className}PO.class);
    }

    @Override
    protected ${table.className}DTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new ${table.className}DTO();
        }

        return BeanUtil.toBean(map, ${table.className}DTO.class);
    }
}

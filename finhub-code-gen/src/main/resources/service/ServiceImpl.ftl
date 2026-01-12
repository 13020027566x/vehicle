package ${conf.servicePackageName}.${table.lowerCamelName}.impl;

import com.finhub.framework.common.service.impl.BaseServiceImpl;
import com.finhub.framework.core.page.Page;

import ${conf.daoPackageName}.${table.lowerCamelName}.po.${table.className}PO;
import ${conf.servicePackageName}.${table.lowerCamelName}.${table.className}Service;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}AddReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}DTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ModifyReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}RemoveReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ShowResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.manager.${table.className}Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${table.comment} ServiceImpl
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Service
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}Manager, ${table.className}PO, ${table.className}DTO> implements ${table.className}Service {

    @Override
    public List<${table.className}ListResDTO> list(final ${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        return manager.list(${table.camelName}ListReqDTO);
    }

    @Override
    public ${table.className}ListResDTO listOne(final ${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        return manager.listOne(${table.camelName}ListReqDTO);
    }

    @Override
    public Page<${table.className}PageResDTO> pagination(final ${table.className}PageReqDTO ${table.camelName}PageReqDTO, final Integer current,
        final Integer size) {
        return manager.pagination(${table.camelName}PageReqDTO, current, size);
    }

    @Override
    public Boolean add(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        return manager.add(${table.camelName}AddReqDTO);
    }

    @Override
    public Boolean addAllColumn(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        return manager.addAllColumn(${table.camelName}AddReqDTO);
    }

    @Override
    public Boolean addBatchAllColumn(final List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList) {
        return manager.addBatchAllColumn(${table.camelName}AddReqDTOList);
    }

    @Override
    public ${table.className}ShowResDTO show(final String id) {
        return manager.show(id);
    }

    @Override
    public List<${table.className}ShowResDTO> showByIds(final List<String> ids) {
        return manager.showByIds(ids);
    }

    @Override
    public Boolean modify(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        return manager.modify(${table.camelName}ModifyReqDTO);
    }

    @Override
    public Boolean modifyAllColumn(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        return manager.modifyAllColumn(${table.camelName}ModifyReqDTO);
    }

    @Override
    public Boolean removeByParams(final ${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO) {
        return manager.removeByParams(${table.camelName}RemoveReqDTO);
    }
}

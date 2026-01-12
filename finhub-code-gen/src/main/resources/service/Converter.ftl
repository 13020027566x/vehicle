package ${conf.servicePackageName}.${table.lowerCamelName}.converter;

import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.converter.BaseConverterConfig;
import com.finhub.framework.core.page.Page;

import cn.hutool.extra.spring.SpringUtil;
import ${conf.daoPackageName}.${table.lowerCamelName}.po.${table.className}PO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}AddReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}DTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ModifyReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}RemoveReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ShowResDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ${table.comment} Converter
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Mapper(config = BaseConverterConfig.class)
public interface ${table.className}Converter extends BaseConverter<${table.className}DTO, ${table.className}PO> {

    static ${table.className}Converter me() {
        return SpringUtil.getBean(${table.className}Converter.class);
    }

    ${table.className}DTO convertTo${table.className}DTO(${table.className}AddReqDTO ${table.camelName}AddReqDTO);

    ${table.className}DTO convertTo${table.className}DTO(${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO);

    ${table.className}DTO convertTo${table.className}DTO(${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO);

    ${table.className}DTO convertTo${table.className}DTO(${table.className}ListReqDTO ${table.camelName}ListReqDTO);

    ${table.className}DTO convertTo${table.className}DTO(${table.className}PageReqDTO ${table.camelName}PageReqDTO);

    ${table.className}ShowResDTO convertTo${table.className}ShowResDTO(${table.className}DTO ${table.camelName}DTO);

    List<${table.className}ShowResDTO> convertTo${table.className}ShowResDTOList(List<${table.className}DTO> ${table.camelName}DTOList);

    ${table.className}ListResDTO convertTo${table.className}ListResDTO(${table.className}DTO ${table.camelName}DTO);

    List<${table.className}ListResDTO> convertTo${table.className}ListResDTOList(List<${table.className}DTO> ${table.camelName}DTOList);

    List<${table.className}DTO> convertTo${table.className}DTOList(List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList);

    ${table.className}PageResDTO convertTo${table.className}PageResDTO(${table.className}DTO ${table.camelName}DTO);

    List<${table.className}PageResDTO> convertTo${table.className}PageResDTOList(List<${table.className}DTO> ${table.camelName}DTOList);

    Page<${table.className}PageResDTO> convertTo${table.className}PageResDTOPage(Page<${table.className}DTO> ${table.camelName}DTOPage);
}

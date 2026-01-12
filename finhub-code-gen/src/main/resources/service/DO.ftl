package ${conf.servicePackageName}.${table.lowerCamelName}.domain;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.domain.BaseDO;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.constant.enums.MessageResponseEnum;

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
import ${conf.servicePackageName}.${table.lowerCamelName}.converter.${table.className}Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ${table.comment} DO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Component
public class ${table.className}DO extends BaseDO<${table.className}DTO, ${table.className}PO, ${table.className}Converter> {

    public static ${table.className}DO me() {
        return SpringUtil.getBean(${table.className}DO.class);
    }

    public void check${table.className}AddReqDTO(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        if (Func.isEmpty(${table.camelName}AddReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void check${table.className}AddReqDTOList(final List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList) {
        if (Func.isEmpty(${table.camelName}AddReqDTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void checkIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "集合不能为空且大小大于0");
        }
    }

    public void check${table.className}ModifyReqDTO(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        if (Func.isEmpty(${table.camelName}ModifyReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public void check${table.className}RemoveReqDTO(final ${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO) {
        if (Func.isEmpty(${table.camelName}RemoveReqDTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "入参不能为空");
        }
    }

    public ${table.className}DTO buildListParamsDTO(final ${table.className}ListReqDTO ${table.camelName}ListReqDTO) {
        return converter.convertTo${table.className}DTO(${table.camelName}ListReqDTO);
    }

    public ${table.className}DTO buildPageParamsDTO(final ${table.className}PageReqDTO ${table.camelName}PageReqDTO) {
        return converter.convertTo${table.className}DTO(${table.camelName}PageReqDTO);
    }

    public ${table.className}DTO buildAdd${table.className}DTO(final ${table.className}AddReqDTO ${table.camelName}AddReqDTO) {
        return converter.convertTo${table.className}DTO(${table.camelName}AddReqDTO);
    }

    public List<${table.className}DTO> buildAddBatch${table.className}DTOList(final List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList) {
        return converter.convertTo${table.className}DTOList(${table.camelName}AddReqDTOList);
    }

    public ${table.className}DTO buildModify${table.className}DTO(final ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO) {
        return converter.convertTo${table.className}DTO(${table.camelName}ModifyReqDTO);
    }

    public ${table.className}DTO buildRemove${table.className}DTO(final ${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO) {
        return converter.convertTo${table.className}DTO(${table.camelName}RemoveReqDTO);
    }

    public List<${table.className}ListResDTO> transfer${table.className}ListResDTOList(final List<${table.className}DTO> ${table.camelName}DTOList) {
        if (Func.isEmpty(${table.camelName}DTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertTo${table.className}ListResDTOList(${table.camelName}DTOList);
    }

    public ${table.className}ListResDTO transfer${table.className}ListResDTO(final ${table.className}DTO ${table.camelName}DTO) {
        if (Func.isEmpty(${table.camelName}DTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertTo${table.className}ListResDTO(${table.camelName}DTO);
    }

    public Page<${table.className}PageResDTO> transfer${table.className}PageResDTOPage(final Page<${table.className}DTO> ${table.camelName}DTOPage) {
        if (Func.isEmpty(${table.camelName}DTOPage) || Func.isEmpty(${table.camelName}DTOPage.getRecords())) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertTo${table.className}PageResDTOPage(${table.camelName}DTOPage);
    }

    public ${table.className}ShowResDTO transfer${table.className}ShowResDTO(final ${table.className}DTO ${table.camelName}DTO) {
        if (Func.isEmpty(${table.camelName}DTO)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertTo${table.className}ShowResDTO(${table.camelName}DTO);
    }

    public List<${table.className}ShowResDTO> transfer${table.className}ShowResDTOList(final List<${table.className}DTO> ${table.camelName}DTOList) {
        if (Func.isEmpty(${table.camelName}DTOList)) {
            MessageResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.SUCCESS, "未查找到记录");
        }

        return converter.convertTo${table.className}ShowResDTOList(${table.camelName}DTOList);
    }
}

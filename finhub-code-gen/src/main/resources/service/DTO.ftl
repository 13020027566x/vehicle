package ${conf.servicePackageName}.${table.lowerCamelName}.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
<#list table.columns as col>
    <#if (col.classImport != "")>
import ${col.classImport};
    </#if>
</#list>

/**
 * ${table.comment} DTO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${table.className}DTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    <#list table.columns as col>
    <#if col.camelName != "id" && col.camelName != "isDel" && col.camelName != "isTest" && col.camelName != "createAt" && col.camelName != "createTime" && col.camelName != "createBy" && col.camelName != "createName" && col.camelName != "updateAt" && col.camelName != "updateTime" && col.camelName != "updateBy" && col.camelName != "updateName">
    /**
     * ${col.comment}
     */
    private ${col.javaType} ${col.camelName};

    </#if>
    </#list>
}

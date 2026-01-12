package ${conf.servicePackageName}.${table.lowerCamelName}.dto;

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
 * ${table.comment} 分页 ReqDTO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${table.className}PageReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    <#list table.columns as col>
    /**
     * ${col.comment}
     */
    private ${col.javaType} ${col.camelName};

    </#list>
}

package ${conf.daoPackageName}.${table.lowerCamelName}.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

<#list table.columns as col>
    <#if (col.classImport != "")>
import ${col.classImport};
    </#if>
</#list>

/**
 * ${table.comment} PO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "${table.name}", resultMap = "${table.className}ResultMap")
public class ${table.className}PO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "${table.name}";

    <#list table.columns as col>
    <#if col.camelName != "id" && col.camelName != "isDel" && col.camelName != "isTest" && col.camelName != "createAt" && col.camelName != "createTime" && col.camelName != "createBy" && col.camelName != "createName" && col.camelName != "updateAt" && col.camelName != "updateTime" && col.camelName != "updateBy" && col.camelName != "updateName">
    public static final String DB_COL_${col.underLineUpperName} = "${col.name}";

    </#if>
    </#list>

    <#list table.columns as col>
    <#if col.camelName != "id" && col.camelName != "isDel" && col.camelName != "isTest" && col.camelName != "createAt" && col.camelName != "createTime" && col.camelName != "createBy" && col.camelName != "createName" && col.camelName != "updateAt" && col.camelName != "updateTime" && col.camelName != "updateBy" && col.camelName != "updateName">
    /**
     * ${col.comment}
     */
    private ${col.javaType} ${col.camelName};

    </#if>
    </#list>
}

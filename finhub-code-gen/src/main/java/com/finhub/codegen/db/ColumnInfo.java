package com.finhub.codegen.db;

import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.util.StrUtil;
import com.finhub.codegen.util.TypeUtils;
import lombok.Data;

/**
 * @author Mickey
 * @version 1.0
 * @since 2014/10/19 12:17
 */
@Data
public class ColumnInfo {

    public ColumnInfo(String name, String type, String comment) {
        this.name = name;
        this.type = type;
        this.comment = comment;
    }

    private String name;

    private String type;

    private String comment;

    public String getName() {
        return name;
    }

    public String getCamelName() {
        if (StrUtil.isNotBlank(this.name) && !StrUtil.contains(this.name, "_")) {
            return this.name.toLowerCase();
        }
        return StrUtil.toCamelCase(name);
    }

    public String getUnderLineUpperName() {
        return StrUtil.toUnderlineCase(name).toUpperCase();
    }

    public String getClassName() {
        return StrUtil.upperFirst(getCamelName());
    }

    public String getJavaType() {
        if ("id".equalsIgnoreCase(this.name)) {
            return TypeUtils.JAVA_TYPE_MAP.get("VARCHAR");
        }

        if (TypeUtils.JAVA_TYPE_MAP.containsKey(type.toUpperCase())) {
            return TypeUtils.JAVA_TYPE_MAP.get(type.toUpperCase());
        }

        return type;
    }

    public String getMyBatisType() {
        if (TypeUtils.MYBATIS_TYPE_MAP.containsKey(type)) {
            return TypeUtils.MYBATIS_TYPE_MAP.get(type);
        }

        return type;
    }

    public String getClassImport() {
        if (TypeUtils.CLASS_IMPORT_MAP.containsKey(type.toUpperCase())) {
            return TypeUtils.CLASS_IMPORT_MAP.get(type.toUpperCase());
        }
        return StrConstants.S_EMPTY;
    }

}

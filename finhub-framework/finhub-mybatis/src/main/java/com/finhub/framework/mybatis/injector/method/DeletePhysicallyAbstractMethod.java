package com.finhub.framework.mybatis.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

/**
 * 物理删除抽象方法
 *
 * @author zhenxing_liang
 * @version 1.0
 * @since 2023/02/16 17:34
 */
public abstract class DeletePhysicallyAbstractMethod extends AbstractMethod {
    protected String sqlWhereByMap(TableInfo table) {
        String sqlScript = SqlScriptUtils.convertChoose("v == null", " ${k} IS NULL ",
            " ${k} = #{v} ");
        sqlScript = SqlScriptUtils.convertForeach(sqlScript, COLUMN_MAP, "k", "v", "AND");
        sqlScript = SqlScriptUtils.convertWhere(sqlScript);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null and !%s", COLUMN_MAP,
            COLUMN_MAP_IS_EMPTY), true);
        return sqlScript;
    }

    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
        sqlScript += NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(String.format(SqlScriptUtils.convertIf(" AND", String.format("%s and %s", WRAPPER_NONEMPTYOFENTITY, WRAPPER_NONEMPTYOFNORMAL), false) + " ${%s}", WRAPPER_SQLSEGMENT),
            String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                WRAPPER_NONEMPTYOFWHERE), true);
        sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
            String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                WRAPPER_EMPTYOFWHERE), true);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER), true);
        return newLine ? NEWLINE + sqlScript : sqlScript;
    }
}

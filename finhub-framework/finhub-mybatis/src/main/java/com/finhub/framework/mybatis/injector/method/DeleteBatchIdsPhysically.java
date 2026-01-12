package com.finhub.framework.mybatis.injector.method;

import com.finhub.framework.mybatis.injector.enums.CustomSqlMethod;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 物理删除
 *
 * @author zhenxing_liang
 * @version 1.0
 * @since 2023/02/16 17:34
 */
public class DeleteBatchIdsPhysically extends DeletePhysicallyAbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomSqlMethod sqlMethod = CustomSqlMethod.DELETE_BATCH_BY_IDS_PHYSICALLY;
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
            SqlScriptUtils.convertForeach("#{item}", COLLECTION, null, "item", COMMA));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);
        return this.addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
    }

}

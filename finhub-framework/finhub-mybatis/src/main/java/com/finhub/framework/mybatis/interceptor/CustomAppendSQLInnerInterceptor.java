package com.finhub.framework.mybatis.interceptor;

import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.logback.util.LogUtils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.sql.Connection;

/**
 * 追加 TraceId & LIMIT SQL 拦截器
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2021/09/08 10:39
 */
@Data
@Slf4j
@AllArgsConstructor
public class CustomAppendSQLInnerInterceptor implements InnerInterceptor {

    private static final String LIMIT = "limit";

    private Integer limitCount;

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = mpSh.mappedStatement();
        SqlCommandType sct = ms.getSqlCommandType();
        PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
        // 追加 LIMIT
        if (sct == SqlCommandType.SELECT) {
            mpBs.sql(appendLimitSql(mpBs.sql()));
        }
        // 追加 TraceId
        mpBs.sql(appendTraceId(mpBs.sql()));
    }

    protected String appendTraceId(String sql) {
        return "/* " + LogUtils.getTraceId() + " */" + StrConstants.S_SPACE + sql;
    }

    protected String appendLimitSql(String sql) {
        return StrUtil.containsIgnoreCase(sql, LIMIT) ?
                sql :
                sql.replaceAll(StrConstants.S_SEMICOLON, StrConstants.S_EMPTY) + StrConstants.S_SPACE + LIMIT + StrConstants.S_SPACE + limitCount;
    }
}

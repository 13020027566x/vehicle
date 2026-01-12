package com.finhub.framework.mybatis.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.MDC;
import org.springframework.util.StopWatch;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.finhub.framework.core.str.StrConstants.S_EMPTY;
import static com.finhub.framework.core.str.StrConstants.S_FAIL;
import static com.finhub.framework.core.str.StrConstants.S_QUESTION;
import static com.finhub.framework.core.str.StrConstants.S_SUCCESS;

/**
 * SQL 指纹 APM 性能日志埋点
 */
@Slf4j
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class})
})
public class ApmMybatisLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object result = S_EMPTY;
        String status = S_FAIL;
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            result = invocation.proceed();

            status = S_SUCCESS;
        } finally {
            stopWatch.stop();
            doAfterInvocation(invocation, status, result, stopWatch.getTotalTimeMillis());
        }

        return result;
    }

    private void doAfterInvocation(Invocation invocation, String status, Object result, Long costTime) {
        Object[] arguments = invocation.getArgs();
        String sqlId = getSqlId(arguments);
        String sqlStatement = getSqlStatement(arguments);

        String jdbcurl = "";
        try {
            Executor executor = (Executor) invocation.getTarget();
            jdbcurl = executor.getTransaction().getConnection().getMetaData().getURL();
            String resName =
                jdbcurl.substring(0, jdbcurl.contains(S_QUESTION) ? jdbcurl.indexOf(S_QUESTION) : jdbcurl.length());

            String resultType = S_EMPTY;
            long resultSize = 0;

            if (!Objects.isNull(result)) {
                resultType = result.getClass().getTypeName();
                if (result instanceof List) {
                    resultSize = ((List) result).size();
                }
            }

            String resultStr = JSON.toJSONString(result);
            if (StringUtils.isNotEmpty(resultStr)) {
                if (resultStr.length() > 2048) {
                    resultStr = resultStr.substring(0, 2048) + "...";
                }
            }

            MDC.put("apm_log_type", "fbt_apm_log_mybatis");
            MDC.put("sqlId", sqlId);
            MDC.put("costTime", String.valueOf(costTime));
            MDC.put("resultSize", String.valueOf(resultSize));

            //logger.info("fbt_apm_log_mybatis sqlId={} status={} costTime={} resultSize={} resName={} sql={} result={}  ",sqlId,status,costTime,resultSize,resName,sql,resultStr);
            // 考虑到查询结果可能会有一些敏感信息，暂时不打印到日志
            log.info(
                "fbt_apm_log_mybatis sqlId={} status={} costTime={} resultType={} resultSize={} resName={} sql={} ",
                sqlId, status, costTime, resultType, resultSize, resName, sqlStatement);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSqlId(Object[] arguments) {
        try {
            MappedStatement mappedStatement = (MappedStatement) arguments[0];
            return mappedStatement.getId();
        } catch (Exception e) {
            log.warn("getSqlId fail.", e);
        }

        return "unknown sqlId";
    }

    private String getSqlStatement(Object[] arguments) {
        try {
            MappedStatement mappedStatement = (MappedStatement) arguments[0];
            Object parameter = null;
            if (arguments.length > 1) {
                parameter = arguments[1];
            }
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Configuration configuration = mappedStatement.getConfiguration();
            String sql = showSql(configuration, boundSql);
            StringBuilder str = new StringBuilder(100);
            str.append(sql);
            str.append(";");
            return str.toString();
        } catch (Exception e) {
            log.warn("getSqlStatement fail.", e);
        }

        return "unknown sql";
    }

    public String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        sql = sql.replaceAll("$", "￥");
        try {
            if (parameterMappings.size() > 0 && parameterObject != null) {
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    for (ParameterMapping parameterMapping : parameterMappings) {
                        String propertyName = parameterMapping.getProperty();
                        if (metaObject.hasGetter(propertyName)) {
                            Object obj = metaObject.getValue(propertyName);
                            sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            Object obj = boundSql.getAdditionalParameter(propertyName);
                            sql = sql.replaceFirst("\\?", getParameterValue(obj));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

}

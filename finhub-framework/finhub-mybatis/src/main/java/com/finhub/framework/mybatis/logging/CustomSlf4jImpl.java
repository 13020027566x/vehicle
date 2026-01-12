package com.finhub.framework.mybatis.logging;

import com.finhub.framework.logback.LogMdcHolder;
import com.finhub.framework.logback.util.LogCommonUtils;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.finhub.framework.core.str.StrConstants.S_EMPTY;
import static com.finhub.framework.core.str.StrConstants.S_SUCCESS;

/**
 * 自定义 Slf4jImpl
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2022/10/10 17:15
 */
public class CustomSlf4jImpl implements Log {

    private static final String PREPARING_PREFIX = "==>  Preparing:";

    private static final String PREPARING_KEY = "sql_preparing";

    private static final String PARAMETERS_PREFIX = "==> Parameters:";

    private static final String PARAMETERS_KEY = "sql_parameters";

    private static final String COLUMNS_PREFIX = "<==    Columns:";

    private static final String COLUMNS_KEY = "sql_columns";

    private static final String ROW_PREFIX = "<==        Row:";

    private static final String TOTAL_PREFIX = "<==      Total:";

    private static final String TOTAL_KEY = "sql_row_total";

    private static final String TIME_KEY = "sql_cost_time";

    private final Logger log;

    public CustomSlf4jImpl(String clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        if (s.startsWith(PREPARING_PREFIX)) {
            LogMdcHolder.putBeginTime(System.currentTimeMillis());
            LogMdcHolder.addTempLog(
                new LogCommonUtils.LogKeyValue(PREPARING_KEY, s.replaceAll(PREPARING_PREFIX, S_EMPTY)));
        } else if (s.startsWith(PARAMETERS_PREFIX)) {
            LogMdcHolder.addTempLog(
                new LogCommonUtils.LogKeyValue(PARAMETERS_KEY, s.replaceAll(PARAMETERS_PREFIX, S_EMPTY)));
        } else if (s.startsWith(TOTAL_PREFIX)) {
            try {
                LogMdcHolder.addTempLog(new LogCommonUtils.LogKeyValue(TOTAL_KEY, s.replaceAll(TOTAL_PREFIX, S_EMPTY)));
                LogMdcHolder.putOnceLog(LogMdcHolder.getTempLog());

                Long beginTime = LogMdcHolder.getBeginTime();
                Long costTime = System.currentTimeMillis() - beginTime;
                LogMdcHolder.addOnceLog(new LogCommonUtils.LogKeyValue(TIME_KEY, String.valueOf(costTime)));

                log.info("sql execute " + S_SUCCESS);
            } catch (Exception e) {
                log.warn("sql execute " + S_SUCCESS + ", But CustomSlf4jImpl debug log fail.", e);
            }finally {
                LogMdcHolder.removeBeginTime();
                LogMdcHolder.removeTempLog();
                LogMdcHolder.removeOnceLog();
            }
        }
    }

    @Override
    public void trace(String s) {
        if (s.startsWith(COLUMNS_PREFIX)) {
            LogMdcHolder.addTempLog(new LogCommonUtils.LogKeyValue(COLUMNS_KEY, s.replaceAll(COLUMNS_PREFIX, S_EMPTY)));
        } else if (s.startsWith(ROW_PREFIX)) {
            // NONE
        } else {
            log.info(s);
        }
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }

}

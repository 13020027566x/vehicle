package com.finhub.framework.mybatis.interceptor;

import com.finhub.framework.core.Func;
import com.finhub.framework.mybatis.config.ConfigUtils;
import com.finhub.framework.mybatis.util.AssertUtils;

import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义 SQL 性能规范拦截器
 * <p>
 * 拦截 SQL 类型的场景
 * 1. 必须使用到索引，包含 left join 连接字段，符合索引最左原则
 * 必须使用索引好处：
 * 1.1 如果因为动态 SQL，bug 导致 update 的 where 条件没有带上，全表更新上万条数据
 * 1.2 如果检查到使用了索引，SQL 性能基本不会太差
 * <p>
 * 2. SQL 单表执行，禁止任何 left join，如果不能拆成单表执行的 SQL，请 RD -> 三级 leader -> 二级 leader 被 TC 挑战后再决定。
 * SQL 尽量单表执行的好处：
 * 2.1 查询条件简单、易于开理解和维护；
 * 2.2 扩展性极强；（可为分库分表做准备）
 * 2.3 缓存利用率高；
 * <p>
 * 3. 在字段上使用函数
 * 4. where 条件为空
 * 5. where 条件使用了 使用子查询
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2021/09/08 10:39
 */
@Slf4j
public class CustomIllegalSQLInnerInterceptor extends JsqlParserSupport implements InnerInterceptor {

    /**
     * 大表的表名
     */
    private static final Set<String> BIG_TABLE_SET = Sets.newHashSet("");

    /**
     * 忽略某些特殊 SQL
     */
    private static final Set<String> IGNORE_SQL_SET =
        Sets.newHashSet("SELECT LAST_INSERT_ID()", "SELECT LAST_INSERT_ID() from dual where 1=1",
            "SELECT LAST_INSERT_ID() FROM DUAL WHERE 1=1");

    /**
     * 缓存验证结果，提高性能
     */
    private static final Set<String> CACHE_VALID_RESULT = new HashSet<>();

    /**
     * 缓存表的索引信息
     */
    private static final Map<String, List<IndexInfo>> INDEX_INFO_MAP = new ConcurrentHashMap<>();

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpStatementHandler = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = mpStatementHandler.mappedStatement();
        SqlCommandType sct = ms.getSqlCommandType();

        if (ConfigUtils.isIgnore(ms.getId())
            || sct == SqlCommandType.INSERT
            || InterceptorIgnoreHelper.willIgnoreIllegalSql(ms.getId())
            || SqlParserHelper.getSqlParserInfo(ms)) {
            return;
        }

        BoundSql boundSql = mpStatementHandler.boundSql();
        String originalSql = boundSql.getSql();
        if (IGNORE_SQL_SET.contains(originalSql.trim())) {
            logger.debug("该SQL已配置忽略，无需验证，SQL:" + originalSql);
            return;
        }

        String md5Base64 = EncryptUtils.md5Base64(originalSql);
        if (CACHE_VALID_RESULT.contains(md5Base64)) {
            logger.debug("该SQL已验证通过，无需再次验证，SQL:" + originalSql);
            return;
        }

        logger.debug("检查SQL是否合规，SQL:" + originalSql);
        parserSingle(originalSql, connection);
        //缓存验证结果
        CACHE_VALID_RESULT.add(md5Base64);
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        AssertUtils.notNull(where, "非法SQL，必须要有where条件");
        Table table = (Table) plainSelect.getFromItem();
        List<Join> joins = plainSelect.getJoins();
        validWhere(where, table, (Connection) obj);
        AssertUtils.isTrue(Func.isEmpty(joins) || joins.size() < 3, "非法SQL，禁止3个表以上连表");
        validJoins(joins, table, (Connection) obj);
    }

    @Override
    protected void processUpdate(Update update, int index, String sql, Object obj) {
        Expression where = update.getWhere();
        AssertUtils.notNull(where, "非法SQL，必须要有where条件");
        Table table = update.getTable();
        List<Join> joins = update.getJoins();
        validWhere(where, table, (Connection) obj);
        AssertUtils.isTrue(Func.isEmpty(joins) || joins.size() < 3, "非法SQL，禁止3个表以上连表");
        validJoins(joins, table, (Connection) obj);
    }

    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        Expression where = delete.getWhere();
        AssertUtils.notNull(where, "非法SQL，必须要有where条件");
        Table table = delete.getTable();
        List<Join> joins = delete.getJoins();
        validWhere(where, table, (Connection) obj);
        AssertUtils.isTrue(Func.isEmpty(joins) || joins.size() < 3, "非法SQL，禁止3个表以上连表");
        validJoins(joins, table, (Connection) obj);
    }

    /**
     * 验证expression对象是不是 or、not等等
     *
     * @param expression ignore
     */
    private void validExpression(Expression expression) {
        // where条件使用了 or 关键字
        if (expression instanceof OrExpression) {
            // OrExpression orExpression = (OrExpression) expression;
            // throw new MybatisPlusException("非法SQL，where条件中不能使用【or】关键字，错误or信息：" + orExpression.toString());
        } else if (expression instanceof NotEqualsTo) {
            // NotEqualsTo notEqualsTo = (NotEqualsTo) expression;
            // throw new MybatisPlusException("非法SQL，where条件中不能使用【!=】关键字，错误!=信息：" + notEqualsTo.toString());
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            // TODO 升级 jsqlparser 后待实现
            // if (binaryExpression.isNot()) {
            //    throw new MybatisPlusException("非法SQL，where条件中不能使用【not】关键字，错误not信息：" + binaryExpression.toString());
            // }
            if (binaryExpression.getLeftExpression() instanceof Function) {
                Function function = (Function) binaryExpression.getLeftExpression();
                AssertUtils.throwMybatisPlusException("非法SQL，where条件中不能使用数据库函数，错误函数信息：" + function.toString());
            }
            if (binaryExpression.getRightExpression() instanceof SubSelect) {
                SubSelect subSelect = (SubSelect) binaryExpression.getRightExpression();
                AssertUtils.throwMybatisPlusException("非法SQL，where条件中不能使用子查询，错误子查询SQL信息：" + subSelect.toString());
            }
        } else if (expression instanceof InExpression) {
            InExpression inExpression = (InExpression) expression;
            if (inExpression.getRightItemsList() instanceof SubSelect) {
                SubSelect subSelect = (SubSelect) inExpression.getRightItemsList();
                AssertUtils.throwMybatisPlusException("非法SQL，where条件中不能使用子查询，错误子查询SQL信息：" + subSelect.toString());
            }
        }
    }

    /**
     * 如果SQL用了 left Join，验证是否有or、not等等，并且验证是否使用了索引
     *
     * @param joins      ignore
     * @param table      ignore
     * @param connection ignore
     */
    private void validJoins(List<Join> joins, Table table, Connection connection) {
        //允许执行join，验证jion是否使用索引等等
        if (joins != null) {
            for (Join join : joins) {
                Table rightTable = (Table) join.getRightItem();
                AssertUtils.isFalse(BIG_TABLE_SET.contains(rightTable.getName()),
                    "非法SQL，禁止大表【" + rightTable.getName() + "】连表");
                Expression expression = join.getOnExpression();
                validWhere(expression, table, rightTable, connection);
            }
        }
    }

    /**
     * 检查是否使用索引
     *
     * @param table      ignore
     * @param columnName ignore
     * @param connection ignore
     */
    private void validUseIndex(Table table, String columnName, Connection connection) {
        //是否使用索引
        boolean useIndexFlag = false;

        String tableInfo = table.getName();
        //表存在的索引
        String dbName = null;
        String tableName;
        String[] tableArray = tableInfo.split("\\.");
        if (tableArray.length == 1) {
            tableName = tableArray[0];
        } else {
            dbName = tableArray[0];
            tableName = tableArray[1];
        }
        List<IndexInfo> indexInfos = getIndexInfos(dbName, tableName, connection);
        for (IndexInfo indexInfo : indexInfos) {
            if (null != columnName && columnName.equalsIgnoreCase(indexInfo.getColumnName())) {
                useIndexFlag = true;
                break;
            }
        }
        if (!useIndexFlag) {
            AssertUtils.throwMybatisPlusException("非法SQL，SQL未使用到索引, table:" + table + ", columnName:" + columnName);
        }
    }

    /**
     * 验证where条件的字段，是否有not、or等等，并且where的第一个字段，必须使用索引
     *
     * @param expression ignore
     * @param table      ignore
     * @param connection ignore
     */
    private void validWhere(Expression expression, Table table, Connection connection) {
        validWhere(expression, table, null, connection);
    }

    /**
     * 验证where条件的字段，是否有not、or等等，并且where的第一个字段，必须使用索引
     *
     * @param expression ignore
     * @param table      ignore
     * @param joinTable  ignore
     * @param connection ignore
     */
    private void validWhere(Expression expression, Table table, Table joinTable, Connection connection) {
        validExpression(expression);
        if (expression instanceof BinaryExpression) {
            //获得左边表达式
            Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
            validExpression(leftExpression);

            //如果左边表达式为Column对象，则直接获得列名
            if (leftExpression instanceof Column) {
                Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
                if (joinTable != null && rightExpression instanceof Column) {
                    if (Objects.equals(((Column) rightExpression).getTable().getName(), table.getAlias().getName())) {
                        validUseIndex(table, ((Column) rightExpression).getColumnName(), connection);
                        validUseIndex(joinTable, ((Column) leftExpression).getColumnName(), connection);
                    } else {
                        validUseIndex(joinTable, ((Column) rightExpression).getColumnName(), connection);
                        validUseIndex(table, ((Column) leftExpression).getColumnName(), connection);
                    }
                } else {
                    //获得列名
                    validUseIndex(table, ((Column) leftExpression).getColumnName(), connection);
                }
            } else if (leftExpression instanceof BinaryExpression) {
                // 如果BinaryExpression，进行迭代
                validWhere(leftExpression, table, joinTable, connection);
            }

            //获得右边表达式，并分解
            Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
            validExpression(rightExpression);
        }
    }

    /**
     * 得到表的索引信息
     *
     * @param dbName    ignore
     * @param tableName ignore
     * @param conn      ignore
     * @return ignore
     */
    public List<IndexInfo> getIndexInfos(String dbName, String tableName, Connection conn) {
        return getIndexInfos(null, dbName, tableName, conn);
    }

    /**
     * 得到表的索引信息
     *
     * @param key       ignore
     * @param dbName    ignore
     * @param tableName ignore
     * @param conn      ignore
     * @return ignore
     */
    public List<IndexInfo> getIndexInfos(String key, String dbName, String tableName, Connection conn) {
        List<IndexInfo> indexInfos = null;
        if (StringUtils.isNotBlank(key)) {
            indexInfos = INDEX_INFO_MAP.get(key);
        }
        if (indexInfos == null || indexInfos.isEmpty()) {
            ResultSet rs;
            try {
                DatabaseMetaData metadata = conn.getMetaData();
                String catalog = StringUtils.isBlank(dbName) ? conn.getCatalog() : dbName;
                String schema = StringUtils.isBlank(dbName) ? conn.getSchema() : dbName;
                rs = metadata.getIndexInfo(catalog, schema, tableName, false, true);
                indexInfos = new ArrayList<>();
                while (rs.next()) {
                    // 索引中的列序列号等于1，才有效
                    if (Objects.equals(rs.getString(8), "1")) {
                        IndexInfo indexInfo = new IndexInfo();
                        indexInfo.setDbName(rs.getString(1));
                        indexInfo.setTableName(rs.getString(3));
                        indexInfo.setColumnName(rs.getString(9));
                        indexInfos.add(indexInfo);
                    }
                }
                if (StringUtils.isNotBlank(key)) {
                    INDEX_INFO_MAP.put(key, indexInfos);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return indexInfos;
    }

    /**
     * 索引对象
     */
    @Data
    private static class IndexInfo {

        private String dbName;

        private String tableName;

        private String columnName;
    }
}

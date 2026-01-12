package com.finhub.codegen.db;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.util.StrUtil;
import com.finhub.codegen.conf.Config;
import com.finhub.codegen.util.CommentUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mickey
 * @version 1.0
 * @since 2014/10/19 11:23
 */
@Slf4j
@Data
public final class DbInfo {

    private static final String MYSQL_TYPE = "MYSQL";

    private static final String PG_TABLE_COMMENT_SQL = "SELECT t.tablename, d.description\n" +
        "FROM pg_tables t\n" +
        "         join pg_class c on t.tablename = c.relname\n" +
        "         join pg_description d on c.relfilenode = d.objoid\n" +
        "where t.tablename = '%s'";

    private static final String MYSQL_TABLE_DDL_SQL = "SHOW CREATE TABLE %s";

    private DbInfo() {
    }

    private String productName;

    private String productVersion;

    private String catalogSeparator;

    private String driverVersion;

    private List<TableInfo> tables;

    private boolean isMysql;


    public static DbInfo getInstance(Config conf) {

        DbInfo info = new DbInfo();

        try {
            Class.forName(conf.getDriverClass());
            Connection con = DriverManager.getConnection(conf.getJdbcUrl(), conf.getUserName(), conf.getUserPwd());

            DatabaseMetaData dbmd = con.getMetaData();
            info.setProductName(dbmd.getDatabaseProductName());
            info.setProductVersion(dbmd.getDatabaseProductVersion());
            info.setCatalogSeparator(dbmd.getCatalogSeparator());
            info.setDriverVersion(dbmd.getDriverVersion());
            info.setIsMysql(isMysqlConnection(con));

            List<TableInfo> tables = new ArrayList<>();
            info.setTables(tables);

            ResultSet rs = dbmd.getTables(conf.getDbName(), null, null, new String[]{"TABLE"});
            while (rs.next()) {

                String tableName = rs.getString("TABLE_NAME");
                boolean isContinue = (Func.isNotEmpty(conf.getTableSet()) && !conf.getTableSet().contains(tableName)) ||
                    (Func.isEmpty(conf.getTableSet()) && !StrUtil.equalsIgnoreCase("*", conf.getTables()) && !tableName.substring(0, 2).equals(conf.getTables().substring(0, 2)));
                if (isContinue) {
                    continue;
                }

                String comment = getCommentByTableName(tableName, con);
                List<ColumnInfo> columns = new ArrayList<>();

                TableInfo tableInfo = new TableInfo(tableName, comment);
                tableInfo.setColumns(columns);

                ResultSet columnRs = dbmd.getColumns(conf.getDbName(), null, tableName, null);
                while (columnRs.next()) {
                    columns.add(new ColumnInfo(columnRs.getString("COLUMN_NAME"), columnRs.getString("TYPE_NAME"), Func.toStr(columnRs.getString("REMARKS"), StrConstants.S_EMPTY)));
                }

                tables.add(tableInfo);
                log.info("read table : " + tableInfo.getName() + " from database success");
            }
        } catch (Exception e) {
            log.error("error exception : ", e);
        }

        return info;
    }

    /**
     * 获得某表的建表语句
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    private static String getCommentByTableName(String tableName, Connection con) throws Exception {

        boolean isMysql = isMysqlConnection(con);

        String commentSql = MYSQL_TABLE_DDL_SQL;
        if (!isMysql) {
            commentSql = PG_TABLE_COMMENT_SQL;
        }
        commentSql = String.format(commentSql, tableName);

        try (
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(commentSql)) {
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                rs.close();
                stmt.close();
                if (!isMysql) {
                    return createDDL;
                }
                return CommentUtils.parse(createDDL);
            }
        }
        return StrConstants.S_EMPTY;
    }


    private static boolean isMysqlConnection(Connection con){
        try {
            if (con.getMetaData().getDriverName().toUpperCase().contains(MYSQL_TYPE)) {
                return true;
            }
        } catch (SQLException e) {
        }

        return false;
    }

    public void setIsMysql(boolean mysql) {
        isMysql = mysql;
    }

    public boolean getIsMysql() {
        return isMysql;
    }
}

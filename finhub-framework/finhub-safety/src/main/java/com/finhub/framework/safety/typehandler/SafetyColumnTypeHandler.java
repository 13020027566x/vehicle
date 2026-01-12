package com.finhub.framework.safety.typehandler;

import com.finhub.framework.safety.jayspt.CustomStringEncryptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义安全字段类型处理器
 *
 * <p>
 * @author TaoBangren
 * @version 1.0.0
 * @since 2023/09/07 15:28
 */
@Slf4j
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SafetyColumnTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, CustomStringEncryptor.me().encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : CustomStringEncryptor.me().decrypt(value);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : CustomStringEncryptor.me().decrypt(value);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : CustomStringEncryptor.me().decrypt(value);
    }
}

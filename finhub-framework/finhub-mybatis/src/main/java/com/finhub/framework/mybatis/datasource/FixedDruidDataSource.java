package com.finhub.framework.mybatis.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class FixedDruidDataSource extends DruidDataSource {

    @Override
    public void close() {
        super.close();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                // 手动注销
                DriverManager.deregisterDriver(driver);
                log.info(String.format("Deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                log.info(String.format("Error deregistering driver: %s", driver), e);
            }
        }
    }
}

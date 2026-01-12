package com.finhub.framework.mybatis.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class ThreadLocalRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }

}

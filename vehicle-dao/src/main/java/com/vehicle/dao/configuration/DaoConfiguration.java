package com.vehicle.dao.configuration;

import com.finhub.framework.mybatis.config.DaoConfig;
import com.finhub.framework.mybatis.datasource.FixedDruidDataSource;
import com.finhub.framework.mybatis.datasource.ThreadLocalRoutingDataSource;
import com.finhub.framework.mybatis.property.JdbcProperties;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.datasource.SpringShardingDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.vehicle.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DaoConfiguration {

    final JdbcProperties jdbcProperties;

    public DaoConfiguration(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    /**
     * 以下三个 DataSource 配置，主要兼容 Spring-boot 2.3.7.RELEASE 版本：
     * 1. DataSource <-> DataSourceInitializerInvoker 循环依赖问题
     */
    @Resource(name = "ds_main_m0")
    private DataSource preDsMainM0;

    @Resource(name = "ds_main_m0_s0")
    private DataSource preDsMainM0S0;

    @Resource(name = "ds_xxx_m0")
    private DataSource preDsXxxM0;

    @Bean
    public DaoConfig daoConfig() {
        return new DaoConfig();
    }

    /**
     * 配置监控统计拦截的 filters
     *
     * @return
     */
    private List<Filter> configFilters(DaoConfig daoConfig) {
        List<Filter> filters = Lists.newArrayList();
        filters.add(new WallFilter());

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(10000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        filters.add(statFilter);

        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        slf4jLogFilter.setStatementExecutableSqlLogEnable(true);
        filters.add(slf4jLogFilter);

        return filters;
    }

    @Bean(name = "ds_main_m0", initMethod = "init", destroyMethod = "close")
    public FixedDruidDataSource ds_main_m0(DaoConfig daoConfig) {
        FixedDruidDataSource fixedDruidDataSource = new FixedDruidDataSource();
        // 基本属性 url、user、password
        fixedDruidDataSource.setUrl(jdbcProperties.getDsMainM0Url());
        fixedDruidDataSource.setUsername(jdbcProperties.getDsMainM0Username());
        fixedDruidDataSource.setPassword(jdbcProperties.getDsMainM0Password());

        // 配置初始化大小、最小、最大
        fixedDruidDataSource.setInitialSize(jdbcProperties.getDsMainM0InitialSize());
        // 连接池激活的最大数据库连接总数。设为0表示无限制
        fixedDruidDataSource.setMaxActive(jdbcProperties.getDsMainM0MaxActive());
        // 最大建立连接等待时间，单位为 ms，如果超过此时间将接到异常。设为-1表示无限制
        fixedDruidDataSource.setMaxWait(jdbcProperties.getDsMainM0MaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        fixedDruidDataSource.setTimeBetweenEvictionRunsMillis(jdbcProperties.getDsMainM0TimeBetweenEvictionRunsMillis());
        // 配置连接池中连接可空闲的时间(针对连接池中的连接对象.空闲超过这个时间则断开，直到连接池中的连接数到minIdle为止)，单位是毫秒
        fixedDruidDataSource.setMinEvictableIdleTimeMillis(jdbcProperties.getDsMainM0MinEvictableIdleTimeMillis());
        // 用来检测连接是否有效的sql，要求是一个查询语句
        fixedDruidDataSource.setValidationQuery(jdbcProperties.getDsMainM0ValidationQuery());
        // 建议配置为true，不影响性能，并且保证安全性
        fixedDruidDataSource.setTestWhileIdle(jdbcProperties.getDsMainM0TestWhileIdle());
        // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnBorrow(jdbcProperties.getDsMainM0TestOnBorrow());
        // 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnReturn(jdbcProperties.getDsMainM0TestOnReturn());
        // 打开PSCache，并且指定每个连接上PSCache的大小(Oracle或mysql5.5及以上使用)
        fixedDruidDataSource.setPoolPreparedStatements(jdbcProperties.getDsMainM0PoolPreparedStatements());
        fixedDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcProperties.getDsMainM0MaxPoolPreparedStatementPerConnectionSize());
        // 超过时间限制是否回收
        fixedDruidDataSource.setRemoveAbandoned(jdbcProperties.getDsMainM0RemoveAbandoned());
        // 超时时间：单位为秒。180 秒=3 分钟
        fixedDruidDataSource.setRemoveAbandonedTimeout(jdbcProperties.getDsMainM0RemoveAbandonedTimeout());
        // 关闭 abanded 连接时输出错误日志
        fixedDruidDataSource.setLogAbandoned(jdbcProperties.getDsMainM0LogAbandoned());

        // 配置监控统计拦截的 filters
        fixedDruidDataSource.setProxyFilters(configFilters(daoConfig));

        return fixedDruidDataSource;
    }

    @Bean(name = "ds_main_m0_s0", initMethod = "init", destroyMethod = "close")
    public FixedDruidDataSource ds_main_m0_s0(DaoConfig daoConfig) {
        FixedDruidDataSource fixedDruidDataSource = new FixedDruidDataSource();
        // 基本属性 url、user、password
        fixedDruidDataSource.setUrl(jdbcProperties.getDsMainM0S0Url());
        fixedDruidDataSource.setUsername(jdbcProperties.getDsMainM0S0Username());
        fixedDruidDataSource.setPassword(jdbcProperties.getDsMainM0S0Password());

        // 配置初始化大小、最小、最大
        fixedDruidDataSource.setInitialSize(jdbcProperties.getDsMainM0S0InitialSize());
        // 连接池激活的最大数据库连接总数。设为0表示无限制
        fixedDruidDataSource.setMaxActive(jdbcProperties.getDsMainM0S0MaxActive());
        // 最大建立连接等待时间，单位为 ms，如果超过此时间将接到异常。设为-1表示无限制
        fixedDruidDataSource.setMaxWait(jdbcProperties.getDsMainM0S0MaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        fixedDruidDataSource.setTimeBetweenEvictionRunsMillis(jdbcProperties.getDsMainM0S0TimeBetweenEvictionRunsMillis());
        // 配置连接池中连接可空闲的时间(针对连接池中的连接对象.空闲超过这个时间则断开，直到连接池中的连接数到minIdle为止)，单位是毫秒
        fixedDruidDataSource.setMinEvictableIdleTimeMillis(jdbcProperties.getDsMainM0S0MinEvictableIdleTimeMillis());
        // 用来检测连接是否有效的sql，要求是一个查询语句
        fixedDruidDataSource.setValidationQuery(jdbcProperties.getDsMainM0S0ValidationQuery());
        // 建议配置为true，不影响性能，并且保证安全性
        fixedDruidDataSource.setTestWhileIdle(jdbcProperties.getDsMainM0S0TestWhileIdle());
        // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnBorrow(jdbcProperties.getDsMainM0S0TestOnBorrow());
        // 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnReturn(jdbcProperties.getDsMainM0S0TestOnReturn());
        // 打开PSCache，并且指定每个连接上PSCache的大小(Oracle或mysql5.5及以上使用)
        fixedDruidDataSource.setPoolPreparedStatements(jdbcProperties.getDsMainM0S0PoolPreparedStatements());
        fixedDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcProperties.getDsMainM0S0MaxPoolPreparedStatementPerConnectionSize());
        // 超过时间限制是否回收
        fixedDruidDataSource.setRemoveAbandoned(jdbcProperties.getDsMainM0S0RemoveAbandoned());
        // 超时时间：单位为秒。180 秒=3 分钟
        fixedDruidDataSource.setRemoveAbandonedTimeout(jdbcProperties.getDsMainM0S0RemoveAbandonedTimeout());
        // 关闭 abanded 连接时输出错误日志
        fixedDruidDataSource.setLogAbandoned(jdbcProperties.getDsMainM0S0LogAbandoned());

        // 配置监控统计拦截的 filters
        fixedDruidDataSource.setProxyFilters(configFilters(daoConfig));

        return fixedDruidDataSource;
    }

    @Bean(name = "ds_xxx_m0", initMethod = "init", destroyMethod = "close")
    public FixedDruidDataSource ds_xxx_m0(DaoConfig daoConfig) {
        FixedDruidDataSource fixedDruidDataSource = new FixedDruidDataSource();
        // 基本属性 url、user、password
        fixedDruidDataSource.setUrl(jdbcProperties.getDsXxxM0Url());
        fixedDruidDataSource.setUsername(jdbcProperties.getDsXxxM0Username());
        fixedDruidDataSource.setPassword(jdbcProperties.getDsXxxM0Password());

        // 配置初始化大小、最小、最大
        fixedDruidDataSource.setInitialSize(jdbcProperties.getDsXxxM0InitialSize());
        // 连接池激活的最大数据库连接总数。设为0表示无限制
        fixedDruidDataSource.setMaxActive(jdbcProperties.getDsXxxM0MaxActive());
        // 最大建立连接等待时间，单位为 ms，如果超过此时间将接到异常。设为-1表示无限制
        fixedDruidDataSource.setMaxWait(jdbcProperties.getDsXxxM0MaxWait());
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        fixedDruidDataSource.setTimeBetweenEvictionRunsMillis(jdbcProperties.getDsXxxM0TimeBetweenEvictionRunsMillis());
        // 配置连接池中连接可空闲的时间(针对连接池中的连接对象.空闲超过这个时间则断开，直到连接池中的连接数到minIdle为止)，单位是毫秒
        fixedDruidDataSource.setMinEvictableIdleTimeMillis(jdbcProperties.getDsXxxM0MinEvictableIdleTimeMillis());
        // 用来检测连接是否有效的sql，要求是一个查询语句
        fixedDruidDataSource.setValidationQuery(jdbcProperties.getDsXxxM0ValidationQuery());
        // 建议配置为true，不影响性能，并且保证安全性
        fixedDruidDataSource.setTestWhileIdle(jdbcProperties.getDsXxxM0TestWhileIdle());
        // 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnBorrow(jdbcProperties.getDsXxxM0TestOnBorrow());
        // 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
        fixedDruidDataSource.setTestOnReturn(jdbcProperties.getDsXxxM0TestOnReturn());
        // 打开PSCache，并且指定每个连接上PSCache的大小(Oracle或mysql5.5及以上使用)
        fixedDruidDataSource.setPoolPreparedStatements(jdbcProperties.getDsXxxM0PoolPreparedStatements());
        fixedDruidDataSource.setMaxPoolPreparedStatementPerConnectionSize(jdbcProperties.getDsXxxM0MaxPoolPreparedStatementPerConnectionSize());
        // 超过时间限制是否回收
        fixedDruidDataSource.setRemoveAbandoned(jdbcProperties.getDsXxxM0RemoveAbandoned());
        // 超时时间：单位为秒。180 秒=3 分钟
        fixedDruidDataSource.setRemoveAbandonedTimeout(jdbcProperties.getDsXxxM0RemoveAbandonedTimeout());
        // 关闭 abanded 连接时输出错误日志
        fixedDruidDataSource.setLogAbandoned(jdbcProperties.getDsXxxM0LogAbandoned());

        // 配置监控统计拦截的 filters
        fixedDruidDataSource.setProxyFilters(configFilters(daoConfig));

        return fixedDruidDataSource;
    }

    /**
     * 数据分片 + 读写分离
     *
     * @param dsMainM0
     * @param dsMainM0S0
     * @param dsXxxM0
     * @return
     * @throws SQLException
     */
    @Bean("dataSource")
    public DataSource dataSource(@Qualifier("ds_main_m0") DataSource dsMainM0,
        @Qualifier("ds_main_m0_s0") DataSource dsMainM0S0,
        @Qualifier("ds_xxx_m0") DataSource dsXxxM0) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put("defaultDb", dsMainM0);
        dataSourceMap.put("ds_main_m0", dsMainM0);
        dataSourceMap.put("ds_main_m0_s0", dsMainM0S0);
        dataSourceMap.put("ds_xxx_m0", dsXxxM0);

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setDefaultDataSourceName("defaultDb");
        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());

        Properties properties = new Properties();
        properties.setProperty("sql.show", Boolean.TRUE.toString());

        // 获取数据源对象
        DataSource shardingDataSource = new SpringShardingDataSource(dataSourceMap, shardingRuleConfig, properties);

        ThreadLocalRoutingDataSource threadLocalRoutingDataSource = new ThreadLocalRoutingDataSource();
        threadLocalRoutingDataSource.setDefaultTargetDataSource(shardingDataSource);

        Map<Object, Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put("ds_main", shardingDataSource);
        targetDataSources.put("ds_xxx_m0", dsXxxM0);
        threadLocalRoutingDataSource.setTargetDataSources(targetDataSources);

        return threadLocalRoutingDataSource;
    }

    private List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        MasterSlaveRuleConfiguration dsMain0 = new MasterSlaveRuleConfiguration("ds_main0", "ds_main_m0", Arrays.asList("ds_main_m0_s0"));
        return Lists.newArrayList(dsMain0);
    }

    /**
     * 设置 ProxyTargetClass 为 True
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator daoAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}

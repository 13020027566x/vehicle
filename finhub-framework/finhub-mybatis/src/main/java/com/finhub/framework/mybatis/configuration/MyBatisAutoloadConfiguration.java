package com.finhub.framework.mybatis.configuration;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.SpringUtils;
import com.finhub.framework.core.aspect.AbstractAspect;
import com.finhub.framework.core.environment.EnvConfig;
import com.finhub.framework.exception.NoRollbackException;
import com.finhub.framework.mybatis.aspect.DaoCostLogAspect;
import com.finhub.framework.mybatis.config.DaoConfig;
import com.finhub.framework.mybatis.datasource.DataSourceRoutingAspectProcessor;
import com.finhub.framework.mybatis.datasource.ThreadLocalRoutingDataSource;
import com.finhub.framework.mybatis.handler.AutoFillMetaObjectHandler;
import com.finhub.framework.mybatis.handler.CustomTenantLineHandler;
import com.finhub.framework.mybatis.injector.CustomSqlInjector;
import com.finhub.framework.mybatis.interceptor.ApmMybatisLogInterceptor;
import com.finhub.framework.mybatis.interceptor.CustomAppendSQLInnerInterceptor;
import com.finhub.framework.mybatis.interceptor.CustomIllegalSQLInnerInterceptor;
import com.finhub.framework.mybatis.logging.CustomSlf4jImpl;
import com.finhub.framework.mybatis.property.JdbcProperties;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.NoRollbackRuleAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties({JdbcProperties.class})
@ConditionalOnProperty(name = "vehicle.mybatis.enabled", matchIfMissing = true)
public class MyBatisAutoloadConfiguration {

    private static final String DATA_SOURCE = "dataSource";

    private static final String VEHICLE_DATA_SOURCE = "vehicleDataSource";

    private static final String DAO_CONFIG = "daoConfig";

    private static final String TRANSACTION_MANAGER = "transactionManager";

    private static final Set<String> IGNORE_DATA_SOURCE = Sets.newHashSet(DATA_SOURCE, VEHICLE_DATA_SOURCE,
        "quartzDataSource", "taskDataSource", "fenbeitongDataSource", "saasplusDataSource", "dynamicDataSource");

    /**
     * 是否开启租户模式
     */
    @Value("${vehicle.tenant.enabled:false}")
    private Boolean tenantEnabled;

    /**
     * 是否开启sql检查
     */
    @Value("${vehicle.illegalsql.enabled:true}")
    private Boolean illegalSqlEnabled;

    /**
     * 是否输出sql日志，默认为 true
     */
    @Value("${vehicle.printSql.enabled:true}")
    private Boolean printSqlEnabled;

    /**
     * 是否输出sql apm日志，默认不输出
     */
    @Value("${vehicle.printApmSql.enabled:false}")
    private Boolean printApmSqlEnabled;

    /**
     * 切面配置：DAO 层方法执行日志
     *
     * @return
     */
    @Bean
    public DaoCostLogAspect daoCostLogAspect() {
        return new DaoCostLogAspect();
    }

    /**
     * 切面配置: Manager层多数据源切换支持,在事务之前执行
     *
     * @return
     */
    @Bean
    public DataSourceRoutingAspectProcessor dataSourceRoutingAspectProcessor() {
        return new DataSourceRoutingAspectProcessor();
    }

    /**
     * 校验 DataSource 配置是否符合规则
     *
     * @param dataSource
     */
    @DependsOn("springUtils")
    @Bean(VEHICLE_DATA_SOURCE)
    public DataSource validateDataSource(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        if (!(dataSource instanceof ThreadLocalRoutingDataSource)) {
            throw new BeanCreationNotAllowedException(VEHICLE_DATA_SOURCE,
                "Singleton bean creation not allowed, because '" + DATA_SOURCE
                    + "' type not is ThreadLocalRoutingDataSource !");
        }

        Set<Object> dataSourceNameSet = Sets.newHashSet();

        ThreadLocalRoutingDataSource threadLocalRoutingDataSource = (ThreadLocalRoutingDataSource) dataSource;
        dataSourceNameSet.addAll(threadLocalRoutingDataSource.getResolvedDataSources().keySet());
        for (DataSource ds : threadLocalRoutingDataSource.getResolvedDataSources().values()) {
            if (ds instanceof ShardingDataSource) {
                // 关闭 ShardingSphere-SQL 日志
                ShardingDataSource shardingDataSource = (ShardingDataSource) ds;
                dataSourceNameSet.addAll(shardingDataSource.getDataSourceMap().keySet());
            }
        }

        String[] dataSourceNames = SpringUtils.getBeanNamesForType(DataSource.class);
        for (String dsName : dataSourceNames) {
            if (!IGNORE_DATA_SOURCE.contains(dsName) && !dataSourceNameSet.contains(
                dsName)) {
                throw new BeanCreationNotAllowedException(VEHICLE_DATA_SOURCE,
                    "Singleton bean creation not allowed, because '" + dsName + "' not never used in bean name '"
                        + DATA_SOURCE + "'!");
            }
        }

        return dataSource;
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier(VEHICLE_DATA_SOURCE) DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template template = new org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template();
        jdbcTemplate.setFetchSize(template.getFetchSize());
        jdbcTemplate.setMaxRows(template.getMaxRows());
        if (template.getQueryTimeout() != null) {
            jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
        }
        return jdbcTemplate;
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(VEHICLE_DATA_SOURCE) DataSource dataSource,
        @Qualifier("globalConfig") GlobalConfig globalConfig,
        @Qualifier("mybatisConfiguration") MybatisConfiguration mybatisConfiguration,
        @Qualifier(DAO_CONFIG) DaoConfig daoConfig) {
        try {
            MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
            mybatisSqlSessionFactoryBean.setDataSource(dataSource);
            if (Func.isNotEmpty(daoConfig.getMapperLocations())) {
                mybatisSqlSessionFactoryBean.setMapperLocations(daoConfig.getMapperLocations());
            } else {
                mybatisSqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath:sqlMapper/*Mapper.xml"));
            }
            if (Func.isNotEmpty(daoConfig.getTypeHandlersPackage())) {
                mybatisSqlSessionFactoryBean.setTypeHandlersPackage(daoConfig.getTypeHandlersPackage());
            }
            if (Func.isNotEmpty(daoConfig.getTypeAliasesPackage())) {
                mybatisSqlSessionFactoryBean.setTypeAliasesPackage(daoConfig.getTypeAliasesPackage());
            }
            mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
            mybatisSqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
            return mybatisSqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("SqlSessionFactoryBean init error.", e);
        }
        return null;
    }

    /**
     * MyBatisPlus 配置
     *
     * @return
     */
    @Bean
    public MybatisConfiguration mybatisConfiguration(@Qualifier(DAO_CONFIG) DaoConfig daoConfig, EnvConfig envConfig) {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 这个配置使全局的映射器启用或禁用缓存
        mybatisConfiguration.setCacheEnabled(true);
        // 允许 JDBC 支持生成的键。需要适合的驱动。如果设置为 true 则这个设置强制生成的键被使用，尽管一些驱动拒绝兼容但仍然有效（比如 Derby）
        mybatisConfiguration.setUseGeneratedKeys(true);
        // 配置默认的执行器。SIMPLE 执行器没有什么特别之处。REUSE 执行器重用预处理语句。BATCH 执行器重用语句和批量更新
        mybatisConfiguration.setDefaultExecutorType(ExecutorType.REUSE);
        // 全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载
        mybatisConfiguration.setLazyLoadingEnabled(true);
        // 设置超时时间，它决定驱动等待一个数据库响应的时间
        mybatisConfiguration.setDefaultStatementTimeout(2500);
        // 是否开启自动驼峰命名规则
        mybatisConfiguration.setMapUnderscoreToCamelCase(daoConfig.isMapUnderscoreToCamelCase());
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
        mybatisConfiguration.setUseDeprecatedExecutor(false);
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        mybatisConfiguration.addInterceptor(mybatisPlusInterceptor(envConfig, daoConfig));

        // 自定义枚举转换器
        mybatisConfiguration.setDefaultEnumTypeHandler(daoConfig.getEnumTypeHandler());
        // 设置当返回数据类型为 map 时是否把值为 null 的 key 也返回
        mybatisConfiguration.setCallSettersOnNulls(daoConfig.isCallSettersOnNulls());
        if (envConfig.isNotProd() && printSqlEnabled) {
            // 配置 SQL 日志
            mybatisConfiguration.setLogImpl(CustomSlf4jImpl.class);
        }

        // APM SQL 指纹
        if (printApmSqlEnabled) {
            mybatisConfiguration.addInterceptor(new ApmMybatisLogInterceptor());
        }

        return mybatisConfiguration;
    }

    /**
     * 定义 DB 配置
     *
     * @return
     */
    @Bean
    public GlobalConfig.DbConfig globalDbConfig(@Qualifier(DAO_CONFIG) DaoConfig daoConfig) {
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.INPUT);
        if (daoConfig.isLogicDelete()) {
            dbConfig.setLogicDeleteField("isDel");
            dbConfig.setLogicDeleteValue("1");
            dbConfig.setLogicNotDeleteValue("0");
        }
        return dbConfig;
    }

    /**
     * 定义 MP 全局策略
     *
     * @return
     */
    @Bean
    public GlobalConfig globalConfig(GlobalConfig.DbConfig globalDbConfig) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        // 自动填充元属性处理器
        globalConfig.setMetaObjectHandler(new AutoFillMetaObjectHandler());
        // 设置全局 DB 配置
        globalConfig.setDbConfig(globalDbConfig);
        // 设置业务自定义业务前缀 ID 生成器
        // globalConfig.setIdentifierGenerator(new CustomBusinessIdGenerator());
        globalConfig.setSqlInjector(new CustomSqlInjector());
        return globalConfig;
    }

    /**
     * MyBatisPlus 核心插件配置
     *
     * @return
     */
    private MybatisPlusInterceptor mybatisPlusInterceptor(EnvConfig envConfig, DaoConfig daoConfig) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户 插件
        if (tenantEnabled != null && tenantEnabled) {
            TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
            tenantLineInnerInterceptor.setTenantLineHandler(new TenantLineHandler() {
                @Override
                public Expression getTenantId() {
                    return CustomTenantLineHandler.me().getTenantId();
                }

                @Override
                public String getTenantIdColumn() {
                    return CustomTenantLineHandler.me().getTenantIdColumn();
                }

                @Override
                public boolean ignoreTable(final String tableName) {
                    return CustomTenantLineHandler.me().ignoreTable(tableName);
                }
            });
            interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
        }
        // 物理分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        if (illegalSqlEnabled == null || illegalSqlEnabled) {
            // SQL 性能规范
            interceptor.addInnerInterceptor(new CustomIllegalSQLInnerInterceptor());
        }

        // SQL 追加 TraceId 注释 & Limit（须放到最后）
        interceptor.addInnerInterceptor(new CustomAppendSQLInnerInterceptor(daoConfig.getLimitCount()));
        return interceptor;
    }

    /**
     * Druid 监控配置
     *
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * Druid 监控配置 切入点
     *
     * @param druidStatInterceptor
     * @return
     */
    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor(DruidStatInterceptor druidStatInterceptor) {
        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor.setPatterns(AbstractAspect.DAO_ASPECT_POINTCUT,
            AbstractAspect.MANAGER_ASPECT_POINTCUT, AbstractAspect.SERVICE_ASPECT_POINTCUT);
        regexpMethodPointcutAdvisor.setAdvice(druidStatInterceptor);
        return regexpMethodPointcutAdvisor;
    }

    private TransactionAttributeSource transactionAttributeSource(DaoConfig daoConfig) {
        // 只读事务，不做更新操作
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        // 当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务
        List<RollbackRuleAttribute> rollbackRuleAttributes = Lists.newArrayList();
        rollbackRuleAttributes.add(new RollbackRuleAttribute(Exception.class));
        rollbackRuleAttributes.add(new NoRollbackRuleAttribute(NoRollbackException.class));

        RuleBasedTransactionAttribute requiredTx =
            new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRuleAttributes);
        requiredTx.setTimeout(daoConfig.getTransactionTimeOut());

        Map<String, TransactionAttribute> transactionAttributeMap = Maps.newHashMap();
        transactionAttributeMap.put("*remove*", requiredTx);
        transactionAttributeMap.put("*save*", requiredTx);
        transactionAttributeMap.put("*modify*", requiredTx);
        transactionAttributeMap.put("*create*", requiredTx);
        transactionAttributeMap.put("*delete*", requiredTx);
        transactionAttributeMap.put("*update*", requiredTx);
        transactionAttributeMap.put("*add*", requiredTx);

        transactionAttributeMap.put("*find*", readOnlyTx);
        transactionAttributeMap.put("*get*", readOnlyTx);
        transactionAttributeMap.put("*page*", readOnlyTx);
        transactionAttributeMap.put("*count*", readOnlyTx);
        transactionAttributeMap.put("*select*", readOnlyTx);
        transactionAttributeMap.put("*query*", readOnlyTx);
        transactionAttributeMap.put("*list*", readOnlyTx);
        transactionAttributeMap.put("*show*", readOnlyTx);

        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource =
            new NameMatchTransactionAttributeSource();
        nameMatchTransactionAttributeSource.setNameMap(transactionAttributeMap);
        return nameMatchTransactionAttributeSource;
    }

    /**
     * 事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean("transactionManager")
    public TransactionManager dataSourceTransactionManager(@Qualifier(VEHICLE_DATA_SOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 事务切面
     *
     * @param transactionManager
     * @param daoConfig
     * @return
     */
    @Bean("useTxAdvice")
    public TransactionInterceptor getTransactionInterceptor(
        @Qualifier(TRANSACTION_MANAGER) TransactionManager transactionManager,
        @Qualifier(DAO_CONFIG) DaoConfig daoConfig) {
        NameMatchTransactionAttributeSource transactionAttributeSource =
            (NameMatchTransactionAttributeSource) transactionAttributeSource(daoConfig);
        if (Func.isNotEmpty(daoConfig.getNameMatchTransactionMap())) {
            transactionAttributeSource.setNameMap(daoConfig.getNameMatchTransactionMap());
        }
        return new TransactionInterceptor(transactionManager, transactionAttributeSource);
    }

    /**
     * 切面拦截规则 参数会自动从容器中注入
     *
     * @param useTxAdvice
     * @return
     */
    @Bean
    public AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor(TransactionInterceptor useTxAdvice) {
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(useTxAdvice);
        pointcutAdvisor.setExpression(AbstractAspect.MANAGER_ASPECT_POINTCUT);
        return pointcutAdvisor;
    }
}

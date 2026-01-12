package com.finhub.framework.mybatis.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {

    public static JdbcProperties me() {
        try {
            return SpringUtil.getBean(JdbcProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new JdbcProperties();
        }
    }

    /**
     * 基本属性 url、user、password
     */
    private String dsMainM0Driverclass;
    private String dsMainM0Url;
    private String dsMainM0Username;
    private String dsMainM0Password;

    /**
     * 配置初始化大小、最小、最大
     */
    private Integer dsMainM0InitialSize = 5;
    /**
     * 连接池激活的最大数据库连接总数。设为0表示无限制
     */
    private Integer dsMainM0MaxActive = 20;
    /**
     * 最大建立连接等待时间，单位为 ms，如果超过此时间将接到异常。设为-1表示无限制
     */
    private Integer dsMainM0MaxWait = 60000;
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒，建议设置为1分钟
     */
    private Integer dsMainM0TimeBetweenEvictionRunsMillis = 60000;
    /**
     * 配置连接池中连接可空闲的时间(针对连接池中的连接对象.空闲超过这个时间则断开，直到连接池中的连接数到minIdle为止)，单位是毫秒，建议设置为5分钟
     */
    private Integer dsMainM0MinEvictableIdleTimeMillis = 300000;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用
     */
    private String dsMainM0ValidationQuery = "SELECT 'x' FROM DUAL";
    /**
     * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
     */
    private Boolean dsMainM0TestWhileIdle = true;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
     */
    private Boolean dsMainM0TestOnBorrow = false;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能，建议仅在DEV使用
     */
    private Boolean dsMainM0TestOnReturn = false;
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小(Oracle或mysql5.5及以上使用)
     */
    private Boolean dsMainM0PoolPreparedStatements = true;
    private Integer dsMainM0MaxPoolPreparedStatementPerConnectionSize = 20;
    /**
     * 超过时间限制是否回收
     */
    private Boolean dsMainM0RemoveAbandoned = false;
    /**
     * 超时时间：单位为秒。180 秒=3 分钟
     */
    private Integer dsMainM0RemoveAbandonedTimeout = 180;
    /**
     * 关闭abanded连接时输出错误日志
     */
    private Boolean dsMainM0LogAbandoned = true;

    private String dsMainM0S0Driverclass;
    private String dsMainM0S0Url;
    private String dsMainM0S0Username;
    private String dsMainM0S0Password;
    private Integer dsMainM0S0InitialSize = 5;
    private Integer dsMainM0S0MaxActive = 20;
    private Integer dsMainM0S0MaxWait = 60000;
    private Integer dsMainM0S0TimeBetweenEvictionRunsMillis = 60000;
    private Integer dsMainM0S0MinEvictableIdleTimeMillis = 300000;
    private String dsMainM0S0ValidationQuery = "SELECT 'x' FROM DUAL";
    private Boolean dsMainM0S0TestWhileIdle = true;
    private Boolean dsMainM0S0TestOnBorrow = false;
    private Boolean dsMainM0S0TestOnReturn = false;
    private Boolean dsMainM0S0PoolPreparedStatements = true;
    private Integer dsMainM0S0MaxPoolPreparedStatementPerConnectionSize = 20;
    private Boolean dsMainM0S0RemoveAbandoned = false;
    private Integer dsMainM0S0RemoveAbandonedTimeout = 180;
    private Boolean dsMainM0S0LogAbandoned = true;

    private String dsXxxM0Driverclass;
    private String dsXxxM0Url;
    private String dsXxxM0Username;
    private String dsXxxM0Password;
    private Integer dsXxxM0InitialSize = 5;
    private Integer dsXxxM0MaxActive = 20;
    private Integer dsXxxM0MaxWait = 60000;
    private Integer dsXxxM0TimeBetweenEvictionRunsMillis = 60000;
    private Integer dsXxxM0MinEvictableIdleTimeMillis = 300000;
    private String dsXxxM0ValidationQuery = "SELECT 'x' FROM DUAL";
    private Boolean dsXxxM0TestWhileIdle = true;
    private Boolean dsXxxM0TestOnBorrow = false;
    private Boolean dsXxxM0TestOnReturn = false;
    private Boolean dsXxxM0PoolPreparedStatements = true;
    private Integer dsXxxM0MaxPoolPreparedStatementPerConnectionSize = 20;
    private Boolean dsXxxM0RemoveAbandoned = false;
    private Integer dsXxxM0RemoveAbandonedTimeout = 180;
    private Boolean dsXxxM0LogAbandoned = true;
}

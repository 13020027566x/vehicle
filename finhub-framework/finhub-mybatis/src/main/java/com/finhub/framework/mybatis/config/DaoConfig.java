package com.finhub.framework.mybatis.config;

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.core.io.Resource;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.util.Map;

/**
 * Dao 配置（主要为程序执行行为的配置，或者Nacos较为复杂的配置）
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class DaoConfig {

    public static DaoConfig me() {
        try {
            return SpringUtil.getBean(DaoConfig.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new DaoConfig();
        }
    }

    /**
     * 格式必须为：1000{} 1001{} 等
     */
    private String idPattern;

    /**
     * 自定义事务 NameMatch
     */
    private Map<String, TransactionAttribute> nameMatchTransactionMap = Maps.newHashMap();

    /**
     * 自定义 mapperLocations
     */
    private Resource[] mapperLocations;

    /**
     * 自定义类型处理器
     */
    private String typeHandlersPackage;

    /**
     * 是否逻辑删除
     */
    private boolean logicDelete = true;

    /**
     * 是否开启自动驼峰命名规则
     */
    private boolean mapUnderscoreToCamelCase = true;

    /**
     * 自定义枚举转换器
     */
    private Class<? extends TypeHandler> enumTypeHandler;

    /**
     * 是否当返回数据类型为map时把值为null的key也返回
     */
    private boolean callSettersOnNulls = false;

    /**
     * SQL Limit Count 设置，默认为 49999
     */
    private Integer limitCount = 49999;

    /**
     * 类别称，注册后可在mapper.xml里只写类名（不带包名）
     */
    private String typeAliasesPackage;

    /**
     * 事务超时时间，基地车默认事务超时时间为5秒 , -1 表示事务一致等待，没有超时时间
     */
    private int transactionTimeOut = 5;

}

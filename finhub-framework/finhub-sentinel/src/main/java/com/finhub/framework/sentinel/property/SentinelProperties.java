package com.finhub.framework.sentinel.property;

import com.finhub.framework.logback.util.EnvUtils;
import com.finhub.framework.nacos.property.AutoRefreshProperties;
import com.finhub.framework.sentinel.constants.SentinelConstants;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.EventObserverRegistry;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

/**
 * Sentinel 配置类
 *
 * @author zhenxing_liang
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.sentinel")
public class SentinelProperties {

    public static SentinelProperties me() {
        try {
            return SpringUtil.getBean(SentinelProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new SentinelProperties();
        }
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active:tx-dev}")
    private String profile;

    @PostConstruct
    public void init() {
        // 设置 Dubbo 资源前缀
        System.setProperty(SentinelConstants.SENTINEL_DUBBO_RESOURCE_PREFIX_KEY,
            profile.concat(SentinelConstants.RESOURCE_NAME_CONCAT_CHAR));

        // Sentinel 默认观察者（监听状态变更）
        EventObserverRegistry.getInstance().addStateChangeObserver(SentinelConstants.DEFAULT_SENTINEL_OBSERVER_NAME,
            (prevState, newState, rule, snapshotValue) -> {
                if (AutoRefreshProperties.me().isOpenSentinelObserverLog()) {
                    log.info("prevState: {}, newState: {}, rule: {}, snapshotValue: {}", prevState, newState,
                        JSONUtil.toJsonStr(rule), snapshotValue);
                }
            });

        // 添加 Sentinel Nacos 变更监听器
        addSentinelNacosChangeListener();
    }

    /**
     * 添加 Sentinel Nacos 变更监听器
     */
    private void addSentinelNacosChangeListener() {
        try {
            Properties nacosProperties = buildNacosProperties();

            // flow rules
            ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacosProperties,
                applicationName, applicationName.concat(SentinelConstants.FLOW_RULE),
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

            // degrade rules
            ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource =
                new NacosDataSource<>(nacosProperties,
                    applicationName, applicationName.concat(SentinelConstants.DEGRADE_RULE),
                    source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                    }));
            DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());

            // system rules
            ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<>(
                nacosProperties,
                applicationName, applicationName.concat(SentinelConstants.SYSTEM_RULE),
                source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
                }));
            SystemRuleManager.register2Property(systemRuleDataSource.getProperty());

            // param rules
            ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource =
                new NacosDataSource<>(nacosProperties,
                    applicationName, applicationName.concat(SentinelConstants.PARAM_RULE),
                    source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                    }));
            ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());

            // authority rules
            ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource =
                new NacosDataSource<>(nacosProperties,
                    applicationName, applicationName.concat(SentinelConstants.AUTHORITY_RULE),
                    source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
                    }));
            AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());

            log.info("Sentinel Nacos 配置监听器初始化成功");
        } catch (Exception e) {
            log.error("Sentinel Nacos 配置监听器初始化失败，errorMsg: {}", e.getMessage(), e);
        }
    }

    private Properties buildNacosProperties() {
        String namespace = SentinelConstants.NACOS_NAMESPACE.concat(profile);
        if (EnvUtils.isProd(profile)) {
            namespace = SentinelConstants.NACOS_NAMESPACE.concat(SentinelConstants.PROFILE_ALI_PROD);
        }

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, SentinelConstants.NACOS_SERVER_ADDR);
        properties.setProperty(PropertyKeyConst.NAMESPACE, namespace);
        properties.setProperty(PropertyKeyConst.USERNAME, SentinelConstants.NACOS_USERNAME);
        properties.setProperty(PropertyKeyConst.PASSWORD, SentinelConstants.NACOS_PASSWORD);
        return properties;
    }
}

package com.finhub.framework.logback.property;

import com.finhub.framework.logback.util.EnvUtils;

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Set;

import static com.finhub.framework.logback.constant.LogConstants.HEADER_TOKEN_KEY;

/**
 * 日志配置
 *
 * @author Mickey
 * @version 1.0
 * @since 2022/09/08 10:45
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.log")
public class LogProperties {

    private static final Set<String> DEFAULT_EXCLUDE_HEADER_KEYS = Sets.newHashSet(HEADER_TOKEN_KEY);

    public static LogProperties me() {
        try {
            return SpringUtil.getBean(LogProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new LogProperties();
        }
    }

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active:tx-dev}")
    private String profile;

    /**
     * 日志内容最大输出长度
     */
    private int serverMaxLength = 10000;

    /**
     * 日志内容最大输出长度
     */
    private int skyWalkingMaxLength = 500;

    /**
     * Log 过长是否按 MaxLength 截取，默认为 true
     */
    private boolean truncatedEnable = true;

    /**
     * 指定包路径参数 日志输出json格式
     */
    private List<String> packagePrefixes;

    /**
     * requestHeaderKeys 默认输出所有key，指定后只输出指定key
     */
    private List<String> requestHeaderKeys;

    /**
     * exclude-request-header-keys 排除header中的元素
     */
    private Set<String> excludeRequestHeaderKeys = DEFAULT_EXCLUDE_HEADER_KEYS;

    /**
     * requestAttributionKeys，与header相反 默认不输出内容，需要指定要输出的key
     */
    private List<String> requestAttributionKeys;

    /**
     * responseHeaderKeys 默认输出所有key，指定后只输出指定key
     */
    private List<String> responseHeaderKeys;

    /**
     * exclude-response-header-keys 排除header中的元素
     */
    private Set<String> excludeResponseHeaderKeys = DEFAULT_EXCLUDE_HEADER_KEYS;

    /**
     * 自定义 MDC 字段配置，根据自身项目进行日志拆分，显示成单独的字段
     */
    private List<String> customMdcFiledList;

    /**
     * 日志输出显示行数 默认false，打印行数影响效率
     */
    private boolean loggerClassLine = false;

    /**
     * 远程日志project
     */
    private String project;

    /**
     * 日志服务器认证ID
     */
    private String accessKeyId;

    /**
     * 日志服务器认证密钥
     */
    private String accessKeySecret;

    /**
     * 日志服务器访问域名
     */
    private String endpoint;

    /**
     * 测试使用，默认关闭
     */
    private boolean test = false;

    /**
     * 是否开启 controller log 日志，默认为 true
     */
    private boolean controllerLogEnable = true;

    /**
     * 是否开启 dubbo provider log 日志，默认为 true
     */
    private boolean dubboProviderLogEnable = true;

    /**
     * 是否开启 dubbo consumer log 日志，默认为 true
     */
    private boolean dubboConsumerLogEnable = true;

    /**
     * 是否开启 datasource routing aspect log 日志，默认为 false
     */
    private boolean datasourceRoutingAspectLogEnable = false;

    /**
     * 判断是否为生产环境
     *
     * @return
     */
    public boolean isRemote() {
        return isTest() || EnvUtils.isProd(profile) || EnvUtils.isUat(profile);
    }

    public Set<String> getExcludeRequestHeaderKeys() {
        if (excludeRequestHeaderKeys == null) {
            return DEFAULT_EXCLUDE_HEADER_KEYS;
        }
        if (!excludeRequestHeaderKeys.contains(HEADER_TOKEN_KEY)) {
            excludeRequestHeaderKeys.add(HEADER_TOKEN_KEY);
        }
        return excludeRequestHeaderKeys;
    }

    public Set<String> getExcludeResponseHeaderKeys() {
        if (excludeResponseHeaderKeys == null) {
            return DEFAULT_EXCLUDE_HEADER_KEYS;
        }
        if (!excludeResponseHeaderKeys.contains(HEADER_TOKEN_KEY)) {
            excludeResponseHeaderKeys.add(HEADER_TOKEN_KEY);
        }
        return excludeResponseHeaderKeys;
    }
}

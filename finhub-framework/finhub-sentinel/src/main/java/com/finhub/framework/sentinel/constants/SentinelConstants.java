package com.finhub.framework.sentinel.constants;

import lombok.experimental.UtilityClass;

/**
 * Sentinel 常量类
 *
 * @author zhenxing_liang
 */
@UtilityClass
public class SentinelConstants {

    public static final String PROFILE_ALI_PROD = "ali-prod";

    public static final String RESOURCE_NAME_CONCAT_CHAR = ":";

    public static final String NACOS_SERVER_ADDR = "mse-64e8f582-nacos-ans.mse.aliyuncs.com:8848";

    public static final String NACOS_NAMESPACE = "sentinel-";

    public static final String NACOS_USERNAME = "rd";

    public static final String NACOS_PASSWORD = "fenbeitong.com";

    public static final String FLOW_RULE = "_flow_rules";

    public static final String DEGRADE_RULE = "_degrade_rules";

    public static final String SYSTEM_RULE = "_system_rules";

    public static final String PARAM_RULE = "_param_rules";

    public static final String AUTHORITY_RULE = "_authority_rules";

    public static final String GATEWAY_API_RULE = "_gateway_api_rules";

    public static final String GATEWAY_FLOW_RULE = "_gateway_flow_rules";

    public static final String DEFAULT_SENTINEL_OBSERVER_NAME = "default_sentinel_observer";

    public static final String SENTINEL_DUBBO_RESOURCE_PREFIX_KEY = "csp.sentinel.dubbo.resource.provider.prefix";

}

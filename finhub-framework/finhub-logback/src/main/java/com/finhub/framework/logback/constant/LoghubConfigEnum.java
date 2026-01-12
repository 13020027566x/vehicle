package com.finhub.framework.logback.constant;

import static cn.hutool.core.text.CharSequenceUtil.containsIgnoreCase;
import static cn.hutool.core.text.CharSequenceUtil.isBlank;

/**
 * Loghub 配置枚举
 *
 * @author liuwei
 * @version 1.0
 * @since 2021/12/14 21:34
 */
public enum LoghubConfigEnum {

    /**
     * 生产日志
     */
    PROD("pro", "ali-prod-vehicle-log", "LTAI5tMcovXe15Sq9jpYubto", "vipVpFMPv7Dp9LLjfjYuWM5jXNn8Fb",
        "cn-beijing-intranet.log.aliyuncs.com"),

    UAT("uat", "ali-uat-vehicle-log", "LTAI5tMcovXe15Sq9jpYubto", "vipVpFMPv7Dp9LLjfjYuWM5jXNn8Fb",
        "cn-beijing-intranet.log.aliyuncs.com"),

    FAT("fat", "ali-fat-vehicle-log", "LTAI5tMcovXe15Sq9jpYubto", "vipVpFMPv7Dp9LLjfjYuWM5jXNn8Fb",
        "cn-beijing.log.aliyuncs.com"),

    DEV("dev", "ali-dev-vehicle-log", "LTAI5tMcovXe15Sq9jpYubto", "vipVpFMPv7Dp9LLjfjYuWM5jXNn8Fb",
        "cn-beijing.log.aliyuncs.com"),

    TEST("", "fbt-test-log", "LTAI5tMcovXe15Sq9jpYubto", "vipVpFMPv7Dp9LLjfjYuWM5jXNn8Fb",
        "cn-beijing.log.aliyuncs.com", true);

    LoghubConfigEnum(String env, String project, String accessKeyId, String accessKeySecret, String endpoint) {
        this(env, project, accessKeyId, accessKeySecret, endpoint, false);
    }

    LoghubConfigEnum(String env, String project, String accessKeyId, String accessKeySecret, String endpoint,
        boolean test) {
        this.test = test;
        this.env = env;
        this.project = project;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.endpoint = endpoint;
    }

    private boolean test;

    private String env;

    private String project;

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;

    public String getEnv() {
        return env;
    }

    public String getProject() {
        return project;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public boolean isTest() {
        return test;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public static LoghubConfigEnum getRemoteConfigEnum(String profile, boolean test) {

        if (test) {
            return TEST;
        }

        if (isBlank(profile)) {
            return null;
        }

        for (LoghubConfigEnum configEnum : LoghubConfigEnum.values()) {
            if (containsIgnoreCase(profile, configEnum.getEnv())) {
                return configEnum;
            }
        }

        return null;
    }
}

package com.finhub.framework.web.constant;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/30 19:07
 */
@Slf4j
@UtilityClass
public class WebConstants {

    public static final String ANON = "anon";

    public static final String USER_KICKOUT = "user, kickout";

    public static final String SESSION_IN_HEADER = "sid";

    public static final String SESSION_IN_COOKIE = "sid";

    public static final String DEFAULT_COOKIE_NAME = "cc";

    public static final String DEFAULT_CACHE_NAME = "cache-captcha";

    /**
     * Cookie超时默认为session会话状态
     */
    public static final int DEFAULT_MAX_AGE = -1;

    public static final String REDIRECT_LOGIN = "redirect:/#/login";

    public static final String REDIRECT_HOME = "redirect:/#/home";

    public static final String SERVER_PORT_KEY = "server.port";

    public static final String APPLICATION_FORM_URLENCODED_UTF8_VALUE =
        "application/x-www-form-urlencoded;charset=UTF-8";

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    public static final List<String> EXCLUDE_PATH_LIST =
        Lists.newArrayList("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html", "/static/**", "/",
            "/captcha*", "/commons/**", "/hook", "/login", "/actuator/**", "/dist/**");

    public static final List<String> INCLUDE_PATH_LIST =
        Lists.newArrayList("/doc/**", "/monitor/**", "/druid/**", "/**");

    public static final String HTTP_PROTOCOL = "http";

    public static final String HTTPS_PROTOCOL = "https";

    public static final String RESULT_KEY = "result";

    public static final String KICKOUT_CACHE_NAME = "shiro-kickout-session";
}

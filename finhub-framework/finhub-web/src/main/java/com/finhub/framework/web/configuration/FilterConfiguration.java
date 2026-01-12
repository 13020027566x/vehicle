package com.finhub.framework.web.configuration;

import com.alibaba.druid.support.http.WebStatFilter;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.web.filter.MDCLogFilter;
import com.finhub.framework.web.safe.HttpServletRequestSafeFilter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import com.thetransactioncompany.cors.CORSFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;

import jakarta.servlet.DispatcherType;

import static com.finhub.framework.logback.constant.LogConstants.DEFAULT_PORT;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_DEPT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.WEB_SERVER_PORT_PROPERTY;

@Slf4j
@Configuration
@ConditionalOnWebApplication
public class FilterConfiguration {

    /**
     * request 编码, 放在所有 filter 之前
     *
     * @return
     */
    private CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new OrderedCharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CharsetUtils.DEFAULT_ENCODE);
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> registerCharacterEncodingFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(characterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CharacterEncodingFilter");
        registration.setOrder(FilterOrders.CHARACTER_ENCODING_FILTER);
        return registration;
    }

    /**
     * 解决 xss & sql 漏洞
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<HttpServletRequestSafeFilter> registerHttpRequestSafeFilter() {
        FilterRegistrationBean<HttpServletRequestSafeFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpServletRequestSafeFilter());
        registration.addUrlPatterns("/*");
        registration.setName("HttpServletRequestSafeFilter");
        registration.setOrder(FilterOrders.HTTP_REQUEST_SAFE_FILTER);
        registration.setDispatcherTypes(DispatcherType.REQUEST);

        return registration;
    }

    /**
     * 解决跨域问题 Filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<CORSFilter> registerCORSFilter() {
        FilterRegistrationBean<CORSFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CORSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CORSFilter");
        registration.setOrder(FilterOrders.CORS_FILTER);

        registration.addInitParameter("cors.allowOrigin", "*");
        registration.addInitParameter("cors.supportedMethods", "GET, POST, HEAD, PUT, DELETE, OPTIONS, PATCH");
        registration.addInitParameter("cors.supportedHeaders",
            "Accept, Origin, X-Requested-With, Content-Type, Last-Modified, X-Auth-Token, Cookie, Cache-Control");
        registration.addInitParameter("cors.exposedHeaders", "Set-Cookie, oauthstatus");
        registration.addInitParameter("cors.supportsCredentials", "true");
        return registration;
    }

    /**
     * 连接池 启用 Web 监控统计功能
     *
     *  YML 配置
     *  # Druid特定配置
     *     druid:
     *       # 启用Web统计过滤器
     *       web-stat-filter:
     *         enabled: true
     *         url-pattern: "/*"
     *         exclusions: "*.js,*mp3,*.swf,*.xls,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"
     *         session-stat-enable: true
     *         session-stat-max-count: 1000
     *         profile-enable: true  # 可选，启用监控统计
     *
     *       # 其他Druid配置（可选）
     *       stat-view-servlet:
     *         enabled: true  # 启用监控页面
     *         url-pattern: /druid/*
     *         login-username: admin  # 监控页面登录用户名
     *         login-password: admin  # 监控页面登录密码
     *
     *       filter:
     *         stat:
     *           enabled: true  # 启用SQL统计
     *           log-slow-sql: true
     *           slow-sql-millis: 2000
     *
     */

    @Bean
    public FilterRegistrationBean<MDCInsertingServletFilter> registerMDCInsertingServletFilter() {
        FilterRegistrationBean<MDCInsertingServletFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MDCInsertingServletFilter());
        registration.addUrlPatterns("/*");
        registration.setName("mdcInsertingServletFilter");
        registration.setOrder(FilterOrders.MDC_INSERTING_SERVLET_FILTER);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<MDCLogFilter> mdcLogFilter(Environment environment) {
        MDCLogFilter mdcLogFilter = new MDCLogFilter();
        mdcLogFilter.setSpringApplicationDept(environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY));
        mdcLogFilter.setSpringApplicationName(environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY));
        mdcLogFilter.setServerPort(environment.getProperty(WEB_SERVER_PORT_PROPERTY, DEFAULT_PORT));

        FilterRegistrationBean<MDCLogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(mdcLogFilter);
        registration.addUrlPatterns("/*");
        registration.setName("mdcLogFilter");
        registration.setOrder(FilterOrders.MDC_LOG_FILTER);
        return registration;
    }

    class FilterOrders {
        public static final int MDC_LOG_FILTER = Integer.MIN_VALUE + 10;
        public static final int MDC_INSERTING_SERVLET_FILTER = Integer.MIN_VALUE + 11;
        public static final int CHARACTER_ENCODING_FILTER = Integer.MIN_VALUE + 20;
        public static final int HTTP_REQUEST_SAFE_FILTER = Integer.MIN_VALUE + 21;
        public static final int CORS_FILTER = Integer.MIN_VALUE + 22;
        public static final int WEB_STAT_FILTER = Integer.MIN_VALUE + 23;
    }
}



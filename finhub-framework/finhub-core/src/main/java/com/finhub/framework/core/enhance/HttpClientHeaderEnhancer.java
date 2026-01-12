package com.finhub.framework.core.enhance;

import com.finhub.framework.i18n.property.I18nProperties;
import com.finhub.framework.logback.constant.LogConstants;
import com.finhub.framework.logback.util.LogUtils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.micrometer.core.ipc.http.HttpSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.apache.http.message.BasicHeader;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_DEPT_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_APPLICATION_NAME_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static org.apache.http.HttpHeaders.ACCEPT_LANGUAGE;

/**
 * HttpClient 相关类增强器
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2022/09/18 10:05
 */
@Slf4j
public final class HttpClientHeaderEnhancer {

    /**
     * 增强 CloseableHttpClient & CloseableAsyncHttpClient 方法：
     * 1. 添加 from headers 信息
     */
    public static void addFromHeadersToHttpClient(final HttpRequest request) {
        try {
            Environment environment = SpringUtil.getBean(Environment.class);

            String fromIp = NetUtil.getLocalhostStr();
            if (isNotBlank(fromIp)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_FROM_IP_KEY, fromIp));
            }

            String fromApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);
            if (isNotBlank(fromApp)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_FROM_APP_KEY, fromApp));
            }

            String fromDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);
            if (isNotBlank(fromDept)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_FROM_DEPT_KEY, fromDept));
            }

            String fromVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
            if (isNotBlank(fromVehicle)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_FROM_VEHICLE_KEY, fromVehicle));
            }

            String fromLocale = LocaleContextHolder.getLocale().toLanguageTag();
            if (isNotBlank(fromLocale)) {
                request.addHeader(new BasicHeader(I18nProperties.me().getInterceptorName(), fromLocale));
                request.addHeader(new BasicHeader(ACCEPT_LANGUAGE, fromLocale));
            }

            String traceId = LogUtils.getTraceId();
            if (isNotBlank(traceId)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_TRACE_ID_KEY, traceId));
            }

            String requestId = LogUtils.getRequestId();
            if (isNotBlank(requestId)) {
                request.addHeader(new BasicHeader(LogConstants.HEADER_REQUEST_ID_KEY, requestId));
            }
        } catch (Exception e) {
            log.warn("Enhance CloseableHttpClient/CloseableAsyncHttpClient add from headers Method fail.", e);
        }
    }

    /**
     * 增强 Hutool HttpRequest 信息：
     * 1. 添加 from headers 信息
     */
    public static void addFromHeadersToHutoolHttpRequest(final cn.hutool.http.HttpRequest httpRequest) {
        try {
            Environment environment = SpringUtil.getBean(Environment.class);

            String fromIp = NetUtil.getLocalhostStr();
            if (isNotBlank(fromIp)) {
                httpRequest.getConnection().header(LogConstants.HEADER_FROM_IP_KEY, fromIp, true);
            }

            String fromApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);
            if (isNotBlank(fromApp)) {
                httpRequest.getConnection().header(LogConstants.HEADER_FROM_APP_KEY, fromApp, true);
            }

            String fromDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);
            if (isNotBlank(fromDept)) {
                httpRequest.getConnection().header(LogConstants.HEADER_FROM_DEPT_KEY, fromDept, true);
            }

            String fromVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
            if (isNotBlank(fromVehicle)) {
                httpRequest.getConnection().header(LogConstants.HEADER_FROM_VEHICLE_KEY, fromVehicle, true);
            }

            String fromLocale = LocaleContextHolder.getLocale().toLanguageTag();
            if (isNotBlank(fromLocale)) {
                httpRequest.getConnection().header(I18nProperties.me().getInterceptorName(), fromLocale, true);
                httpRequest.getConnection().header(ACCEPT_LANGUAGE, fromLocale, true);
            }

            String traceId = LogUtils.getTraceId();
            if (isNotBlank(traceId)) {
                httpRequest.getConnection().header(LogConstants.HEADER_TRACE_ID_KEY, traceId, true);
            }

            String requestId = LogUtils.getRequestId();
            if (isNotBlank(requestId)) {
                httpRequest.getConnection().header(LogConstants.HEADER_REQUEST_ID_KEY, requestId, true);
            }
        } catch (Exception e) {
            log.warn("Enhance Hutool HttpRequest add from headers Method fail.", e);
        }
    }

    /**
     * 增强 OkHttp Request 信息：
     * 1. 添加 from headers 信息
     */
    /**
     * 增强 OkHttp Request 信息：
     * 1. 添加 from headers 信息
     */
    public static okhttp3.Request addFromHeadersToOkHttpRequest(final okhttp3.Request request) {
        try {
            // 修改这里：使用 okhttp3.Request.Builder 而不是 HttpSender.Request.Builder
            okhttp3.Request.Builder result = request.newBuilder();
            Environment environment = SpringUtil.getBean(Environment.class);

            String fromIp = NetUtil.getLocalhostStr();
            if (isNotBlank(fromIp)) {
                result.header(LogConstants.HEADER_FROM_IP_KEY, fromIp);
            }

            String fromApp = environment.getProperty(SPRING_APPLICATION_NAME_PROPERTY);
            if (isNotBlank(fromApp)) {
                result.header(LogConstants.HEADER_FROM_APP_KEY, fromApp);
            }

            String fromDept = environment.getProperty(SPRING_APPLICATION_DEPT_PROPERTY);
            if (isNotBlank(fromDept)) {
                result.header(LogConstants.HEADER_FROM_DEPT_KEY, fromDept);
            }

            String fromVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
            if (isNotBlank(fromVehicle)) {
                result.header(LogConstants.HEADER_FROM_VEHICLE_KEY, fromVehicle);
            }

            String fromLocale = LocaleContextHolder.getLocale().toLanguageTag();
            if (isNotBlank(fromLocale)) {
                result.header(I18nProperties.me().getInterceptorName(), fromLocale);
                result.header(ACCEPT_LANGUAGE, fromLocale);
            }

            String traceId = LogUtils.getTraceId();
            if (isNotBlank(traceId)) {
                result.header(LogConstants.HEADER_TRACE_ID_KEY, traceId);
            }

            String requestId = LogUtils.getRequestId();
            if (isNotBlank(requestId)) {
                result.header(LogConstants.HEADER_REQUEST_ID_KEY, requestId);
            }
            return result.build();
        } catch (Exception e) {
            log.warn("Enhance okhttp3.Request add from headers Method fail.", e);
        }
        return request;
    }
}

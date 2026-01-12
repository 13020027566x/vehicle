package com.finhub.framework.web.filter;

import com.finhub.framework.i18n.constant.LocaleTypeEnum;
import com.finhub.framework.i18n.property.I18nProperties;
import com.finhub.framework.i18n.resolver.CustomHeaderLocaleResolver;
import com.finhub.framework.logback.LogMdcHolder;
import com.finhub.framework.logback.util.LogUtils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;
import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_APP_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_DEPT_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_IP_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_FROM_VEHICLE_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_REQUEST_ID_KEY;
import static com.finhub.framework.logback.constant.LogConstants.HEADER_TRACE_ID_KEY;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_CAMEL_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_PORT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_FROM_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_REQUEST_ID;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_APP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_DEPT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_IP;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_LANG;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_PORT;
import static com.finhub.framework.logback.constant.LogConstants.MDC_KEY_TO_VEHICLE;
import static com.finhub.framework.logback.constant.LogConstants.REQ_LEVEL;
import static com.finhub.framework.logback.constant.LogConstants.SPRING_VEHICLE_VERSION_PROPERTY;
import static com.finhub.framework.logback.constant.LogConstants.S_EMPTY;
import static org.apache.http.HttpHeaders.ACCEPT_LANGUAGE;


/**
 * 记录日志 Filter
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
public class MDCLogFilter implements Filter {

    private static final String UNKNOWN = "unknown";
    private static final String LOCAL_IP = "127.0.0.1";
    private static final int IP_LENGTH = 15;
    private static final String LOCAL_IP_2 = "0:0:0:0:0:0:0:1";
    private static final String S_COMMA = ",";

    private String springApplicationDept;

    private String springApplicationName;

    private String serverPort;

    @Override
    public void doFilter(final ServletRequest rq, final ServletResponse rp, final FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rp;

        try {
            String requestLevel = request.getHeader(REQ_LEVEL);
            if (requestLevel == null) {
                requestLevel = request.getParameter(REQ_LEVEL);
            }

            if (isNotBlank(requestLevel)) {
                MDC.put(REQ_LEVEL, requestLevel.toUpperCase());
            }

            // 保存信息
            putMdc(request, response);
            chain.doFilter(request, response);
        } finally {
            // 记得 clear 相关信息，否则会导致内存溢出
            LogUtils.clear();
            LogMdcHolder.clear();
        }
    }

    @Override
    public void init(final FilterConfig config) throws ServletException {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

    private void putMdc(final HttpServletRequest request, final HttpServletResponse response) {
        String fromIp = request.getHeader(HEADER_FROM_IP_KEY);
        if (isBlank(fromIp)) {
            fromIp = getRealIp(request);
        }
        MDC.put(MDC_KEY_FROM_IP, isBlank(fromIp) ? S_EMPTY : fromIp);

        int fromPort = request.getRemotePort();
        MDC.put(MDC_KEY_FROM_PORT, fromPort <= 0 ? S_EMPTY : String.valueOf(fromPort));

        String fromApp = request.getHeader(HEADER_FROM_APP_KEY);
        MDC.put(MDC_KEY_FROM_APP, isBlank(fromApp) ? S_EMPTY : fromApp);

        String fromDept = request.getHeader(HEADER_FROM_DEPT_KEY);
        MDC.put(MDC_KEY_FROM_DEPT, isBlank(fromDept) ? S_EMPTY : fromDept);

        String fromVehicle = request.getHeader(HEADER_FROM_VEHICLE_KEY);
        MDC.put(MDC_KEY_FROM_VEHICLE, isBlank(fromVehicle) ? S_EMPTY : fromVehicle);

        String fromLang = request.getHeader(I18nProperties.me().getInterceptorName());
        if (isBlank(fromLang)) {
            fromLang = request.getHeader(ACCEPT_LANGUAGE);
        }
        MDC.put(MDC_KEY_FROM_LANG, isBlank(fromLang) ? S_EMPTY : fromLang);

        MDC.put(MDC_KEY_TO_IP, NetUtil.getLocalhostStr());
        MDC.put(MDC_KEY_TO_PORT, serverPort);
        MDC.put(MDC_KEY_TO_APP, springApplicationName);
        MDC.put(MDC_KEY_TO_DEPT, springApplicationDept);

        Environment environment = SpringUtil.getBean(Environment.class);
        String toVehicle = environment.getProperty(SPRING_VEHICLE_VERSION_PROPERTY);
        MDC.put(MDC_KEY_TO_VEHICLE, isBlank(toVehicle) ? S_EMPTY : toVehicle);

        Locale defaultLocal = LocaleTypeEnum.getValue(I18nProperties.me().getDefaultLocale());
        Locale fromLocale = LocaleTypeEnum.getValue(fromLang);

        CustomHeaderLocaleResolver.me().setLocaleContext(defaultLocal, fromLocale);

        String toLang = LocaleContextHolder.getLocale().toString();
        MDC.put(MDC_KEY_TO_LANG, isBlank(toLang) ? S_EMPTY : toLang);

        String fromTraceId = request.getHeader(HEADER_TRACE_ID_KEY);
        LogUtils.fillTraceId(fromTraceId);

        String fromRequestId = request.getHeader(HEADER_REQUEST_ID_KEY);
        if (isBlank(fromRequestId)) {
            fromRequestId = request.getHeader(MDC_KEY_CAMEL_REQUEST_ID);
        }
        if (isBlank(fromRequestId)) {
            fromRequestId = request.getHeader(MDC_KEY_REQUEST_ID);
        }

        LogUtils.fillRequestId(fromRequestId);
    }

    private String getRealIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (StrUtil.equals(LOCAL_IP, ipAddress) || StrUtil.equals(LOCAL_IP_2, ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
            if (ipAddress.indexOf(S_COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(S_COMMA));
            }
        }
        return ipAddress;
    }
}

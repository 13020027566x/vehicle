package com.finhub.framework.core.actuator;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.web.RequestUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.ApiVersion;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.WebServerNamespace;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.health.*;
import org.springframework.boot.actuate.health.HealthEndpoint;

import java.time.Duration;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;  // 使用 jakarta

import static com.finhub.framework.core.net.IpUtils.LOCAL_IP;
import static com.finhub.framework.core.net.IpUtils.LOCAL_IP_2;

@Slf4j
@EndpointWebExtension(endpoint = HealthEndpoint.class)
public class CustomHealthEndpointWebExtension extends HealthEndpointWebExtension {

    private static final String CUSTOM_CODE_PARAMETER_NAME = "code";
    private static final Set<String> LOCAL_HOST_IP_SET = Sets.newHashSet(LOCAL_IP, LOCAL_IP_2, "172.16.2.1");
    private volatile int code = WebEndpointResponse.STATUS_OK;

    /**
     * Spring Boot 3.5.8 构造函数需要3个参数
     */
    public CustomHealthEndpointWebExtension(HealthContributorRegistry registry,
                                            HealthEndpointGroups groups,
                                            Duration slowIndicatorLoggingThreshold) {
        // 调用父类的3参数构造函数
        super(registry, groups, slowIndicatorLoggingThreshold);
    }

    /**
     * 重写 health 方法
     */
    @ReadOperation
    public WebEndpointResponse<HealthComponent> health(ApiVersion apiVersion,
                                                       WebServerNamespace namespace,
                                                       SecurityContext securityContext) {

        HttpServletRequest request = RequestUtils.getRequest();
        String reqCode = request.getParameter(CUSTOM_CODE_PARAMETER_NAME);
        String reqRealIp = request.getRemoteAddr();

        if (Func.isNotBlank(reqCode) && Func.isNotBlank(reqRealIp) && LOCAL_HOST_IP_SET.contains(reqRealIp)) {
            try {
                int customCode = Integer.parseInt(reqCode);
                if (customCode != WebEndpointResponse.STATUS_OK) {
                    code = customCode;
                    return new WebEndpointResponse<>(Health.unknown().build(), customCode);
                }
                code = WebEndpointResponse.STATUS_OK;
            } catch (NumberFormatException e) {
                log.warn("get server status fail, because code parameter can not convert Integer.class", e);
            }
        }

        if (code != WebEndpointResponse.STATUS_OK) {
            return new WebEndpointResponse<>(Health.unknown().build(), code);
        }

        // 调用父类方法
        return super.health(apiVersion, namespace, securityContext, false);
    }

    /**
     * 重写带路径的 health 方法
     */
    @ReadOperation
    public WebEndpointResponse<HealthComponent> health(ApiVersion apiVersion,
                                                       WebServerNamespace namespace,
                                                       SecurityContext securityContext,
                                                       @Selector(match = Selector.Match.ALL_REMAINING) String... path) {
        // 调用父类方法
        return super.health(apiVersion, namespace, securityContext, false, path);
    }
}

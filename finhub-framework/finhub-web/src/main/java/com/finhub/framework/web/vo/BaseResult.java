package com.finhub.framework.web.vo;

import com.finhub.framework.core.environment.EnvConfig;
import com.finhub.framework.logback.util.LogUtils;
import com.finhub.framework.web.property.ResponseProperties;

import lombok.Data;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import static com.finhub.framework.core.str.StrConstants.S_AT;

/**
 * 基础result相应结构体，统一设置requestId和traceId
 *
 * @author liuwei
 */
@Data
public class BaseResult implements Serializable {

    /**
     * 响应码
     */
    @NotBlank
    private Integer code = ResponseProperties.me().getSuccessCode();

    /**
     * 请求ID
     */
    @NotBlank
    private String requestId;

    /**
     * Skywalking 追踪ID
     */
    @NotBlank
    private String traceId;

    /**
     * 不建议覆盖
     *
     * @return
     */
    public String getRequestId() {
        return getRequestId4Response();
    }

    /**
     * 获取 RequestId
     * @return
     */
    public static String getRequestId4Response() {
        return LogUtils.getRequestId();
    }

    /**
     * 不建议覆盖
     *
     * @return
     */
    public String getTraceId() {
        return getTraceId4Response();
    }

    /**
     * 获取 TraceId@VehicleVersion
     * @return
     */
    public static String getTraceId4Response() {
        return EnvConfig.environment() == null ?
            LogUtils.getTraceId() :
            LogUtils.getTraceId() + S_AT + EnvConfig.me().getVehicleVersion();
    }
}

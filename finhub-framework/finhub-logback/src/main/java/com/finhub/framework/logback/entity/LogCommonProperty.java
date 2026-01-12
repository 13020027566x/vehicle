package com.finhub.framework.logback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : liuwei
 * @date : 2021/11/8
 * @desc :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogCommonProperty {

    private String mode;

    private String className;

    private String simpleClassName;

    private String methodName;

    private Object[] args;

    private String argsJson;

    private String body;

    private String fullClassMethod;

    private String simpleClassMethod;

    private Object result;

    @Builder.Default
    private boolean success = true;

    private long costTime;

    public void paddingProcessResult(Object result, long costTime) {
        paddingProcessResult(result, costTime, true);
    }

    public void paddingProcessResult(Object result, long costTime, boolean success) {
        this.result = result;
        this.costTime = costTime;
        this.success = success;
    }

}

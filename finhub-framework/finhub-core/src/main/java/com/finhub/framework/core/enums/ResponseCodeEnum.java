package com.finhub.framework.core.enums;

import com.finhub.framework.exception.assertion.ServletExceptionAssert;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应结果定义枚举
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements ServletExceptionAssert {

    SUCCESS(HttpStatus.HTTP_OK, "success"),
    BAT_REQUEST(HttpStatus.HTTP_BAD_REQUEST, "fail"),
    UNAUTHORIZED(HttpStatus.HTTP_UNAUTHORIZED, "未经授权, 请联系管理员"),
    INTERNAL_ERROR(HttpStatus.HTTP_INTERNAL_ERROR, "fail"),
    NOT_ACCEPTABLE(HttpStatus.HTTP_NOT_ACCEPTABLE, "fail");

    /**
     * 响应码
     */
    private final int code;

    /**
     * 响应信息
     */
    private final String message;
}

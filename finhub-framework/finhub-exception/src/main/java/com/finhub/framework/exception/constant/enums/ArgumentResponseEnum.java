package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.ArgumentExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>参数处理状态码枚举（8000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements ArgumentExceptionAssert {
    /**
     * 参数异常
     */
    COMMON_ERROR(8000, "参数异常"),

    /**
     * 参数缺失
     */
    MISSING_PARAM(8001, "参数缺失"),

    /**
     * 参数无效
     */
    INVALID_ERROR(8002, "参数无效");

    /**
     * 返回码
     */
    private final int code;
    /**
     * 返回消息
     */
    private final String message;

    @Override
    public String getMessage() {
        return MessageSourceManager.me().getMessage("ArgumentResponseEnum." + this.name(), this.message);
    }

}

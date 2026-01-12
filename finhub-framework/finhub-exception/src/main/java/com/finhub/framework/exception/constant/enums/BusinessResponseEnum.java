package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.BusinessExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>业务处理状态码枚举（7000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {
    /**
     * 通用业务异常
     */
    COMMON_ERROR(7000, "业务异常");

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
        return MessageSourceManager.me().getMessage("BusinessResponseEnum." + this.name(), this.message);
    }
}

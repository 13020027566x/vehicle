package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.ClientExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>客户端处理状态码枚举（6000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum ClientResponseEnum implements ClientExceptionAssert {
    /**
     * 通用客户端异常
     */
    COMMON_ERROR(6000, "客户端异常");

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
        return MessageSourceManager.me().getMessage("ClientResponseEnum." + this.name(), this.message);
    }
}

package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.UtilExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>工具类状态码枚举（3000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum UtilResponseEnum implements UtilExceptionAssert {
    /**
     * 通用工具类异常
     */
    COMMON_ERROR(3000, "通用工具类异常");

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
        return MessageSourceManager.me().getMessage("UtilResponseEnum." + this.name(), this.message);
    }
}

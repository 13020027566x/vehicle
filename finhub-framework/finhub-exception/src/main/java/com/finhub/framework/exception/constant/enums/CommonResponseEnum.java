package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.CommonExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * <p>通用状态码枚举（200 & 9000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements CommonExceptionAssert {
    /**
     * 成功
     */
    SUCCESS(HttpServletResponse.SC_OK, "success"),

    /**
     * 失败
     */
    FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "fail"),

    /**
     * 服务器繁忙，请稍后重试
     */
    SERVER_BUSY(9998, "服务器繁忙"),
    /**
     * 服务器异常，无法识别的异常，尽可能对通过判断减少未定义异常抛出
     */
    SERVER_ERROR(9999, "网络异常");

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
        return MessageSourceManager.me().getMessage("CommonResponseEnum." + this.name(), this.message);
    }
}

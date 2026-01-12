package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.MessageExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>消息提示状态码枚举</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
@AllArgsConstructor
public enum MessageResponseEnum implements MessageExceptionAssert {
    /**
     * 通用消息提示异常
     */
    COMMON_ERROR(5000, "消息提示异常"),

    /**
     * 5***，一般对应于{@link com.finhub.framework.exception.ArgumentException}，系统封装的工具出现异常
     */
    // Time
    DATE_NOT_NULL(5001, "日期不能为空"),
    DATETIME_NOT_NULL(5001, "时间不能为空"),
    TIME_NOT_NULL(5001, "时间不能为空"),
    DATE_PATTERN_MISMATCH(5002, "日期[%s]与格式[%s]不匹配，无法解析"),
    PATTERN_NOT_NULL(5003, "日期格式不能为空"),
    PATTERN_INVALID(5003, "日期格式[%s]无法识别");

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
        return MessageSourceManager.me().getMessage("MessageResponseEnum." + this.name(), this.message);
    }

}

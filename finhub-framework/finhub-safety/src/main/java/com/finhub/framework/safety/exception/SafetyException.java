package com.finhub.framework.safety.exception;

import com.finhub.framework.exception.BaseException;

/**
 * kms相关异常
 *
 * @author zhenxing_liang
 * @version 1.0
 * @since 2023/02/13 11:37
 */
public class SafetyException extends BaseException {

    public SafetyException(String message) {
        super(message);
    }

    public SafetyException(Throwable cause) {
        super(cause);
    }
}

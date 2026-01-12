package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * 工具类 异常
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class UtilException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public UtilException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public UtilException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public UtilException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(int code) {
        super(code);
    }

    public UtilException(int code, Throwable cause) {
        super(code, cause);
    }

    public UtilException(int code, String message) {
        super(code, message);
    }

    public UtilException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * Client层 异常
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class ClientException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public ClientException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public ClientException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public ClientException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public ClientException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(int code) {
        super(code);
    }

    public ClientException(int code, Throwable cause) {
        super(code, cause);
    }

    public ClientException(int code, String message) {
        super(code, message);
    }

    public ClientException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

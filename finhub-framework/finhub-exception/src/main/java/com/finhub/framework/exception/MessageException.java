package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * 消息异常
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class MessageException extends NoRollbackException {

    private static final long serialVersionUID = 8247610319171014183L;

    public MessageException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public MessageException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public MessageException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public MessageException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(int code) {
        super(code);
    }

    public MessageException(int code, Throwable cause) {
        super(code, cause);
    }

    public MessageException(int code, String message) {
        super(code, message);
    }

    public MessageException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

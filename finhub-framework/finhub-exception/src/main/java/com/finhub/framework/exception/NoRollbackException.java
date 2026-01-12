package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * @author : liuwei
 * @date : 2022/4/24
 * @desc : 事务不回滚异常
 */
public class NoRollbackException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public NoRollbackException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public NoRollbackException(int code, String message) {
        super(code, message);
    }

    public NoRollbackException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public NoRollbackException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public NoRollbackException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public NoRollbackException(Throwable cause) {
        super(cause);
    }

    public NoRollbackException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public NoRollbackException(String message) {
        super(message);
    }

    public NoRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRollbackException(int code) {
        super(code);
    }

    public NoRollbackException(int code, Throwable cause) {
        super(code, cause);
    }

    public NoRollbackException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

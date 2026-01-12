package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * <p>业务异常</p>
 * <p>业务处理时，出现异常，可以抛出该异常</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public BusinessException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public BusinessException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public BusinessException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code) {
        super(code);
    }

    public BusinessException(int code, Throwable cause) {
        super(code, cause);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

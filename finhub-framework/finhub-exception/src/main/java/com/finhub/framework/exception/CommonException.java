package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * <p>通用异常</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public class CommonException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public CommonException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public CommonException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public CommonException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public CommonException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(int code) {
        super(code);
    }

    public CommonException(int code, Throwable cause) {
        super(code, cause);
    }

    public CommonException(int code, String message) {
        super(code, message);
    }

    public CommonException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * <p>Web Servlet 异常</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public class ServletException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public ServletException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public ServletException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public ServletException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public ServletException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public ServletException(Throwable cause) {
        super(cause);
    }

    public ServletException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public ServletException(String message) {
        super(message);
    }

    public ServletException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServletException(int code) {
        super(code);
    }

    public ServletException(int code, Throwable cause) {
        super(code, cause);
    }

    public ServletException(int code, String message) {
        super(code, message);
    }

    public ServletException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

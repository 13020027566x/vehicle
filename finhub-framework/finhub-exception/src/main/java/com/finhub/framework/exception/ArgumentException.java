package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.CodeEnum;

/**
 * <p>参数异常</p>
 * <p>在处理业务过程中校验参数出现错误, 可以抛出该异常</p>
 * <p>编写公共代码（如工具类）时，对传入参数检查不通过时，可以抛出该异常</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public class ArgumentException extends BaseException {

    private static final long serialVersionUID = 8247610319171014183L;

    public ArgumentException(CodeEnum codeEnum, String message, Object[] args) {
        super(codeEnum, message, args);
    }

    public ArgumentException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(codeEnum, message, args, cause);
    }

    public ArgumentException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public ArgumentException(CodeEnum codeEnum, String message) {
        super(codeEnum, message);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }

    public ArgumentException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentException(int code) {
        super(code);
    }

    public ArgumentException(int code, Throwable cause) {
        super(code, cause);
    }

    public ArgumentException(int code, String message) {
        super(code, message);
    }

    public ArgumentException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

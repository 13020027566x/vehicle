package com.finhub.framework.exception;

import com.finhub.framework.exception.constant.BaseCodeEnum;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * <p>基础异常类，所有自定义异常类都需要继承本类</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    /**
     * 返回码
     */
    protected CodeEnum codeEnum;

    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public BaseException(int code, String message) {
        super(message);
        this.codeEnum = new BaseCodeEnum(code, message);
    }

    public BaseException(CodeEnum codeEnum, String message, Object[] args) {
        super(message);
        this.codeEnum = codeEnum;
        this.args = args;
    }

    public BaseException(CodeEnum codeEnum, String message, Object[] args, Throwable cause) {
        super(message, cause);
        this.codeEnum = codeEnum;
        this.args = args;
    }

    public BaseException(CodeEnum codeEnum, String message) {
        super(message);
        this.codeEnum = new BaseCodeEnum(codeEnum.getCode(), message);
    }

    public BaseException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public BaseException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum.getMessage(), cause);
        this.codeEnum = codeEnum;
    }

    public BaseException(String message) {
        super(message);
        this.codeEnum = new BaseCodeEnum(500, message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.codeEnum = new BaseCodeEnum(500, message);
    }

    public BaseException(int code) {
        super(String.valueOf(code));
        this.codeEnum = new BaseCodeEnum(code, StrUtil.EMPTY);
    }

    public BaseException(int code, Throwable cause) {
        super(String.valueOf(code), cause);
        this.codeEnum = new BaseCodeEnum(code, StrUtil.EMPTY);
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.codeEnum = new BaseCodeEnum(code, message);
    }
}

package com.finhub.framework.exception;

/**
 * 只包装了 错误信息 的 {@link RuntimeException}.
 * 用于 {@link com.finhub.framework.exception.assertion.Assert} 中用于包装自定义异常信息
 *
 * @author Mickey
 * @date 2020/6/20
 */
public class WrapMessageException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }

}

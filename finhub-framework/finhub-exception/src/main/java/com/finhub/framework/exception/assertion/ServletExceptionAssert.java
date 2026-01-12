package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.ServletException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>Web Servlet 异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface ServletExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ServletException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ServletException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new ServletException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new ServletException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new ServletException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new ServletException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new ServletException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new ServletException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new ServletException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new ServletException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new ServletException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new ServletException(code, message, cause);
    }

}

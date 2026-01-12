package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.ArgumentException;
import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>参数异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface ArgumentExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new ArgumentException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new ArgumentException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new ArgumentException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new ArgumentException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new ArgumentException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new ArgumentException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new ArgumentException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new ArgumentException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new ArgumentException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new ArgumentException(code, message, cause);
    }

}

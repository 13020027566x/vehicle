package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.UtilException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>工具类异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface UtilExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new UtilException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new UtilException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new UtilException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new UtilException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new UtilException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new UtilException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new UtilException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new UtilException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new UtilException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new UtilException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new UtilException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new UtilException(code, message, cause);
    }
}

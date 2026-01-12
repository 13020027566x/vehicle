package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.MessageException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>消息提示异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface MessageExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new MessageException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new MessageException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new MessageException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new MessageException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new MessageException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new MessageException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new MessageException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new MessageException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new MessageException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new MessageException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new MessageException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new MessageException(code, message, cause);
    }
}

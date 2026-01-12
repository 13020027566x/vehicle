package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.ClientException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>客户端异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface ClientExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ClientException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ClientException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new ClientException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new ClientException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new ClientException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new ClientException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new ClientException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new ClientException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new ClientException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new ClientException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new ClientException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new ClientException(code, message, cause);
    }
}

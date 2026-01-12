package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.CommonException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <pre>通用异常断言</pre>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface CommonExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new CommonException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new CommonException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new CommonException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new CommonException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new CommonException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new CommonException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new CommonException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new CommonException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new CommonException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new CommonException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new CommonException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new CommonException(code, message, cause);
    }

}

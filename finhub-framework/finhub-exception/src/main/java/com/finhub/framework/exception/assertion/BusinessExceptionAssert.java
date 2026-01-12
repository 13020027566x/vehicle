package com.finhub.framework.exception.assertion;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.BusinessException;
import com.finhub.framework.exception.constant.CodeEnum;

import cn.hutool.core.util.ArrayUtil;

import java.text.MessageFormat;

/**
 * <p>业务异常断言</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
public interface BusinessExceptionAssert extends CodeEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new BusinessException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new BusinessException(this, msg, args, t);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum) {
        throw new BusinessException(codeEnum);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, String message) {
        throw new BusinessException(codeEnum, message);
    }

    @Override
    default BaseException newException(Throwable cause) {
        throw new BusinessException(cause);
    }

    @Override
    default BaseException newException(CodeEnum codeEnum, Throwable cause) {
        throw new BusinessException(codeEnum, cause);
    }

    @Override
    default BaseException newException(String message) {
        throw new BusinessException(message);
    }

    @Override
    default BaseException newException(String message, Throwable cause) {
        throw new BusinessException(message, cause);
    }

    @Override
    default BaseException newException(int code) {
        throw new BusinessException(code);
    }

    @Override
    default BaseException newException(int code, Throwable cause) {
        throw new BusinessException(code, cause);
    }

    @Override
    default BaseException newException(int code, String message) {
        throw new BusinessException(code, message);
    }

    @Override
    default BaseException newException(int code, String message, Throwable cause) {
        throw new BusinessException(code, message, cause);
    }

}

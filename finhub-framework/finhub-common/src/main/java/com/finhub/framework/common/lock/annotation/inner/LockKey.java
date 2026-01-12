package com.finhub.framework.common.lock.annotation.inner;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : liuwei
 * @date : 2021/10/27
 * @desc :
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LockKey {
    /**
     * 锁前缀
     *
     * @return key-prefix
     */
    String prefix();

    /**
     * key-spring-expression-language表达式 <demo>#id or #dto.id</demo>
     *
     * @return spring-expression-language
     */
    String[] expression() default {};

    /**
     * key连接模式
     *
     * @return key-connect-mode
     */
    KeyConnectMode connect() default KeyConnectMode.append;


    /**
     *
     */
    enum KeyConnectMode {

        /**
         * 追加
         */
        append,

        /**
         * StringFormat.format--> %s
         */
        StringFormat,

        /**
         * MessageFormat.format--> {}
         */
        MessageFormat

    }
}

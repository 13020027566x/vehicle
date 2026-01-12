package com.finhub.framework.common.lock.annotation;

import com.finhub.framework.common.lock.annotation.inner.LockKey;
import com.finhub.framework.common.lock.annotation.inner.LockTime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : liuwei
 * @date : 2021/10/27
 * @desc :
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiDistributeLock {

    /**
     * 分布式key注解集合
     *
     * @return 分布式锁key相关信息
     */
    LockKey[] keys();

    /**
     * 锁时间 默认等待3秒，占有锁时间根据程序实际使用时间为准
     *
     * @return 分布式锁time相关信息
     */
    LockTime time() default @LockTime;

    /**
     * 联合锁上锁模式，默认联合锁
     *
     * @return 锁模式
     */
    LockMode mode() default LockMode.MULTI_LOCK;

    enum LockMode {

        /**
         * 联合锁
         */
        MULTI_LOCK,

        /**
         * 红锁联合
         */
        RED_LOCK,

    }

}

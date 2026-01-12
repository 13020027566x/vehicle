package com.finhub.framework.common.lock.annotation;

import com.finhub.framework.common.lock.annotation.inner.LockKey;
import com.finhub.framework.common.lock.annotation.inner.LockTime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc : 分布式锁标示，标注该注解的方法会进行分布式加锁操作
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {

    /**
     * 分布式key
     *
     * @return 分布式key相关信息
     */
    LockKey key();

    /**
     * 锁时间 默认等待3秒，占有锁时间根据程序实际使用时间为准
     *
     * @return 分布式time相关信息
     */
    LockTime time() default @LockTime;

    /**
     * 锁模式：默认非公平可重入锁, 在@MultiDistributeLock注解联合锁引用下，该属性不生效，固定为<>REENTRANT_LOCK模式</>
     *
     * @return 锁模式
     */
    LockMode mode() default LockMode.REENTRANT_LOCK;

    /**
     *
     */
    enum LockMode {
        /**
         * 可重入锁
         */
        REENTRANT_LOCK,

        /**
         * 公平锁
         */
        FAIR_LOCK,
    }


}

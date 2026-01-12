package com.finhub.framework.common.lock.annotation.inner;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author : liuwei
 * @date : 2021/10/27
 * @desc :
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LockTime {

    /**
     * 获取锁超时时间 默认3秒， time<0：表示一直等待，直到获取到锁（不推荐）。
     *
     * @return 最大等待获取锁时间
     */
    long waitTime() default 3L;

    /**
     * 推荐该字段使用默认值-1
     * <p>
     * leaseTime = -1 内部会有续约锁 直到目标方法结束锁释放 or 程序异常退出后（key过期）默认30秒
     * 保障加锁单元同一时间内只有一个线程执行业务单元
     * leaseTime != -1 获取锁后，最大持有锁的时间，超过自动释放，不会进行锁续约 （不推荐使用，除特殊情况）
     * 不保障同一时间内只有一个线程执行加锁业务单元
     *
     * @return 最大持有锁时间
     */
    long leaseTime() default -1L;

    /**
     * 时间类型
     *
     * @return 类型
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}

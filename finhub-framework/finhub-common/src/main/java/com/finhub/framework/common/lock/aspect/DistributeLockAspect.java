package com.finhub.framework.common.lock.aspect;

import com.finhub.framework.common.lock.DistributeLockHolder;
import com.finhub.framework.common.lock.annotation.DistributeLock;
import com.finhub.framework.common.lock.annotation.inner.LockTime;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc : Redis分布式切面
 */
@Slf4j
@Aspect
public class DistributeLockAspect extends AbstractLockAspect<DistributeLock> {

    public DistributeLockAspect(RedissonClient redissonClient) {
        super(redissonClient);
    }

    /**
     * distributed lock pointcut
     *
     * @param distributeLock @RedisLock
     */
    @Pointcut("@annotation(distributeLock))")
    public void lockPointcut(DistributeLock distributeLock) {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * invoke method
     *
     * @param joinPoint      切入方法
     * @param distributeLock @RedisLock
     * @return 返回方法返回值
     * @throws Throwable 执行异常
     */
    @Around(value = "lockPointcut(distributeLock)", argNames = "joinPoint,distributeLock")
    public Object lockAround(ProceedingJoinPoint joinPoint, DistributeLock distributeLock) throws Throwable {
        return super.proceedWithLog(joinPoint, distributeLock);
    }



    @Override
    protected RLock getFinalDistributeLock(ProceedingJoinPoint joinPoint, Method method, DistributeLock annotation) {
        String key =
            super.parseLockKey(joinPoint, annotation.key(), ((MethodSignature) joinPoint.getSignature()).getMethod());
        DistributeLockHolder.put(key);
        return super.getSingleDistributeLock(key, annotation.mode());
    }

    @Override
    protected LockTime getRedisTime(DistributeLock annotation) {
        return annotation.time();
    }

}

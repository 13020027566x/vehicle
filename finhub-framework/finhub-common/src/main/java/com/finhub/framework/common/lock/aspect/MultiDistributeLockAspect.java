package com.finhub.framework.common.lock.aspect;

import com.finhub.framework.common.lock.DistributeLockHolder;
import com.finhub.framework.common.lock.annotation.DistributeLock;
import com.finhub.framework.common.lock.annotation.MultiDistributeLock;
import com.finhub.framework.common.lock.annotation.inner.LockKey;
import com.finhub.framework.common.lock.annotation.inner.LockTime;
import com.finhub.framework.core.json.JsonUtils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

/**
 * @author : liuwei
 * @date : 2021/10/27
 * @desc :
 */
@Slf4j
@Aspect
public class MultiDistributeLockAspect extends AbstractLockAspect<MultiDistributeLock> {

    public MultiDistributeLockAspect(RedissonClient redissonClient) {
        super(redissonClient);
    }

    /**
     * multi distribute lock lock pointcut
     *
     * @param multiDistributeLock 方法注解
     */
    @Pointcut("@annotation(multiDistributeLock))")
    public void lockPointcut(MultiDistributeLock multiDistributeLock) {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * invoke method
     *
     * @param joinPoint           切面
     * @param multiDistributeLock @RedisLock
     * @return 返回方法返回值
     * @throws Throwable 执行异常
     */
    @Around(value = "lockPointcut(multiDistributeLock)", argNames = "joinPoint,multiDistributeLock")
    public Object lockAround(ProceedingJoinPoint joinPoint, MultiDistributeLock multiDistributeLock) throws Throwable {
        return super.proceedWithLog(joinPoint, multiDistributeLock);
    }

    @Override
    protected RLock getFinalDistributeLock(ProceedingJoinPoint joinPoint, Method method,
        MultiDistributeLock annotation) {
        return this.getMultiLock(joinPoint, method, annotation);
    }

    @Override
    protected LockTime getRedisTime(MultiDistributeLock annotation) {
        return annotation.time();
    }

    /**
     * 获取分布锁多条件锁
     *
     * @param joinPoint           切面
     * @param method              切入目标方法
     * @param multiDistributeLock 多条件锁注解
     * @return 获得多条件锁
     */
    private RedissonMultiLock getMultiLock(ProceedingJoinPoint joinPoint, Method method,
        MultiDistributeLock multiDistributeLock) {
        LockKey[] keys = multiDistributeLock.keys();
        org.springframework.util.Assert.notEmpty(keys, "@MultiDistributeLock[keys]不能为空");
        RLock[] rLockArray = new RLock[keys.length];
        String[] lockKeyArray = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            LockKey lockKey = keys[i];
            String key = this.parseLockKey(joinPoint, lockKey, method);
            RLock rLock = this.getSingleDistributeLock(key, DistributeLock.LockMode.REENTRANT_LOCK);
            rLockArray[i] = rLock;
            lockKeyArray[i] = key;
        }
        String finalKeys = JsonUtils.toJson(lockKeyArray);
        DistributeLockHolder.put(finalKeys);
        return multiDistributeLock.mode() == MultiDistributeLock.LockMode.RED_LOCK ?
            new RedissonRedLock(rLockArray) :
            new RedissonMultiLock(rLockArray);
    }
}

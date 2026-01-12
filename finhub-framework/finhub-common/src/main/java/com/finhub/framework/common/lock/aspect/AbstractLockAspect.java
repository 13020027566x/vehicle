package com.finhub.framework.common.lock.aspect;

import com.finhub.framework.common.lock.DistributeLockHolder;
import com.finhub.framework.common.lock.annotation.DistributeLock;
import com.finhub.framework.common.lock.annotation.inner.LockKey;
import com.finhub.framework.common.lock.annotation.inner.LockTime;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.aspect.AbstractAspect;
import com.finhub.framework.core.number.NumberConstants;
import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.util.ExceptionUtils;
import com.finhub.framework.logback.entity.LogCommonProperty;
import com.finhub.framework.logback.util.LogCommonUtils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : liuwei
 * @date : 2021/10/27
 * @desc :
 */
@Slf4j
public abstract class AbstractLockAspect<T extends Annotation> extends AbstractAspect {

    private static final String LOG_ASPECT_MODULE = "DistributeLock";

    private final RedissonClient redissonClient;

    protected AbstractLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 切面环绕入口&日志记录
     *
     * @param pjp            切入方法
     * @param distributeLock 方法声命注解
     * @return 切入方法返回值
     * @throws Throwable 异常抛出
     */
    public Object proceedWithLog(ProceedingJoinPoint pjp, T distributeLock) throws Throwable {
        LogCommonProperty logProperty = this.buildAspectLogCommonProperty(pjp, LOG_ASPECT_MODULE);
        if (isPrintLog()) {
            LogCommonUtils.LogKeyValue[] defaultInputLogKeyValues = LogCommonUtils.defaultInputLogKeyValues(logProperty,
                new LogCommonUtils.LogKeyValue("annotation", distributeLock.toString()));
            LogCommonUtils.processInputServerLog(logProperty.getMode() , LogLevel.INFO, defaultInputLogKeyValues);
        }

        Object result = null;
        Throwable throwable = null;
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            result = this.processLock(pjp, distributeLock);
        } catch (Throwable e) {
            if (isPrintLog()) {
                LogCommonUtils.processErrorServerLog(LOG_ASPECT_MODULE, ExceptionUtils.getErrorLogLevel(e),
                    LogCommonUtils.defaultErrorLogKeyValues(logProperty, e));
            }

            throwable = e;
            throw e;
        } finally {
            stopWatch.stop();

            logProperty.paddingProcessResult(result, stopWatch.getTotalTimeMillis(), Func.isNull(throwable));

            if (isPrintLog()) {
                LogCommonUtils.LogKeyValue[] defaultOutputLogKeyValues = LogCommonUtils.defaultOutputLogKeyValues(logProperty,
                    new LogCommonUtils.LogKeyValue("lock", DistributeLockHolder.get()));
                LogCommonUtils.processOutputServerLog(logProperty.getMode(), LogLevel.INFO, defaultOutputLogKeyValues);
            }
            LogCommonUtils.processSkyWalkingLog(logProperty, throwable);

            DistributeLockHolder.remove();
        }
        return result;
    }

    /**
     * 执行加锁处理&日志记录&sky walking
     *
     * @param joinPoint  切入方法
     * @param annotation @DistributeLock/@MultiDistributeLock
     * @return 返回方法返回值
     * @throws Throwable 执行异常
     */
    protected Object processLock(ProceedingJoinPoint joinPoint, T annotation) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RLock rLock = getFinalDistributeLock(joinPoint, method, annotation);
        return doProcess(joinPoint, rLock, getRedisTime(annotation));
    }

    /**
     * 该抽象方法主要目的为获取最终锁，获取最终锁的方式由子类实现。
     *
     * @param joinPoint  切入点
     * @param method     切入目标方法
     * @param annotation 方法分布式注解
     * @return 返回最终加锁对象
     */
    protected abstract RLock getFinalDistributeLock(ProceedingJoinPoint joinPoint, Method method, T annotation);

    /**
     * 该抽象方法主要目的为获取锁时间注解，子类的泛型注解返回该属性
     *
     * @param annotation 方法分布式注解
     * @return 锁时间信息注解
     */
    protected abstract LockTime getRedisTime(T annotation);


    /**
     * 执行目标方法加锁处理
     *
     * @param joinPoint 切入目标点
     * @param rLock     最终锁
     * @param lockTime  锁时间信息注解
     * @return 切入方法返回值
     * @throws Throwable 异常抛出
     */
    protected Object doProcess(ProceedingJoinPoint joinPoint, RLock rLock, LockTime lockTime)
        throws Throwable {
        Object retVal;
        if (lockTime.waitTime() < 0) {
            retVal = distributeLock(joinPoint, lockTime, rLock);
        } else {
            retVal = tryDistributeLock(joinPoint, lockTime, rLock);
        }
        return retVal;
    }

    /**
     * 获取单个锁实例
     *
     * @param key  实际在redis中存储的key标示
     * @param mode 锁模式，默认非公平锁
     * @return 单条件锁实例
     */
    protected RLock getSingleDistributeLock(String key, DistributeLock.LockMode mode) {
        return mode == DistributeLock.LockMode.FAIR_LOCK ?
            redissonClient.getFairLock(key) :
            redissonClient.getLock(key);
    }

    /**
     * void lock(long leaseTime, TimeUnit unit);
     * 所有未获取到锁线程阻塞，直到所有线程都获取到锁后执行业务。
     * 当 redisTime.leaseTime = -1 时 保障所有线程都会执行业务方法且同一时间内只有一个线程执行业务单元。
     * 当 redisTime.leaseTime != -1时 保障所有线程都会拿到锁，但不保证同一时间内只有一个线程执行业务单元。
     * **** leaseTime = -1  内部会有续约锁 直到目标方法结束锁释放 or 程序异常退出后（key过期）默认30秒 *****
     * **** leaseTime != -1 获取锁后，最大持有锁的时间，超过自动释放，不会进行锁续约 *****
     *
     * @param joinPoint 切入方法
     * @param lockTime  锁的时间信息，waitTime leaseTime timeUnit
     * @return 切入方法返回值
     * @throws Throwable 执行异常
     */
    protected Object distributeLock(ProceedingJoinPoint joinPoint, LockTime lockTime, RLock rLock)
        throws Throwable {
        try {
            rLock.lock(lockTime.leaseTime(), lockTime.timeUnit());
            log.info("get distributed lock :[{}] success", DistributeLockHolder.get());
            return joinPoint.proceed();
        } finally {
            unlock(rLock, true);
        }
    }

    /**
     * boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;
     * 在规定时间内尝试获取锁，超时自动放弃获取锁的机会（抛出超时未获取到锁）
     * 当 redisTime.leaseTime = -1 时 保障所有持有过线程在同一时间内只有一个线程执行业务单元。
     * 当 redisTime.leaseTime != -1时 不保障所有持有过线程在同一时间内只有一个线程执行业务单元。
     * **** leaseTime = -1 内部会有续约锁 直到目标方法结束锁释放 or 程序异常退出后（key过期）默认30秒 *****
     * **** leaseTime != -1 获取锁后，最大持有锁的时间，超过自动释放，不会进行锁续约 *****
     *
     * @param joinPoint 切入方法
     * @param lockTime  @RedisLock
     * @param rLock     redis RLock
     * @return 返回方法返回值
     * @throws Throwable 执行异常
     */
    protected Object tryDistributeLock(ProceedingJoinPoint joinPoint, LockTime lockTime, RLock rLock)
        throws Throwable {
        boolean success = false;
        try {
            success = rLock.tryLock(lockTime.waitTime(), lockTime.leaseTime(), lockTime.timeUnit());
            if (success) {
                log.info("get distributed tryLock :[{}] success", rLock);
                return joinPoint.proceed();
            }

            log.error("get distributed tryLock timeout，RLock:{} ，give up", rLock);
            throw new BaseException("get lock fail because lock timeout");
        } finally {
            unlock(rLock, success);
        }
    }

    protected void unlock(RLock rLock, boolean success) {

        if (success && rLock instanceof RedissonMultiLock) {
            rLock.unlock();
            log.info("unlock distributed lock :[{}] success", DistributeLockHolder.get());
        }

        if (success && !(rLock instanceof RedissonMultiLock) && rLock.isLocked() && rLock.isHeldByCurrentThread()) {
            rLock.unlock();
            log.info("unlock distributed multi lock :[{}] success", DistributeLockHolder.get());
        }
    }

    /**
     * parse redis-key
     *
     * @param joinPoint 切入方法
     * @param lockKey   @RedisLock
     * @param method    目标方法
     * @return final-redis-key
     */
    protected String parseLockKey(ProceedingJoinPoint joinPoint, LockKey lockKey, Method method) {
        return this.connectKey(lockKey, this.parseExpressionArgs(joinPoint, lockKey, method));
    }

    /**
     * 解析方法参数args，通过注解spel参数获取拼接redis-key的参数数组
     *
     * @param joinPoint 切入方法
     * @param lockKey   @RedisLock
     * @param method    目标方法
     * @return 获得SPEL表达式参数集合
     */
    protected Object[] parseExpressionArgs(ProceedingJoinPoint joinPoint, LockKey lockKey, Method method) {
        Assert.notNull(lockKey, "@RedisLock must not null");

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == NumberConstants.N_ZERO) {
            return args;
        }

        String[] expressionArray = lockKey.expression();
        if (expressionArray.length == NumberConstants.N_ZERO) {
            return args;
        }

        StandardEvaluationContext context = new StandardEvaluationContext(method);
        context.addPropertyAccessor(new MapAccessor());

        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        Object[] objArray = new Object[expressionArray.length];
        ExpressionParser parser = new SpelExpressionParser();
        for (int i = 0; i < expressionArray.length; i++) {
            Object value = parser.parseExpression(expressionArray[i]).getValue(context);
            Assert.notNull(value, "redis all key params must not null");
            objArray[i] = value;
        }

        return objArray;
    }

    /**
     * 连接前缀&keyArgs数组 得到最终redis-key
     *
     * @param lockKey         @RedisLock
     * @param expressionArray SPEL表达式参数集合
     * @return final-redis-key
     */
    protected String connectKey(LockKey lockKey, Object[] expressionArray) {
        String prefix = lockKey.prefix();
        if (expressionArray == null) {
            return prefix;
        }

        LockKey.KeyConnectMode connect = lockKey.connect();

        String result;
        switch (connect) {
            case MessageFormat:
                result = MessageFormatter.arrayFormat(prefix, expressionArray).getMessage();
                break;
            case StringFormat:
                result = String.format(prefix, expressionArray);
                break;
            default:
                result = appendStr(prefix, appendStr(expressionArray));
                break;
        }

        return result;
    }


    /**
     * 拼接字符串数组
     *
     * @param objects 数组对象
     * @return 数组对象字符串
     */
    protected String appendStr(Object... objects) {
        if (objects == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            Assert.notNull(object, "redis all key params must not null");
            stringBuilder.append(object);
        }

        return stringBuilder.toString();
    }
}

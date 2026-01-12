package com.finhub.framework.common.domain;

import com.finhub.framework.core.aspect.AbstractAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Domain 层日志切面
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Slf4j
@Aspect
public class DomainCostLogAspect extends AbstractAspect {

    private static final String LOG_ASPECT_MODULE = "Domain";

    /**
     * Pointcut
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut(DOMAIN_ASPECT_POINTCUT)
    private void aspectjMethod() {
    }

    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        return super.proceedWithLog(pjp, LOG_ASPECT_MODULE);
    }
}

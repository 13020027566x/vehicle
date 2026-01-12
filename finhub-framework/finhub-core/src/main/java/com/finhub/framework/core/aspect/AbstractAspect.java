package com.finhub.framework.core.aspect;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.environment.EnvConfig;
import com.finhub.framework.exception.util.ExceptionUtils;
import com.finhub.framework.logback.entity.LogCommonProperty;
import com.finhub.framework.logback.util.LogCommonUtils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.logging.LogLevel;
import org.springframework.util.StopWatch;

import static com.finhub.framework.logback.constant.LogConstants.LOG_CLASS_METHOD_TEMPLATE;
import static com.finhub.framework.logback.util.LogCommonUtils.arrayToString;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/08 17:41
 */
@Slf4j
public abstract class AbstractAspect {

    public static final String DAO_ASPECT_POINTCUT =
        "execution(public * com..dao.*.*DAO.*(..)) || @within(com.finhub.framework.mybatis.aspect.DaoAspect)";

    public static final String DOMAIN_ASPECT_POINTCUT =
        "execution(public * com..domain.*DO.*(..)) || @within(com.finhub.framework.common.domain.DomainAspect)";

    public static final String MANAGER_ASPECT_POINTCUT =
        "execution(public * com..manager.*Manager.*(..)) || @within(com.finhub.framework.common.manager.aspect.ManagerAspect)";

    public static final String SERVICE_ASPECT_POINTCUT =
        "execution(public * com..service.*.*Service.*(..)) || @within(com.finhub.framework.common.service.aspect.ServiceAspect)";

    /**
     * 处理切面方法调用&日志打印规则统一入口
     *
     * @param joinPoint 切面
     * @param module    切面业务代码
     * @return
     * @throws Throwable 方法异常抛出
     */
    protected Object proceedWithLog(final ProceedingJoinPoint joinPoint, String module) throws Throwable {
        LogCommonProperty logProperty = this.buildAspectLogCommonProperty(joinPoint, module);

        if (isPrintLog()) {
            LogCommonUtils.processInputServerLog(module, LogLevel.INFO,
                LogCommonUtils.defaultInputLogKeyValues(logProperty));
        }

        Object result = null;
        Throwable throwable = null;
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            result = joinPoint.proceed();
        } catch (Throwable e) {
            if (isPrintLog()) {
                LogCommonUtils.processErrorServerLog(module, ExceptionUtils.getErrorLogLevel(e),
                    LogCommonUtils.defaultErrorLogKeyValues(logProperty, e));
            }

            throwable = e;
            throw e;
        } finally {
            stopWatch.stop();

            logProperty.paddingProcessResult(result, stopWatch.getTotalTimeMillis(), Func.isNull(throwable));

            if (isPrintLog()) {
                LogCommonUtils.processOutputServerLog(module, LogLevel.INFO,
                    LogCommonUtils.defaultOutputLogKeyValues(logProperty));
            }
            LogCommonUtils.processSkyWalkingLog(logProperty, throwable);
        }

        return result;
    }

    /**
     * 构建参数打印实体
     *
     * @param joinPoint
     * @param mode
     */
    protected LogCommonProperty buildAspectLogCommonProperty(ProceedingJoinPoint joinPoint, String mode) {
        final Class<?> aClass = joinPoint.getTarget().getClass();
        String className = aClass.getName();
        String simpleName = aClass.getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = arrayToString(args);

        return LogCommonProperty.builder()
            .mode(mode)
            .className(className)
            .simpleClassName(simpleName)
            .methodName(methodName)
            .args(args)
            .argsJson(argsString)
            .fullClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, className, methodName))
            .simpleClassMethod(String.format(LOG_CLASS_METHOD_TEMPLATE, simpleName, methodName))
            .build();

    }

    /**
     * 是否打印日志
     * 非prod环境默认打印，
     * prod环境只有在debug模式才打印日志
     *
     * @return true 打印
     */
    protected boolean isPrintLog() {
        final EnvConfig me = EnvConfig.me();
        if (me.isNotProd()) {
            return me.isOpenAspectLog();
        }

        return me.isDebug();
    }

}

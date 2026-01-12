package com.finhub.framework.mybatis.aspect;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

/**
 * 对拦截的方法进行计数和计时统计，
 * 通过java.util.concurrent包减少对拦截方法本身的性能影响。
 *
 * @author Mickey
 * @version 1.0
 * @since 2014/9/17 10:26
 */
@Slf4j
public class TimeCostDaoInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        String className = invocation.getMethod().getDeclaringClass().getName();
        String methodName = invocation.getMethod().getName();
        Object[] args = invocation.getArguments();

        StringBuilder logMsg = new StringBuilder("\nDAO execute report -------- " + DateUtil.date() + " ----------------------------------------");
        logMsg.append("\nDAO       : ").append(className);
        logMsg.append("\nMethod    : ").append(methodName);
        logMsg.append("\nParameter : ").append(Joiner.on(",").join(Lists.transform(Arrays.asList(args), new Function<Object, String>() {
            @Override
            public String apply(Object input) {
                return input == null ? StrUtil.NULL : JSONUtil.toJsonStr(input);
            }
        })));

        long startTime = System.currentTimeMillis();
        Object retVal = null;
        try {
            retVal = invocation.proceed();
            return retVal;
        } catch (Exception e) {
            throw e;
        } finally {
            logMsg.append("\nResult    : ").append(Func.isEmpty(retVal) ? StrConstants.S_EMPTY_JSON : JSONUtil.toJsonStr(retVal));
            logMsg.append("\nCost Time : ").append(System.currentTimeMillis() - startTime).append(" ms");
            logMsg.append("\n--------------------------------------------------------------------------------------------");
            log.info(logMsg.toString());
        }
    }
}

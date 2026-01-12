package com.finhub.framework.mybatis.datasource;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.aspect.AbstractAspect;
import com.finhub.framework.mybatis.aspect.DS;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.finhub.framework.logback.util.LogCommonUtils.DATASOURCE_ROUTING_ASPECT_LOG_MODULE;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@Aspect
@Order(0)
public class DataSourceRoutingAspectProcessor extends AbstractAspect implements BeanPostProcessor {

    private static final Map<String, Boolean> READ_METHOD_MAP = new HashMap<>();

    private static final List<String> LOOKUP_MASTER_KEYS = new ArrayList<>();

    private static final List<String> LOOKUP_SLAVE_KEYS = new ArrayList<>();

    private Boolean forceChoiceReadWhenWrite = Boolean.FALSE;

    public void setForceChoiceReadWhenWrite(final boolean forceChoiceReadWhenWrite) {
        this.forceChoiceReadWhenWrite = forceChoiceReadWhenWrite;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof NameMatchTransactionAttributeSource) {
            try {
                NameMatchTransactionAttributeSource transactionAttributeSource =
                    (NameMatchTransactionAttributeSource) bean;
                Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
                nameMapField.setAccessible(true);
                Map<String, TransactionAttribute> nameMap =
                    (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);

                for (Map.Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
                    RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute) entry.getValue();

                    //仅对read-only的处理
                    if (!attr.isReadOnly()) {
                        continue;
                    }

                    String methodName = entry.getKey();
                    Boolean isForceChoiceRead = Boolean.FALSE;
                    if (forceChoiceReadWhenWrite) {
                        // 不管之前操作是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
                        // NOT_SUPPORTED会挂起之前的事务
                        attr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
                        isForceChoiceRead = Boolean.TRUE;
                    } else {
                        //否则 设置为SUPPORTS（这样可以参与到写事务）
                        attr.setPropagationBehavior(Propagation.SUPPORTS.value());
                    }
                    READ_METHOD_MAP.put(methodName, isForceChoiceRead);
                }
            } catch (Exception e) {
                log.error("Init readMethodMap occur exception : ", e);
            }
        }

        if (bean instanceof ThreadLocalRoutingDataSource) {
            try {
                ThreadLocalRoutingDataSource routingDataSource = (ThreadLocalRoutingDataSource) bean;
                Field targetDataSourcesField =
                    ReflectionUtils.findField(ThreadLocalRoutingDataSource.class, "targetDataSources");
                targetDataSourcesField.setAccessible(true);
                Map<Object, Object> targetDataSources =
                    (Map<Object, Object>) targetDataSourcesField.get(routingDataSource);

                for (Map.Entry<Object, Object> entry : targetDataSources.entrySet()) {
                    if (entry.getKey().toString().contains(DataSourceTypeManager.MASTER)) {
                        LOOKUP_MASTER_KEYS.add(entry.getKey().toString());
                    } else if (entry.getKey().toString().contains(DataSourceTypeManager.SLAVE)) {
                        LOOKUP_SLAVE_KEYS.add(entry.getKey().toString());
                    }
                }
            } catch (Exception e) {
                log.error("Init lookupMasterKeys or lookupSlaveKeys occur exception : ", e);
            }

        }
        return bean;
    }

    @Pointcut(AbstractAspect.MANAGER_ASPECT_POINTCUT)
    public void aspectjMethod() {
    }

    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            DS ds = null;
            Signature signature = pjp.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method =
                    pjp.getTarget().getClass().getMethod(methodSignature.getName(),
                        methodSignature.getParameterTypes());
                ds = method.getAnnotation(DS.class);

                if (ds == null) {
                    ds = pjp.getTarget().getClass().getAnnotation(DS.class);
                }
            }

            if (ds != null && StrUtil.isNotBlank(ds.value())) {
                DataSourceTypeManager.set(ds.value());
                return proceedWithLog(pjp, DATASOURCE_ROUTING_ASPECT_LOG_MODULE);
            }

            if (isChoiceReadDB(pjp.getSignature().getName())) {
                if (Func.isNotEmpty(LOOKUP_SLAVE_KEYS)) {
                    Collections.shuffle(LOOKUP_SLAVE_KEYS);
                    DataSourceTypeManager.markRead(LOOKUP_SLAVE_KEYS.get(0));
                }
            } else {
                if (Func.isNotEmpty(LOOKUP_MASTER_KEYS)) {
                    Collections.shuffle(LOOKUP_MASTER_KEYS);
                    DataSourceTypeManager.markWrite(LOOKUP_MASTER_KEYS.get(0));
                }
            }
            return proceedWithLog(pjp, DATASOURCE_ROUTING_ASPECT_LOG_MODULE);
        } finally {
            DataSourceTypeManager.reset();
        }

    }


    private boolean isChoiceReadDB(final String methodName) {
        String bestNameMatch = null;
        for (String mappedName : READ_METHOD_MAP.keySet()) {
            if (PatternMatchUtils.simpleMatch(mappedName, methodName)) {
                bestNameMatch = mappedName;
                break;
            }
        }

        Boolean isForceChoiceRead = READ_METHOD_MAP.get(bestNameMatch);
        // 表示强制选择读库
        if (Convert.toBool(isForceChoiceRead, false)) {
            return true;
        }

        // 如果之前选择了写库 现在还选择 写库
        if (DataSourceTypeManager.isChoiceWrite()) {
            return false;
        }

        // 表示应该选择读库
        if (Func.isNotEmpty(isForceChoiceRead)) {
            return true;
        }

        // 默认选择 写库
        return false;
    }
}

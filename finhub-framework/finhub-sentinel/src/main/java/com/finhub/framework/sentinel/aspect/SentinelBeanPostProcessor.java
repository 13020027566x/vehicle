package com.finhub.framework.sentinel.aspect;

import com.finhub.framework.sentinel.intercepter.CustomSentinelWebInterceptor;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Sentinel 后置处理类
 * 1. 替换 SentinelWebInterceptor 为 CustomSentinelWebInterceptor
 *
 * @author zhenxing_liang
 */
public class SentinelBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SentinelWebInterceptor) {
            SentinelWebMvcConfig sentinelWebMvcConfig = SpringUtil.getBean(SentinelWebMvcConfig.class);
            bean = new CustomSentinelWebInterceptor(sentinelWebMvcConfig);
        }
        return bean;
    }
}

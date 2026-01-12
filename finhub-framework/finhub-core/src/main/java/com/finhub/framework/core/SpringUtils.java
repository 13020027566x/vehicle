package com.finhub.framework.core;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 覆盖 Hutool SpringUtil
 * 借助 BeanPostProcessor 前置 SpringUtil 初始化
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class SpringUtils extends SpringUtil implements BeanPostProcessor {

    /**
     * 获取aop代理对象
     *
     * @param invoker
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }
}

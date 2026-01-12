package com.finhub.framework.mybatis.handler;

import com.finhub.framework.core.thread.ThreadLocalUtils;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

/**
 * 自定义多租户配置处理器
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/05/18 21:25
 */
public interface CustomTenantLineHandler extends TenantLineHandler {

    org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomTenantLineHandler.class);

    static CustomTenantLineHandler me() {
        try {
            return SpringUtil.getBean(CustomTenantLineHandler.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new CustomTenantLineHandler() {};
        }
    }

    @Override
    default Expression getTenantId() {
        return new StringValue(ThreadLocalUtils.getTenantId());
    }
}

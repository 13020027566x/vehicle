package com.finhub.framework.i18n.annotation;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标示国际化文件名注解
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午1:53
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface I18n {
    /**
     * 国际化文件名
     */
    String value() default StrUtil.EMPTY;
}

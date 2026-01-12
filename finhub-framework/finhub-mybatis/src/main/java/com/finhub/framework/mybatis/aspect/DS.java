package com.finhub.framework.mybatis.aspect;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mickey
 * @version 1.0
 * @since 15-8-25 上午11:58
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DS {

    String value() default StrUtil.EMPTY;
}

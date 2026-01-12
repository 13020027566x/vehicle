package com.finhub.framework.core.annotation;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitize {

    String field();

    DesensitizedUtil.DesensitizedType type();

    String desensitizeField() default StrUtil.EMPTY;
}

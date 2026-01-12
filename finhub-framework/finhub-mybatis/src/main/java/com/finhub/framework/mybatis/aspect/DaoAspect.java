package com.finhub.framework.mybatis.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DAO 层切面注解
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoAspect {

}

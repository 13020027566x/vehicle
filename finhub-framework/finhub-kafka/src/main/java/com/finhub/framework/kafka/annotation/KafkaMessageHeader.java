package com.finhub.framework.kafka.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MQ 消息注解类
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023/07/13 17:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface KafkaMessageHeader {

    String projectKey() default "";

    String projectDesc() default "";

    String topic() default "default";

    int orderTypeId() default 0;

    String orderTypeDesc() default "";
}

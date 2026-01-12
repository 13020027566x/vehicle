package com.finhub.framework.validator.manager;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;


/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/14 17:56
 */
@Data
@Slf4j
@AllArgsConstructor
public class ValidatorManager {

    private Validator validator;

    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return validator.validate(object, groups);
    }

    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return validator.validateProperty(object, propertyName, groups);
    }

    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return validator.validateValue(beanType, propertyName, value, groups);
    }

    public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return validator.getConstraintsForClass(clazz);
    }

    public <T> T unwrap(Class<T> type) {
        return validator.unwrap(type);
    }

    public ExecutableValidator forExecutables() {
        return validator.forExecutables();
    }
}

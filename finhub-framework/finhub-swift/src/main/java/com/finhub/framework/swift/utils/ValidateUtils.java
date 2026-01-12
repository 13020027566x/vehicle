package com.finhub.framework.swift.utils;

import cn.hutool.core.exceptions.ValidateException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * @Author shuangfei.chen
 * @Description ValidateUtils
 * @Date 2021/8/16 16:29
 **/
@Slf4j
@UtilityClass
public class ValidateUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> T validate(T obj) {
        if (obj == null) {
            throw new ValidateException("参数对象不能为空");
        }
        Set<ConstraintViolation<T>> violationSet = validator.validate(obj);
        for (ConstraintViolation<T> violation : violationSet) {
            throw new ValidateException(String.format("参数[%s]%s", violation.getPropertyPath(), violation.getMessage()));
        }
        return obj;
    }

}

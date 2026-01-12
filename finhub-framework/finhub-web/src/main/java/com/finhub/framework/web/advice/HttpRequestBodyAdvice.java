package com.finhub.framework.web.advice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 对 @RequestBody 注解请求进行处理
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Data
@Slf4j
@RestControllerAdvice
@ConditionalOnWebApplication
public class HttpRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        log.info("HttpRequestBodyAdvice supports. [supports={}]", false);
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return null;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
        Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}

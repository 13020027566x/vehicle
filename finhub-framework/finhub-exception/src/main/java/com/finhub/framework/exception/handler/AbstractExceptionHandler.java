package com.finhub.framework.exception.handler;

import com.finhub.framework.exception.BaseException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 异常处理器
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2021/08/30 14:43
 */
@Slf4j
public class AbstractExceptionHandler {

    public static final String S_COLON = ":";

    public static final String S_SEMICOLON = ";";

    @Deprecated
    public static final String RESULT_KEY = "result";

    public static final String CODE_PREFIX = "response.";

    @Autowired
    protected MessageSource messageSource;

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getResponseMessage(BaseException e) {
        String code = CODE_PREFIX + e.getCodeEnum().getCode();
        String message = this.getMessage(code, e.getArgs());

        if (code.equals(message)) {
            return e.getMessage();
        }

        return message;
    }

    /**
     * 获取国际化消息
     *
     * @param code 消息code
     * @param args 参数
     * @return
     */
    private String getMessage(String code, Object[] args) {

        return getMessage(code, args, code);
    }

    /**
     * 获取国际化消息
     *
     * @param code           消息code
     * @param args           参数
     * @param defaultMessage 默认消息
     * @return
     */
    private String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            return messageSource.getMessage(code, args, defaultMessage, locale);
        } catch (Exception e) {
            log.warn("Get i18n local message fail. return empty.", e);
            return code;
        }
    }
}

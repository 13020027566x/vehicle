package com.finhub.framework.i18n.manager;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

/**
 * 国际化处理类
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/1 下午8:43
 */
@Slf4j
public class MessageSourceManager extends MessageSourceAccessor {

    public static MessageSourceManager me() {
        return SpringUtil.getBean(MessageSourceManager.class);
    }

    public MessageSourceManager(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    public Locale getDefaultLocale() {
        return super.getDefaultLocale();
    }

    /**
     * 参考：@Link{String getMessage(String key, Object[] args, Locale locale)}
     *
     * @return
     */
    @Override
    public String getMessage(String key, Object[] args) {
        return super.getMessage(key, args);
    }

    /**
     * 根据key得到译文内容，根据args替换译文内容里面的格式化字符，locale指定语言信息，例如：
     * key="{0}， {1, number, currency}"，args={99,100000}，locale=Locale.US;
     * 输出：99，$100,000.00
     * key="{0}， {1, number, currency}"，args={99,100000}，locale=Locale.CHINA;
     * 输出：99，￥100,000.00
     *
     * @param key    译文的标识
     * @param args   字符串替换信息
     * @param locale 语言类型
     * @return
     */
    @Override
    public String getMessage(String key, Object[] args, Locale locale) {
        return super.getMessage(key, args, locale);
    }

    /**
     * 根据key得到译文内容
     *
     * @param key 译文的标识
     * @return
     */
    @Override
    public String getMessage(String key) {
        return super.getMessage(key);
    }

    /**
     * @param key    译文的标识
     * @param locale 语言类型
     * @return
     */
    @Override
    public String getMessage(String key, Locale locale) {
        return super.getMessage(key, null, null, locale);
    }

    /**
     * @param key            译文的标识
     * @param args           字符串替换信息 参考：@Link{String getMessage(String key, Object[] args, Locale locale)}
     * @param defaultMessage 未知key的默认值
     * @param locale         语言类型
     * @return
     */
    @Override
    public String getMessage(String key, Object[] args, String defaultMessage, Locale locale) {
        return super.getMessage(key, args, defaultMessage, locale);
    }

    /**
     * @param resolvable 自定义多语言参数结构体，包括：code（key），args，defaultMessage参数
     * @param locale     语言类型
     * @return
     */
    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) {
        return super.getMessage(resolvable, locale);
    }
}

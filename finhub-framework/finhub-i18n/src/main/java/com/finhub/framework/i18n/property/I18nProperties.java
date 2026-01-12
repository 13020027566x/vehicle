package com.finhub.framework.i18n.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * I18n Server 配置
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/2 上午8:27
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "i18n")
public class I18nProperties {

    public static I18nProperties me() {
        try {
            return SpringUtil.getBean(I18nProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new I18nProperties();
        }
    }

    private static final I18nProperties INSTANCE = new I18nProperties();

    private String baseFolder = "i18n";

    private String baseName = "messages";

    private String defaultEncoding = "UTF-8";

    private boolean concurrentRefresh = true;

    private boolean useCodeAsDefaultMessage = true;

    private int cacheSeconds = -1;

    private String defaultLocale = "zh_CN";

    private String supportedLocales = "zh_CN,en_US";

    private String interceptorName = "lang";

    private String interceptorResolverType = "HEADER";

    private String interceptorPatterns = "/**";

}

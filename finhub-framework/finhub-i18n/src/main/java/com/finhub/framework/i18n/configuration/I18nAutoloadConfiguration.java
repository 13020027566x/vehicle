package com.finhub.framework.i18n.configuration;

import com.finhub.framework.i18n.component.CustomReloadableResourceBundleMessageSource;
import com.finhub.framework.i18n.constant.I18nConstants;
import com.finhub.framework.i18n.constant.LocaleTypeEnum;
import com.finhub.framework.i18n.interceptor.CustomLocaleChangeInterceptor;
import com.finhub.framework.i18n.interceptor.MessageResourceInterceptor;
import com.finhub.framework.i18n.manager.MessageSourceManager;
import com.finhub.framework.i18n.property.I18nProperties;
import com.finhub.framework.i18n.resolver.CustomHeaderLocaleResolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <pre>
 * I18n 配置
 * </pre>
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
@EnableConfigurationProperties({I18nProperties.class})
@ConditionalOnProperty(name = "vehicle.i18n.enabled", matchIfMissing = true)
public class I18nAutoloadConfiguration implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource(I18nProperties i18nProperties) {
        CustomReloadableResourceBundleMessageSource messageBundle = new CustomReloadableResourceBundleMessageSource();

        messageBundle.setI18nProperties(i18nProperties);
        messageBundle.setDefaultEncoding(i18nProperties.getDefaultEncoding());
        messageBundle.setConcurrentRefresh(i18nProperties.isConcurrentRefresh());
        messageBundle.setUseCodeAsDefaultMessage(i18nProperties.isUseCodeAsDefaultMessage());
        messageBundle.setCacheSeconds(i18nProperties.getCacheSeconds());

        return messageBundle;
    }

    private Locale handleDefaultLocale(I18nProperties i18nProperties) {
        Locale defaultLocale = LocaleTypeEnum.getValue((i18nProperties.getDefaultLocale()));
        if (defaultLocale != null) {
            Locale.setDefault(defaultLocale);
        }
        return defaultLocale;
    }

    @Bean
    public MessageSourceManager getDefaultMessageSourceAccessor(MessageSource messageSource, I18nProperties i18nProperties) {
        return new MessageSourceManager(messageSource);
    }

    @Bean(name = DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
    public LocaleResolver localeResolver(I18nProperties i18nProperties) {
        CustomHeaderLocaleResolver resolver = new CustomHeaderLocaleResolver();

        resolver.setSupportedLocales(
            Arrays.stream(i18nProperties.getSupportedLocales().split(I18nConstants.S_COMMA))
                .map(LocaleTypeEnum::getValue)
                .collect(Collectors.toList()));
        resolver.setDefaultLocale(handleDefaultLocale(i18nProperties));

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = I18nProperties.me().getInterceptorPatterns().split(I18nConstants.S_COMMA);

        CustomLocaleChangeInterceptor customLocaleChangeInterceptor = new CustomLocaleChangeInterceptor();
        registry.addInterceptor(customLocaleChangeInterceptor).addPathPatterns(patterns);

        MessageResourceInterceptor messageResourceInterceptor = new MessageResourceInterceptor();
        registry.addInterceptor(messageResourceInterceptor).addPathPatterns(patterns);
    }
}

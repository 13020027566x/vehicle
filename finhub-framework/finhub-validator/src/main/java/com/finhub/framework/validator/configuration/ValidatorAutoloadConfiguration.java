package com.finhub.framework.validator.configuration;

import com.finhub.framework.validator.boot.SpringBootProperties;
import com.finhub.framework.validator.manager.ValidatorManager;

import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Configuration
@AutoConfigureBefore({ValidationAutoConfiguration.class})
@EnableConfigurationProperties({SpringBootProperties.class})
@ConditionalOnProperty(name = "vehicle.validator.enabled", matchIfMissing = true)
public class ValidatorAutoloadConfiguration {

    private final ApplicationContext applicationContext;
    private final ObjectProvider<ValidationConfigurationCustomizer> customizers;

    public ValidatorAutoloadConfiguration(
        ApplicationContext applicationContext,
        ObjectProvider<ValidationConfigurationCustomizer> customizers) {
        this.applicationContext = applicationContext;
        this.customizers = customizers;
    }

    @Bean
    public ValidatorManager validatorManager(Validator validator) {
        return new ValidatorManager(validator);
    }

    /**
     *
     * @return jakarta.validation.Validator 对象
     */
    @Bean
    public Validator validator(MessageSource messageSource) {
        // Spring Boot 3.5.8 需要传递参数
        LocalValidatorFactoryBean validator = ValidationAutoConfiguration.defaultValidator(
            applicationContext,
            customizers
        );

        // 设置 messageSource 属性，实现 i18n 国际化
        validator.setValidationMessageSource(messageSource);

        return validator;
    }
}

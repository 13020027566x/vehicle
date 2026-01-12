package com.finhub.framework.safety.configuration;

import com.finhub.framework.safety.jayspt.AvailableStringEncryptor;
import com.finhub.framework.safety.jayspt.CustomEncryptablePropertyDetector;
import com.finhub.framework.safety.jayspt.CustomEncryptablePropertyResolver;
import com.finhub.framework.safety.jayspt.CustomStringEncryptor;
import com.finhub.framework.safety.jayspt.JaysptAdapter;
import com.finhub.framework.safety.kms.key.KmsDataKeyPropertyDetector;
import com.finhub.framework.safety.kms.key.KmsDataKeyStringEncryptor;
import com.finhub.framework.safety.kms.key.KmsKeyPropertyDetector;
import com.finhub.framework.safety.kms.key.KmsKeyStringEncryptor;
import com.finhub.framework.safety.kms.secret.KmsSecretPropertyDetector;
import com.finhub.framework.safety.kms.secret.KmsSecretStringEncryptor;
import com.finhub.framework.safety.property.SafetyProperties;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.configuration.StringEncryptorBuilder;
import com.ulisesbocchio.jasyptspringboot.detector.DefaultPropertyDetector;
import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import com.ulisesbocchio.jasyptspringboot.resolver.DefaultPropertyResolver;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

import static com.finhub.framework.safety.constant.SafetyConstants.JASYPT_ENCRYPTOR_PREFIX;

@Slf4j
@Configuration
@EnableEncryptableProperties
@EnableConfigurationProperties({SafetyProperties.class})
@ConditionalOnProperty(name = "vehicle.safety.enabled", matchIfMissing = true)
public class SafetyAutoloadConfiguration {

    private JasyptEncryptorConfigurationProperties jasyptEncryptorConfigurationProperties(ConfigurableEnvironment environment) {
        JasyptEncryptorConfigurationProperties props = JasyptEncryptorConfigurationProperties.bindConfigProps(environment);
        if (StrUtil.isBlank(props.getPassword())) {
            // 如未配置jasypt.encryptor.password，则将设置默认密钥
            props.setPassword("moI4YQqtZmTVZx5i4XHyXSpwtGJzrdbK");
        }
        return props;
    }

    @Bean
    public StringEncryptor defaultStringEncryptor(ConfigurableEnvironment environment) {
        return new StringEncryptorBuilder(jasyptEncryptorConfigurationProperties(environment), JASYPT_ENCRYPTOR_PREFIX).build();
    }

    @Bean
    public EncryptablePropertyDetector defaultPropertyDetector(ConfigurableEnvironment environment) {
        JasyptEncryptorConfigurationProperties props = jasyptEncryptorConfigurationProperties(environment);
        return new DefaultPropertyDetector(props.getProperty().getPrefix(), props.getProperty().getSuffix());
    }

    @Bean
    public KmsKeyStringEncryptor kmsKeyStringEncryptor() {
        return new KmsKeyStringEncryptor();
    }

    @Bean
    public KmsKeyPropertyDetector kmsKeyPropertyDetector(KmsKeyStringEncryptor kmsKeyStringEncryptor) {
        return new KmsKeyPropertyDetector(kmsKeyStringEncryptor);
    }

    @Bean
    public KmsDataKeyStringEncryptor kmsDataKeyStringEncryptor() {
        return new KmsDataKeyStringEncryptor();
    }

    @Bean
    public KmsDataKeyPropertyDetector kmsDataKeyPropertyDetector(KmsDataKeyStringEncryptor kmsDataKeyStringEncryptor) {
        return new KmsDataKeyPropertyDetector(kmsDataKeyStringEncryptor);
    }

    @Bean
    public KmsSecretStringEncryptor kmsSecretStringEncryptor() {
        return new KmsSecretStringEncryptor();
    }

    @Bean
    public KmsSecretPropertyDetector kmsSecretPropertyDetector(KmsSecretStringEncryptor kmsSecretStringEncryptor) {
        return new KmsSecretPropertyDetector(kmsSecretStringEncryptor);
    }

    @Bean
    public JaysptAdapter jaysptAdapter(@Qualifier("defaultStringEncryptor") StringEncryptor encryptor, @Qualifier("defaultPropertyDetector") EncryptablePropertyDetector detector, KmsKeyPropertyDetector kmsKeyPropertyDetector, KmsDataKeyPropertyDetector kmsDataKeyPropertyDetector, KmsSecretPropertyDetector kmsSecretPropertyDetector) {
        Map<String, AvailableStringEncryptor> prefixAvailableStringEncryptorMap = Maps.newHashMap();
        prefixAvailableStringEncryptorMap.put(KmsKeyPropertyDetector.PREFIX, kmsKeyPropertyDetector);
        prefixAvailableStringEncryptorMap.put(KmsDataKeyPropertyDetector.PREFIX, kmsDataKeyPropertyDetector);
        prefixAvailableStringEncryptorMap.put(KmsSecretPropertyDetector.PREFIX, kmsSecretPropertyDetector);

        return new JaysptAdapter(encryptor, detector, prefixAvailableStringEncryptorMap);
    }

    @Bean("jasyptStringEncryptor")
    @ConditionalOnMissingBean
    public StringEncryptor jasyptStringEncryptor(@Qualifier("jaysptAdapter") JaysptAdapter jaysptAdapter) {
        return new CustomStringEncryptor(jaysptAdapter);
    }

    @Bean("encryptablePropertyDetector")
    @ConditionalOnMissingBean
    public CustomEncryptablePropertyDetector encryptablePropertyDetector(@Qualifier("jaysptAdapter") JaysptAdapter jaysptAdapter) {
        return new CustomEncryptablePropertyDetector(jaysptAdapter);
    }

    @Bean
    public EncryptablePropertyResolver defaultPropertyResolver(@Qualifier("jasyptStringEncryptor") StringEncryptor encryptor, @Qualifier("encryptablePropertyDetector") EncryptablePropertyDetector detector, ConfigurableEnvironment environment) {
        return new DefaultPropertyResolver(encryptor, detector, environment);
    }

    @Bean("encryptablePropertyResolver")
    @ConditionalOnMissingBean
    public EncryptablePropertyResolver encryptablePropertyResolver(@Qualifier("defaultPropertyResolver") EncryptablePropertyResolver defaultPropertyResolver) {
        return new CustomEncryptablePropertyResolver(defaultPropertyResolver);
    }
}

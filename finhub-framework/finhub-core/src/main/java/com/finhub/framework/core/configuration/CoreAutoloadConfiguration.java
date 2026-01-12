package com.finhub.framework.core.configuration;

import com.finhub.framework.core.SpringUtils;
import com.finhub.framework.core.actuator.CustomHealthEndpointWebExtension;
import com.finhub.framework.core.actuator.config.ActuatorConfig;
import com.finhub.framework.core.date.DateConstants;
import com.finhub.framework.core.environment.EnvConfig;
import com.finhub.framework.core.json.JsonProperties;
import com.finhub.framework.core.json.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.actuate.health.HealthEndpointGroups;
import org.springframework.boot.actuate.health.HealthEndpointWebExtension;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static com.finhub.framework.core.json.JsonUtils.getInstance;
import static com.finhub.framework.core.json.JsonUtils.setObjectMapperConfigure;

@Slf4j
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({JsonProperties.class})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ConditionalOnProperty(name = "vehicle.core.enabled", matchIfMissing = true)
public class CoreAutoloadConfiguration {

    @Bean
    EnvConfig envConfig() {
        return new EnvConfig();
    }

    @Bean
    ActuatorConfig actuatorConfig() {
        return new ActuatorConfig();
    }

    @Bean
    public CoreBeanPostProcessor coreBeanPostProcessor() {
        return new CoreBeanPostProcessor();
    }

    @Bean
    HealthEndpointWebExtension healthEndpointWebExtension(HealthContributorRegistry healthContributorRegistry,
                                                          HealthEndpointGroups groups) {
        // 添加第三个参数：Duration，可以使用 null 或合适的值
        return new CustomHealthEndpointWebExtension(
            healthContributorRegistry,
            groups,
            Duration.ofSeconds(10)  // 或者使用 null
        );
    }

    /**
     * 全局格式化 LocalDate 、 LocalDateTime 类型
     * 如需在特定的字段属性添加 @JsonFormat 注解即可，因为 @JsonFormat 注解优先级比较高，会以 @JsonFormat 注解标注的时间格式为主
     * 示例：
     * 1. @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
     * 2. @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     * 3. @JsonFormat(pattern = DateConstants.NORM_DATETIME_PATTERN)
     *
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateConstants.NORM_DATETIME_PATTERN)));
            builder.serializerByType(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DateConstants.NORM_DATE_PATTERN)));
            builder.serializerByType(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateConstants.NORM_TIME_PATTERN)));
            builder.deserializerByType(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateConstants.NORM_DATETIME_PATTERN)));
            builder.deserializerByType(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateConstants.NORM_DATE_PATTERN)));
            builder.deserializerByType(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateConstants.NORM_TIME_PATTERN)));

            builder.dateFormat(DateConstants.NORM_DATETIME_FORMAT);
            builder.timeZone(TimeZone.getDefault());
        };
    }

    /**
     * Jackson ObjectMapper 配置
     * 相关链接：
     * （1）https://blog.csdn.net/m0_69305074/article/details/125583813
     * （2）https://segmentfault.com/a/1190000041997183
     *
     * @param builder
     * @param jsonProperties
     * @return
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder, JsonProperties jsonProperties) {
        return setObjectMapperConfigure(builder.createXmlMapper(false).build(), null, jsonProperties);
    }

    /**
     * JsonUtils Bean
     *
     * @param jsonProperties
     * @return
     */
    @Bean
    public JsonUtils jsonUtils(JsonProperties jsonProperties) {
        return getInstance(jsonProperties);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SpringUtils springUtils() {
        return new SpringUtils();
    }
}

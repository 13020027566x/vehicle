package com.finhub.framework.kafka.configuration;

import com.finhub.framework.kafka.producer.KafkaProducerPublisher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka 生产者配置（默认不启用，必须设置 kafka.producer.enabled=true 启用）
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023/07/13 17:41
 */
@Slf4j
@EnableKafka
@Configuration
@ConditionalOnProperty(name = "kafka.producer.enabled")
public class KafkaProducerAutoloadConfiguration {

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${key.serializer:org.apache.kafka.common.serialization.StringSerializer}")
    private String keySerializer;

    @Value("${value.serializer:org.apache.kafka.common.serialization.StringSerializer}")
    private String valueSerializer;

    @Value("${acks:all}")
    private String acks;

    @Value("${batch.size:16384}")
    private int batchSize;

    @Value("${linger.ms:0}")
    private long lingerMs;

    @Value("${max.request.size:1048576}")
    private int maxRequestSize;

    @Value("${retries:0}")
    private int retries;

    @Value("${compression.type:none}")
    private String compressionType;

    @Value("${partitioner.class:org.apache.kafka.clients.producer.internals.DefaultPartitioner}")
    private Class partitionerClass;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        configs.put(ProducerConfig.ACKS_CONFIG, acks);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configs.put(ProducerConfig.LINGER_MS_CONFIG , lingerMs);
        configs.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize);
        configs.put(ProducerConfig.RETRIES_CONFIG, retries);
        configs.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, partitionerClass);

        return new DefaultKafkaProducerFactory(configs);
    }

    @Bean
    @ConditionalOnMissingBean(name = "kafkaTemplate")
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate(producerFactory, true);
    }

    @Bean
    public KafkaProducerPublisher kafkaProducerPublisher(@Qualifier("kafkaTemplate") KafkaTemplate<String, Object> kafkaTemplate) {
        return new KafkaProducerPublisher(kafkaTemplate);
    }
}

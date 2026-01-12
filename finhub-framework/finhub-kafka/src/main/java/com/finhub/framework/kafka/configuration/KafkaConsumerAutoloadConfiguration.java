package com.finhub.framework.kafka.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode; // 添加这个导入

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka 消费者配置（默认不启用，必须设置 kafka.consumer.enabled=true 启用）
 */
@Slf4j
@EnableKafka
@Configuration
@ConditionalOnProperty(name = "kafka.consumer.enabled")
public class KafkaConsumerAutoloadConfiguration {

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${group.id:default}")
    private String groupId;

    @Value("${pool.timeout:30000}")
    private String poolTimeout;

    @Value("${consumer.pool.timeout:1000}")
    private long consumerPoolTimeout = 1000;

    @Value("${fetch.min.bytes:1}")
    private String fetchMinBytes;

    @Value("${fetch.max.wait.ms:500}")
    private String fetchMaxWaitMs;

    @Value("${key.deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
    private String keyDeserializer;

    @Value("${value.deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
    private String valueDeserializer;

    @Value("${consumer.concurrency:10}")
    private int consumerConcurrency = 10;

    @Value("${enable.auto.commit:true}")
    private boolean autoCommit;

    @Value("${max.poll.records:500}")
    private int maxPollRecords;

    @Value("${max.poll.interval.ms:300000}")
    private int maxPollIntervalMs;

    @Value("${fetch.max.bytes:52428800}")
    private int fetchMaxBytes;

    @Value("${heartbeat.interval.ms:3000}")
    private int heartbeatIntervalMs;

    @Value("${auto.commit.interval.ms:5000}")
    private int autoCommitIntervalMs;

    @Value("${max.partition.fetch.bytes:1048576}")
    private int maxPartitionFetchBytes;

    /**
     * 创建 KafkaListenerContainerFactory（自动提交）
     */
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(consumerConcurrency);
        factory.getContainerProperties().setPollTimeout(consumerPoolTimeout);
        factory.setAutoStartup(true);
        return factory;
    }

    /**
     * 创建 AckListenerContainerFactory（手动提交）
     */
    @Bean("ackListenerContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> ackListenerContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE); // 使用正确的常量
        factory.setConcurrency(consumerConcurrency);
        factory.getContainerProperties().setPollTimeout(consumerPoolTimeout);
        factory.setAutoStartup(true);
        return factory;
    }

    /**
     * 创建ConsumerFactory
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, poolTimeout);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        configs.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, fetchMinBytes);
        configs.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, fetchMaxWaitMs);
        configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollIntervalMs);
        configs.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatIntervalMs);
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMs);
        configs.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, fetchMaxBytes);
        configs.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, maxPartitionFetchBytes);

        return new DefaultKafkaConsumerFactory<>(configs);
    }
}

package com.finhub.framework.kafka.producer;

import com.finhub.framework.core.json.JsonUtils;
import com.finhub.framework.kafka.annotation.KafkaMessageHeader;
import com.finhub.framework.kafka.message.Message;
import cn.hutool.extra.spring.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * MQ 消息提供者工具类
 */
@Data
@Slf4j
@AllArgsConstructor
public class KafkaProducerPublisher {

    private static final Map<String, KafkaMessageHeader> KAFKA_MESSAGE_HEADER_MAP = new ConcurrentHashMap<>();

    public static KafkaProducerPublisher me() {
        return SpringUtil.getBean(KafkaProducerPublisher.class);
    }

    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(Message message) {
        KafkaMessageHeader header = getHeader(message);
        sendMessage(header.topic(), null, null, JsonUtils.toJson(message));
    }

    public void publish(Message message, String partitionKey) {
        KafkaMessageHeader header = getHeader(message);
        sendMessage(header.topic(), null, partitionKey, JsonUtils.toJson(message));
    }

    private KafkaMessageHeader getHeader(Message message) {
        String messageName = message.getClass().getName();
        Optional<KafkaMessageHeader> header = Optional.ofNullable(KAFKA_MESSAGE_HEADER_MAP.get(messageName));
        if (header.isPresent()) {
            return header.get();
        }
        header = Optional.ofNullable(message.getClass().getAnnotation(KafkaMessageHeader.class));
        header.ifPresent(h -> KAFKA_MESSAGE_HEADER_MAP.put(messageName, h));
        return header.orElse(null);
    }

    private void sendMessage(String topic, Integer partition, String key, String data) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, data);

        // 使用 KafkaCallbackAdapter 处理回调
        KafkaCallbackAdapter.addCallback(future,
            result -> log.info("Kafka message send success. [topic={}, data={}, result={}]",
                topic, data, JsonUtils.toJson(result)),
            ex -> log.error("Kafka message send fail. [topic={}, data={}]", topic, data, ex)
        );
    }

    public void publishSync(Message message) {
        KafkaMessageHeader header = getHeader(message);
        sendMessageSync(header.topic(), null, null, JsonUtils.toJson(message));
    }

    private void sendMessageSync(String topic, Integer partition, String key, String data) {
        try {
            // 同步发送，等待结果
            SendResult<String, Object> result = kafkaTemplate.send(topic, key, data).get();
            log.info("Kafka message send success synchronously. [topic={}, data={}, result={}]",
                topic, data, JsonUtils.toJson(result));
        } catch (Exception e) {
            log.error("Kafka message send fail synchronously. [topic={}, data={}]", topic, data, e);
            throw new RuntimeException("Kafka send failed", e);
        }
    }
}

/**
 * Kafka 回调适配器 - 独立的工具类
 */
class KafkaCallbackAdapter {

    private KafkaCallbackAdapter() {
        // 工具类，私有构造函数
    }

    public static <T> void addCallback(CompletableFuture<SendResult<String, T>> future,
                                       Consumer<SendResult<String, T>> successCallback,
                                       Consumer<Throwable> failureCallback) {
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                failureCallback.accept(ex);
            } else {
                successCallback.accept(result);
            }
        });
    }
}

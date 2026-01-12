package com.finhub.framework.kafka.message;

import lombok.Data;

import java.io.Serializable;

/**
 * MQ 消息基类
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2023/07/13 17:41
 */
@Data
public class Message implements Serializable {

    private String name;
}

/**
 * @Copyright (c) 2018/8/27, Lianjia Group All Rights Reserved.
 */
package com.wilson.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * rabbitmq 生产者
 *
 * @author wilson wei
 * @version 1.0
 * @since 2018/8/27 14:25
 */
@Component
@Slf4j
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param message 消息
     * @param properties 附加属性
     * @author wilson wei
     * @date 14:28 2018/8/27
     */
    public void send(Object message, Map<String, Object> properties){
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("1234567890");
        rabbitTemplate.convertAndSend("hello-exchange", "hello", msg, correlationData);
    }

    /**
     * 回调函数：消息发送 confirm 确认
     * @date 14:31 2018/8/27
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = (CorrelationData correlationData, boolean ack, String cause) -> {

        log.info("correlationData: {}", correlationData);
        log.info("ack: {}", ack);
        log.info("cause: {}", cause);
        if (!ack) {
            // 消息发送出现异常，需要立即处理
            log.error("消息发送异常, {}", correlationData);
        }
    };

    /**
     * 回调函数：消息发送 return 返回
     * @date 14:31 2018/8/27
     */
    private final RabbitTemplate.ReturnCallback returnCallback = (org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) -> {

        log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message.getBody());
    };

}

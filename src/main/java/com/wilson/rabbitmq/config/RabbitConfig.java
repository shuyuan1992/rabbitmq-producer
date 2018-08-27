/**
 * @Copyright (c) 2018/8/27, Lianjia Group All Rights Reserved.
 */
package com.wilson.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit 配置类
 * @author wilson wei
 * @version 1.0
 * @since 2018/8/27 14:44
 */
@Configuration
public class RabbitConfig {

    private static final String HELLO_EXCHANGE = "hello-exchange";

    private static final String HELLO_QUEUE = "hello-queue";

    private static final String HELLO_ROUTING_KEY = "hello";

    // 1. 声明交换器

    @Bean
    public Exchange helloExchange () {
        return ExchangeBuilder.directExchange(HELLO_EXCHANGE).durable(true).build();
    }

    // 2. 声明队列

    @Bean
    public Queue helloQueue () {
        return QueueBuilder.durable(HELLO_QUEUE).build();
    }

    // 3. 绑定队列，交换器

    @Bean
    public Binding helloBinding() {
        return BindingBuilder.bind(helloQueue()).to(helloExchange()).with(HELLO_ROUTING_KEY).noargs();
    }
}

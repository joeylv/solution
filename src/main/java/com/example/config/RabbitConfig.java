package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String DIRECT_ROUTING_KEY_RECV_QUEUE = "recv_queue";
    public static final String DIRECT_ROUTING_KEY_SEND_QUEUE = "send_queue";

    @Bean
    public Queue recvQueue() {
        return new Queue(DIRECT_ROUTING_KEY_RECV_QUEUE, true);
    }

    @Bean
    public Queue sendQueue() {
        return new Queue(DIRECT_ROUTING_KEY_SEND_QUEUE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(sendQueue()).to(exchange()).withQueueName();
    }


}

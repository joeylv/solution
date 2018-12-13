package com.example.mq;

import com.example.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageProducer {
    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    public AmqpTemplate amqpTemplate;

    public void sendMessage(Object msg) {
        logger.info("To send message :" + msg);
        amqpTemplate.convertAndSend(RabbitConfig.DIRECT_ROUTING_KEY_SEND_QUEUE, msg);
    }
}

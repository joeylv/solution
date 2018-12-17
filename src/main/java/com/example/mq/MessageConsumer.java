package com.example.mq;

import com.example.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);


    @Autowired
    MessageProducer messageProducer;


    /***
     * 监听方法 传入参数需要和消息生产者一致
     * @param msg
     */
    @RabbitListener(queues = RabbitConfig.DIRECT_ROUTING_KEY_RECV_QUEUE) //监听 recv_queue 队列
    @RabbitHandler //指定对消息的处理方法
    public void onMessage(Message msg) {
        logger.info("On message:" + msg);
//        User user = userService.getUser(new String(msg.getBody()));
        try {
            messageProducer.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 业务消费
     */
    @RabbitListener(queues = RabbitConfig.DIRECT_ROUTING_KEY_SEND_QUEUE) //监听 send_queue 队列
    @RabbitHandler //指定对消息的处理方法
    public void basicMessage(Object msg) {
        System.out.println(" Received :::" + msg + "");
    }

}

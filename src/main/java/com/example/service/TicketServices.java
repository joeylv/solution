package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TicketServices {
    public final Logger logger = LoggerFactory.getLogger(TicketServices.class);
    @Resource(name = "mainRedisTemplate")
    StringRedisTemplate mainRedisTemplate;

    @Autowired
    DatabaseService databaseService;


    public Object queryTicketStock(final String ticketSeq) {
        //1. 先到缓存中取数据
        String value = mainRedisTemplate.opsForValue().get(ticketSeq);
        if (value != null) {
            logger.warn(Thread.currentThread().getName());
            return value;
        }

//        //2.缓存中没有数据
//        value = databaseService.query(ticketSeq);
//        System.out.println(value);
//
//        //3. 缓存120秒过期
//        final String v = value;
//        mainRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setEx(ticketSeq.getBytes(), 120, v.getBytes()));

        return value;
    }

}

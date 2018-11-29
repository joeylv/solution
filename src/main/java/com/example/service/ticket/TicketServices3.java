package com.example.service.ticket;

import com.example.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TicketServices3 implements TickerInterface {
    public final Logger logger = LoggerFactory.getLogger(TicketServices3.class);

    @Autowired
    DatabaseService databaseService;

    @Resource(name = "mainRedisTemplate")
    public StringRedisTemplate mainRedisTemplate;

    @Resource(name = "bakRedisTemplate")
    public StringRedisTemplate bakRedisTemplate;


    ConcurrentHashMap<String, String> mapLock = new ConcurrentHashMap<>();


    /**
     * 缓存降级
     */
    @Override
    public Object queryTicketStock(final String ticketSeq) {
        // 1. 从主缓存中取数据
        String value = mainRedisTemplate.opsForValue().get(ticketSeq);
        if (value != null) {
            logger.warn(Thread.currentThread().getName());
            return value;
        }
        boolean lock;


        try {
            //1000个线程并发争夺锁 1个拿到锁 999个采用缓存降级
            lock = mapLock.putIfAbsent(ticketSeq, "true") == null;

            if (lock) {
                //2.缓存中没有数据
                value = databaseService.query(ticketSeq);
                System.out.println("缓存中没有数据 从数据库中读取数据" + value);

                //3. 缓存120秒过期
                final String v = value;
                mainRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setEx(ticketSeq.getBytes(), 120, v.getBytes()));

                // 4. 操作缓存的时候 双写 备份缓存 不设置过期时间
                bakRedisTemplate.opsForValue().set(ticketSeq, value);
            } else {
                value = bakRedisTemplate.opsForValue().get(ticketSeq);
                if (value != null) {
                    // 缓存降级 备份缓存
                    System.out.println(Thread.currentThread().getName() + "降级，没有拿到锁的线程从取备份缓存取数据" + value);
                } else {
                    // 缓存降级 固定值
                    value = "0";
                    System.out.println(Thread.currentThread().getName() + "降级，没拿到锁&备份缓没有取到数据，使用固定值" + value);
                }
            }
        } finally {
            // 释放锁
            mapLock.remove(ticketSeq);
        }
        return value;
    }

}

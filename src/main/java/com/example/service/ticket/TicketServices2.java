package com.example.service.ticket;

import com.example.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TicketServices2 implements TickerInterface {
    public final Logger logger = LoggerFactory.getLogger(TicketServices2.class);

    @Autowired
    DatabaseService databaseService;

    @Resource(name = "mainRedisTemplate")
    public StringRedisTemplate mainRedisTemplate;

    Lock lock = new ReentrantLock();


    /**
     * 线程等待
     */
    @Override
    public Object queryTicketStock(final String ticketSeq) {
        String value = mainRedisTemplate.opsForValue().get(ticketSeq);
        if (value != null) {
            logger.warn(Thread.currentThread().getName());
            return value;
        }

        lock.lock();
        //1000个线程并发争夺锁 1个拿到锁 999个等待
        try {
            // 再次查询缓存
            value = mainRedisTemplate.opsForValue().get(ticketSeq);
            if (value != null) {
                logger.warn(Thread.currentThread().getName());
                return value;
            }

            //2.缓存中没有数据
            value = databaseService.query(ticketSeq);
            System.out.println("缓存中没有数据 从数据库中读取数据" + value);

            //3. 缓存120秒过期
            final String v = value;
            mainRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setEx(ticketSeq.getBytes(), 120, v.getBytes()));

        } finally {
            lock.unlock();

        }


        return value;
    }

}

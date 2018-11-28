package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MiaoshaService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    DatabaseService databaseService;

    @PostConstruct
    public void init() {
        System.out.println("初始化完毕！");
    }

    public boolean miaosha(String goodCode, String userId) {

        return databaseService.buy(goodCode, userId);

    }


}

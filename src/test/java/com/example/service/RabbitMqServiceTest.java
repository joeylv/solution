package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RabbitMqServiceTest {

    // 线程数量
    public final int THREAD_NUM = 300;

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void concurrentTest() {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ResponseEntity<String> result = restTemplate.getForEntity("", String.class);
                System.out.println(result);
            }).start();
        }

    }
}
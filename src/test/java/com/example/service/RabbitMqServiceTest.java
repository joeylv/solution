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
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {

            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8080/user/get/1", String.class);
                    System.out.println(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i] = thread;
            thread.start();
            countDownLatch.countDown();
        }
        System.out.println(":::::::::::::::::End");
    }
}
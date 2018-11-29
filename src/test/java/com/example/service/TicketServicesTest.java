package com.example.service;

import com.example.service.ticket.TicketServices;
import com.example.service.ticket.TicketServices2;
import com.example.service.ticket.TicketServices3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketServicesTest {
    @Autowired
    private TicketServices ticketServices;

    @Autowired
    private TicketServices2 ticketServices2;

    @Autowired
    private TicketServices3 ticketServices3;
    private static final String TICKER_SEQ = "G296";
    private static final int THREAD_NUM = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    @Test
    public void benchmark() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    // 使用Redis缓存
//                    ticketServices.queryTicketStock(TICKER_SEQ);
                    // 增加锁
//                    ticketServices2.queryTicketStock(TICKER_SEQ);
                    //缓存降级
                    ticketServices3.queryTicketStock(TICKER_SEQ);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            threads[i] = thread;
            thread.start();
            countDownLatch.countDown();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void queryTicketStock() {

    }
}
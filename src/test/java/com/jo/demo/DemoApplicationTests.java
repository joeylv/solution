package com.jo.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        int currency = 50;

        final CyclicBarrier cy = new CyclicBarrier(currency);
        final VipOrder vo = new VipOrder();
        for (int i = 0; i < currency; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    cy.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("" + finalI + ":::::::::::::" + vo.OrderGender());
            }).start();
        }

    }

}

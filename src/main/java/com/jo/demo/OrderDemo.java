package com.jo.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class OrderDemo {

    public static void main(String[] args) {
        int currency = 50;
        final CyclicBarrier cy = new CyclicBarrier(currency);
        final OrderService os = new VipOrder();
        IntStream.range(0, currency).forEach(i -> new Thread(() -> {

//            try {
//                cy.await();
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
            System.out.println(i);

//            System.out.println(os.OrderGender());
        }).start());
    }
}

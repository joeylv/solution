package com.distribute.lock.sample;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentTestDistributeDemo {

    public static void main(String[] args) {
        // 并发数
        int currency = 50;

        // 循环屏障
        final CyclicBarrier cb = new CyclicBarrier(currency);

        // 多线程模拟高并发
        for (int i = 0; i < currency; i++) {
            new Thread(() -> {

                // 模拟分布式集群的场景
                OrderService orderService = new OrderServiceImplWithDisLock();

                System.out.println(Thread.currentThread().getName() + "---------我准备好---------------");
                // 等待一起出发
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

                // 调用创建订单服务
                orderService.createOrder();
            }).start();

        }
    }

}

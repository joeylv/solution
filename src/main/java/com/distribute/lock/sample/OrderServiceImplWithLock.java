package com.distribute.lock.sample;

import com.distribute.lock.zk.ZKDistributeLock;

import java.util.concurrent.locks.Lock;

public class OrderServiceImplWithLock implements OrderService {

    private static OrderCodeGenerator ocg = new OrderCodeGenerator();

    private Lock lock = new ZKDistributeLock("/order_lock");

    // 创建订单接口
    @Override
    public void createOrder() {

        String orderCode = null;
        try {
            lock.lock();
            // 获取订单号
            orderCode = ocg.getOrderCode();

        } finally {
            lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + " =============>" + orderCode);

        // ……业务代码，此处省略100行代码

    }

}

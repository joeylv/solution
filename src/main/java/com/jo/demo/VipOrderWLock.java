package com.jo.demo;

import java.util.concurrent.locks.Lock;

public class VipOrderWLock implements OrderService {
    OrderCodeGenerator ocg = new OrderCodeGenerator();

    @Override
    public String OrderGender() {
        String order_code = ocg.getOrderCode();
        System.out.println(Thread.currentThread().getName() + ":::::::::::::::::" + order_code);

        return order_code;
    }

}

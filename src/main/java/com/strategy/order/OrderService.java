package com.strategy.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ApplicationContext applicationContext;


    public void saveOrder() {
        System.out.println("1.Create Order");

        applicationContext.publishEvent(new OrderEvent(""));

    }
}

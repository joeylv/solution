package com.example.service;

import com.DemoApplication;
import com.strategy.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void saveOrder() {
        orderService.saveOrder();
    }
}
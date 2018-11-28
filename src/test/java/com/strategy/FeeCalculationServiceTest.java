package com.strategy;

import com.example.DemoApplication;
import com.strategy.discount.FeeCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class FeeCalculationServiceTest {
    @Autowired
    FeeCalculationService feeCalculationService;

    @Test
    public void calculate() {
        double discount = feeCalculationService.calculate("vip", 1);
        System.out.println(discount);

        double n_discount = feeCalculationService.calculate("normal", 1);
        System.out.println(n_discount);

    }
}
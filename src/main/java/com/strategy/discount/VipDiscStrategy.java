package com.strategy.discount;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@EnableAutoConfiguration
@Service
public class VipDiscStrategy implements DiscountStrategy {
    @Override
    public String type() {
        return "vip";
    }

    @Override
    public double discount(double cost) {
        return 0.95 * cost;
    }
}

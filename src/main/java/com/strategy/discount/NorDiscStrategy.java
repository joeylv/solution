package com.strategy.discount;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@EnableAutoConfiguration
@Service
public class NorDiscStrategy implements DiscountStrategy {
    @Override
    public String type() {
        return "normal";
    }

    @Override
    public double discount(double cost) {
        return 1 * cost;
    }
}

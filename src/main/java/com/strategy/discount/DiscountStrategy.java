package com.strategy.discount;

public interface DiscountStrategy {
    String type();

    double discount(double cost);
}

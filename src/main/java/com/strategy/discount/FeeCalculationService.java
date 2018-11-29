package com.strategy.discount;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeeCalculationService {

//    @Autowired
//    VipDiscStrategy vipDiscStrategy;
//    @Autowired
//    NorDiscStrategy norDiscStrategy;

    Map<String, DiscountStrategy> map = new HashMap<>();

    public FeeCalculationService(List<DiscountStrategy> discountStrategies) {
        for (DiscountStrategy discountStrategy : discountStrategies) {
            map.put(discountStrategy.type(), discountStrategy);
        }
    }


    public double calculate(String type, double cost) {
        return map.get(type).discount(cost);

//        if ("Vip".equals(type)) {
//            return vipDiscStrategy.discount();
//        } else if ("normal".equals(type)) {
//            return norDiscStrategy.discount();
//        } else {
//            return cost;
//        }
    }
}

package com.strategy.order;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class WeixinListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == OrderEvent.class;
    }

    @Override
    public int getOrder() {
        return 101;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("3. Send Weixin Msg");
    }

}

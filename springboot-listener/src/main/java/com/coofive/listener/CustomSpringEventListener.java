package com.coofive.listener;

import com.coofive.event.CustomSpringEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wenwu.liu.o
 */
@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {
    @Override
    public void onApplicationEvent(CustomSpringEvent customSpringEvent) {
        System.out.println("Received spring custom event - " + customSpringEvent.getMessage());
    }
}

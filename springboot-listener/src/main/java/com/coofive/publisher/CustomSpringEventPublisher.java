package com.coofive.publisher;

import com.coofive.event.CustomSpringEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author wenwu.liu.o
 */
@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(final String message) {
        System.out.println("Publish custom event.");
        CustomSpringEvent event = new CustomSpringEvent(this, message);
        applicationEventPublisher.publishEvent(event);
    }
}

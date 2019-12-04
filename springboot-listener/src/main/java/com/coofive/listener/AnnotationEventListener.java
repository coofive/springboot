package com.coofive.listener;

import com.coofive.event.AnotherSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 12/3/2019 8:47 PM
 */
@Component
public class AnnotationEventListener {

    @EventListener
    public void handleContextStart(AnotherSpringEvent event) {
        System.out.println("Handling event..." + event.getMessage());
    }
}

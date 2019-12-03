package com.coofive.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wenwu.liu.o
 */
public class CustomSpringEvent extends ApplicationEvent {
    private String message;

    public CustomSpringEvent(Object source) {
        super(source);
    }

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

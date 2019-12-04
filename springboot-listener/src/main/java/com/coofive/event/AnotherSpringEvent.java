package com.coofive.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wenwu.liu.o
 */
public class AnotherSpringEvent extends ApplicationEvent {
    private String message;

    public AnotherSpringEvent(Object source) {
        super(source);
    }

    public AnotherSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package com.coofive.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author wenwu.liu.o
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomSpringEventPublisherTest {
    @Autowired
    private CustomSpringEventPublisher publisher;

    @Test
    public void doStuffAndPublishAnEvent() {
        publisher.doStuffAndPublishAnEvent("hello");
    }
}
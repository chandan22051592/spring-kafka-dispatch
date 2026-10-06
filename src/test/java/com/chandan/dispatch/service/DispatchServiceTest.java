package com.chandan.dispatch.service;

import com.chandan.dispatch.message.OrderCreated;
import com.chandan.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class DispatchServiceTest {

    private DispatchService service;

    @BeforeEach
    void setUp() {
        service = new DispatchService();
    }

    @Test
    void process() {
        OrderCreated testEvent = TestEventData.buildOrderCreateEvent(randomUUID(),randomUUID().toString());

        service.process(testEvent);
    }
}
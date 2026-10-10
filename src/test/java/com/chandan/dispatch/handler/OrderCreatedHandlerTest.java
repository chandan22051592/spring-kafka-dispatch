package com.chandan.dispatch.handler;

import com.chandan.dispatch.message.OrderCreated;
import com.chandan.dispatch.message.OrderDispatched;
import com.chandan.dispatch.service.DispatchService;
import com.chandan.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private  OrderCreatedHandler handler;
    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        handler = new OrderCreatedHandler(dispatchServiceMock);
    }

    @Test
    void listen_Success() throws Exception{
        OrderCreated testEvent = TestEventData.buildOrderCreateEvent(randomUUID(),randomUUID().toString());
        handler.listen(testEvent);
        verify(dispatchServiceMock,times(1)).process(testEvent);
    }

    @Test
    void listen_ServiceThrowsException() throws Exception{
        OrderCreated testEvent = TestEventData.buildOrderCreateEvent(randomUUID(),randomUUID().toString());
        doThrow(new RuntimeException("Service failure")).when(dispatchServiceMock).process(testEvent);
        handler.listen(testEvent);
        verify(dispatchServiceMock,times(1)).process(testEvent);
    }
}
package com.chandan.dispatch.service;

import com.chandan.dispatch.message.OrderCreated;
import com.chandan.dispatch.message.OrderDispatched;
import com.chandan.dispatch.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;
import java.util.concurrent.CompletableFuture;

import static java.util.UUID.randomUUID;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DispatchServiceTest {

    private DispatchService service;
    private KafkaTemplate kafkaProducerMock;

    @BeforeEach
    void setUp() {

        kafkaProducerMock = mock(KafkaTemplate.class);
        service = new DispatchService(kafkaProducerMock);
    }

    @Test
    void process_Success() throws Exception {
        when(kafkaProducerMock.send(anyString(),any(OrderDispatched.class))).thenReturn(mock(CompletableFuture.class));
        OrderCreated testEvent = TestEventData.buildOrderCreateEvent(randomUUID(),randomUUID().toString());

        service.process(testEvent);
        verify(kafkaProducerMock,times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));
    }

    @Test
    void process_ProducerThrowsException() {
        OrderCreated testEvent = TestEventData.buildOrderCreateEvent(randomUUID(),randomUUID().toString());
        doThrow(new RuntimeException("Producer failure")).when(kafkaProducerMock).send(eq("order.dispatched"),any(OrderDispatched.class));
        Exception exception = assertThrows(RuntimeException.class,() ->service.process(testEvent));
        verify(kafkaProducerMock,times(1)).send(eq("order.dispatched"),any(OrderDispatched.class));
        assertThat(exception.getMessage(),equalTo("Producer failure"));
    }
}
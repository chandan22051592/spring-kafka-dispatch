package com.chandan.dispatch.util;

import com.chandan.dispatch.message.OrderCreated;
import org.junit.jupiter.api.Order;

import java.util.UUID;

public class TestEventData {
    public static OrderCreated buildOrderCreateEvent(UUID orderId, String item){
        return OrderCreated.builder()
                .orderId(orderId)
                .item(item)
                .build();
    }
}

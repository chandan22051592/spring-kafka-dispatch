package com.chandan.dispatch.service;


import com.chandan.dispatch.message.OrderCreated;
import com.chandan.dispatch.message.OrderDispatched;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private static final String ORDER_DISPATCH_TOPIC = "order.dispatched";

    private final KafkaTemplate<String,Object> kafkaProducer;

    public void process(OrderCreated orderCreated) throws Exception{
        OrderDispatched orderDispatched = OrderDispatched.builder()
                .orderId(orderCreated.getOrderId())
                .build();

        kafkaProducer.send(ORDER_DISPATCH_TOPIC,orderDispatched).get();

    }
}

# Spring Kafka Dispatch

A Spring Boot application that consumes `OrderCreated` messages from an Apache Kafka topic.

## Tech Stack

- Java 17
- Spring Boot
- Spring Kafka
- Apache Kafka
- Maven
- Jackson JSON

## Kafka Configuration

The application connects to Kafka running locally on port `9092`.

### application.properties

```properties
spring.application.name=dispatch
spring.kafka.bootstrap-servers=localhost:9092

spring.kafka.consumer.group-id=dispatch.order.created.consumer-v2

spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
spring.kafka.consumer.properties.spring.deserializer.value.delegate.class=org.springframework.kafka.support.serializer.JsonDeserializer

spring.kafka.properties.spring.json.value.default.type=com.chandan.dispatch.message.OrderCreated

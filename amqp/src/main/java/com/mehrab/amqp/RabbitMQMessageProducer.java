package com.mehrab.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

// this class allow us to publish to an exchange by passing the arguments

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQMessageProducer {
    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routing {}. Payload: {} ", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing {}. Payload: {} ", exchange, routingKey, payload);
    }
}

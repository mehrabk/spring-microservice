package com.mehrab.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    // 1. able to publish message to queue
    @Bean
    public AmqpTemplate amqpTemplate() {
        // we want customized this default implemention
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // recive json and convert to java
        rabbitTemplate.setMessageConverter(jaksonConverter());

        return rabbitTemplate;
    }

    // 2. setup listenner or recive a message
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jaksonConverter());
        return factory;
    }

    @Bean
    public MessageConverter jaksonConverter() {
        MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }
}

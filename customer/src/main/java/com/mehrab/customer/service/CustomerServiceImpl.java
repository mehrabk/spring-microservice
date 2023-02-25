package com.mehrab.customer.service;

import com.mehrab.amqp.RabbitMQMessageProducer;
import com.mehrab.clients.fraud.FraudCheckResponse;
import com.mehrab.clients.fraud.FraudClient;
import com.mehrab.clients.notification.NotificationClient;
import com.mehrab.clients.notification.NotificationRequest;
import com.mehrab.customer.model.Customer;
import com.mehrab.customer.payload.request.CustomerRegistrationRequest;
import com.mehrab.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
//    private final RestTemplate restTemplate;
//    private final NotificationClient notificationClient;
    private final FraudClient fraudClient;
    private final CustomerRepository customerRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        // todo: check if email valid

        // todo: check if email not taken
        customerRepository.saveAndFlush(customer); // we can access id of saved customer

        // todo: check if fraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: make it async send notification. i.e add to queue
        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), String.format("Hi %s, Welcome to my miroservice", customer.getFirstName()));
//        notificationClient.sendNotification(notificationRequest);
        rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");
    }
}

package com.mehrab.customer.service;

import com.mehrab.customer.model.Customer;
import com.mehrab.customer.payload.request.CustomerRegistrationRequest;
import com.mehrab.customer.payload.response.FraudCheckResponse;
import com.mehrab.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
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
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // todo: send notification

    }
}

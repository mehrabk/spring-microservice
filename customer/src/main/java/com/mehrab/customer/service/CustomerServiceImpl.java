package com.mehrab.customer.service;

import com.mehrab.customer.model.Customer;
import com.mehrab.customer.payload.CustomerRegistrationRequest;
import com.mehrab.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        customerRepository.save(customer);
    }
}

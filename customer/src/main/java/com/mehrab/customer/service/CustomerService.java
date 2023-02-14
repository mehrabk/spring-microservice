package com.mehrab.customer.service;

import com.mehrab.customer.payload.CustomerRegistrationRequest;

public interface CustomerService {
    void registerCustomer(CustomerRegistrationRequest request);
}

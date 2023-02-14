package com.mehrab.customer.service;

import com.mehrab.customer.payload.request.CustomerRegistrationRequest;

public interface CustomerService {
    void registerCustomer(CustomerRegistrationRequest request);
}

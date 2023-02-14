package com.mehrab.customer.controller;

import com.mehrab.customer.payload.request.CustomerRegistrationRequest;
import com.mehrab.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration -> {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}

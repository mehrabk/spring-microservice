package com.mehrab.fraud.service;

import org.springframework.stereotype.Service;

@Service
public interface FraudCheckService {

    boolean isFraudulentCustomer(Integer customerId);
}

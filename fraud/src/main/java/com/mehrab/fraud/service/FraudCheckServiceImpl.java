package com.mehrab.fraud.service;

import com.mehrab.fraud.model.FraudCheckHistory;
import com.mehrab.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class FraudCheckServiceImpl implements FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    @Override
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}

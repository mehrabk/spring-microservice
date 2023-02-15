package com.mehrab.fraud.controller;


import com.mehrab.fraud.payload.response.FraudCheckResponse;
import com.mehrab.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        var isFraudulentCustomer =  fraudCheckService.isFraudulentCustomer(customerId);
        log.info("fraud check request for cutomer id {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}

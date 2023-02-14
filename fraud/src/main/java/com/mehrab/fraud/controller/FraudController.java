package com.mehrab.fraud.controller;


import com.mehrab.fraud.payload.response.FraudCheckResponse;
import com.mehrab.fraud.service.FraudCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        var isFraudulentCustomer =  fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}

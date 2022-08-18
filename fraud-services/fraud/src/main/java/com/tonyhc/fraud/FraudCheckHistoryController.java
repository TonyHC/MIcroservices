package com.tonyhc.fraud;

import com.tonyhc.clients.fraud.FraudCheckHistoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/fraud-check-history")
@AllArgsConstructor
public class FraudCheckHistoryController {
    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public FraudCheckHistoryResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);

        log.info("Fraud check history request for customer {}", customerId);

        return new FraudCheckHistoryResponse(isFraudulentCustomer);
    }
}
package com.tonyhc.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("New customer registration {}", customerRegistrationRequest);
        return customerService.registerCustomer(customerRegistrationRequest);
    }
}
package com.springboot.studentservice.service;

import com.springboot.studentservice.feignclients.OpenFeignClient;
import com.springboot.studentservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CircuitBreakerService {
    private final OpenFeignClient openFeignClient;
    private long count = 1;

    public CircuitBreakerService(OpenFeignClient openFeignClient) {
        this.openFeignClient = openFeignClient;
    }

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(Long addressId) {
        log.info("Number of calls to getAddressById(): " + count);
        count++;

        return openFeignClient.getAddressById(addressId);
    }

    public AddressResponse fallbackGetAddressById(Long addressId, Throwable throwable) {
        log.error("Error: " + throwable);

        return new AddressResponse();
    }
}
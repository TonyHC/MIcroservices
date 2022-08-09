package com.springboot.studentservice.feignclients;

import com.springboot.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
public interface OpenFeignClient {
    @GetMapping("/address-service/api/addresses/{id}")
    public AddressResponse getAddressById(@PathVariable Long id);
}
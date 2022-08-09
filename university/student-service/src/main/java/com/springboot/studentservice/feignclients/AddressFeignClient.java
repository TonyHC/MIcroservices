package com.springboot.studentservice.feignclients;

import com.springboot.studentservice.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "address-service", path = "${address.service.path}")
public interface AddressFeignClient {
    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable Long id);
}
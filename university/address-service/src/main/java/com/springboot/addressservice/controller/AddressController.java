package com.springboot.addressservice.controller;

import com.springboot.addressservice.request.CreateAddressRequest;
import com.springboot.addressservice.response.AddressResponse;
import com.springboot.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@RefreshScope
public class AddressController {
    @Value("${address.test}")
    private String test;

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/test")
    public String getTestString() {
        return test;
    }
}
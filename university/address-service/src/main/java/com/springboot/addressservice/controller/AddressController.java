package com.springboot.addressservice.controller;

import com.springboot.addressservice.request.CreateAddressRequest;
import com.springboot.addressservice.response.AddressResponse;
import com.springboot.addressservice.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
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
}
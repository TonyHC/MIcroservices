package com.springboot.addressservice.service;

import com.springboot.addressservice.entity.Address;
import com.springboot.addressservice.repository.AddressRepository;
import com.springboot.addressservice.request.CreateAddressRequest;
import com.springboot.addressservice.response.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        addressRepository.save(address);

        return new AddressResponse(address);
    }

    public AddressResponse getAddressById(Long id) {
        log.info("Inside getAddressById(): " + id);
        Address address = addressRepository.findById(id).get();
        return new AddressResponse(address);
    }
}
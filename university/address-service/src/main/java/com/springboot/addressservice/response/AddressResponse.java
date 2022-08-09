package com.springboot.addressservice.response;

import com.springboot.addressservice.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long addressId;
    private String street;
    private String city;

    public AddressResponse(Address address) {
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }
}

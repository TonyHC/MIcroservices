package com.springboot.addressservice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequest {
    private String street;
    private String city;
}

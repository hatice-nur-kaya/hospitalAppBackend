package com.kodhnk.base.dto.addresses.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequest {
    private String city;
    private String district;
}
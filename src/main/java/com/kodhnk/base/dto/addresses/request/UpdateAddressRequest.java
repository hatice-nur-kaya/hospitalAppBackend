package com.kodhnk.base.dto.addresses.request;

import lombok.Getter;

@Getter
public class UpdateAddressRequest {
    private Long id;
    private String city;
    private String district;
}
package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.addresses.request.CreateAddressRequest;
import com.kodhnk.base.dto.addresses.request.UpdateAddressRequest;
import com.kodhnk.base.entities.Address;

import java.util.List;

public interface IAddressService {
    DataResult<List<Address>> getAllAddresses();

    DataResult<Address> getAddressById(Long id);
    
    DataResult<Address> updateAddress(UpdateAddressRequest request);

    DataResult<Address> deleteAddress(Long id);

    DataResult<Address> createAddress(CreateAddressRequest request);
}

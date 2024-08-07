package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.ErrorDataResult;
import com.kodhnk.base.core.utilities.SuccessDataResult;
import com.kodhnk.base.dataAccess.AddressRepository;
import com.kodhnk.base.dto.addresses.request.CreateAddressRequest;
import com.kodhnk.base.dto.addresses.request.UpdateAddressRequest;
import com.kodhnk.base.entities.Address;
import com.kodhnk.base.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public DataResult<List<Address>> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return new SuccessDataResult<>(Response.GET_ADDRESS.getMessage(), addresses, 200);
    }

    @Override
    public DataResult<Address> getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return new ErrorDataResult<>(Response.ADDRESS_NOT_FOUND.getMessage(), null, 400);
        }
        return new SuccessDataResult<>(Response.GET_ADDRESS.getMessage(), address, 200);
    }

    @Override
    public DataResult<Address> updateAddress(UpdateAddressRequest request) {
        if (request == null) {
            return new ErrorDataResult<>(Response.REQUEST_IS_NULL.getMessage(), null, 400);
        }
        Address address = addressRepository.findById(request.getId()).orElse(null);
        if (address == null) {
            return new ErrorDataResult<>(Response.ADDRESS_NOT_FOUND.getMessage(), null, 400);
        }
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        addressRepository.save(address);
        return new SuccessDataResult<>(Response.UPDATE_ADDRESS.getMessage(), address, 200);
    }

    @Override
    public DataResult<Address> deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return new ErrorDataResult<>(Response.ADDRESS_NOT_FOUND.getMessage(), null, 400);
        }
        addressRepository.delete(address);
        return new SuccessDataResult<>(Response.DELETE_ADDRESS.getMessage(), address, 200);
    }

    @Override
    public DataResult<Address> createAddress(CreateAddressRequest request) {
        if (request == null) {
            return new ErrorDataResult<>(Response.REQUEST_IS_NULL.getMessage(), null, 400);
        }
        Address address = new Address();
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        addressRepository.save(address);
        return new SuccessDataResult<>(Response.CREATE_ADDRESS.getMessage(), address, 201);
    }
}
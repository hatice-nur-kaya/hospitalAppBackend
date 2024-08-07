package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.addresses.request.CreateAddressRequest;
import com.kodhnk.base.dto.addresses.request.UpdateAddressRequest;
import com.kodhnk.base.entities.Address;
import com.kodhnk.base.services.interfaces.IAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAllAddresses")
    ResponseEntity<DataResult<List<Address>>> getAllAddresses() {
        DataResult<List<Address>> result = addressService.getAllAddresses();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getAddressById")
    ResponseEntity<DataResult<Address>> getAddressById(Long id) {
        DataResult<Address> result = addressService.getAddressById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createAddress")
    ResponseEntity<DataResult<Address>> createAddress(CreateAddressRequest request) {
        DataResult<Address> result = addressService.createAddress(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateAddress")
    ResponseEntity<DataResult<Address>> updateAddress(UpdateAddressRequest request) {
        DataResult<Address> result = addressService.updateAddress(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteAddress")
    ResponseEntity<DataResult<Address>> deleteAddress(Long id) {
        DataResult<Address> result = addressService.deleteAddress(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
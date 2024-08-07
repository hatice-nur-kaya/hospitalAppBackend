package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
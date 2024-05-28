package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllById(Long hospitalId);

    List<Patient> findAllByHospital(Hospital data);

    Optional<Object> findByEmail(String email);
}
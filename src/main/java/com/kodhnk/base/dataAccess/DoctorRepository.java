package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Doctor;
import com.kodhnk.base.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByHospitalId(Long hospitalId);

    Set<Doctor> findAllByHospital(Hospital data);
}
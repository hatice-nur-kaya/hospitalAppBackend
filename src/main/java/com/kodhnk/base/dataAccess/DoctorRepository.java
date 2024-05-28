package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
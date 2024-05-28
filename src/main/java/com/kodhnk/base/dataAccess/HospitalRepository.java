package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
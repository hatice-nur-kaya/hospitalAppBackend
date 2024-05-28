package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    List<Examination> findAllByHospitalId(Long hospitalId);
}
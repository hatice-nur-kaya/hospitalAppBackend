package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}
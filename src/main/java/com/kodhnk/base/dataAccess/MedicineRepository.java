package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
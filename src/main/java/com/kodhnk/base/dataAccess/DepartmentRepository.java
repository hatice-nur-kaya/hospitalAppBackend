package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
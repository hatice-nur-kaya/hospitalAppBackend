package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.departments.CreateDepartmentRequest;
import com.kodhnk.base.dto.departments.UpdateDepartmentRequest;
import com.kodhnk.base.entities.Department;

import java.util.List;

public interface IDepartmentService {
    DataResult<List<Department>> getAllDepartments();

    DataResult<Department> getDepartmentById(Long id);

    DataResult<Department> createDepartment(CreateDepartmentRequest request);

    DataResult<Department> updateDepartment(UpdateDepartmentRequest request);

    Result deleteDepartment(Long id);
}
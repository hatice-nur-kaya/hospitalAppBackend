package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.DepartmentRepository;
import com.kodhnk.base.dto.departments.CreateDepartmentRequest;
import com.kodhnk.base.dto.departments.UpdateDepartmentRequest;
import com.kodhnk.base.entities.Department;
import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.services.interfaces.IDepartmentService;
import com.kodhnk.base.services.interfaces.IHospitalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;
    private final IHospitalService hospitalService;

    public DepartmentService(DepartmentRepository departmentRepository, IHospitalService hospitalService) {
        this.departmentRepository = departmentRepository;
        this.hospitalService = hospitalService;
    }

    @Override
    public DataResult<List<Department>> getAllDepartments() {
        List<Department> department = departmentRepository.findAll();
        return new SuccessDataResult<>(Response.DEPARTMENT_LISTED.getMessage(), department, 200);

    }

    @Override
    public DataResult<Department> getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return new SuccessDataResult<>(Response.DEPARTMENT_FOUND.getMessage(), department.get(), 200);
        }
        return new ErrorDataResult<>(Response.DEPARTMENT_NOT_FOUND.getMessage(), department.get(), 400);
    }

    @Override
    public DataResult<Department> createDepartment(CreateDepartmentRequest request) {
        DataResult<Hospital> hospitalDataResult = hospitalService.getById(request.getHospitalId());
        if (!hospitalDataResult.isSuccess()) {
            return new ErrorDataResult<>(Response.HOSPITAL_NOT_FOUND.getMessage(), null, 400);
        }
        Department department = new Department();
        department.setHospital(hospitalDataResult.getData());
        department.setName(request.getName());
        departmentRepository.save(department);
        return new SuccessDataResult<>(Response.CREATE_DEPARTMENT.getMessage(), department, 201);
    }

    @Override
    public DataResult<Department> updateDepartment(UpdateDepartmentRequest request) {
        DataResult<Department> result = getDepartmentById(request.getId());
        if (result.isSuccess()) {
            Department department = result.getData();
            BeanUtils.copyProperties(request, department);
            departmentRepository.save(department);
            return new SuccessDataResult<>(Response.UPDATE_DEPARTMENT.getMessage(), department, 200);
        }
        return new ErrorDataResult<>(Response.DEPARTMENT_NOT_FOUND.getMessage(), null, 400);
    }

    @Override
    public Result deleteDepartment(Long id) {
        DataResult<Department> departmentDataResult = getDepartmentById(id);
        if (departmentDataResult.isSuccess()) {
            Department department = departmentDataResult.getData();
            departmentRepository.delete(department);
            return new SuccessResult(Response.DELETE_DEPARTMENT.getMessage(), 200);
        }
        return new ErrorResult(Response.DEPARTMENT_NOT_FOUND.getMessage(), 400);
    }
}
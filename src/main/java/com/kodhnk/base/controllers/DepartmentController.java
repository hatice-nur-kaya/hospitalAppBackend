package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.departments.CreateDepartmentRequest;
import com.kodhnk.base.dto.departments.UpdateDepartmentRequest;
import com.kodhnk.base.entities.Department;
import com.kodhnk.base.services.interfaces.IDepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getAllDepartments")
    ResponseEntity<DataResult<List<Department>>> getAllDepartments() {
        DataResult<List<Department>> result = departmentService.getAllDepartments();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getDepartmentById")
    ResponseEntity<DataResult<Department>> getDepartmentById(@RequestParam Long id) {
        DataResult<Department> result = departmentService.getDepartmentById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createDepartment")
    ResponseEntity<DataResult<Department>> createDepartment(@RequestBody CreateDepartmentRequest request) {
        DataResult<Department> result = departmentService.createDepartment(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateDepartment")
    ResponseEntity<DataResult<Department>> updateDepartment(@RequestBody UpdateDepartmentRequest request) {
        DataResult<Department> result = departmentService.updateDepartment(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteDepartment")
    ResponseEntity<Result> deleteDepartment(@RequestParam Long id) {
        Result result = departmentService.deleteDepartment(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
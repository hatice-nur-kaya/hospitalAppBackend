package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.patients.CreatePatientRequest;
import com.kodhnk.base.dto.patients.UpdatePatientRequest;
import com.kodhnk.base.entities.Patient;
import com.kodhnk.base.services.interfaces.IPatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<DataResult<Set<Patient>>> getAllPatients() {
        DataResult<Set<Patient>> result = patientService.getAllPatients();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getByPatientId")
    public ResponseEntity<DataResult<Patient>> getByPatientId(@RequestParam Long id) {
        DataResult<Patient> result = patientService.getByPatientId(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createPatient")
    public ResponseEntity<DataResult<Patient>> createPatient(@RequestBody CreatePatientRequest request) {
        DataResult<Patient> result = patientService.createPatient(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<DataResult<Patient>> updatePatient(@RequestBody UpdatePatientRequest request) {
        DataResult<Patient> result = patientService.updatePatient(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deletePatient")
    public ResponseEntity<Result> deletePatient(@RequestParam Long id) {
        Result result = patientService.deletePatient(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
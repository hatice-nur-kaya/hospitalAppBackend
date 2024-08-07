package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.doctors.CreateDoctorRequest;
import com.kodhnk.base.dto.doctors.UpdateDoctorRequest;
import com.kodhnk.base.entities.Doctor;
import com.kodhnk.base.services.interfaces.IDoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/getDoctorsByHospital")
    public ResponseEntity<DataResult<Set<Doctor>>> getDoctorsByHospital(@RequestParam Long hospitalId) {
        DataResult<Set<Doctor>> result = doctorService.getDoctorsByHospital(hospitalId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/doctorId/{id}")
    public ResponseEntity<DataResult<Doctor>> getDoctorById(@PathVariable Long id) {
        DataResult<Doctor> result = doctorService.getDoctorById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createHospitalDoctor")
    public ResponseEntity<Result> createHospitalDoctor(@RequestBody CreateDoctorRequest request) {
        Result result = doctorService.createHospitalDoctor(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> updateDoctor(@RequestBody UpdateDoctorRequest request) {
        Result result = doctorService.updateDoctor(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteDoctor")
    public ResponseEntity<Result> deleteDoctor(@RequestParam Long id) {
        Result result = doctorService.deleteDoctor(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
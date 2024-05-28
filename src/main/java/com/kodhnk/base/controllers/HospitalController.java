package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.hospitals.AddDoctorToHospitalRequest;
import com.kodhnk.base.dto.hospitals.CreateHospitalRequest;
import com.kodhnk.base.dto.hospitals.UpdateHospitalRequest;
import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.services.interfaces.IHospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalController {
    private final IHospitalService hospitalService;

    public HospitalController(IHospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/getAllHosptial")
    public ResponseEntity<DataResult<List<Hospital>>> getAllHosptial() {
        DataResult<List<Hospital>> result = hospitalService.getAllHosptial();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getHospitalById")
    public ResponseEntity<DataResult<Hospital>> getHospitalById(@RequestParam Long id) {
        DataResult<Hospital> result = hospitalService.getById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createHospital")
    public ResponseEntity<Result> createHospital(@RequestBody CreateHospitalRequest request) {
        Result result = hospitalService.createHospital(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateHospital")
    public ResponseEntity<Result> updateHospital(@RequestBody UpdateHospitalRequest request) {
        Result result = hospitalService.updateHospital(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("deleteHospital")
    public ResponseEntity<Result> deleteHospital(@RequestParam Long id) {
        Result result = hospitalService.deleteHospital(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/addDoctorToHospital")
    public ResponseEntity<Result> addDoctorToHospital(@RequestBody AddDoctorToHospitalRequest request) {
        Result result = hospitalService.addDoctorToHospital(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
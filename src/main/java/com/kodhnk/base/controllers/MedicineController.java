package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.medicines.CreateMedicineRequest;
import com.kodhnk.base.dto.medicines.UpdateMedicineRequest;
import com.kodhnk.base.entities.Medicine;
import com.kodhnk.base.services.interfaces.IMedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
public class MedicineController {
    private final IMedicineService medicineService;

    public MedicineController(IMedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/getAllMedicines")
    ResponseEntity<DataResult<List<Medicine>>> getAllMedicines() {
        DataResult<List<Medicine>> result = medicineService.getAllMedicines();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getMedicineById")
    ResponseEntity<DataResult<Medicine>> getMedicineById(@RequestParam Long id) {
        DataResult<Medicine> result = medicineService.getMedicineById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createMedicine")
    ResponseEntity<DataResult<Medicine>> createMedicine(@RequestBody CreateMedicineRequest request) {
        DataResult<Medicine> result = medicineService.createMedicine(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateMedicine")
    ResponseEntity<DataResult<Medicine>> updateMedicine(@RequestBody UpdateMedicineRequest request) {
        DataResult<Medicine> result = medicineService.updateMedicine(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteMedicine")
    ResponseEntity<Result> deleteMedicine(@RequestParam Long id) {
        Result result = medicineService.deleteMedicine(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
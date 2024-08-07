package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.examinations.CreateExaminationRequest;
import com.kodhnk.base.dto.examinations.UpdateExaminationRequest;
import com.kodhnk.base.entities.Examination;
import com.kodhnk.base.services.interfaces.IExaminationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examinations")
public class ExaminationController {


    private final IExaminationService examinationService;

    public ExaminationController(IExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @GetMapping("getAllExaminations")
    public ResponseEntity<?> getAllExaminations(@RequestParam Long hospitalId) {
        DataResult<List<Examination>> result = examinationService.getAllExaminations(hospitalId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getExaminationById")
    public ResponseEntity<DataResult<Examination>> getExaminationById(@RequestParam Long id) {
        DataResult<Examination> result = examinationService.getExaminationById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createExamination")
    public ResponseEntity<DataResult<Examination>> createExamination(@RequestBody CreateExaminationRequest request) {
        DataResult<Examination> result = examinationService.createExamination(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateExamination")
    public ResponseEntity<DataResult<Examination>> updateExamination(@RequestBody UpdateExaminationRequest request) {
        DataResult<Examination> result = examinationService.updateExamination(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteExamination")
    public ResponseEntity<Result> deleteExamination(@RequestParam Long id) {
        Result result = examinationService.deletedeleteExamination(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
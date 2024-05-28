package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.appointments.CreateAppointmentRequest;
import com.kodhnk.base.dto.appointments.UpdateAppointmentRequest;
import com.kodhnk.base.entities.Appointment;
import com.kodhnk.base.services.interfaces.IAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/getAllAppointment")
    public ResponseEntity<DataResult<List<Appointment>>> getAllAppointment() {
        DataResult<List<Appointment>> result = appointmentService.getAllAppointment();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getAppointmentById")
    public ResponseEntity<DataResult<Appointment>> getAppointmentById(@RequestParam Long id) {
        DataResult<Appointment> result = appointmentService.getAppointmentById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<Result> createAppointment(@RequestBody CreateAppointmentRequest request) {
        Result result = appointmentService.createAppointment(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateAppointment")
    public ResponseEntity<Result> updateAppointment(@RequestBody UpdateAppointmentRequest request) {
        Result result = appointmentService.updateAppointment(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/cancelAppointment/{id}")
    public ResponseEntity<Result> cancelAppointment(@PathVariable Long id) {
        Result result = appointmentService.cancelAppointment(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
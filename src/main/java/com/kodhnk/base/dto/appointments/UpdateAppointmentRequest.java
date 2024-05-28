package com.kodhnk.base.dto.appointments;

import com.kodhnk.base.entities.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateAppointmentRequest {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private Date appointmentDate;
    private AppointmentStatus status;
}
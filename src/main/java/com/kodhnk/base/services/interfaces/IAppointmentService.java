package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.appointments.CreateAppointmentRequest;
import com.kodhnk.base.dto.appointments.UpdateAppointmentRequest;
import com.kodhnk.base.entities.Appointment;

import java.util.List;

public interface IAppointmentService {
    DataResult<List<Appointment>> getAllAppointment();

    DataResult<Appointment> getAppointmentById(Long id);

    Result createAppointment(CreateAppointmentRequest request);

    Result updateAppointment(UpdateAppointmentRequest request);

    Result cancelAppointment(Long id);
}
package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.patients.CreatePatientRequest;
import com.kodhnk.base.dto.patients.UpdatePatientRequest;
import com.kodhnk.base.entities.Patient;

import java.util.Set;

public interface IPatientService {
    DataResult<Set<Patient>> getAllPatients(Long hospitalId);

    DataResult<Patient> getByPatientId(Long hospitalId);

    DataResult<Patient> createPatient(CreatePatientRequest request);

    DataResult<Patient> updatePatient(UpdatePatientRequest request);

    Result deletePatient(Long id);
}
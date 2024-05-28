package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.doctors.CreateDoctorByHospitalRequest;
import com.kodhnk.base.dto.doctors.CreateDoctorRequest;
import com.kodhnk.base.dto.doctors.UpdateDoctorRequest;
import com.kodhnk.base.entities.Doctor;

import java.util.Set;

public interface IDoctorService {
    DataResult<Set<Doctor>> getDoctorsByHospital(Long hospitalId);

    DataResult<Doctor> getDoctorById(Long id);

    Result createHospitalByDoctor(CreateDoctorByHospitalRequest request);

    Result updateDoctor(UpdateDoctorRequest request);

    Result deleteDoctor(Long id);

    Result createDoctor(CreateDoctorRequest request);
}
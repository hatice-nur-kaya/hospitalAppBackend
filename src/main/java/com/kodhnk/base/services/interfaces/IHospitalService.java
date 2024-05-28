package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.hospitals.AddDoctorToHospitalRequest;
import com.kodhnk.base.dto.hospitals.CreateHospitalRequest;
import com.kodhnk.base.dto.hospitals.UpdateHospitalRequest;
import com.kodhnk.base.entities.Hospital;

import java.util.List;

public interface IHospitalService {
    DataResult<List<Hospital>> getAllHosptial();

    Result createHospital(CreateHospitalRequest request);

    Result updateHospital(UpdateHospitalRequest request);

    Result deleteHospital(Long id);

    DataResult<Hospital> getById(Long id);

    Result addDoctorToHospital(AddDoctorToHospitalRequest request);
}
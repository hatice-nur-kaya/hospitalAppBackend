package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.DoctorRepository;
import com.kodhnk.base.dataAccess.HospitalRepository;
import com.kodhnk.base.dto.hospitals.AddDoctorToHospitalRequest;
import com.kodhnk.base.dto.hospitals.CreateHospitalRequest;
import com.kodhnk.base.dto.hospitals.UpdateHospitalRequest;
import com.kodhnk.base.entities.Address;
import com.kodhnk.base.entities.Doctor;
import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.services.interfaces.IAddressService;
import com.kodhnk.base.services.interfaces.IHospitalService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService implements IHospitalService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final IAddressService addressService;

    public HospitalService(HospitalRepository hospitalRepository, DoctorRepository doctorRepository, IAddressService addressService) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.addressService = addressService;
    }

    @Override
    public DataResult<List<Hospital>> getAllHosptial() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return new SuccessDataResult<>(Response.HOSPITAL_GET_ALL.getMessage(), hospitals, 200);
    }

    @Override
    public Result createHospital(CreateHospitalRequest request) {
        Hospital hospital = new Hospital();
        DataResult<Address> addressResult = addressService.getAddressById(request.getAddressId());
        if (!addressResult.isSuccess()) {
            return new ErrorDataResult<>(Response.ADDRESS_NOT_FOUND.getMessage(), null, 400);
        }
        BeanUtils.copyProperties(request, hospital);
        hospital.setAddress(addressResult.getData());
        hospitalRepository.save(hospital);
        return new SuccessDataResult<>(Response.CREATE_HOSPITAL.getMessage(), hospital, 201);
    }

    @Override
    public Result updateHospital(UpdateHospitalRequest request) {
        DataResult<Hospital> result = getById(request.getId());
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(Response.HOSPITAL_NOT_FOUND.getMessage(), null, 400);
        }
        Hospital hospital = result.getData();
        BeanUtils.copyProperties(request, hospital);
        hospitalRepository.save(hospital);
        return new SuccessDataResult<>(Response.UPDATE_HOSPITAL.getMessage(), hospital, 200);
    }

    @Override
    public Result deleteHospital(Long id) {
        Hospital hospital = getById(id).getData();
        if (hospital == null) {
            return new ErrorResult(Response.HOSPITAL_NOT_FOUND.getMessage(), 400);
        }
        hospitalRepository.delete(hospital);
        return new SuccessResult(Response.DELETE_HOSPITAL.getMessage(), 200);
    }

    @Override
    public DataResult<Hospital> getById(Long id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if (hospital.isPresent()) {
            return new SuccessDataResult<>(Response.HOSPITAL_BY_ID.getMessage(), hospital.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.HOSPITAL_NOT_FOUND.getMessage(), null, 400);
        }
    }

    @Override
    public Result addDoctorToHospital(AddDoctorToHospitalRequest request) {
        Optional<Hospital> hospitalOpt = hospitalRepository.findById(request.getHospitalId());
        Optional<Doctor> doctorOpt = doctorRepository.findById(request.getDoctorId());

        if (hospitalOpt.isPresent() && doctorOpt.isPresent()) {
            Hospital hospital = hospitalOpt.get();
            Doctor doctor = doctorOpt.get();
            doctor.setHospital(hospital);
            doctorRepository.save(doctor);
            hospitalRepository.save(hospital);
            return new SuccessResult(Response.SUCCESS.getMessage(), 200);
        } else {
            return new ErrorResult(Response.ERROR.getMessage(), 400);
        }
    }
}
package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.PatientRepository;
import com.kodhnk.base.dto.patients.CreatePatientRequest;
import com.kodhnk.base.dto.patients.UpdatePatientRequest;
import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.entities.Patient;
import com.kodhnk.base.entities.UserType;
import com.kodhnk.base.services.interfaces.IHospitalService;
import com.kodhnk.base.services.interfaces.IPatientService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;
    private final IHospitalService hospitalService;
    private final BCryptPasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, IHospitalService hospitalService, BCryptPasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DataResult<Set<Patient>> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        Set<Patient> patientSet = new HashSet<>(patients);
        return new SuccessDataResult<>(Response.GET_PATIENT.getMessage(), patientSet, 200);
    }

    @Override
    public DataResult<Patient> getByPatientId(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return new SuccessDataResult<>(Response.GET_PATIENT.getMessage(), patient.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
    }

    @Override
    public DataResult<Patient> createPatient(CreatePatientRequest request) {
        // E-posta adresini kontrol edin
        if (patientRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ErrorDataResult<>(Response.EMAIL_ALREADY_EXISTS.getMessage(), null, 409);
        }

        Patient patient = new Patient();
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setUsername(request.getUsername());
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patient.setBirthDate(request.getBirthDate());
        patient.setUserType(UserType.PATIENT);
        patientRepository.save(patient);
        return new SuccessDataResult<>(Response.CREATE_PATIENT.getMessage(), patient, 201);
    }


    @Override
    public DataResult<Patient> updatePatient(UpdatePatientRequest request) {
        DataResult<Patient> patientDataResult = getByPatientId(request.getId());
        if (!patientDataResult.isSuccess()) {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
        Patient patient = patientDataResult.getData();
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setUsername(request.getUsername());
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patient.setBirthDate(request.getBirthDate());
        patient.setUserType(UserType.PATIENT);
        patientRepository.save(patient);
        patientRepository.save(patient);
        return new SuccessDataResult<>(Response.UPDATE_PATIENT.getMessage(), patient, 200);
    }

    @Override
    public Result deletePatient(Long id) {
        DataResult<Patient> patientDataResult = getByPatientId(id);
        if (!patientDataResult.isSuccess()) {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
        Patient patient = patientDataResult.getData();
        patientRepository.delete(patient);
        return new SuccessDataResult<>(Response.DELETE_PATIENT.getMessage(), patient, 200);
    }
}
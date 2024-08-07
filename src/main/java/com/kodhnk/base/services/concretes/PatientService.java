package com.kodhnk.base.services.concretes;

import com.kodhnk.base.entities.Hospital;
import com.kodhnk.base.entities.Role;
import com.kodhnk.base.services.interfaces.IPatientService;
import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.PatientRepository;
import com.kodhnk.base.dataAccess.UserRepository;
import com.kodhnk.base.dto.patients.CreatePatientRequest;
import com.kodhnk.base.dto.patients.UpdatePatientRequest;
import com.kodhnk.base.entities.Patient;
import com.kodhnk.base.entities.User;
import com.kodhnk.base.services.interfaces.IHospitalService;
import com.kodhnk.base.services.interfaces.IRoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final IHospitalService hospitalService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IRoleService roleService;
    public PatientService(PatientRepository patientRepository, UserRepository userRepository, IHospitalService hospitalService, BCryptPasswordEncoder passwordEncoder, IRoleService roleService) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.hospitalService = hospitalService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public DataResult<Set<Patient>> getAllPatients(Long hospitalId) {
        List<Patient> patients = patientRepository.findAllByHospitals_Id(hospitalId);
        Set<Patient> patientSet = new HashSet<>(patients);
        return new SuccessDataResult<>(Response.GET_PATIENT.getMessage(), patientSet, 200);
    }

    @Override
    public DataResult<Patient> getByPatientId(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            return new SuccessDataResult<>(Response.GET_PATIENT.getMessage(), patient.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
    }

    @Override
    public DataResult<Patient> createPatient(CreatePatientRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ErrorDataResult<>(Response.EMAIL_ALREADY_EXISTS.getMessage(), null, 409);
        }

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();
        for (Long roleId : request.getRoleIds()) {
            DataResult<Role> roleDataResult = roleService.getRoleById(roleId);
            if (!roleDataResult.isSuccess()) {
                return new ErrorDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 400);
            }
            Role role = roleDataResult.getData();
            roles.add(role);
        }
        user.setRoles(roles);

        // Kullanıcıyı kaydet
        User savedUser = userRepository.save(user);
        // Yeni hasta oluştur
        Patient patient = new Patient();
        patient.setPhone(request.getPhone());
        patient.setGender(request.getGender());
        patient.setBirthDate(request.getBirthDate());
        patient.setUser(savedUser);
        // Hastaneleri ekle
        Set<Hospital> hospitals = new HashSet<>();
        for (Long hospitalId : request.getHospitalIds()) {
            DataResult<Hospital> hospitalOptional = hospitalService.getById(hospitalId);
            if (!hospitalOptional.isSuccess()) {
                return new ErrorDataResult<>(Response.HOSPITAL_NOT_FOUND.getMessage(), null, 400);
            }
            hospitals.add(hospitalOptional.getData());
        }
        patient.setHospitals(hospitals);

        // Hastayı kaydet
        patientRepository.save(patient);

        return new SuccessDataResult<>(Response.CREATE_PATIENT.getMessage(), patient, 200);
    }

    @Override
    public DataResult<Patient> updatePatient(UpdatePatientRequest request) {
        DataResult<Patient> patientDataResult = getByPatientId(request.getId());
        if (!patientDataResult.isSuccess()) {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
        Patient patient = patientDataResult.getData();
        Set<Hospital> hospitals = new HashSet<>();
        for (Long hospitalId : request.getHospitalIds()) {
            DataResult<Hospital> hospitalOptional = hospitalService.getById(hospitalId);
            if (!hospitalOptional.isSuccess()) {
                return new ErrorDataResult<>(Response.HOSPITAL_NOT_FOUND.getMessage(), null, 400);
            }
            if (!hospitals.contains(hospitalOptional.getData())) {
                hospitals.add(hospitalOptional.getData());
            }
        }
        patient.setHospitals(hospitals);

        patient.setPhone(request.getPhone());
        patient.setBirthDate(request.getBirthDate());
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
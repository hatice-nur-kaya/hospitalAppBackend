package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.*;
import com.kodhnk.base.dto.appointments.CreateAppointmentRequest;
import com.kodhnk.base.dto.appointments.UpdateAppointmentRequest;
import com.kodhnk.base.entities.*;
import com.kodhnk.base.services.interfaces.IAppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository, PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DataResult<List<Appointment>> getAllAppointment() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return new SuccessDataResult<>(Response.GET_APPOINTMENT.getMessage(), appointments, 200);
    }

    @Override
    public DataResult<Appointment> getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            return new SuccessDataResult<>(Response.GET_APPOINTMENT.getMessage(), appointment.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.APPOINTMENT_NOT_FOUND.getMessage(), null, 400);
        }
    }

    @Override
    public DataResult<Appointment> createAppointment(CreateAppointmentRequest request) {
        Optional<Patient> patient = patientRepository.findById(request.getPatientId());
        if (!patient.isPresent()) {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 400);
        }
        Optional<Doctor> doctor = doctorRepository.findById(request.getDoctorId());
        if (!doctor.isPresent()) {
            return new ErrorDataResult<>(Response.DOCTOR_NOT_FOUND.getMessage(), null, 400);
        }
        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());
        if (!department.isPresent()) {
            return new ErrorDataResult<>(Response.DEPARTMENT_NOT_FOUND.getMessage(), null, 400);
        }
        Appointment appointment = new Appointment();
        appointment.setPatient(patient.get());
        appointment.setDoctor(doctor.get());
        appointment.setDepartment(department.get());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointmentRepository.save(appointment);
        return new SuccessDataResult<>(Response.CREATE_APPOINTMENT.getMessage(), appointment, 200);
    }

    @Override
    public Result updateAppointment(UpdateAppointmentRequest request) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(request.getAppointmentId());
        if (!existingAppointment.isPresent()) {
            return new ErrorResult(Response.APPOINTMENT_NOT_FOUND.getMessage(), 400);
        }
        Appointment appointment = existingAppointment.get();
        Optional<Patient> patient = patientRepository.findById(request.getPatientId());
        if (!patient.isPresent()) {
            return new ErrorResult("Patient not found", 400);
        }
        Optional<Doctor> doctor = doctorRepository.findById(request.getDoctorId());
        if (!doctor.isPresent()) {
            return new ErrorResult("Doctor not found", 400);
        }
        appointment.setPatient(patient.get());
        appointment.setDoctor(doctor.get());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
        return new SuccessResult(Response.APPOINTMENT_UPDATE.getMessage(), 200);
    }

    @Override
    public Result cancelAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            Appointment currentAppointment = appointment.get();
            currentAppointment.setStatus(AppointmentStatus.CANCELLED);
            appointmentRepository.save(currentAppointment);
            return new SuccessResult(Response.APPOINTMENT_DELETE.getMessage(), 200);
        }
        return new ErrorResult(Response.APPOINTMENT_NOT_FOUND.getMessage(), 400);
    }
}
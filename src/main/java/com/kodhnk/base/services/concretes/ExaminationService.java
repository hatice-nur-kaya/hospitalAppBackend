package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.DoctorRepository;
import com.kodhnk.base.dataAccess.ExaminationRepository;
import com.kodhnk.base.dataAccess.MedicineRepository;
import com.kodhnk.base.dataAccess.PatientRepository;
import com.kodhnk.base.dto.examinations.CreateExaminationRequest;
import com.kodhnk.base.dto.examinations.UpdateExaminationRequest;
import com.kodhnk.base.entities.Doctor;
import com.kodhnk.base.entities.Examination;
import com.kodhnk.base.entities.Medicine;
import com.kodhnk.base.entities.Patient;
import com.kodhnk.base.services.interfaces.IExaminationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationService implements IExaminationService {

    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicineRepository medicineRepository;

    public ExaminationService(ExaminationRepository examinationRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, MedicineRepository medicineRepository) {
        this.examinationRepository = examinationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public DataResult<List<Examination>> getAllExaminations(Long hospitalId) {
        List<Examination> examinations = examinationRepository.findAllByHospitalId(hospitalId);
        if (examinations.isEmpty()) {
            return new ErrorDataResult<>(Response.EXAMINATION_NOT_FOUND.getMessage(), null, 404);
        }
        return new SuccessDataResult<>(Response.GET_EXAMINATION.getMessage(), examinations, 200);
    }

    @Override
    public DataResult<Examination> getExaminationById(Long id) {
        Optional<Examination> examination = examinationRepository.findById(id);
        if (examination.isPresent()) {
            return new SuccessDataResult<>(Response.GET_EXAMINATION.getMessage(), examination.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.EXAMINATION_NOT_FOUND.getMessage(), null, 404);
        }
    }

    @Override
    public DataResult<Examination> createExamination(CreateExaminationRequest request) {
        Optional<Patient> patient = patientRepository.findById(request.getPatientId());
        Optional<Doctor> doctor = doctorRepository.findById(request.getDoctorId());
        if (!patient.isPresent() || !doctor.isPresent()) {
            return new ErrorDataResult<>("Patient or Doctor not found", null, 400);
        }

        List<Medicine> medicines = medicineRepository.findAllById(request.getMedicineIds());
        if (medicines.size() != request.getMedicineIds().size()) {
            return new ErrorDataResult<>("One or more medicines not found", null, 404);
        }

        Examination newExamination = new Examination();
        newExamination.setPatient(patient.get());
        newExamination.setDoctor(doctor.get());
        newExamination.setExaminationDate(request.getExaminationDate());
        newExamination.setDiagnosis(request.getDiagnosis());
        newExamination.setTreatment(request.getTreatment());
        newExamination.setNotes(request.getNotes());
        newExamination.setPrescribedMedicines(medicines);
        Examination savedExamination = examinationRepository.save(newExamination);
        return new SuccessDataResult<>(Response.CREATE_EXAMINATION.getMessage(), savedExamination, 201);
    }

    @Override
    public DataResult<Examination> updateExamination(UpdateExaminationRequest request) {
        Optional<Examination> existingExaminationOpt = examinationRepository.findById(request.getId());
        if (!existingExaminationOpt.isPresent()) {
            return new ErrorDataResult<>(Response.EXAMINATION_NOT_FOUND.getMessage(), null, 404);
        }

        Examination existingExamination = existingExaminationOpt.get();
        Optional<Patient> patientOpt = patientRepository.findById(request.getId());
        if (!patientOpt.isPresent()) {
            return new ErrorDataResult<>(Response.PATIENT_NOT_FOUND.getMessage(), null, 404);
        }

        Optional<Doctor> doctorOpt = doctorRepository.findById(request.getDoctorId());
        if (!doctorOpt.isPresent()) {
            return new ErrorDataResult<>(Response.DOCTOR_NOT_FOUND.getMessage(), null, 404);
        }

        existingExamination.setPatient(patientOpt.get());
        existingExamination.setDoctor(doctorOpt.get());
        existingExamination.setExaminationDate(request.getExaminationDate());
        existingExamination.setDiagnosis(request.getDiagnosis());
        existingExamination.setTreatment(request.getTreatment());
        existingExamination.setNotes(request.getNotes());
        examinationRepository.save(existingExamination);
        return new SuccessDataResult<>(Response.UPDATE_MEDICINE.getMessage(), existingExamination, 200);
    }

    @Override
    public Result deleteExamination(Long id) {
        Optional<Examination> examination = examinationRepository.findById(id);
        if (!examination.isPresent()) {
            return new ErrorResult(Response.EXAMINATION_NOT_FOUND.getMessage(), 404);
        }

        examinationRepository.delete(examination.get());
        return new SuccessResult(Response.EXAMINATION_DELETE.getMessage(), 200);
    }
}
package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.ErrorDataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.core.utilities.SuccessDataResult;
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
import org.springframework.security.core.userdetails.UserDetails;
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
        return null;
    }

    @Override
    public DataResult<Examination> getExaminationById(Long id) {
        Optional<Examination> examination = examinationRepository.findById(id);
        if (examination.isPresent()) {
            return new SuccessDataResult<>(Response.GET_EXAMINATION.getMessage(), examination.get(), 200);
        } else {
            return new ErrorDataResult<>(Response.EXAMINATION_NOT_FOUND.getMessage(), null, 200);
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
        return null;
    }

    @Override
    public Result deletedeleteExamination(Long id) {
        return null;
    }
}
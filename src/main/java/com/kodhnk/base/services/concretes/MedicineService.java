package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.*;
import com.kodhnk.base.dataAccess.MedicineRepository;
import com.kodhnk.base.dto.medicines.CreateMedicineRequest;
import com.kodhnk.base.dto.medicines.UpdateMedicineRequest;
import com.kodhnk.base.entities.Medicine;
import com.kodhnk.base.services.interfaces.IMedicineService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService implements IMedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public DataResult<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return new SuccessDataResult<>(Response.MEDICINE_LISTED.getMessage(), medicines, 200);
    }

    @Override
    public DataResult<Medicine> getMedicineById(Long id) {
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            return new SuccessDataResult<>(Response.MEDICINE_FOUND.getMessage(), medicine.get(), 200);
        }
        return new ErrorDataResult<>(Response.MEDICINE_NOT_FOUND.getMessage(), null, 400);
    }

    @Override
    public DataResult<Medicine> createMedicine(CreateMedicineRequest request) {
        Medicine medicine = new Medicine();
        BeanUtils.copyProperties(request, medicine);
        medicineRepository.save(medicine);
        return new SuccessDataResult<>(Response.MEDICINE_CREATED.getMessage(), medicine, 201);
    }

    @Override
    public DataResult<Medicine> updateMedicine(UpdateMedicineRequest request) {
        DataResult<Medicine> result = getMedicineById(request.getId());
        if (result.isSuccess()) {
            Medicine medicine = result.getData();
            BeanUtils.copyProperties(request, medicine);
            medicineRepository.save(medicine);
            return new SuccessDataResult<>(Response.MEDICINE_UPDATED.getMessage(), medicine, 200);
        }
        return new ErrorDataResult<>(Response.MEDICINE_NOT_FOUND.getMessage(), null, 400);
    }

    @Override
    public Result deleteMedicine(Long id) {
        DataResult<Medicine> medicineDataResult = getMedicineById(id);
        if (medicineDataResult.isSuccess()) {
            Medicine medicine = medicineDataResult.getData();
            medicineRepository.delete(medicine);
            return new SuccessResult(Response.MEDICINE_DELETED.getMessage(), 200);
        }
        return new ErrorResult(Response.MEDICINE_NOT_FOUND.getMessage(), 400);
    }
}
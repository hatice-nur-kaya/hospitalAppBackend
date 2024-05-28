package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.medicines.CreateMedicineRequest;
import com.kodhnk.base.dto.medicines.UpdateMedicineRequest;
import com.kodhnk.base.entities.Medicine;

import java.util.List;

public interface IMedicineService {
    DataResult<List<Medicine>> getAllMedicines();

    DataResult<Medicine> getMedicineById(Long id);

    DataResult<Medicine> createMedicine(CreateMedicineRequest request);

    DataResult<Medicine> updateMedicine(UpdateMedicineRequest request);

    Result deleteMedicine(Long id);
}
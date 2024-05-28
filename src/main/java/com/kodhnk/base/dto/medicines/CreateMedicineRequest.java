package com.kodhnk.base.dto.medicines;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMedicineRequest {
    private String medicineName;
    private String dosage;
    private String instructions;
}
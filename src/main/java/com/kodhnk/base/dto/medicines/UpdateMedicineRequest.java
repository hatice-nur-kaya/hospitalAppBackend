package com.kodhnk.base.dto.medicines;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMedicineRequest {
    private Long id;
    private String medicineName;
    private String dosage;
    private String instructions;
}
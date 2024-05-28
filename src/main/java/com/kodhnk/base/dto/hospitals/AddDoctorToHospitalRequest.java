package com.kodhnk.base.dto.hospitals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDoctorToHospitalRequest {
    private Long hospitalId;
    private Long doctorId;
}
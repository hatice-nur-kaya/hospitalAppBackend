package com.kodhnk.base.dto.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateHospitalUserRequest {
    private Long hospitalId;
    private String speciality;
    private Long departmentId;
}
package com.kodhnk.base.dto.doctors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String speciality;
    private Long hospitalId;
    private Long departmentId;
}
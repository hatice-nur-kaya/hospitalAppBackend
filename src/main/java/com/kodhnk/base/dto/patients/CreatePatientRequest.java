package com.kodhnk.base.dto.patients;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CreatePatientRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private LocalDate birthDate;
    private String password;
    private String phone;
}
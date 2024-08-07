package com.kodhnk.base.dto.patients;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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
    private String gender;
    private Set<Long> hospitalIds;
    private Set<Long> roleIds;
    private Set<Long> medicineIds;
}
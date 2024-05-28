package com.kodhnk.base.dto.users;

import com.kodhnk.base.dto.hospitals.HospitalResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private List<HospitalResponse> hospitals;
}
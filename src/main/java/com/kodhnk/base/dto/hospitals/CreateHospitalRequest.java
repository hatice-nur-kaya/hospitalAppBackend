package com.kodhnk.base.dto.hospitals;

import com.kodhnk.base.entities.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateHospitalRequest {
    private String name;
    private String city;
    private String district;
}

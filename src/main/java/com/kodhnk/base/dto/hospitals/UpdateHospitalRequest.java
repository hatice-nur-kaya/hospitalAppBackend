package com.kodhnk.base.dto.hospitals;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateHospitalRequest {
    private Long id;
    private String name;
    private List<Long> doctorId;
}
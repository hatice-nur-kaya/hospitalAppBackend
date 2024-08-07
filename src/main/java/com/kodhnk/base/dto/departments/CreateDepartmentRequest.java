package com.kodhnk.base.dto.departments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepartmentRequest {
    private String name;
    private Long hospitalId;
}
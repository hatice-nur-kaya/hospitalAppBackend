package com.kodhnk.base.dto.departments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentRequest {
    private Long id;
    private String name;
}
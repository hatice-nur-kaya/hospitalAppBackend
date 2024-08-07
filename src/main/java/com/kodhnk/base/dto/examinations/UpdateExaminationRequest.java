package com.kodhnk.base.dto.examinations;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateExaminationRequest {
    private Long id;
    private Long doctorId;
    private Date examinationDate;
    private String diagnosis;
    private String treatment;
    private String notes;
}
package com.kodhnk.base.dto.examinations;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateExaminationRequest {
    private Long patientId;
    private Long doctorId;
    private Date examinationDate;
    private String diagnosis;
    private String treatment;
    private String notes;
    private List<Long> medicineIds;
}
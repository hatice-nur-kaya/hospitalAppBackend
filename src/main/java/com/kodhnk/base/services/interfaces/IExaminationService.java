package com.kodhnk.base.services.interfaces;


import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.Result;
import com.kodhnk.base.dto.examinations.CreateExaminationRequest;
import com.kodhnk.base.dto.examinations.UpdateExaminationRequest;
import com.kodhnk.base.entities.Examination;

import java.util.List;

public interface IExaminationService {

    DataResult<List<Examination>> getAllExaminations(Long hospitalId);

    DataResult<Examination> getExaminationById(Long id);

    DataResult<Examination> createExamination(CreateExaminationRequest request);

    DataResult<Examination> updateExamination(UpdateExaminationRequest request);

    Result deleteExamination(Long id);
}
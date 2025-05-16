package com.example.studyapp.services.ai;

import com.example.studyapp.dtos.CourseExamDto;
import com.example.studyapp.dtos.examChoice.ExamChoiceDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MultipleChoiceService {

    ExamChoiceDto getMultipleChoice(Long id, CourseExamDto courseExamDto) throws JsonProcessingException;
}

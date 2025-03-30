package com.example.studyapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseExamDto {
    private String title;
    private String[] topics;
}

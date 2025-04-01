package com.example.studyapp.dtos;

import com.example.studyapp.validations.IsExistsCourse;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseDto {
    @NotBlank
    @IsExistsCourse
    private String title;
    private String contentBibliography;
}

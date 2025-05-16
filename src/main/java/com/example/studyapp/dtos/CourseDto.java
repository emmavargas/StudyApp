package com.example.studyapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseDto {
    @NotBlank
    private String title;
    private String contentBibliography;
}

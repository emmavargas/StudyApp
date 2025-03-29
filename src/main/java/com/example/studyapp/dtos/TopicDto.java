package com.example.studyapp.dtos;

import com.example.studyapp.validations.IsExistsTopic;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TopicDto {
    @IsExistsTopic
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    private String Bibliography;
}

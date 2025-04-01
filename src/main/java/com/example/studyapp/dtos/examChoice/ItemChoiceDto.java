package com.example.studyapp.dtos.examChoice;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ItemChoiceDto {
    private String question;
    private OptionsQuestionDto options;
    private String answer;

}

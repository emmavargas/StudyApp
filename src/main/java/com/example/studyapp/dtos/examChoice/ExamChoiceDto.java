package com.example.studyapp.dtos.examChoice;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ExamChoiceDto {
    private String course;
    private String[] topics;
    List<ItemChoiceDto> items;
}

package com.example.studyapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String password;
}

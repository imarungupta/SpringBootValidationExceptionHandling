package com.javatechie.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserRequest {
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String nationality;
    private int age;
}
// Note: This dto package and this class is created so that
// we can perform validation here instead of doing all things in entity class only
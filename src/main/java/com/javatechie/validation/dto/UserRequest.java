package com.javatechie.validation.dto;


import lombok.Data;

import javax.validation.constraints.*;

/*@NoArgsConstructor
@AllArgsConstructor(staticName = "build")*/
@Data
public class UserRequest {


    @NotNull(message = "Name cannot be empty")
    @Size(min = 2 , max = 10)
    private String name;
    @NotNull(message = "Email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "Mobile cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid Mobile Number")
    private String mobile;

    private String gender;
    @NotNull(message = "Nationality cannot be null")
    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;
    @NotNull(message = "Age cannot be empty")
    @Min(value = 18,message = "Age cannot below 18")
    @Max(value = 60,message = "Age Cannot more than 60")
    private int age;

    public UserRequest(String name, String email, String mobile, String gender, String nationality, int age) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.nationality = nationality;
        this.age = age;
    }


}
// Note: This dto package and this class is created so that
// we can perform validation here instead of doing all things in entity class only
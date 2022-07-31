package com.javatechie.validation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@AllArgsConstructor(staticName = "build") // Now it will act as a builder design pattern
//@NoArgsConstructor
@Data
@Table(name = "USERS_TBL")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String nationality;
    private int age;

    public User(int userId, String name, String email, String mobile, String gender, String nationality, int age) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.nationality = nationality;
        this.age = age;
    }

    public User() {
    }


}

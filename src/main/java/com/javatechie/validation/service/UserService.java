package com.javatechie.validation.service;

import com.javatechie.validation.dto.UserRequest;
import com.javatechie.validation.entity.User;

import java.util.List;

public interface UserService{
    public User saveUser(UserRequest userRequest);
    public List<User> getAllUsers();
    public User getUser(int id);
}

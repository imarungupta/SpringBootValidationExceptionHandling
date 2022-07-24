package com.javatechie.validation.controller;

import com.javatechie.validation.dto.UserRequest;
import com.javatechie.validation.entity.User;
import com.javatechie.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest userRequest){
        //User user = service.saveUser(userRequest);
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<User> getUser(@PathVariable int id){
        return ResponseEntity.ok(service.getUser(id));
    }
}

package com.javatechie.validation.controller;

import com.javatechie.validation.advice.UserNotFoundException;
import com.javatechie.validation.dto.UserRequest;
import com.javatechie.validation.entity.User;
import com.javatechie.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest){
        //User user = service.saveUser(userRequest);
        return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
    }
    @PostMapping("/signup1")
    public ResponseEntity<String> saveUser1(@RequestBody @Valid UserRequest userRequest){
        service.saveUser(userRequest);
        return new ResponseEntity<>("{\"message\":\"" +"New User is added" +"\"}", HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<User> getUser(@PathVariable int id) throws UserNotFoundException {

        return ResponseEntity.ok(service.getUser(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUsers(@RequestBody @Valid UserRequest userRequest, @PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok(service.updateUser(userRequest,id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException {
        service.deleteUser(id);
        return new ResponseEntity("{\"message\":\"" + "User deleted sucessfully" +"\"}", HttpStatus.OK);
    }
}

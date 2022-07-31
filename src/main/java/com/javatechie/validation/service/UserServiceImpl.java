package com.javatechie.validation.service;

import com.javatechie.validation.advice.UserNotFoundException;
import com.javatechie.validation.dto.UserRequest;
import com.javatechie.validation.entity.User;
import com.javatechie.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User saveUser(UserRequest userRequest) {
        User user = new User(0, userRequest.getName(), userRequest.getEmail(),
                userRequest.getMobile(), userRequest.getGender(), userRequest.getNationality(),
                userRequest.getAge());

        return repository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(int Id) throws UserNotFoundException {

        User userId= repository.findByUserId(Id);
        if(userId!=null && userId.getUserId()==Id){
            return userId;
        }else{
            throw new UserNotFoundException("user not found with Id:"+Id);
        }

    }
    @Override
    public User updateUser(UserRequest userRequest , int id) throws UserNotFoundException {
        System.out.println("  request user id "+ id);
        User dbUser= repository.findByUserId(id);

        if(dbUser!=null) {
            if (Objects.nonNull(userRequest.getName()) && userRequest.getName() != "") {
                dbUser.setName(userRequest.getName());
            }
            if (Objects.nonNull(userRequest.getAge())) {
                dbUser.setAge(userRequest.getAge());
            }
            if (userRequest.getEmail() != null && userRequest.getEmail() != "") {
                dbUser.setEmail(userRequest.getEmail());
            }
            if (userRequest.getGender() != null && userRequest.getGender() != "") {
                dbUser.setGender(userRequest.getGender());
            }
            if (userRequest.getMobile() != null && userRequest.getMobile() != "") {
                dbUser.setMobile(userRequest.getMobile());
            }
            if (userRequest.getNationality() != null && userRequest.getNationality() != "") {
                dbUser.setNationality(userRequest.getNationality());
            }
            return repository.save(dbUser);
        }else {
            throw new UserNotFoundException("user not found with Id:" + id);
        }

    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        try{
            User dbUser= repository.findByUserId(id);
            if(dbUser!=null){
                repository.deleteById(id);
            }else
                throw new UserNotFoundException("user not found with Id:"+id);
        }catch (Exception ex){
            ex.getMessage();
            throw new UserNotFoundException("user not found with Id:"+id);
        }
    }
}

package com.javatechie.validation;

import com.javatechie.validation.advice.UserNotFoundException;
import com.javatechie.validation.dto.UserRequest;
import com.javatechie.validation.entity.User;
import com.javatechie.validation.repository.UserRepository;
import com.javatechie.validation.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ValidationExampleApplicationTests {
    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    public void getAllUserTest() {
        Mockito.when(repository.findAll()).
                thenReturn(Arrays.asList(new User(1, "Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28),
                        new User(2, "Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28)));
        assertEquals(2, service.getAllUsers().size());

    }
    @Test
    public void saveUserTest() {
        UserRequest user = new UserRequest("Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28);
        User user1 = new User(0, user.getName(), user.getEmail(), user.getMobile(), user.getGender(), user.getNationality(), user.getAge());
        Mockito.when(repository.save(user1)).
                thenReturn(user1);
        assertEquals(user1, service.saveUser(user));
    }

    @Test
    public void getUser() throws UserNotFoundException {
        int id = 1;
        UserRequest user = new UserRequest("Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28);
        User user1 = new User(1, user.getName(), user.getEmail(), user.getMobile(), user.getGender(), user.getNationality(), user.getAge());
        Mockito.when(repository.findByUserId(id)).thenReturn(user1);
        assertEquals(user1, service.getUser(id));
    }
    // Note: In case of deleteUser we are not returing any thing so here we will jsut verify that
    // whethere the repositoyr is getting called or not for one times
    @Test
    public void deleteUser() throws UserNotFoundException {
        int id = 1;
        UserRequest user = new UserRequest("Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28);
        User user1 = new User(1, user.getName(), user.getEmail(), user.getMobile(), user.getGender(), user.getNationality(), user.getAge());
        User user2 = repository.findByUserId(id);
        if (user2 != null) {
            service.deleteUser(id);
            Mockito.verify(repository, Mockito.times(1)).deleteById(id);
        }
    }

    @Test
    public void updateUser() throws UserNotFoundException {
        int id = 1;
        UserRequest userRequest = new UserRequest("Arun", "arun@gmail.com", "1234567890", "M", "Indian", 28);
        User user1 = new User(1, userRequest.getName(), userRequest.getEmail(), userRequest.getMobile(), userRequest.getGender(), userRequest.getNationality(), userRequest.getAge());
        User dbUser = repository.findByUserId(id);
        if (dbUser != null) {
            dbUser.setNationality(userRequest.getNationality());
            dbUser.setEmail(userRequest.getEmail());
            dbUser.setNationality(userRequest.getNationality());
            dbUser.setGender(userRequest.getGender());
            dbUser.setAge(userRequest.getAge());
            dbUser.setName(userRequest.getName());

            Mockito.when(repository.save(dbUser)).thenReturn(dbUser);
            assertEquals(dbUser, service.updateUser(userRequest, id));
        }
    }

}








package com.faltdor.webservices.restfullwebservices.controllers;

import com.faltdor.webservices.restfullwebservices.domain.User;
import com.faltdor.webservices.restfullwebservices.exceptions.UserNotFoundException;
import com.faltdor.webservices.restfullwebservices.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User retrieveUser(@PathVariable int userId){
        return userService.findById(userId).map(user -> user).orElseThrow(()-> new UserNotFoundException("User Not Found : Id : "+ userId));
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable int userId){
        return userService.delete(userId).map(user -> user).orElseThrow(()-> new UserNotFoundException("User Not Found : Id : "+ userId));
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.save(user).map(user1 -> user1).get();
    }

}

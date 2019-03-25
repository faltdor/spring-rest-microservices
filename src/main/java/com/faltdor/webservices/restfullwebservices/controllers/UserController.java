package com.faltdor.webservices.restfullwebservices.controllers;

import com.faltdor.webservices.restfullwebservices.domain.User;
import com.faltdor.webservices.restfullwebservices.exceptions.UserNotFoundException;
import com.faltdor.webservices.restfullwebservices.services.UserService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public Resource<User> saveUser(@RequestBody User user){
        Resource<User> resource = new Resource<User>(userService.save(user).map(user1 -> user1).get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

}

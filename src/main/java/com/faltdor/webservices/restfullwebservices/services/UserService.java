package com.faltdor.webservices.restfullwebservices.services;

import com.faltdor.webservices.restfullwebservices.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(int userId);

    Optional<User> delete(int userId);

    Optional<User> save(User user);
}

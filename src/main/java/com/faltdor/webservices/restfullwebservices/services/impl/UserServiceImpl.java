package com.faltdor.webservices.restfullwebservices.services.impl;

import com.faltdor.webservices.restfullwebservices.domain.User;
import com.faltdor.webservices.restfullwebservices.services.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService{
    private static List<User> users =new ArrayList<>();

    private static AtomicInteger userId =  new AtomicInteger();

    static {
        users.add(new User(userId.getAndIncrement(), "Ada", new Date()));
        users.add(new User(userId.getAndIncrement(), "Eve", new Date()));
        users.add(new User(userId.getAndIncrement(), "Jack", new Date()));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).map(user -> user).findFirst();
    }

    @Override
    public Optional<User> delete(int userId) {
        return users.stream().filter(user -> user.getId() == userId)
                .map(user -> user)
                .findFirst()
                .map(user -> {
                    users.remove(user);
                    return  user;
                });
    }

    @Override
    public Optional<User> save(User user) {
        return Optional.ofNullable(Optional.ofNullable(user).filter(user1 -> user1.getId() != 0).map(user1 -> {
           users.add(user1);
           return user1;
        }).orElseGet(()->{
            user.setId(userId.getAndIncrement());
            users.add(user);
            return  user;
        }));
    }
}

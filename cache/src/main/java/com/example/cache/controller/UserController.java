package com.example.cache.controller;

import com.example.cache.entity.User;
import com.example.cache.repo.UserReactiveRepository;
import com.example.cache.repo.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserReactiveRepository userReactiveRepository;

    public UserController(UserRepository userRepository, UserReactiveRepository userReactiveRepository) {
        this.userRepository = userRepository;
        this.userReactiveRepository = userReactiveRepository;
    }

    @GetMapping("/get/{id}")
    @Cacheable(value = "product",key = "#id",unless = "#result == null")
    public User getById(@PathVariable Long id)
    {
        System.out.println("DB hit.");
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) { return user.get();}

        return null;
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return userReactiveRepository.findById(id);
    }

}

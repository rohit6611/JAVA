package com.example.reactive.controller;

import com.example.reactive.entity.User;
import com.example.reactive.repo.UserReactiveRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserReactiveRepository userReactiveRepository;

    private final Set<Long> seenUserIds = ConcurrentHashMap.newKeySet();

    public UserController( UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    @GetMapping(value = "/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return userReactiveRepository.findById(id);
    }

    @GetMapping(value="/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUser() {
        return userReactiveRepository.findAll()
                .delayElements(Duration.ofSeconds(1));

    }

}

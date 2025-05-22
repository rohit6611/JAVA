package com.my.my_app.service;


import com.my.my_app.dto.UserInput;
import com.my.my_app.model.Address;
import com.my.my_app.model.Post;
import com.my.my_app.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class UserResolver {

    private final Map<String, User> userStore = new ConcurrentHashMap<>();

    @QueryMapping
    public User getUserById(@Argument String id) {
        return userStore.get(id);
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }

    @MutationMapping
    public User createUser(@Argument UserInput userInput) {
        User user = toUser(userInput);
        userStore.put(user.getId(), user);
        return user;
    }

    @MutationMapping
    public User updateUser(@Argument String id, @Argument UserInput userInput) {
        User user = toUser(userInput);
        userStore.put(id, user);
        return user;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument String id) {
        return userStore.remove(id) != null;
    }

    private User toUser(UserInput input) {
        Address address = new Address(input.getAddress().getCity(), input.getAddress().getState());
        List<Post> posts = input.getPosts().stream()
                .map(p -> new Post(p.getTitle(), p.getContent()))
                .collect(Collectors.toList());
        return new User(input.getId(), input.getName(), input.getAge(), address, posts);
    }
}


package com.example.reactive.repo;

import com.example.reactive.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReactiveRepository extends ReactiveCrudRepository<User,Long> {
}

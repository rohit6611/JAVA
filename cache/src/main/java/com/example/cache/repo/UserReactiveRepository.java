package com.example.cache.repo;

import com.example.cache.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReactiveRepository extends ReactiveCrudRepository<User,Long> {
}

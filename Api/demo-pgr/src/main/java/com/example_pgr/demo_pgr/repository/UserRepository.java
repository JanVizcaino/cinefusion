package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

}

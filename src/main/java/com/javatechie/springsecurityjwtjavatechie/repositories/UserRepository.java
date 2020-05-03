package com.javatechie.springsecurityjwtjavatechie.repositories;

import com.javatechie.springsecurityjwtjavatechie.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
      User findByUserName(String username);
}

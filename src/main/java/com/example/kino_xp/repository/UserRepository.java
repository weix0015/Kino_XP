package com.example.kino_xp.repository;

import com.example.kino_xp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    String findAllByEmail(String email);
}

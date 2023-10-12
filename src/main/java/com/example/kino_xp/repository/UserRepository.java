package com.example.kino_xp.repository;

import com.example.kino_xp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAllByEmail(String email);

  List<User> findAllByName(String name);

  boolean existsByEmail(String email);
}

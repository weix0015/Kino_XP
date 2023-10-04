package com.example.kino_xp.repository;

import com.example.kino_xp.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmail(String email);



}

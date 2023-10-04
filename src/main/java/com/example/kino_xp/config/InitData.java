package com.example.kino_xp.config;

import com.example.kino_xp.entity.User;
import com.example.kino_xp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner
{
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User();
        u1.setName("Mikkel");
        u1.setEmail("MikkelsEmail@Email.com");
        u1.setAdmin(true);
        u1.setPassword("Herre sejt password");

        userRepository.save(u1);
    }
}

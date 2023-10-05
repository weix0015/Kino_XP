package com.example.kino_xp.config;

import com.example.kino_xp.model.User;
import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
import com.example.kino_xp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  MovieRepository movieRepository;

  @Override
  public void run(String... args) throws Exception {
    User u1 = new User();
    u1.setName("Mikkel");
    u1.setEmail("MikkelsEmail@Email.com");
    u1.setAdmin(true);
    u1.setPassword("$2a$12$u6UI8steCkpOVSVEpvO5UeAuK28jEIeOkBSpXjsTFbRYKb1JXsVlW"); //password i plain text

    userRepository.save(u1);

    Movie m1 = new Movie();
    m1.setTitle("Barbie");
    m1.setGenre(Genre.COMEDY);
    m1.setAge(18);
    m1.setShowLength(LocalTime.of(2, 30, 30));

    movieRepository.save(m1);
  }
}

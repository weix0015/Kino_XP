package com.example.kino_xp.service;

import com.example.kino_xp.dto.MovieDTO;
import com.example.kino_xp.exception.MovieNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

  @Autowired
  MovieService movieService;

  @Test
  void getAllTheMovie() {
    // Act
    List<MovieDTO> movies = movieService.getAllTheMovie();
    // Assert
    assertEquals(1, movies.size());
  }

  @Test
  void getMovieById() {
    // Act
    MovieDTO movieDTO = movieService.getMovieById(1);
    // Assert
    assertEquals("Barbie", movieDTO.title());
    assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(27));
  }

  @Test
  void createMovie() {
  }

  @Test
  void updateMovie() {
  }

  @Test
  void deleteMovie() {
  }

  @Test
  void getAllMoviesByTitle() {
  }
}
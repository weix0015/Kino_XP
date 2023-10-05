package com.example.kino_xp.service;

import com.example.kino_xp.converter.MovieConverter;
import com.example.kino_xp.dto.MovieDTO;
import com.example.kino_xp.exception.MovieNotFoundException;
import com.example.kino_xp.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieConverter movieConverter;

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
        // Arrange
        MovieDTO movieDTO = new MovieDTO(
                2,
                "The java",
                Genre.ACTION,
                LocalTime.of(3, 30, 30),
                22);
        // Act
        MovieDTO createdMovieDTO = movieService.createMovie(movieDTO);
        // assert
        assertNotNull(createdMovieDTO);
        assertNotEquals(0, createdMovieDTO.id());
        assertEquals(movieDTO.title(), createdMovieDTO.title());
        assertEquals(movieDTO.genre(), createdMovieDTO.genre());
        assertEquals(movieDTO.showLength(), createdMovieDTO.showLength());
        assertEquals(movieDTO.age(), createdMovieDTO.age());


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
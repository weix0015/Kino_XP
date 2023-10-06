package com.example.kino_xp.service;

import com.example.kino_xp.converter.MovieConverter;
import com.example.kino_xp.dto.MovieDTO;
import com.example.kino_xp.exception.MovieNotFoundException;
import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
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

    @Autowired
    MovieRepository movieRepository;

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
                22
        );
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
        // Arrange
        MovieDTO movieDTO = new MovieDTO(
          1,
          "World",
          Genre.ADVENTURE,
          LocalTime.of(1, 30, 30),
          18
        );
        // Act
        MovieDTO updatedMovieDTO = movieService.updateMovie(1, movieDTO);

        // Assert
        assertNotNull(updatedMovieDTO);
        assertEquals("World", updatedMovieDTO.title());
        assertEquals(Genre.ADVENTURE, updatedMovieDTO.genre());
        assertEquals(LocalTime.of(1, 30, 30), updatedMovieDTO.showLength());
        assertEquals(18, updatedMovieDTO.age());
    }

    @Test
    void deleteMovie() {
        // Arrange
        Movie movieToDelete = new Movie();
        movieToDelete.setTitle("Barbie");
        movieToDelete.setGenre(Genre.COMEDY);
        movieToDelete.setShowLength(LocalTime.of(2, 30, 30));
        movieToDelete.setAge(18);
        movieRepository.save(movieToDelete);

        // Act
        movieService.deleteMovie(movieToDelete.getId());

        // Assert
        assertFalse(movieRepository.existsById(movieToDelete.getId()));
    }

    @Test
    void getAllMoviesByTitle() {
        // Arrange
        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre(Genre.ACTION);
        movie1.setShowLength(LocalTime.of(2, 0, 0));
        movie1.setAge(18);
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Hero");
        movie2.setGenre(Genre.ACTION);
        movie2.setShowLength(LocalTime.of(2, 10, 5));
        movie2.setAge(18);
        movieRepository.save(movie2);

        // Act
        List<MovieDTO> movies1 = movieService.getAllMoviesByTitle("Batman");
        List<MovieDTO> movies2 = movieService.getAllMoviesByTitle("Hero");

        // Assert
        assertEquals(1, movies1.size());
        assertEquals(1, movies2.size());
        assertEquals("Batman", movies1.get(0).title());
        assertEquals("Hero", movies2.get(0).title());
    }
}
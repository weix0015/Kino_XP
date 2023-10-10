package com.example.kino_xp.restController;

import com.example.kino_xp.dto.movie.MovieRequest;
import com.example.kino_xp.dto.movie.MovieResponse;
import com.example.kino_xp.repository.MovieRepository;
import com.example.kino_xp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MovieController {

  @Autowired
  MovieService movieService;

  // list of all movies
  @GetMapping("/movies")
  public ResponseEntity<List<MovieResponse>> getAllMovie() {
    List<MovieResponse> movies = movieService.getAllMovies();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  // create a movie
  @PostMapping("/movies")
  public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest movieRequest) {
    MovieResponse creatMovie = movieService.createMovie(movieRequest);
    return new ResponseEntity<>(creatMovie, HttpStatus.CREATED);
  }

  // get movie by id
  @GetMapping("/movie/{id}")
  public ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") Long id) {
    MovieResponse movieResponse = movieService.getMovieById(id);
    return ResponseEntity.ok(movieResponse);
  }

  // test fail
  // update movies
  @PutMapping("/movies/{id}")
  public ResponseEntity<MovieResponse> updateMovie(@PathVariable("id") Long id, MovieRequest movieRequest) {
    MovieResponse updateMovie = movieService.updateMovie(id, movieRequest);
    return ResponseEntity.ok(updateMovie);
  }

  // delete movies
  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
    movieService.deleteMovie(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }

  // get movie by title
  @GetMapping("/movies/{title}")
  public ResponseEntity<List<MovieResponse>> getAllMoviesByTitle(@PathVariable String title) {
    List<MovieResponse> movies = movieService.getAllMoviesByTitle(title);
    return ResponseEntity.ok(movies);
  }

}

package com.example.kino_xp.restController;

import com.example.kino_xp.dto.movie.MovieRequest;
import com.example.kino_xp.dto.movie.MovieResponse;
import com.example.kino_xp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MovieRestController {

  @Autowired
  MovieService movieService;

  // list of all movies
  @GetMapping("/movies")
  public ResponseEntity<List<MovieResponse>> getAllMovie() {
    List<MovieResponse> movies = movieService.getAllMovies();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  // create a movie
  @PostMapping("/movie")
  public ResponseEntity<String> postMovie(@RequestBody MovieRequest movieRequest) {
    MovieResponse creatMovie = movieService.createMovie(movieRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("Movie created successfully");
  }

  // get movie by id
  @GetMapping("/movie/{title}")
  public ResponseEntity<MovieResponse> getMovieById(@PathVariable("title") String title) {
    MovieResponse movieResponse = movieService.getMovieById(title);
    return ResponseEntity.ok(movieResponse);
  }

  // test fail
  // update movies
  @PutMapping("/movie/{title}")
  public ResponseEntity<MovieResponse> updateMovie(@PathVariable("title") String title, MovieRequest movieRequest) {
    MovieResponse updateMovie = movieService.updateMovie(title, movieRequest);
    return ResponseEntity.ok(updateMovie);
  }

  // delete movies
  @DeleteMapping("/movie/{title}")
  public ResponseEntity<String> deleteMovie(@PathVariable("title") String title) {
    movieService.deleteMovie(title);
    return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");

  }

}

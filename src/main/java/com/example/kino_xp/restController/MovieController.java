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
  @PostMapping("/movie")
  public ResponseEntity<MovieResponse> postMovie(@RequestBody MovieRequest movieRequest) {
    MovieResponse creatMovie = movieService.createMovie(movieRequest);
    return new ResponseEntity<>(creatMovie, HttpStatus.CREATED);
  }

  // get movie by id
  @GetMapping("/movie/id/{id}")
  public ResponseEntity<MovieResponse> getMovieById(@PathVariable("id") String title) {
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
  public ResponseEntity<Void> deleteMovie(@PathVariable("title") String title) {
    movieService.deleteMovie(title);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }

}

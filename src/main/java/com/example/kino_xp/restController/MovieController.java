package com.example.kino_xp.restController;

import com.example.kino_xp.dto.MovieDTO;
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
  public ResponseEntity<List<MovieDTO>> getAllMovie() {
    List<MovieDTO> movies = movieService.getAllTheMovie();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  // create a movie
  @PostMapping("/movies")
  public ResponseEntity<MovieDTO> postMovie(@RequestBody MovieDTO movieDTO) {
    MovieDTO creatMovie = movieService.createMovie(movieDTO);
    return new ResponseEntity<>(creatMovie, HttpStatus.CREATED);
  }

  // get movie by id
  @GetMapping("/movie/{id}")
  public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") int id) {
    MovieDTO movieDTO = movieService.getMovieById(id);
    return ResponseEntity.ok(movieDTO);
  }

  // test fail
  // update movies
  @PutMapping("/movies/{id}")
  public ResponseEntity<MovieDTO> updateMovie(@PathVariable("id") int id, MovieDTO movieDTO) {
    MovieDTO updateMovie = movieService.updateMovie(id, movieDTO);
    return ResponseEntity.ok(updateMovie);
  }

  // delete movies
  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable("id") int id) {
    movieService.deleteMovie(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }

  // get movie by title
  @GetMapping("/movies/{title}")
  public ResponseEntity<List<MovieDTO>> getAllMoviesByTitle(@PathVariable String title) {
    List<MovieDTO> movies = movieService.getAllMoviesByTitle(title);
    return ResponseEntity.ok(movies);
  }

}

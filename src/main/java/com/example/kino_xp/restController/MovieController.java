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

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getAllMovie() {
        List<MovieDTO> movies = movieService.getAllTheMovie();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDTO>postMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO creatMovie = movieService.createMovie(movieDTO);
        return new ResponseEntity<>(creatMovie,HttpStatus.CREATED);
    }
}

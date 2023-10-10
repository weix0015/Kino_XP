package com.example.kino_xp.service;

import com.example.kino_xp.converter.MovieConverter;
import com.example.kino_xp.dto.movie.MovieRequest;
import com.example.kino_xp.dto.movie.MovieResponse;
import com.example.kino_xp.exception.MovieNotFoundException;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;

    // get all movies
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    // find movie by id
    public MovieResponse getMovieById(Long id) {
        Optional<Movie> foundMovie = movieRepository.findById(id);
        if (foundMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie with given id: " + id + " cannot be found");
        } else {
            return new MovieResponse(foundMovie.get());
        }
    }

    // create the movie
    public MovieResponse createMovie(MovieRequest movieRequest) {
        if (movieRepository.existsByTitle(movieRequest.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with title: "
                    + movieRequest.getTitle()
                    + " already exists");
        } else {
            Movie newMovie = movieRequest.getMovieEntity(movieRequest);
            movieRepository.save(newMovie);
            return new MovieResponse(newMovie);
        }
    }

    // update the movie.

    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) {
        Optional<Movie> optionalMovieToEdit = movieRepository.findById(id);
        Movie movieToEdit = optionalMovieToEdit.get();
        if (optionalMovieToEdit.isEmpty()) {
            throw new MovieNotFoundException("Movie with id: " +
                    id +
                    " could not be found");
        } else {
            movieRequest.copyTo(movieToEdit);
            return new MovieResponse(movieRepository.save(movieToEdit));
        }
    }
    // The delete method.

    public void deleteMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
        } else {
            throw new MovieNotFoundException("The movie is not found with id: " + id);
        }
    }

    // get the all movies by title
    public List<MovieResponse> getAllMoviesByTitle(String title) {
        List<Movie> foundMovies = movieRepository.findAllByTitle(title);
        if (foundMovies.isEmpty()) {
            throw new MovieNotFoundException("No movies with given title: "
                    + title
                    + " could be found");
        } else {
            return foundMovies.stream()
                    .map(MovieResponse::new)
                    .collect(Collectors.toList());
        }
    }

}

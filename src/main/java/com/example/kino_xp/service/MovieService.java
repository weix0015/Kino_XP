package com.example.kino_xp.service;

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
    public MovieResponse getMovieById(String title) {
        Optional<Movie> foundMovie = movieRepository.findById(title);
        if (foundMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie with given title: " + title + " cannot be found");
        } else {
            return new MovieResponse(foundMovie.get());
        }
    }

    // create the movie
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movieToSave = new Movie();
        if (movieRepository.existsByTitle(movieRequest.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with title: "
                    + movieRequest.getTitle()
                    + " already exists");
        } else {
            movieRequest.copyTo(movieToSave);
            movieRepository.save(movieToSave);
            return new MovieResponse(movieToSave);
        }
    }

    // update the movie.

    public MovieResponse updateMovie(String title, MovieRequest movieRequest) {
        Optional<Movie> optionalMovieToEdit = movieRepository.findById(title);
        Movie movieToEdit = optionalMovieToEdit.get();
        if (optionalMovieToEdit.isEmpty()) {
            throw new MovieNotFoundException("Movie with id: " +
                    title +
                    " could not be found");
        } else {
            movieRequest.copyTo(movieToEdit);
            return new MovieResponse(movieRepository.save(movieToEdit));
        }
    }
    // The delete method.

    public void deleteMovie(String title) {
        Optional<Movie> movie = movieRepository.findById(title);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
        } else {
            throw new MovieNotFoundException("The movie is not found with id: " + title);
        }
    }
}

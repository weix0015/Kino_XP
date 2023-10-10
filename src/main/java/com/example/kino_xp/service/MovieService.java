package com.example.kino_xp.service;

import com.example.kino_xp.converter.MovieConverter;
import com.example.kino_xp.exception.MovieNotFoundException;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {


  @Autowired
  MovieRepository movieRepository;
  @Autowired
  MovieConverter movieConverter;

  // get all movies
  public List<MovieDTO> getAllTheMovie() {
    List<Movie> movies = movieRepository.findAll();
    return movies.stream()
      .map(movieConverter::movieToDTO)
      .collect(Collectors.toList());

  }

  // find movie by id
  public MovieDTO getMovieById(int id) {
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    if (optionalMovie.isPresent()) {
      return movieConverter.movieToDTO(optionalMovie.get());
    } else {
      throw new MovieNotFoundException("Movie not found" + id);
    }
  }

  // create the movie
  public MovieDTO createMovie(MovieDTO movieDTO) {
    Movie movieToSave = movieConverter.movieDTOToEntity(movieDTO);
    movieToSave.setId(0);
    Movie movie = movieRepository.save(movieToSave);
    return movieConverter.movieToDTO(movie);
  }

  // update the movie.

  public MovieDTO updateMovie(int id, MovieDTO movieDTO) {
    Optional<Movie> existingMovie = movieRepository.findById(id);
    if (existingMovie.isPresent()) {
      Movie movieToUpdate = movieConverter.movieDTOToEntity(movieDTO);
      movieToUpdate.setId(id);
      Movie savedMovie = movieRepository.save(movieToUpdate);
      return movieConverter.movieToDTO(savedMovie);
    } else {
      throw new MovieNotFoundException("Movie is not found with id: " + id);
    }
  }
  // The delete method.

  public void deleteMovie(int id) {
    Optional<Movie> movie = movieRepository.findById(id);
    if (movie.isPresent()) {
      movieRepository.delete(movie.get());
    } else {
      throw new MovieNotFoundException("The movie is not found with id: " + id);
    }
  }

  // get the all movies by title
  public List<MovieDTO> getAllMoviesByTitle(String title) {
    List<Movie> movies = movieRepository.findAllByTitle(title);
    return movies.stream()
      .map(movieConverter::movieToDTO)
      .collect(Collectors.toList());
  }

}

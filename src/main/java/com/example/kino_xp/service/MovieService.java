package com.example.kino_xp.service;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.exception.MovieNotFoundException;
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

  public List<MovieDTO> getAllTheMovie() {
    List<Movie> movies = movieRepository.findAll();
    return movies.stream()
      .map(movieConverter::movieToDTO)
      .collect(Collectors.toList());

  }

  public MovieDTO getMovieById(int id) {
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    if (optionalMovie.isPresent()) {
      return movieConverter.movieToDTO(optionalMovie.get());
    } else {
      throw new MovieNotFoundException("Movie not found" + id);
    }
  }

  public MovieDTO createMovie(MovieDTO movieDTO) {
    Movie movieToSave = movieConverter.movieToEntity(movieDTO);
    movieToSave.setId(0);
    Movie movie = movieRepository.save(movieToSave);
    return movieConverter.movieToDTO(movie);
  }
}

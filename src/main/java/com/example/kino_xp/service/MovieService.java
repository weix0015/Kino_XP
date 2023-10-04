package com.example.kino_xp.service;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {


  @Autowired
  MovieRepository movieRepository;
  @Autowired
  MovieConverter movieConverter;

public List<MovieDTO>getAllTheMovie() {
List<Movie>movies=movieRepository.findAll();
  return movies.stream()
          .map(movieConverter::toDTO)
          .collect(Collectors.toList());

}
// make a method to fix the repository.

}

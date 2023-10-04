package com.example.kino_xp.service;

import com.example.kino_xp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  MovieRepository movieRepository;


}

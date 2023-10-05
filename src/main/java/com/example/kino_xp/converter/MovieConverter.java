package com.example.kino_xp.converter;

import com.example.kino_xp.dto.MovieDTO;
import com.example.kino_xp.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {
  public Movie movieToEntity(MovieDTO movieDTO) {
    return new Movie(
      movieDTO.id(),
      movieDTO.title(),
      movieDTO.genre(),
      movieDTO.showLength(),
      movieDTO.age()
    );
  }

  public MovieDTO movieToDTO(Movie movie) {
    return new MovieDTO(
      movie.getId(),
      movie.getTitle(),
      movie.getGenre(),
      movie.getShowLength(),
      movie.getAge()
    );
  }
}

package com.example.converter;

import com.example.dto.MovieDTO;
import com.example.kino_xp.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {
  public Movie toEntity(MovieDTO movieDTO) {
   return new Movie(
           movieDTO.id(),
           movieDTO.title(),
           movieDTO.genre(),
           movieDTO.showLength(),
           movieDTO.age()
   );
  }
  public MovieDTO toDTO(Movie movie) {
    return new MovieDTO(
        movie.getId(),
        movie.getTitle(),
        movie.getGenre(),
        movie.getShowLength(),
        movie.getAge()
    );
  }
}

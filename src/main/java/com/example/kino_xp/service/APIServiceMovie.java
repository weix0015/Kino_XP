package com.example.kino_xp.service;

import com.example.kino_xp.model.Movie;
import com.example.kino_xp.repository.MovieRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class APIServiceMovie {
private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

    public APIServiceMovie(RestTemplate restTemplate, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }


    private void saveMovies(List<Movie> movieList){
        for (Movie movie:movieList) {
            movieRepository.save(movie);
        }
        //lambda-udtryk
        movieList.forEach(movie -> movieRepository.save(movie));
    }

    public List<Movie> getMoviePosters() {

        // f43152bdba138068b6a45577fd2cb356

        String movieUrl = "https://api.themoviedb.org/3/authentication";

        ResponseEntity<List<Movie>> listResponseEntity =
                restTemplate.exchange(movieUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Movie>>() {
                        });
        List<Movie> movieList = listResponseEntity.getBody();
        saveMovies(movieList);
        return movieList;
    }
}

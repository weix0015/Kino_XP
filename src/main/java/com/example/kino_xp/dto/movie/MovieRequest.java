package com.example.kino_xp.dto.movie;

import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private Genre genre;

    private String posterUrl;
    private LocalTime showLength;
    private int age;
    private List<Long> viewing_ids;

    public Movie getMovieEntity(MovieRequest m){
        return Movie.builder().title(m.title)
                .genre(m.genre)
                .showLength(m.showLength)
                .posterUrl(m.posterUrl)
                .age(m.age)
                .build();
    }


    public void copyTo(Movie movie){
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setShowLength(showLength);
        movie.setAge(age);
        movie.setPosterUrl(posterUrl);
    }

    public MovieRequest(Movie m){
        this.title = m.getTitle();
        this.genre = m.getGenre();
        this.showLength = m.getShowLength();
        this.age = m.getAge();
        this.posterUrl = m.getPosterUrl();
    }
}

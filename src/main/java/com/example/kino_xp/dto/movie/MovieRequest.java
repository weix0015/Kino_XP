package com.example.kino_xp.dto.movie;

import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private Genre genre;
    private LocalTime showLength;
    private int age;

    public void copyTo(Movie movie){
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setShowLength(showLength);
        movie.setAge(age);
    }

    public MovieRequest(Movie m){
        this.title = m.getTitle();
        this.genre = m.getGenre();
        this.showLength = m.getShowLength();
        this.age = m.getAge();
    }
}

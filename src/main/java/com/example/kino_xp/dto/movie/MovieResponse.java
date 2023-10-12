package com.example.kino_xp.dto.movie;


import com.example.kino_xp.model.Genre;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.model.Viewing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponse {
    private String title;
    private Genre genre;

    private String posterUrl;
    private LocalTime showLength;
    private int age;
    private List<Long> viewing_ids;

    public MovieResponse(Movie m){
        this.title = m.getTitle();
        this.genre = m.getGenre();
        this.showLength = m.getShowLength();
        this.age = m.getAge();
        this.posterUrl = m.getPosterUrl();
        this.viewing_ids = m.getViewing().stream()
                .map(Viewing::getId)
                .toList();
    }
}

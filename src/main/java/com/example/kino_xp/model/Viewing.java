package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime showTime;
    private int hall;
    private LocalDateTime showEndTime;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ticket-id")
    @JsonManagedReference("viewingReference")
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "movie_id")
    @JsonManagedReference("viewingReference")
    private Movie movie;


}



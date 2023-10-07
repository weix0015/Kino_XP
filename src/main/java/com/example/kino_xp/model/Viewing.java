package com.example.kino_xp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @OneToOne
    @JoinColumn(name = "ticket-id")
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


}


